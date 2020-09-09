package ovcApi;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * ScheduleExecutorService kicks off run method every 1 minute. Last status is pulled from ovc-status.txt.
 * Current status is then pulled from an OVC API call to the OVC_API_IMPORT_STATUS_URL, which determines the
 * current status of the devAll importer job (the last step for OVC deployments). If status is FINISHED and
 * last result is SUCCESS, this means the importer job was successful. Then, if current status != last status
 * (ie, lastStatus stored in ovc-status.txt), this means that since the last API call an OVC importer job was
 * executed, indicating that a deployment was indeed completed, at which point a Jenkins run is kicked off via
 * an API call if the current status is FINISHED with a SUCCESS result. Note that OVC_ENVIRONMENT needs to be
 * passed in correct case: test, dev, or UAT
 *
 * COMPILING:   mvn compile
 * SETUP:       Set environmental variables OVC_USER, OVC_PSWD, JENKINS_USER, and JENKINS_PASS
 * RUNNING:     mvn exec:java -Dexec.mainClass="ovcApi.OvcApi" -Dexec.args="JENKINS_JOB_NAME OVC_ENVIRONMENT"
 *
 * @author Aaron Briel
 * @version 1.0
 */
public abstract class OvcApi extends TimerTask {

    private static Logger LOGGER = Logger.getLogger(OvcApi.class.getName());
    private static final String OVC_USER = System.getenv("OVC_USER");
    private static final String OVC_PSWD = System.getenv("OVC_PSWD");
    private static final String OVC_SERVER = "https://test-pos.ovcdtc.com/";
    private static final String JENKINS_USER = System.getenv("JENKINS_USER");
    private static final String JENKINS_PASS = System.getenv("JENKINS_PASS");
    private static final String STATUS_FILE = System.getProperty("user.dir") + "/src/main/java/ovcApi/ovc-status.txt";
    private static final String FINISHED = "FINISHED";
    private static final String SUCCESS = "SUCCESS";
    private static final int INTERVAL = 1;
    private static final String CURRENT_STATUS = "currentStatus";
    private static final String LAST_RESULT = "lastResult";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String JENKINS_BASE_URL = "https://jenkinsld02.trtc.com/job/";

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                LOGGER.info("Running OvcApi scheduler...");
                String currentStatus, result, jenkinsTriggerJobUrl, ovcApiImportStatusUrl;
                String lastStatus = readFile(STATUS_FILE);
                String jobName = "";
                String ovcEnvironment = "";

                if (args.length != 2) {
                    LOGGER.info("FAIL: Please include the Jenkins job name and OVC environment as command-line " +
                            "parameters. For example: -Dexec.args=\"OVC_SMOKE_QA test\"");
                    System.exit(1);
                } else {
                    jobName = args[0];
                    ovcEnvironment = args[1];
                }

                ovcApiImportStatusUrl = "https://" + ovcEnvironment +
                        "-pos.ovcdtc.com:443/json/jobScheduler/jobs/?pageSize=10&pageNum=0&sort=DESC&job_name=devAll";

                jenkinsTriggerJobUrl = JENKINS_BASE_URL + jobName + "/" + "buildWithParameters";

                try {
                    LOGGER.info("Attempting OVC API call...");
                    String ovcResponse = getResponse(OVC_USER, OVC_PSWD, ovcApiImportStatusUrl, GET, false);

                    // OVC API Json response is in array form, so first element must be extracted
                    JSONArray ovcResponseJsonArray = new JSONArray(ovcResponse);
                    JSONObject ovcResponseJson = ovcResponseJsonArray.getJSONObject(0);
                    currentStatus = ovcResponseJson.getString(CURRENT_STATUS);
                    result = ovcResponseJson.getString(LAST_RESULT);

                    LOGGER.info("Current status of OVC devAll job: " + currentStatus);

                    // Cancel current Jenkins job with passed in JOB_NAME if another devAll import has started
                    if (!currentStatus.equalsIgnoreCase(lastStatus) && !currentStatus.equalsIgnoreCase(FINISHED)) {
                        stopJenkinsRun(jobName);
                    }

                    // Successful OVC deployment status that has changed within the last 30 minutes
                    if (currentStatus.equalsIgnoreCase(FINISHED) &&
                        result.equalsIgnoreCase(SUCCESS) &&
                        !currentStatus.equalsIgnoreCase(lastStatus)) {
                        LOGGER.info("Attempting Jenkins API call...");
                        String response = getResponse(JENKINS_USER, JENKINS_PASS, jenkinsTriggerJobUrl, POST, true);
                        if (Integer.parseInt(response) == 201) {
                            LOGGER.info("Jenkins call to trigger job successful!");
                        }
                        writeToFile(STATUS_FILE, FINISHED);
                    } else {
                        // Writing current, non-FINISHED status to status file
                        writeToFile(STATUS_FILE, currentStatus);
                        LOGGER.info("Skipping Jenkins call.");
                    }

                } catch (Exception e) {
                    LOGGER.info("Catching exception in logic of main method: " + e);
                }
            }
        };

        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, INTERVAL, TimeUnit.MINUTES);
    }


    /**
     * Stops currently running Jenkins build with specified job name
     *
     * @param job  The name of the job to stop
     */
    private static void stopJenkinsRun(String job) throws Exception {
        LOGGER.info("Stopping Jenkins job \"" + job + "\" if it is running...");
        String jenkinsLastBuildNumberUrl= JENKINS_BASE_URL + job + "/lastBuild/buildNumber/";
        String build = getResponse(JENKINS_USER, JENKINS_PASS, jenkinsLastBuildNumberUrl, GET, false);
        String jenkinsStopBuildUrl = JENKINS_BASE_URL + job + "/" + build + "/stop/";
        String response = getResponse(JENKINS_USER, JENKINS_PASS, jenkinsStopBuildUrl, POST, true);
    }


    /**
     * Reads a file and returns as a string
     *
     * @param file  The file to read from
     * @return String  The text read from the file
     */
    private static String readFile(String file) {
        String line = null;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            LOGGER.info("Unable to open file '" + file + "': " + ex);
        } catch (IOException ex) {
            LOGGER.info("Error reading file '" + file + "': " + ex);
        }

        return line;
    }

    /**
     * Writes string to file, overwriting existing contents
     *
     * @param file  The file to write to
     * @param text  The text to write
     */
    private static void writeToFile(String file, String text) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.close();
        }catch (IOException ex) {
            LOGGER.info("Error writing to file '" + file + "'");
        }
    }

    /**
     * Returns response string from request to specified url with basic authentication, in case
     * of POST request it returns the response code as a string
     *
     * @param username  The username for basic authentication
     * @param password  The password for basic authentication
     * @param url       The URL to complete the request with
     * @param requestMethod GET or POST
     * @return String   The string of the response
     */
    private static String getResponse(String username, String password, String url, String requestMethod,
                                      boolean getResponseCode) throws Exception {
        String response = null;
        int responseCode = 0;
        HttpsURLConnection connection = null;

        //Setting Cookies to prevent redirect loop
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        // Creating a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

        // Installing trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            LOGGER.info("FAIL: Installation of trust manager failed with error: " + e);
        }

        try {
            connection = (HttpsURLConnection) new URL(url).openConnection();
            String encoded = Base64.getEncoder().encodeToString((username + ":" + password)
                    .getBytes(StandardCharsets.UTF_8));
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Authorization", "Basic " + encoded);

            if (getResponseCode) {
                responseCode = connection.getResponseCode();
                LOGGER.info("Response was: " + responseCode);
                response = Integer.toString(responseCode);
            } else {
                response = readInputStreamToString(connection);
            }

        } catch (Exception e) {
            LOGGER.info("Catching exception in the request for url (" + url + "): " + e);
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

        return response;
    }

    /**
     * Converts input stream from HttpConnection to a string
     *
     * @param connection    The HttpURLConnection to read
     * @return String       The string of the input stream
     */
    private static String readInputStreamToString(HttpURLConnection connection) {
        String result;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try {
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        }
        catch (Exception e) {
            LOGGER.info("FAIL: Error reading InputStream: " + e);
            result = null;
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (IOException e) {
                    LOGGER.info("FAIL: Error closing InputStream: " + e);
                }
            }
        }

        return result;
    }
}