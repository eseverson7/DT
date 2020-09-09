package ovc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by eseverson on 6/27/2017.
 */
public class VTVPage {

    private Driver driver;
    private WebDriver webDriver;
    private ParentElementsPage parentElementsPage;
    private final Logger LOGGER = Logger.getLogger(VTVPage.class.getName());
    private CommonActions commonActions;

    public VTVPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        parentElementsPage = new ParentElementsPage(driver);
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
    }

    @FindBy(id = "header-VTV")
    private static WebElement vtvHeader;

    @FindBy(css = ".tireLF span")
    public static WebElement frontLeftTireSpan;

    @FindBy(css = ".tireRF span")
    public static WebElement frontRightTireSpan;

    @FindBy(css = ".tireLR span")
    public static WebElement rearLeftTireSpan;

    @FindBy(css = ".tireRR span")
    public static WebElement rearRightTireSpan;

    @FindBy(id = "ovcVisitVehicleView")
    public static WebElement vtvView;

    @FindBy(className = "btn-red-link")
    public static WebElement addRemoveDualButton;

    @FindBy(id = "miles")
    public static WebElement milesRadioBtn;

    @FindBy(id = "kilometers")
    public static WebElement kilometersRadioBtn;

    @FindBy(id = "tpms_status_na")
    public static WebElement tpmsNARadioBtn;

    @FindBy(id = "tpms_status_solid")
    public static WebElement tpmsSolidRadioBtn;

    @FindBy(id = "tpms_status_flashing")
    public static WebElement tpmsFlashingRadioBtn;

    @FindBy(id = "wheel_lock_na")
    public static WebElement wheelLocksNARadioBtn;

    @FindBy(id = "wheel_lock_yes")
    public static WebElement wheelLocksYesRadioBtn;

    @FindBy(id = "carry_out_na")
    public static WebElement carryOutNARadioBtn;

    @FindBy(id = "carry_out_yes")
    public static WebElement carryOutYesRadioBtn;

    @FindBy(xpath = "//select[@ng-model=\"form.work_order.rotation_type\"]")
    public static WebElement tireRotationPattern;

    public static final By treadDepthCirclesBy = By.className("fa-circle");

    public static final By mileageFieldBy = By.xpath("//input[@ng-model=\"form.mileage\"]");

    public static final By locationFieldBy = By.xpath("//input[@ng-model=\"form.location\"]");

    public static final By conditionFieldBy = By.xpath("//input[@ng-model=\"form.condition\"]");

    public static final By treadDepthsBy = By.className("treadDepths");
    
    public static final By tireOptionBy = By.className("tire");

    private static final By individualServicesBy = By.className("indService");

    private static final String NOT_EMPTY = "ng-not-empty";

    private static final String EMPTY = "ng-empty";

    private static final String CHECKED = "Checked";

    private static final String KILOMETERS = "Kilometers";

    private static final String TPMS_STATUS_NA = "TPMS Status N/A";

    private static final String TPMS_STATUS_SOLID = "TPMS Status Solid";

    private static final String TPMS_STATUS_FLASHING = "TPMS Status Flashing";

    private static final String WHEEL_LOCKS_NA = "Wheel Locks N/A";

    private static final String WHEEL_LOCKS_YES = "Wheel Locks Yes";

    private static final String CARRY_OUT_NA = "Carry Out N/A";

    private static final String CARRY_OUT_YES = "Carry Out Yes";

    public static final String LF = "LF";

    public static final String RF = "RF";

    public static final String LR = "LR";

    public static final String RR = "RR";

    private static final String OVC_RED = "rgba(255, 0, 0, 1)";

    private static final String OVC_YELLOW = "rgba(255, 255, 0, 1)";

    private static final String OVC_GREEN = "rgba(0, 128, 0, 1)";

    /**
     * Clicks on the individual service box for replace tire
     */
    public void replaceTire() {
        LOGGER.info("replaceTire started");
        driver.waitForElementClickable(frontLeftTireSpan);
        frontLeftTireSpan.click();
        List<WebElement> servicesList = webDriver.findElements(individualServicesBy);
        for (WebElement service : servicesList) {
            if (service.getText().equalsIgnoreCase(ConstantsOvc.REPLACE_TIRE)) {
                service.click();
                break;
            } else {
                LOGGER.info("Replace tire service option not found on the VTV page");
            }
        }
        LOGGER.info("replaceTire completed");
    }

    /**
     * Clicks on the specified tire and then on the specified service option
     *
     * @param serviceOption Option to select for the tire that's been selected
     * @param tire Specified tire to select on page
     */
    public void selectTireAndServiceOption(String serviceOption, String tire) {
        LOGGER.info("selectTireAndServiceOption started");
        driver.waitForElementClickable(frontLeftTireSpan);
        selectTire(tire);
        List<WebElement> servicesList = webDriver.findElements(individualServicesBy);
        for (WebElement service : servicesList) {
            if (service.getText().equalsIgnoreCase(serviceOption)) {
                service.click();
                break;
            }
        }
        LOGGER.info("selectTireAndServiceOption completed");
    }

    /**
     * Selects the tire passed to it
     *
     * @param tire Abbreviation of tire on VTV page
     */
    public void selectTire(String tire) {
        LOGGER.info("selectTire started");
        driver.waitForElementClickable(frontLeftTireSpan);

        if (tire.equalsIgnoreCase(LF)) {
            frontLeftTireSpan.click();
        } else if (tire.equalsIgnoreCase(RF)) {
            frontRightTireSpan.click();
        } else if (tire.equalsIgnoreCase(LR)) {
            rearLeftTireSpan.click();
        } else if (tire.equalsIgnoreCase(RR)) {
            rearRightTireSpan.click();
        }

        LOGGER.info("selectTire completed");
    }

    /**
     * Clicks on the inspect tire checkboxes
     */
    public void selectInspectIcons() {
        LOGGER.info("selectInspectIcons Started");
        driver.waitForElementClickable(frontLeftTireSpan);
        // TODO: Update to actual functionality. Inspect is currently gone.
        frontLeftTireSpan.click();
        frontRightTireSpan.click();
        rearLeftTireSpan.click();
        rearRightTireSpan.click();
        LOGGER.info("selectInspectIcons Completed");
    }

    /**
     * Enters value into the specified VTV Vehicle Section field
     *
     * @param field     Vehicle section field that will receive value
     * @param fieldInfo Value to enter into specified VTV Vehicle section field
     */
    public void enterInfoIntoVehicleInfoField(String field, String fieldInfo) {
        LOGGER.info("enterInfoIntoVehicleInfoField started");
        driver.waitForElementClickable(frontLeftTireSpan);
        WebElement fieldElement = returnVehicleTextBoxElement(field);
        fieldElement.clear();
        fieldElement.sendKeys(fieldInfo);
        LOGGER.info("enterInfoIntoVehicleInfoField completed");
    }

    /**
     * Validates value in the specified VTV Vehicle Section field
     *
     * @param field     Vehicle section field
     * @param fieldInfo Value to verify
     */
    public void assertVehicleTextField(String field, String fieldInfo) {
        LOGGER.info("assertVehicleTextField started");
        driver.waitForElementVisible(vtvView);
        WebElement fieldEle = returnVehicleTextBoxElement(field);
        Assert.assertTrue("FAIL: " + field + "text box did not equal: " + fieldInfo + ". Instead was: " +
                fieldEle.getAttribute("value"), fieldEle.getAttribute("value").equals(fieldInfo));
        LOGGER.info("assertVehicleTextField completed");
    }

    /**
     * Returns the webelement of the VTV Vehicle text box
     *
     * @param field Field of Vehicle element to reutnr
     * @return WebElement
     */
    public WebElement returnVehicleTextBoxElement(String field) {
        LOGGER.info("returnVehicleTextBoxElement started");
        WebElement fieldEle;

        if (field.equalsIgnoreCase(ConstantsOvc.MILEAGE)) {
            fieldEle = vtvView.findElement(mileageFieldBy);
        } else if (field.equalsIgnoreCase(ConstantsOvc.LOCATION)) {
            fieldEle = vtvView.findElement(locationFieldBy);
        } else {
            fieldEle = vtvView.findElement(conditionFieldBy);
        }

        LOGGER.info("returnVehicleTextBoxElement completed");
        return fieldEle;
    }

    /**
     * Verifies the selected Global Header icon color is 'color'
     *
     * @param color Color of the VTV button
     */
    public void verifyVTVHeaderIconColor(String color) {
        LOGGER.info("verifyVTVHeaderIconIsGreen started");
        driver.waitForPageToLoad();
        String colorToVerify = null;

        if (color.equalsIgnoreCase(Constants.GREEN)) {
            color = vtvHeader.getCssValue(Constants.COLOR);
            colorToVerify = ConstantsOvc.GREEN_COLOR;
        }

        if (colorToVerify != null) {
            Assert.assertTrue("FAIL: The rgba value of the VTV header - '" + color +
                    "' did NOT match the expected -'" + colorToVerify + "'!", color.equals(colorToVerify));
        }

        LOGGER.info("verifyVTVHeaderIconIsGreen completed");
    }

    /**
     * Clicks the '+ ADD DUAL' or '- REMOVE DUAL' button
     */
    public void selectAddRemoveDualSymbol() {
        LOGGER.info("selectAddRemoveDualSymbol started");
        driver.waitForPageToLoad();
        addRemoveDualButton.click();
        LOGGER.info("selectAddRemoveDualSymbol completed");
    }

    /**
     * Verifies passed in element is displayed on the page
     */
    public void assertElementIsDisplayedOnPage(String elementName) {
        LOGGER.info("assertElementIsDisplayedOnPage started");
        driver.waitForPageToLoad();
        WebElement element = null;

        Assert.assertTrue("FAIL: Element with text '" + elementName + "' was NOT displayed",
                driver.isElementDisplayed(element));

        LOGGER.info("assertElementIsDisplayedOnPage completed");
    }

    /**
     * Selects an input box based on input string and enters the value
     *
     * @param input Title of the input box
     * @param value Value to enter into input
     */
    public void enterValueTireStatsInput(String input, String value) {
        LOGGER.info("enterValueTireStatsInput started");
        driver.waitForPageToLoad();
        boolean found = false;

        WebElement parentPage = parentElementsPage.returnPageObjectElement(ConstantsOvc.VTV);
        List<WebElement> treads = parentPage.findElements(treadDepthsBy);
        for (WebElement tread : treads) {
            if (tread.getText().toLowerCase().contains(input.toLowerCase())) {
                WebElement inputTag = tread.findElement(CommonActions.inputTagBy);
                inputTag.clear();
                inputTag.sendKeys(value);
                found = true;
                break;
            }
        }

        if (!found)
            Assert.fail("FAIL: Could not find/manipulate input box " + input + "' on the VTV page");

        LOGGER.info("enterValueTireStatsInput completed");
    }

    /**
     * Verifies the color of the Tread Depth box as specified by the label and position
     *
     * @param tireTagLabel Text to locate the dropdown itself
     * @param color        Color of the circle (Red, Green, Yellow)
     */
    public void assertTireStatTreadDepthColor(String tireTagLabel, String color) {
        LOGGER.info("assertTireStatTreadDepthColor started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        boolean found = false;
        String ovcColor = null;

        if (color.equalsIgnoreCase(Constants.RED)) {
            ovcColor = OVC_RED;
        } else if (color.equalsIgnoreCase(Constants.GREEN)) {
            ovcColor = OVC_GREEN;
        } else if (color.equalsIgnoreCase(Constants.YELLOW)) {
            ovcColor = OVC_YELLOW;
        }

        WebElement parentPage = parentElementsPage.returnPageObjectElement(ConstantsOvc.VTV);
        List<WebElement> treads = parentPage.findElements(treadDepthsBy);
        for (WebElement tread : treads) {
            if (tread.getText().contains(tireTagLabel)) {
                WebElement spanColorTag = tread.findElement(treadDepthCirclesBy);
                String cssValue = spanColorTag.getCssValue(Constants.COLOR);
                Assert.assertTrue("FAIL: Color of VTV circle with label " + tireTagLabel + " did NOT equal "
                        + color + ". It was " + cssValue + " instead", ovcColor.equals(cssValue));
                found = true;
                break;
            }
        }

        if (!found)
            Assert.fail("FAIL: Could not verify tread depth of '" + tireTagLabel + "' on the VTV page");

        LOGGER.info("assertTireStatTreadDepthColor completed");
    }

    /**
     * Selects the radio button via passed in label text
     *
     * @param labelText Text next to the radio button to select
     */
    public void selectVTVRadioButton(String labelText) {
        LOGGER.info("selectVtvRadioButton started");
        driver.waitForMilliseconds();
        WebElement radio = null;

        switch (labelText) {
            case Constants.MILES:
                radio = milesRadioBtn;
                break;
            case KILOMETERS:
                radio = kilometersRadioBtn;
                break;
            case TPMS_STATUS_NA:
                radio = tpmsNARadioBtn;
                break;
            case TPMS_STATUS_SOLID:
                radio = tpmsSolidRadioBtn;
                break;
            case TPMS_STATUS_FLASHING:
                radio = tpmsFlashingRadioBtn;
                break;
            case WHEEL_LOCKS_NA:
                radio = wheelLocksNARadioBtn;
                break;
            case WHEEL_LOCKS_YES:
                radio = wheelLocksYesRadioBtn;
                break;
            case CARRY_OUT_NA:
                radio = carryOutNARadioBtn;
                break;
            case CARRY_OUT_YES:
                radio = carryOutYesRadioBtn;
                break;
            default:
                Assert.fail("Radio button " + labelText + " does not exist on the VTV page.");
        }

        radio.click();
        LOGGER.info("selectVtvRadioButton completed");
    }
    
    /**
     * Clicks on the desired tire replacement option
     * 
     * @param option option to be selected for tire replacement
     */
    public void replaceDesiredTireOption(String option) {
        LOGGER.info("replaceDesiredTireOption started");
        commonActions.selectElementWithText(tireOptionBy,option);
        LOGGER.info("replaceDesiredTireOption completed");
    }

    /**
     * Expands the Tire Rotation Pattern dropdown
     */
    public void expandTireRotationDropdown() {
        LOGGER.info("expandTireRotationDropdown started");
        tireRotationPattern.click();
        LOGGER.info("expandTireRotationDropdown completed");
    }
}