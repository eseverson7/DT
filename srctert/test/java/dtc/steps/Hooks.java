package dtc.steps;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.CommonUtils;
import utilities.Driver;
import utilities.SauceUtils;

/**
 * Created by aaronbriel on 10/21/16.
 */
public class Hooks {

    private Driver driver;

    public Hooks(Driver driver) {
        this.driver = driver;
    }

    @Before
    public void beforeScenario() {
        driver.initialize();

        //Creating orders artifact for Jenkins post build step
        CommonUtils.appendFile(ConstantsDtc.ORDERS_FILE, "");
    }

    @After
    public void afterScenario(Scenario scenario) throws Throwable{
        if (!Config.getBrowser().equalsIgnoreCase(Constants.NONE)) {
            //Passing test details to Saucelabs
            if (Config.getIsSaucelabs())
                SauceUtils.updateResults(scenario.getName(), driver.getRemoteSessionId(), !scenario.isFailed());

            //Embedding DT session id to cucumber report
            scenario.write("JSESSIONID:" + driver.scenarioData.getDtSessionId());

            driver.embedScreenshotOnFailure(scenario);
            new Driver().destroyDriver();
        }
    }
}