package ovc.steps;

import ovc.pages.AircheckPage;
import ovc.pages.CommonActions;
import utilities.Driver;

/**
 * Created by eseverson on 6/29/2017.
 */
public class AircheckPageSteps {

    private AircheckPage aircheckPage;
    private CommonActions commonActions;

    public AircheckPageSteps(Driver driver) {
        aircheckPage = new AircheckPage(driver);
        commonActions = new CommonActions(driver);
    }

}
