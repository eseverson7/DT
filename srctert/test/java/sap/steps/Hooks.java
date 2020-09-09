package sap.steps;

import common.Config;
import common.Constants;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.Driver;
import utilities.SauceUtils;

/**
 * Created by mnabizadeh on 5/11/18.
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

	            //Passing test details to Saucelabs
	            if (Config.getIsSaucelabs())
	                SauceUtils.updateResults(scenario.getName(), driver.getRemoteSessionId(), !scenario.isFailed());

	            driver.embedScreenshotOnFailure(scenario);
	            new Driver().destroyDriver();
	        }
	    }
}
