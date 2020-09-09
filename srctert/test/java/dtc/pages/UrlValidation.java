package dtc.pages;

import common.Config;
import common.Constants;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import utilities.Driver;

import java.io.IOException;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utilities.HttpUtils;

/**
 * Created by aaronbriel on 12/21/16.
 * <p>
 * Creates a local UrlElement class to pull 'significant' elements to validate against pages
 */
public class UrlValidation {

    private final Logger LOGGER = Logger.getLogger(HomePage.class.getName());
    private static final String BREAD_CRUMB = "breadcrumb-items";
    HttpUtils httpUtils = new HttpUtils();

    public UrlValidation(Driver driver) {
    }

    /**
     * Asserts that an element with specific text is visible a page with the path
     *
     * @param  path          The path of the page to validate the element
     * @param  elementText   The text of the element to validate
     * @throws IOException General exception caught to allow for graceful failure
     */
    public void assertElementWithTextExists(String path, String elementText) throws IOException {
        LOGGER.info("assertElementExists started");
        String url = Config.getBaseUrl() + path;

        if (path.contains(Constants.HTTP))
            url = path;

        Document doc = Jsoup.connect(url).timeout(Constants.DEFAULT_MILLISEC_WAIT)
                .validateTLSCertificates(false).get();

        Assert.assertTrue("FAIL: Element with text '" + elementText + "' does not exist on page (" + url + ").",
                doc.getElementsContainingText(elementText).size() > 0);
        LOGGER.info("Verified element with text '" + elementText + "' exists on page (" + url + ").");
        LOGGER.info("assertElementExists completed");
    }

    /**
     * Asserts that comma separated list of breadcrumb css elements exist
     *
     * @param path              Path of page to verify breadcrumbs on
     * @param breadCrumbList    Comma separated string of breadcrumb links
     * @throws IOException General exception caught to allow for graceful failure
     */
    public void assertBreadCrumbsExist(String path, String breadCrumbList) throws IOException {
        LOGGER.info("assertBreadCrumbsExist started");

        String url = Config.getBaseUrl() + path;

        Document doc = Jsoup.connect(url).timeout(Constants.DEFAULT_MILLISEC_WAIT)
                .validateTLSCertificates(false).get();

        int count = 0;
        String breadCrumbLinks[] = breadCrumbList.split(",");
        Elements breadCrumbs = doc.getElementsByClass(BREAD_CRUMB);

        for (int i = 0; i < breadCrumbLinks.length; i++) {
            String breadCrumbText = breadCrumbLinks[count].trim();
            Element breadCrumb = breadCrumbs.get(count);
            Assert.assertTrue("FAIL: Bread crumb '" + breadCrumbText + "' does not exist on page (" + url + ").",
                    breadCrumb.text().contains(breadCrumbText));
            LOGGER.info("Verified bread crumb '" + breadCrumbText + "' exists on page (" + url + ").");
            count++;
        }
        LOGGER.info("assertBreadCrumbsExist started");
    }

    /**
     * Verifies that all href links on the page with path have an http response that is below 400
     *
     * @param   path URL Path to verify links on
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void verifyUrlLinks(String path)
            throws Exception {
        LOGGER.info("verifyUrlLinks started");

        String url = Config.getBaseUrl() + path;
        String linkUrl;

        boolean passed = true;
        String failureMessage = "FAIL:\n";

        Document doc = Jsoup.connect(url).timeout(Constants.DEFAULT_MILLISEC_WAIT)
                .validateTLSCertificates(false).get();

        for (Element link : doc.select("a")) {
            String href = link.attr("href");

            if (!href.contains("http") && !href.contains("javascript")) {
                linkUrl = url + href;
                int response = HttpUtils.getStatusCode(linkUrl);

                if (response > 400) {
                    passed = false;
                    failureMessage = failureMessage + "Link '" + linkUrl + " had response of " + response + ".\n";
                } else {
                    LOGGER.info("Verified Link '" + linkUrl + " had response below 400 (response: " + response + ").");
                }
            }
        }

        Assert.assertTrue(failureMessage, passed);
        LOGGER.info("verifyUrlLinks started");
    }
}