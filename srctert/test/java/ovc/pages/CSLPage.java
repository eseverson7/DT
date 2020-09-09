package ovc.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.Constants;
import ovc.data.ConstantsOvc;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aarora on 8/15/2017.
 */
public class CSLPage {
    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CSLPage.class.getName());

    public CSLPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "fa-gavel")
    public static WebElement serviceHomeIcon;

    @FindBy(className = "fa-list")
    public static WebElement pullListIcon;

    @FindBy(className = "fa-check")
    public static WebElement doneIcon;

    @FindBy(className = "fa-calendar")
    public static WebElement scheduleIcon;

    @FindBy(className = "fa-chevron-left")
    public static WebElement pullListChevron;

    @FindBy(className = "listItemWarp")
    public static WebElement inactiveListWrap;

    @FindBy(className = "listItemWarpT")
    public static WebElement activePaneListWrap;

    @FindBy(id = "item_left")
    public static WebElement leftSideDetails;

    @FindBy(className = "vehicleBox")
    public static WebElement workOrderVehicleInfo;

    @FindBy(className = "mainContainer")
    public static WebElement mainContainer;

    @FindBy(className = "modal-content")
    public static WebElement CSLPopup;

    @FindBy(className = "md-ink-ripple")
    public static WebElement doneCheckBox;

    @FindBy(className = "toast-title")
    public static WebElement notificationTitle;

    @FindBy(id = "cboxClose")
    public static WebElement backToSaleButton;

    @FindBy(id = "input_1")
    private static WebElement serviceCoordinatorInput;

    @FindBy(className = "md-transition-in")
    public static WebElement workOrderRequirementContainer;

    @FindBy(className = "paymentStatus")
    private static WebElement paymentStatus;

    @FindBy(className = "airpressureTable")
    private static WebElement airPressureTable;

    private static final By cslCheckBoxBy = By.className(" md-icon");

    private static final By cslRepairReason = By.xpath("//select[contains(@ng-model,'form.repairReason')]");

    private static final By pullListInputs = By.className("tranItemInput");

    public static final By modalPopup = By.className("modal-content");

    public static final By delayButtonBy = By.className("btn-black");

    public static final By toastContainerBy = By.className("toast-success");

    private static final By listItemsBy = By.className("listItems");

    private static final By ghostListRemoveItemBy = By.className("ghostRemove");

    private static final By ghostListItemBy = By.className("ghostStatus");

    private static final By carBlockBy = By.className("car-block");

    private static final By tireDotsBy = By.className("tireDots");

    private static final By tireServiceBy = By.className("tireService");

    private static String[] cslMenus = {"SERVICE HOME", "PULL LIST", "DONE", "APPOINTMENT"};

    public static String[] airPressureWheelValues = {"Torque", "Lug Size", "Bolt Pattern", "Hub Bore"};

    private static final String dotNumber[] = {"U2LL", "LMLR", "5107"};

    private static final String repairReason = "Repair Reason";

    private static final String serviceCoordinator = "Service Coordinator";

    /**
     * Verifies the CSL page header menus exist
     */
    public void verifyCSLHeaderMenusExist() {
        LOGGER.info("verifyCSLHeaderMenusExist started");
        driver.waitForElementVisible(serviceHomeIcon, Constants.DEFAULT_SEC_WAIT);
        String failedElements = "";
        for (String cslMenu : cslMenus) {
            try {
                driver.checkIfElementContainsText(CommonActions.hrefTagBy, cslMenu);
                LOGGER.info("Menu " + cslMenu + " was displayed on CSL Page");
            } catch (NotFoundException e) {
                failedElements += cslMenu.concat(", ");
                LOGGER.info("Menu " + cslMenu + " was not displayed on CSL Page");
            }
        }
        if (!failedElements.isEmpty()) {
            Assert.fail("CSL Menu(s) " + failedElements + "were not found on the CSL Page");
        }
        LOGGER.info("verifyCSLHeaderMenusExist completed");
    }

    /**
     * Select main CSL page Work Order via the title
     *
     * @param title Title of the text inside the order box
     * @param list  Either the Inactive or Active work order list you are selecting from
     */
    public void selectWorkOrderTitle(String title, String list) {
        LOGGER.info("selectWorkOrderTitle started");
        WebElement listWrapper;

        if (list.equalsIgnoreCase(Constants.ACTIVE)) {
            listWrapper = activePaneListWrap;
        } else {
            listWrapper = inactiveListWrap;
        }

        driver.waitForElementVisible(listWrapper);
        driver.waitForPageToLoad();
        Boolean found = false;

        List<WebElement> rows = listWrapper.findElements(CommonActions.hrefTagBy);
        for (WebElement row : rows) {
            if (row.getText().contains(title)) {
                row.click();
                found = true;
                break;
            }
        }

        if (!found)
            Assert.fail("FAIL: Work order with title '" + title + "' NOT found on page.");

        LOGGER.info("selectWorkOrderTitle completed");
    }

    /**
     * Verifies the passed in text is present in the work order title section
     *
     * @param text String of text to appear in section
     */
    public void assertTextInWorkOrderTitle(String text) {
        LOGGER.info("assertTextInWorkOrderTitle started");
        driver.waitForElementClickable(workOrderVehicleInfo);
        Assert.assertTrue("FAIL: Work order title did not contain '" + text + "'",
                workOrderVehicleInfo.getText().contains(text));
        LOGGER.info("assertTextInWorkOrderTitle completed");
    }

    /**
     * Verifies at least one service appears in the tire section of the page
     */
    public void assertServiceInTireBoxes() {
        LOGGER.info("assertServiceInTireBoxes started");
        driver.waitForElementVisible(mainContainer);
        WebElement carBlock = driver.getDisplayedElement(carBlockBy, Constants.DEFAULT_MILLISEC_WAIT);
        List<WebElement> boxes = carBlock.findElements(tireServiceBy);
        int textLength = 0;
        for (WebElement box : boxes) {
            if (box.getText().length() > 0) {
                textLength = box.getText().length();
            }
        }

        if (textLength == 0) {
            Assert.fail("FAIL: Found 0 instances of text in the CSL tire image");
        }

        LOGGER.info("assertServiceInTireBoxes completed");
    }

    /**
     * Enter previous dotNumber into all DOT box fields
     */
    public void enterDOTNumbersIntoDOTBoxes() {
        LOGGER.info("enterDOTNumbersIntoDOTBoxes started");
        driver.waitForElementVisible(mainContainer);
        WebElement carBlock = driver.getDisplayedElement(carBlockBy, Constants.DEFAULT_MILLISEC_WAIT);
        List<WebElement> dotInputs = carBlock.findElements(tireDotsBy);

        for (WebElement dotInput : dotInputs) {
            List<WebElement> dotSections = dotInput.findElements(CommonActions.inputTagBy);
            int i = 0;
            for (WebElement dotSection : dotSections) {
                if (dotSection.isDisplayed()) {
                    dotSection.click();
                    dotSection.clear();
                    dotSection.sendKeys(dotNumber[i]);
                    i++;
                }
            }
        }

        LOGGER.info("enterDOTNumbersIntoDOTBoxes completed");
    }

    /**
     * Verify previous dotNumber entered into all DOT box fields
     */
    public void assertDOTNumbersIntoDOTBoxes() {
        LOGGER.info("assertDOTNumbersIntoDOTBoxes started");
        driver.waitForElementVisible(mainContainer);
        WebElement carBlock = driver.getDisplayedElement(carBlockBy, Constants.DEFAULT_MILLISEC_WAIT);
        List<WebElement> dotInputs = carBlock.findElements(tireDotsBy);

        for (WebElement dotInput : dotInputs) {
            if (dotInput.isDisplayed()) {
                List<WebElement> dotSections = dotInput.findElements(CommonActions.inputTagBy);
                for (int i = 0; i <= 2; i++) {
                    String dot = dotSections.get(i).getAttribute(Constants.VALUE);
                    Assert.assertTrue("FAIL: Not all DOT inputs contained the value " + dotNumber[i] + ".",
                            dot.equals(dotNumber[i]));
                }
            }
        }

        LOGGER.info("assertDOTNumbersIntoDOTBoxes completed");
    }

    /**
     * Verifies if the Work Order is currently paid or unpaid
     *
     * @param paid "Paid" or "Unpaid"
     */
    public void verifyPaidOrderSignOffStatus(String paid) {
        LOGGER.info("verifyPaidOrderSignOffStatus started");
        driver.waitForElementVisible(mainContainer);
        Assert.assertTrue("FAIL: Sign off value was NOT " + paid + ".", paymentStatus.getText().contains(paid));
        LOGGER.info("verifyPaidOrderSignOffStatus completed");
    }

    /**
     * Verifies values appear in the Air Pressure table at bottom of screen
     *
     * @param values String values to appear in table
     */
    public void verifyValuesInAirPressureTable(String[] values) {
        LOGGER.info("verifyValuesInAirPressureTable started");
        driver.waitForElementVisible(airPressureTable);
        for (String value : values) {
            Assert.assertTrue("FAIL: Air pressure table did NOT contain value: " + value,
                    airPressureTable.getText().contains(value));
        }
        LOGGER.info("verifyValuesInAirPressureTable completed");
    }

    /**
     * Enters text into Service Coordinator text box on CSL page
     *
     * @param text Text to enter into field
     */
    public void enterTextIntoServiceCoordinatorField(String text) {
        LOGGER.info("enterTextIntoServiceCoordinatorField started");
        driver.waitForPageToLoad();
        serviceCoordinatorInput.click();
        serviceCoordinatorInput.clear();
        serviceCoordinatorInput.sendKeys(text);
        LOGGER.info("enterTextIntoServiceCoordinatorField completed");
    }

    /**
     * Verifies text is entered into Service Coordinator text box on CSL page
     *
     * @param text Text to enter into field
     */
    public void verifyWorkOrderServiceCoordinatorContainsText(String text) {
        LOGGER.info("verifyWorkOrderServiceCoordinatorContainsText started");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: Service Coordinator field did NOT contain the value " + text,
                serviceCoordinatorInput.getAttribute(Constants.VALUE).contains(text));
        LOGGER.info("verifyWorkOrderServiceCoordinatorContainsText completed");
    }

    /**
     * Verifies that the modal popup window contains specific text
     *
     * @param text Text of the modal popup to verify
     */
    public void assertModalPopupTextContains(String text) {
        LOGGER.info("assertModalTextContains started");
        driver.waitForPageToLoad();
        WebElement dialog = driver.getDisplayedElement(modalPopup, Constants.ONE_SEC_WAIT);
        Assert.assertTrue("FAIL: Expected dialog to contain text '" + text + "', contained text was:'"
                + dialog.getText().trim() + "'!", dialog.getText().trim().contains(text.trim()));
        LOGGER.info("assertModalTextContains completed");
    }

    /**
     * Selects a button on either the Active or Inactive service home lists.
     *
     * @param text button text you are looking to click
     * @param list either the "active" or "inactive" list
     */
    public void selectCSLServiceHomeButton(String text, String list) {
        LOGGER.info("selectCSLButton started");
        driver.waitForElementVisible(inactiveListWrap);
        WebElement listWrapper;

        if (list.equalsIgnoreCase(Constants.ACTIVE)) {
            listWrapper = activePaneListWrap;
        } else {
            listWrapper = inactiveListWrap;
        }

        if (text.equalsIgnoreCase(ConstantsOvc.DELAY)) {
            List<WebElement> delayRows = listWrapper.findElements(delayButtonBy);
            for (WebElement row : delayRows) {
                if (row.getText().contains(text)) {
                    row.click();
                    break;
                }
            }
        } else {
            List<WebElement> rows = listWrapper.findElements(CommonActions.buttonTagBy);
            for (WebElement row : rows) {
                if (row.getText().contains(text)) {
                    row.click();
                    break;
                }
            }
        }

        LOGGER.info("selectCSLButton completed");
    }

    /**
     * Selects an option for the CSL Delay reason Modal
     *
     * @param selection specific option you want to select from dropdown
     */
    public void selectDelayDropDownReason(String selection) {
        LOGGER.info("selectDelayDropDownReason started");
        driver.waitForElementVisible(CSLPopup);
        WebElement dropdown = CSLPopup.findElement(CashManagementPage.formControlBy);
        driver.selectFromDropdownByVisibleText(dropdown, selection);
        LOGGER.info("selectDelayDropDownReason completed");
    }

    /**
     * Selects a button off the CSL popup menu
     *
     * @param text text of the button you want to select
     */
    public void selectCSLPopupButton(String text) {
        LOGGER.info("selectCSLPopupButton started");
        driver.waitForElementVisible(CSLPopup);
        List<WebElement> buttons = CSLPopup.findElements(CommonActions.buttonTagBy);
        for (WebElement button : buttons) {
            if (button.getText().equalsIgnoreCase(text)) {
                button.click();
                break;
            }
        }
        driver.waitForPageToLoad();
        LOGGER.info("selectCSLPopupButton completed");
    }

    /**
     * Selects an icon off the CLS header list
     *
     * @param icon name of the icon on header list
     */
    public void selectCSLHeaderIcon(String icon) {
        LOGGER.info("selectCSLHeaderIcon started");
        driver.waitForElementVisible(serviceHomeIcon);
        WebElement headerIcon = returnCSLHeaderIcon(icon);
        headerIcon.click();
        driver.waitForPageToLoad();
        LOGGER.info("selectCSLHeaderIcon completed");
    }

    /**
     * logic used by selectCSLHeaderIcon to determine icon to select
     *
     * @param icon string name of icon passed from selectCSLHeaderIcon
     * @return WebElement of Icon in the header list
     */
    private WebElement returnCSLHeaderIcon(String icon) {
        LOGGER.info("returnCSLHeaderIcon started");
        WebElement headerIcon = null;
        if (icon.equalsIgnoreCase(cslMenus[0])) {
            headerIcon = serviceHomeIcon;
        } else if (icon.equalsIgnoreCase(cslMenus[1])) {
            headerIcon = pullListIcon;
        } else if (icon.equalsIgnoreCase(cslMenus[2])) {
            headerIcon = doneIcon;
        } else {
            headerIcon = scheduleIcon;
        }
        LOGGER.info("returnCSLHeaderIcon completed");
        return headerIcon;
    }

    /**
     * Selects the checkbox on the Done CSL page marking the work order complete
     */
    public void selectCSLDoneCheckBox() {
        LOGGER.info("selectCSLDoneCheckBox started");
        driver.waitForElementVisible(doneIcon);
        doneCheckBox.click();
        LOGGER.info("selectCSLDoneCheckBox completed");
    }

    /**
     * asserts the dialog of the toast-notification container. Requires a mouse over to pause the timer of
     * notification to prevent it from closing before assertion can take place.
     *
     * @param dialog string of specific text you are looking to assert in the notification
     */
    public void assertGreenDialogBanner(String dialog) {
        LOGGER.info("assertGreenDialogBanner started");
        driver.waitForElementVisible(toastContainerBy);
        driver.mouseHoverOverElement(toastContainerBy);
        String actualText = notificationTitle.getText();
        LOGGER.info(actualText);
        Assert.assertTrue("FAIL: Text expected: \"" + dialog + "\", actual text: \"" + actualText + "\".",
                actualText.contains(dialog));
        notificationTitle.click();
        LOGGER.info("assertGreenDialogBanner completed");
    }

    /**
     * inputs string data into text box inputs on Pull List CSL page
     *
     * @param inputField string of input field you will send data to
     * @param DOTNumber  string of a DOT Number to insert into input
     */
    public void fillOutPullListInput(String inputField, String DOTNumber) {
        LOGGER.info("fillOutPullListInput started");
        driver.waitForElementVisible(pullListIcon);
        List<WebElement> inputs = webDriver.findElements(pullListInputs);

        if (inputField.equalsIgnoreCase(ConstantsOvc.DOT_NUMBER)) {
            for (WebElement input : inputs) {
                String attributeValue = input.getAttribute(ConstantsOvc.NG_MODEL);
                if (attributeValue.contains(ConstantsOvc.DOT)) {
                    input.sendKeys(DOTNumber);
                    break;
                }
            }
        } else if (inputField.equalsIgnoreCase(ConstantsOvc.PULLED_QUANTITY)) {
            for (WebElement input : inputs) {
                String attributeValue = input.getAttribute(ConstantsOvc.NG_MODEL);
                if (attributeValue.contains(ConstantsOvc.PULLED_QTY)) {
                    input.sendKeys(ConstantsOvc.ONE);
                    break;
                }
            }
        }

        LOGGER.info("fillOutPullListInput completed");
    }

    /**
     * selects the drop down chevron on the top pull list item
     */
    public void selectCSLPullListChevron() {
        LOGGER.info("selectCSLPullListChevron started");
        driver.waitForElementClickable(pullListChevron);
        pullListChevron.click();
        doneCheckBox.click();
        LOGGER.info("selectCSLPullListChevron completed");
    }

    /**
     * Selects the "Back to Sale" button to exit CSL application
     */
    public void selectBackToSale() {
        LOGGER.info("selectBackToSale started");
        driver.waitForElementClickable(backToSaleButton);
        backToSaleButton.click();
        driver.waitForPageToLoad();
        LOGGER.info("selectBackToSale completed");
    }

    /**
     * Dismisses the work order requirements popup
     */
    public void closeWorkOrderRequirementsPopup() {
        LOGGER.info("closeWorkOrderRequirementsPopup started");
        driver.waitForElementVisible(workOrderRequirementContainer);
        WebElement dismissButton = workOrderRequirementContainer.findElement(CommonActions.buttonTagBy);
        dismissButton.click();
        LOGGER.info("closeWorkOrderRequirementsPopup compelted");
    }

    /**
     * Makes a list of all the work orders in the inactive tab and asserts there is at least one
     */
    public void verifyInactiveListWorkOrderQuantity() {
        LOGGER.info("verifyInactiveListWorkOrderQuantity started");
        driver.waitForElementVisible(leftSideDetails);
        int workOrderListLen = 0;
        try {
            List<WebElement> workOrders = inactiveListWrap.findElements(listItemsBy);
            workOrderListLen = workOrders.size();
            Assert.assertTrue("FAIL: Number of work orders found was: \"" + workOrderListLen + "\"," +
                    " but was expecting more than 1.", workOrderListLen > 0);
            LOGGER.info("verifyInactiveListWorkOrderQuantity completed");
        } catch (Exception e) {
            LOGGER.info("CSL has " + workOrderListLen + " inactive work orders. More than 1 is required.");
        }
    }

    /**
     * Iterates through a list of work orders in the active tab and moves them to the inactive tab
     */
    public void removeActiveWorkOrdersFromActiveList() {
        LOGGER.info("removeActiveWorkOrdersFromActiveList started");
        int activeWorkOrderLen = 0;
        try {
            driver.waitForElementVisible(activePaneListWrap);

            for (activeWorkOrderLen = activePaneListWrap.findElements(listItemsBy).size(); activeWorkOrderLen > 0;
                 activeWorkOrderLen--) {
                List<WebElement> workOrders = activePaneListWrap.findElements(listItemsBy);
                List<WebElement> buttons = workOrders.get(0).findElements(CommonActions.buttonTagBy);

                for (WebElement button : buttons) {
                    if (button.getText().contains(Constants.BACK)) {
                        button.click();
                        selectCSLHeaderIcon(ConstantsOvc.SERVICE_HOME); //refreshes page to avoid stale element
                        driver.waitForPageToLoad();
                        LOGGER.info("Removed one work order from the active list and there are " +
                                (activeWorkOrderLen - 1) + " remaining to be removed!");
                        break;
                    }
                }
            }

        } finally {
            LOGGER.info("There is currently " + activeWorkOrderLen + " work orders in the active work order pane.");
        }
        LOGGER.info("removeActiveWorkOrdersFromActiveList completed");
    }

    /**
     * Looks for inactive "ghost" work orders in the CSL inactive work order list that cannot be used and removes them
     */
    public void removeDisabledInactiveWorkOrdersFromInactiveList() {
        LOGGER.info("removeDisabledInactiveWorkOrdersFromInactiveList started");
        int workOrderLength = 0;
        try {
            driver.waitForElementVisible(inactiveListWrap);
            for (workOrderLength = inactiveListWrap.findElements(ghostListItemBy).size(); workOrderLength > 0; workOrderLength--) {

                List<WebElement> inactiveWorkOrderRemoveButtons = inactiveListWrap.findElements(ghostListRemoveItemBy);
                for (WebElement inactiveRemoveButton : inactiveWorkOrderRemoveButtons) {
                    if (inactiveRemoveButton.getText().contains(Constants.REMOVE)) {
                        inactiveRemoveButton.click();
                        selectCSLHeaderIcon(ConstantsOvc.SERVICE_HOME); //refreshes page to avoid stale element
                        driver.waitForPageToLoad();
                        LOGGER.info("Removed one ghost work order from the inactive list and there are " +
                                (workOrderLength - 1) + " remaining to be removed.");
                        break;
                    }
                }
            }
        } finally {
            LOGGER.info("There is currently " + workOrderLength +
                    " disabled work orders in the inactive work order pane.");
        }
        LOGGER.info("removeDisabledInactiveWorkOrdersFromInactiveList completed");
    }

    /**
     * Selects an option from the repair reason dropdown
     *
     * @param selection option from the dropdown list to select
     */
    public void selectRepairReasonForCSL(String selection) {
        LOGGER.info("selectRepairReasonForCSL started");
        driver.selectFromDropdownByVisibleText(cslRepairReason, selection);
        LOGGER.info("selectRepairReasonForCSL completed");
    }

    /**
     * Clicks one of two check boxes on the CSL Work Order page based on step input
     *
     * @param checkBox check box to be clicked
     */
    public void selectCheckBoxOnCSLWorkOrderPage(String checkBox) {
        LOGGER.info("selectCheckBoxOnCSLWorkOrderPage started");
        List<WebElement> checkBoxes = webDriver.findElements(cslCheckBoxBy);
        if (checkBox.equalsIgnoreCase(ConstantsOvc.DELAY)) {
            driver.jsClick(checkBoxes.get(0));
        } else if (checkBox.equalsIgnoreCase(ConstantsOvc.BAY_OUT)) {
            driver.jsClick(checkBoxes.get(1));
        }
        LOGGER.info("selectCheckBoxOnCSLWorkOrderPage completed");
    }
}
