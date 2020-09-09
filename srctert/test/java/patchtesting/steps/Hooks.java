package patchtesting.steps;

import common.Config;
import common.Constants;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.Driver;

/**
 * Created by eseverson on 5/21/18.
 */
public class Hooks {

    private Driver driver;

    public Hooks(Driver driver) {
        this.driver = driver;
    }

    @Before
    public void beforeScenario() {
        driver.initialize();
    }

    @After
    public void afterScenario(Scenario scenario) throws Throwable{
        if (!Config.getBrowser().equalsIgnoreCase(Constants.NONE)) {
            driver.embedScreenshotOnFailure(scenario);
            new Driver().destroyDriver();
        }
    }
}
