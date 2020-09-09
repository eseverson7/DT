package ovc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import utilities.Driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Logger;

import static ovc.pages.LoginPage.loader;

/**
 * Created by aaronbriel on 5/18/17.
 */
public class CommonActions {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());
    private ParentElementsPage parentElementsPage;
    private CustomerProfilePage customerProfilePage;

    public CommonActions(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        parentElementsPage = new ParentElementsPage(driver);
    }

    public static final String PLACEHOLDER = "placeholder";
    public static final String YEAR = "Year";
    public static final String MAKE = "Make";
    public static final String MODEL = "Model";
    public static final String TRIM = "Trim";
    public static final String ASSEMBLY = "Assembly";

    public static final String ENTER_PHONE_NUMBER = "Enter Phone number";

    @FindBy(className = "fa-home")
    public static WebElement homeIcon;

    @FindBy(className = "fa-gavel")
    public static WebElement cslIcon;

    @FindBy(className = "fa-calendar")
    public static WebElement alertsIcon;

    @FindBy(className = "fa-clock-o")
    public static WebElement appointmentIcon;

    @FindBy(className = "fa-search")
    public static WebElement lookupIcon;

    @FindBy(className = "fa-user")
    public static WebElement customerIcon;

    @FindBy(className = "fa-crosshairs")
    public static WebElement aircheckIcon;

    @FindBy(className = "fa-shopping-cart")
    public static WebElement endlessAisleIcon;

    @FindBy(className = "fa-gear")
    public static WebElement fitmentIcon;

    @FindBy(className = "fa-car")
    public static WebElement vtvIcon;

    @FindBy(className = "enhancedLookupContainer")
    public static WebElement resultsTable;

    @FindBy(className = "mobileNumber")
    public static WebElement cslMobileNumber;

    @FindBy(className = "cboxIframe")
    public static WebElement cslIFrame;

    @FindBy(id = "innerFrame")
    public static WebElement pdpIFrame;

    public static final By closeBy = By.className("fa-close");

    public static final By fitmentYearBy = By.id("fitment_year");

    public static final By fitmentMakeBy = By.id("fitment_make");

    public static final By fitmentModelBy = By.id("fitment_model");

    public static final By fitmentTrimBy = By.id("fitment_trim");

    public static final By fitmentAssemblyBy = By.id("fitment_assembly");

    public static final By tableRowBy = By.className("col-sm-12");

    public static final By btnClearClassBy = By.className("clearSrchBtn");

    public static final By buttonTagBy = By.tagName("button");

    public static final By selectTagBy = By.tagName("select");

    public static final By roleTagBy = By.tagName("role");

    public static final By inputTagBy = By.tagName("input");

    public static final By liTagBy = By.tagName("li");

    public static final By imgTagBy = By.tagName("img");

    public static final By labelTagBy = By.tagName("label");

    public static final By tableHeaderBy = By.tagName("th");

    public static final By tableBy = By.className("table");

    public static final By trBy = By.tagName("tr");

    public static final By tdBy = By.tagName("td");

    public static final By hrefTagBy = By.tagName("a");

    public static final By pTagBy = By.tagName("p");

    public static final By divTagBy = By.tagName("div");

    public static final By h1TagBy = By.tagName("h1");

    public static final By h4TagBy = By.tagName("h4");

    public static final By spanBy = By.tagName("span");

    public static final By btnLink = By.className("btn-link");

    public static final By listItemsBy = By.className("list-group-item");

    public static final By rightPaneSubMenuBy = By.id("subMenuView");

    public static final By navBarOptionBy = By.className("btn-navbar");

    public static final By navBarBy = By.className("navbar-brand");

    public static final By searchContainerBy = By.className("srchContainer");

    private static final By pageHeaderBy = By.className("scrollable-header");

    private static final By popupModalBy = By.className("ng-modal-dialog");

    private static final By popupContainerModalBy = By.className("ContainerNode");

    public static final By dialogModalBy = By.className("mblSimpleDialogContainer");

    private static final By radioButtonCashManagementBy = By.className("mblRadioButton");

    private static final By radioButtonLabelBy = By.className("radioButtonlabel");

    public static final By ngBindingClassBy = By.className("ng-binding");

    public static final By mblTxtBy = By.className("mblTextBox");

    public static final By flatRepairInputField = By.cssSelector("fieldset > input");

    public static final By cslSendTextMessageToggle = By.cssSelector(".smsDialogContent span");

    public static final By pagePreLoaderBy = By.id("preloader");

    public static final By stateDropdownBy = By.tagName("select");
    
    public static final By  licenseState = By.id("licenseState");

    /**
     * Asserts that the expected title is displayed on the page
     *
     * @param expectedTitle The expected title of the page
     */
    public void assertPageTitleOvc(String expectedTitle) {
        LOGGER.info("assertPageTitleOvc started");
        String actualTitle = webDriver.getTitle();
        Assert.assertEquals("FAIL: Expected title: \"" + expectedTitle + "\", Actual title: \"" + actualTitle + "\"",
                expectedTitle, actualTitle);
        LOGGER.info("Confirmed that the page title is \"" + expectedTitle + "\".");
        LOGGER.info("assertPageTitleOvc completed");
    }

    /**
     * Selects the page in the Global Header
     *
     * @param icon Icon to click in the header
     */
    public void selectGlobalHeader(String icon) {
        LOGGER.info("selectGlobalHeader started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        WebElement header = returnGlobalHeader(icon);
        header.click();
        driver.waitForPageToLoad();
        LOGGER.info("selectGlobalHeader completed");
    }

    /**
     * Returns Global Header WebElement
     *
     * @icon Text of the icon in Global Header to return
     * returns WebElement
     */
    public WebElement returnGlobalHeader(String icon) {
        LOGGER.info("returnGlobalHeader started");
        WebElement iconToClick = null;

        if (icon.equalsIgnoreCase(ConstantsOvc.HOME)) {
            iconToClick = homeIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.CSL)) {
            iconToClick = cslIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.ALERTS)) {
            iconToClick = alertsIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.APPOINTMENT)) {
            iconToClick = appointmentIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.LOOKUP)) {
            iconToClick = lookupIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.CUSTOMER)) {
            iconToClick = customerIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.AIR_CHECK)) {
            iconToClick = aircheckIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.ENDLESS_AISLE)) {
            iconToClick = endlessAisleIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.FITMENT)) {
            iconToClick = fitmentIcon;
        } else if (icon.equalsIgnoreCase(ConstantsOvc.VTV)) {
            iconToClick = vtvIcon;
        }

        LOGGER.info("returnGlobalHeader completed");
        return iconToClick;
    }

    /**
     * Conducts a vehicle search by selecting specified values from dropdown controls
     *
     * @param year     The year value to select
     * @param make     The make value to select
     * @param model    The model value to select
     * @param trim     The trim value to select
     * @param assembly The assembly value to select
     * @throws Exception Exception
     */
    public void vehicleSearch(String year, String make, String model,
                              String trim, String assembly) throws Exception {
        LOGGER.info("vehicleSearch started");
        try {

            driver.waitForPageToLoad();

            WebElement yearDrop = driver.getDisplayedElement(fitmentYearBy, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(yearDrop, year);

            WebElement makeDrop = driver.getDisplayedElement(fitmentMakeBy, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(makeDrop, make);

            WebElement modelDrop = driver.getDisplayedElement(fitmentModelBy, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(modelDrop, model);

            WebElement trimDrop = driver.getDisplayedElement(fitmentTrimBy, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(trimDrop, trim);

            WebElement assemblyDrop = driver.getDisplayedElement(fitmentAssemblyBy, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(assemblyDrop, assembly);

        } catch (Exception e) {
            Assert.fail("FAIL: Vehicle search FAILED with error: " + e);
        }
        LOGGER.info("vehicleSearch completed");
    }

    /**
     * Enters vehicle search criteria by filling in each field
     *
     * @param year     The year value to select
     * @param make     The make value to select
     * @param model    The model value to select
     * @param trim     The trim value to select
     * @param assembly The assembly value to select
     * @throws Exception Exception
     */
    public void enterVehicleSearchCriteria(String year, String make, String model,
                                           String trim, String assembly) throws Exception {
        LOGGER.info("enterVehicleSearchCriteria started");
        try {
            WebElement yearDrop = getDisplayedElementWithPlaceholder(inputTagBy, YEAR);
            yearDrop.sendKeys(year);

            WebElement makeDrop = getDisplayedElementWithPlaceholder(inputTagBy, MAKE);
            makeDrop.sendKeys(make);

            WebElement modelDrop = getDisplayedElementWithPlaceholder(inputTagBy, MODEL);
            modelDrop.sendKeys(model);

            WebElement trimDrop = getDisplayedElementWithPlaceholder(inputTagBy, TRIM);
            trimDrop.sendKeys(trim);

            WebElement assemblyDrop = getDisplayedElementWithPlaceholder(inputTagBy, ASSEMBLY);
            assemblyDrop.sendKeys(assembly);

        } catch (Exception e) {
            Assert.fail("FAIL: Entering vehicle search FAILED with error: " + e);
        }
        LOGGER.info("enterVehicleSearchCriteria completed");
    }

    /**
     * Grabs a list of elements and returns the one with the specific attribute text
     *
     * @param element   By selector of element
     * @param attribute Attribute of element
     * @param text      Text element should contain
     * @return WebElement The WebElement to return
     */
    public WebElement getOVCElementWithAttribute(String page, By element, String attribute, String text) {
        LOGGER.info("getOVCElementWithAttribute started");
        WebElement returnPage = parentElementsPage.returnPageObjectElement(page);
        WebElement returnElement = null;

        List<WebElement> objects = returnPage.findElements(element);

        for (WebElement object : objects) {
            if (object.getAttribute(attribute).equalsIgnoreCase(text)) {
                boolean displayed = driver.isElementDisplayed(object, Constants.ONE_SEC_WAIT);
                if (displayed) {
                    LOGGER.info("String '" + text + "' matched with rendered  ==>"
                            + object.getAttribute(attribute));
                    returnElement = object;
                    break;
                }
            }
        }

        LOGGER.info("getOVCElementWithAttribute completed");
        return returnElement;
    }

    /**
     * Returns OVC element among multiple that contains text substring, if substring not found
     * returns null
     *
     * @param elementBy The By element to build list with
     * @param value     The value to search for with element
     * @return WebElement
     */
    public WebElement getOVCElementWithText(WebElement page, By elementBy, String value) {
        LOGGER.info("getOVCElementWithText started looking for OVC element \"" +
                elementBy + "\" with value \"" + value + "\"");
        WebElement returnElement = null;
        List<WebElement> elements;

        elements = page.findElements(elementBy);

        try {
            for (WebElement element : elements) {
                if (element.getText().toLowerCase().contains(value.toLowerCase())) {
                    returnElement = element;
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            returnElement = null;
        }
        LOGGER.info("getOVCElementWithText completed looking for OVC element \"" +
                elementBy + "\" with value \"" + value + "\"");
        return returnElement;
    }

    /**
     * Finds an element via text value and clicks it
     *
     * @param page Page the element is located on
     * @param text Text of the element to find
     */
    public void selectButtonWithText(String page, String text) {
        LOGGER.info("selectButtonWithText started");
        // TODO: Uncomment after Defect_ID 9430 is resolved
        //   driver.waitForElementClickable(alertsIcon);
        driver.waitForMilliseconds(Constants.FIVE_HUNDRED_MILLISEC_WAIT);

        // TODO: Wait time added for inconsistent credit card functionality
        if ((page.equalsIgnoreCase(ConstantsOvc.PRINTER)) || (text.equalsIgnoreCase(ConstantsOvc.CHECKOUT))
                || (page.equalsIgnoreCase(ConstantsOvc.LOOKUP) && text.equalsIgnoreCase(ConstantsOvc.CLOSE))) {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        }

        WebElement button;
        WebElement pageEle = parentElementsPage.returnPageObjectElement(page);

        button = getOVCElementWithText(pageEle, buttonTagBy, text);
        if (button == null)
            button = getOVCElementWithAttribute(page, inputTagBy, Constants.VALUE, text);

        try {
            button.click();
        } catch (Exception e) {
            driver.jsScrollToElementClick(button);
        }

        if (text.equalsIgnoreCase(ConstantsOvc.CHECKOUT)) {
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        }

        driver.waitForPageToLoad();
        driver.waitForElementNotVisible(loader);
        LOGGER.info("selectButtonWithText completed");
    }

    /**
     * Verifies a button element is displayed via text value
     *
     * @param text Text of the element to find
     */
    public void verifyButtonWithText(String page, String text) {
        LOGGER.info("verifyButtonWithText started");
        driver.waitForElementClickable(homeIcon);
        driver.waitForMilliseconds();
        WebElement button;
        WebElement pageEle = parentElementsPage.returnPageObjectElement(page);

        button = getOVCElementWithText(pageEle, buttonTagBy, text);
        if (button == null)
            button = getOVCElementWithAttribute(page, inputTagBy, Constants.VALUE, text);

        if (!button.isDisplayed())
            Assert.fail("FAIL: Expected button: \"" + text + "\" was not displayed on the page \"");
        LOGGER.info("Confirmed: Expected button: \"" + text + "\" was displayed on the page \"");
        LOGGER.info("verifyButtonWithText completed");
    }

    /**
     * Clicks an element based on By and Text
     *
     * @param by   By element
     * @param text Text of the element
     */
    public void selectListElementWithText(By by, String text) {
        LOGGER.info("selectElementWithText started");
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        WebElement displayedList = driver.getDisplayedElement(by, Constants.THREE_SEC_WAIT);

        List<WebElement> items = displayedList.findElements(listItemsBy);
        for (WebElement item : items) {
            if (item.getText().contains(text)) {
                item.click();
                break;
            }
        }
        LOGGER.info("selectElementWithText completed");
    }

    /**
     * Verifies the text of the element
     *
     * @param element      The WebElement to check
     * @param expectedText The text of the element to verify
     */
    public void verifyElementText(By element, String expectedText) {
        LOGGER.info("verifyElementText started");
        driver.waitForMilliseconds();
        driver.waitForElementVisible(element);
        String actualText = driver.getElementWithText(element, expectedText).getText();
        Assert.assertTrue("FAIL: Text expected: \"" + expectedText + "\", actual text: \"" + actualText + "\".",
                actualText.contains(expectedText));
        LOGGER.info("Confirmed that \"" + expectedText + "\" was displayed .");
        LOGGER.info("verifyElementText completed");
    }

    /**
     * Grabs a list of elements and returns the one with the specific attribute text
     *
     * @param element By selector of element
     * @param text    Text element should contain
     * @return WebElement The WebElement to return
     */
    public WebElement getDisplayedElementWithPlaceholder(By element, String text) {
        LOGGER.info("getDisplayedElementWithPlaceholder started");

        List<WebElement> objects = webDriver.findElements(element);
        WebElement returnElement = null;

        for (WebElement object : objects) {
            if (object.getAttribute(PLACEHOLDER).equalsIgnoreCase(text) && object.isDisplayed()) {
                LOGGER.info("String '" + text + "' matched with rendered  ==>"
                        + object.getAttribute(PLACEHOLDER));
                returnElement = object;
                break;
            }
        }

        LOGGER.info("getDisplayedElementWithPlaceholder completed");
        return returnElement;
    }

    /**
     * Enters text into the provided text box
     *
     * @param text    Text to enter
     * @param textbox Placeholder text of the box itself
     */
    public void enterTextIntoInputBox(String text, String textbox) {
        LOGGER.info("enterTextIntoBox started");
        driver.waitForMilliseconds();
        WebElement inputBox = getDisplayedElementWithPlaceholder(inputTagBy, textbox);
        inputBox.clear();
        inputBox.sendKeys(text);
        LOGGER.info("enterTextIntoBox completed");
    }

    /**
     * Enters text into the provided text box, waits, then sends Enter key
     *
     * @param text    Text to enter
     * @param textbox Placeholder text of the box itself
     */
    public void enterTextIntoInputBoxWithEnter(String text, String textbox) {
        LOGGER.info("enterTextIntoInputBoxWithEnter started");
        driver.waitForMilliseconds();
        WebElement inputBox = getDisplayedElementWithPlaceholder(inputTagBy, textbox);
        inputBox.clear();
        inputBox.sendKeys(text);
        driver.waitForMilliseconds();
        inputBox.sendKeys(Keys.ENTER);
        driver.waitForPageToLoad();
        LOGGER.info("enterTextIntoInputBoxWithEnter completed");
    }

    /**
     * Helper method to access driver method, clicking a page link with text
     *
     * @param linkText Text of link to click
     */
    public void selectLinkWithText(String linkText) {
        LOGGER.info("clickPageLink " + linkText + " started");
        driver.clickElementWithLinkText(linkText);
        driver.waitForPageToLoad();
        LOGGER.info("clickPageLink " + linkText + " complete");
    }

    /**
     * Selects the specified option from the right pane navigation bar (Main, Checkout, etc. )
     *
     * @param option Text of the option to select / click
     */
    public void selectOptionFromRightPaneNavBar(String option) {
        LOGGER.info("selectOptionFromRightPaneNavBar started w/ option:'" + option + "'");
        driver.waitForElementVisible(rightPaneSubMenuBy);

        List<WebElement> navBarOptionList = webDriver.findElements(navBarOptionBy);

        for (WebElement navOption : navBarOptionList) {
            if (navOption.getText().toLowerCase().contains(option.toLowerCase())) {
                LOGGER.info("Nav option found containing text: '" + option + "'!");
                navOption.click();
                break;
            }
        }

        driver.waitForPageToLoad();
        LOGGER.info("selectOptionFromRightPaneNavBar completed w/ option:'" + option + "'");
    }

    /**
     * Verifies the specified text on the right pane navigation title
     *
     * @param title Text of the title to verify
     */
    public void assertRightPaneNavTitle(String title) {
        LOGGER.info("assertRightPaneNavTitle started w/ title:'" + title + "'");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        boolean found = false;

        List<WebElement> navBarList = webDriver.findElements(navBarBy);

        for (WebElement navTitle : navBarList) {
            if (navTitle.getText().toLowerCase().contains(title.toLowerCase())) {
                Assert.assertTrue("FAIL: Right pane title " + title + " was not found!",
                        navTitle.getText().trim().contains(title));
                found = true;
                break;
            }
        }

        if (!found)
            Assert.fail("FAIL: Title '" + title + "' was NOT found on the page!");

        driver.waitForPageToLoad();
        LOGGER.info("assertRightPaneNavTitle completed w/ title:'" + title + "'");
    }

    /**
     * Verifies a page header in OVC matches the provided value
     *
     * @param pageHeader Expected value of the page header
     */
    public void verifyPageHeaderOvc(String pageHeader) {
        LOGGER.info("verifyPageHeaderOvc started looking for page header: '" + pageHeader + "'");
        driver.waitForPageToLoad();
        WebElement headerEle = driver.getDisplayedElement(pageHeaderBy, Constants.ONE_SEC_WAIT);
        Assert.assertTrue("FAIL: Expected page header was - '" + pageHeader + "' but was actually - '"
                + headerEle.getText().trim() + "'!", headerEle.getText().trim().equalsIgnoreCase(pageHeader.trim()));
        LOGGER.info("verifyPageHeaderOvc completed and found page header: '" + pageHeader + "'");
    }

    /**
     * Finds an element via text value and Verify its displayed
     *
     * @param page Page the element is located on
     * @param text Text of the element to find
     */
    public void verifyButtonElementIsDisplayed(String page, String text) {
        LOGGER.info("verifyButtonElementIsDisplayed started");
        driver.waitForElementClickable(homeIcon);
        driver.waitForMilliseconds();
        WebElement button;

        button = driver.getElementWithText(buttonTagBy, text);
        if (button == null)
            button = getOVCElementWithAttribute(page, inputTagBy, Constants.VALUE, text);
        Assert.assertTrue("FAIL: Button Element \"" + text + "\", was not displayed", button.isDisplayed());
        driver.waitForPageToLoad();
        LOGGER.info("verifyButtonElementIsDisplayed completed");
    }

    /**
     * Verifies the header title of a popup window
     *
     * @param text Text of the popup header to verify
     */
    public void assertPopupTextContains(String text) {
        LOGGER.info("assertPopupTextContains started");
        driver.waitForPageToLoad();
        WebElement popupDialog = driver.getDisplayedElement(popupModalBy, Constants.ONE_SEC_WAIT);
        if (popupDialog == null) {
            popupDialog = driver.getDisplayedElement(popupModalBy, Constants.ONE_SEC_WAIT);
        }
        Assert.assertTrue("FAIL: Expected popup header to be - '" + text + "' but was actually - '"
                + popupDialog.getText().trim() + "'!", popupDialog.getText().trim().contains(text.trim()));
        LOGGER.info("assertPopupTextContains completed");
    }

    /**
     * Clicks the 'fa-close' element
     */
    public void selectCloseElement() {
        LOGGER.info("selectCloseElement started");
        driver.waitForMilliseconds();
        WebElement close = driver.getDisplayedElement(closeBy, Constants.ONE_SEC_WAIT);
        close.click();
        LOGGER.info("selectCloseElement completed");
    }

    /**
     * Verifies that a dialog window contains specific text
     *
     * @param text Text of the dialog to verify
     */
    public void assertDialogTextContains(String text) {
        LOGGER.info("assertDialogTextContains started");
        driver.waitForPageToLoad();
        WebElement dialog = driver.getDisplayedElement(dialogModalBy, Constants.ONE_SEC_WAIT);
        Assert.assertTrue("FAIL: Expected dialog to contain text '" + text + "', contained text was:'"
                + dialog.getText().trim() + "'!", dialog.getText().trim().contains(text.trim()));
        LOGGER.info("assertDialogTextContains completed");
    }

    /**
     * Enters Price into Flat Repair dialog window
     *
     * @param amount Amount to enter into dialog
     */
    public void enterFlatRepairDialogPrice(String amount) {
        LOGGER.info("enterFlatRepairDialogPrice started");
        driver.waitForPageToLoad();
        WebElement dialog =
                driver.getDisplayedElement(dialogModalBy, Constants.ONE_SEC_WAIT).findElement(flatRepairInputField);
        dialog.click();
        dialog.sendKeys(amount);
        LOGGER.info("enterFlatRepairDialogPrice completed");
    }

    /**
     * Verifies that a dialog window contains specific text with waiting for loader to disappear
     *
     * @param text Text of the dailog to verify
     */
    public void assertDialogTextContainsWithLoaderWait(String text) {
        LOGGER.info("assertDialogTextContainsWithLoaderWait started");
        driver.waitForPageToLoad();
        driver.waitForElementNotVisible(loader);
        WebElement dialog = driver.getDisplayedElement(dialogModalBy, Constants.ONE_SEC_WAIT);
        Assert.assertTrue("FAIL: Expected dialog to contain text '" + text + "', contained text was:'"
                + dialog.getText().trim() + "'!", dialog.getText().trim().contains(text.trim()));
        LOGGER.info("assertDialogTextContainsWithLoaderWait completed");
    }

    /**
     * Selects a radio button with the specified label
     *
     * @param page  Page the element is located on
     * @param label The label of the radio button to select
     */
    public void selectRadioButton(String label, String page) {
        LOGGER.info("selectRadioButton started");

        // TODO: Method needs to be refactored and broken out to specific pages
        int index = 0;
        List<WebElement> radioButtons;
        WebElement radioByLabelLower = getOVCElementWithAttribute(page, inputTagBy, Constants.VALUE, label.toLowerCase());
        WebElement radioByLabelUpper = getOVCElementWithAttribute(page, inputTagBy, Constants.VALUE, label.toUpperCase());

        if (driver.isElementDisplayed(radioButtonCashManagementBy, Constants.ONE_SEC_WAIT)) {
            radioButtons = webDriver.findElements(radioButtonCashManagementBy);
            List<WebElement> radioButtonLabels = webDriver.findElements(radioButtonLabelBy);

            for (WebElement radioButton : radioButtons) {
                if (radioButtonLabels.get(index).getText().contains(label)) {
                    radioButton.click();
                    break;
                }
                index++;
            }

        } else if (radioByLabelLower != null) {
            radioByLabelLower.click();
        } else if (radioByLabelUpper != null) {
            radioByLabelUpper.click();
        } else {
            WebElement returnPage = parentElementsPage.returnPageObjectElement(page);
            List<WebElement> listRows = returnPage.findElements(liTagBy);

            if (listRows.isEmpty()) {
                listRows = returnPage.findElements(labelTagBy);
                for (WebElement listRow : listRows) {
                    if (listRow.isDisplayed()) {
                        if (listRow.getText().contains(label)) {
                            listRow.click();
                            break;
                        }
                    }
                }
            } else {
                // Added for Fitment page radio button selection
                for (WebElement listRow : listRows) {
                    if (listRow.findElement(labelTagBy).getText().contains(label)) {
                        listRow.findElement(inputTagBy).click();
                        break;
                    }
                }
            }
        }
        LOGGER.info("selectRadioButton completed");
    }

    /**
     * Verifies no popup is displayed
     *
     * @param page Page the element is located on
     * @param text Text to check for
     */
    public void assertPopupClosed(String page, String text) {
        LOGGER.info("assertPopupClosed started");
        WebElement popup;
        popup = getOVCElementWithAttribute(page, inputTagBy, Constants.VALUE, text);
        Assert.assertTrue("FAIL: Popup with text \"" + text + "\" was still displayed!", popup == null);
        LOGGER.info("assertPopupClosed completed");
    }


    /**
     * Enters text into a popup with a single input field
     *
     * @param inputValue The value to enter into the input field
     */
    public void enterTextSingleInputField(String inputValue) {
        LOGGER.info("enterTextSingleInputField started");
        driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        WebElement input = driver.getDisplayedElement(inputTagBy, Constants.ONE_SEC_WAIT);
        input.click();
        input.clear();
        input.sendKeys(Keys.ARROW_RIGHT);
        input.sendKeys(inputValue);
        LOGGER.info("enterTextSingleInputField completed");
    }

    /**
     * Verifies single text input field contains a value
     *
     * @param inputValue The value to verify in the input field
     */
    public void assertTextSingleInputFieldContains(String inputValue) {
        LOGGER.info("assertTextSingleInputFieldContains started");
        WebElement input = driver.getDisplayedElement(inputTagBy, Constants.ONE_SEC_WAIT);
        Assert.assertTrue("FAIL: Popup text field did not contain text " + inputValue + ". Instead was " +
                input.getText(), input.getAttribute("value").equalsIgnoreCase(inputValue));
        LOGGER.info("assertTextSingleInputFieldContains completed");
    }

    /**
     * Checks if text is displayed
     *
     * @param text The string text to check
     */
    public void assertTextPresentInPage(String text) {
        LOGGER.info("assertTextPresentInPage started with text \"" + text + "\"");
        driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        Assert.assertTrue("FAIL: Text: \"" + text + "\" - was NOT present!",
                driver.isTextPresentInPageSource(text));
        LOGGER.info("Text: \"" + text + "\" - was present");
        LOGGER.info("assertTextPresentInPage completed with text \"" + text + "\"");
    }

    /**
     * Verifies that an element contains specific text
     *
     * @param by   By selector for element
     * @param text Text of the element to verify
     */
    public void assertElementTextContains(By by, String text) {
        LOGGER.info("assertElementTextContains started");
        driver.waitForPageToLoad();
        if (!driver.checkIfElementContainsTextLowerCase(by, text)) {
            Assert.fail("Element with text: '" + text + "'not found on the page");
        }
        LOGGER.info("assertElementTextContains completed");
    }

    /**
     * Finds an element via text value and clicks it
     *
     * @param text Text of the element to find
     */
    public void selectElementWithText(By by, String text) {
        LOGGER.info("selectElementWithText started");
        driver.waitForElementClickable(homeIcon);
        driver.waitForMilliseconds();
        driver.clickElementWithText(by, text);
        driver.waitForPageToLoad();
        LOGGER.info("selectElementWithText completed");
    }

    /**
     * Verify Input-field Element is displayed or not
     *
     * @param textbox Placeholder text of the box itself
     */
    public void verifyInputBoxElementDisplayed(String textbox) {
        LOGGER.info("verifyInputBoxElementDisplayed started");
        driver.waitForMilliseconds();
        WebElement textBoxElement = getDisplayedElementWithPlaceholder(inputTagBy, textbox);
        Assert.assertTrue("FAIL: Text box element \"" + textbox + "\", was not displayed", textBoxElement.isDisplayed());
        LOGGER.info("verifyInputBoxElementDisplayed completed");
    }

    /**
     * Converts a time in either 24 or 12 hour notation to the other. e.g. 18:00 to 12 hour notation of 6:00 PM OR
     * 6:00 PM (12hr) to 18:00 (24hr)
     *
     * @param time                        24 hour notation time, with AM / PM to be converted
     * @param convertToTwentyFourHrFormat True to convert a 12 hr time to the 24 hr equivalent. False to do the opposite.
     * @return 12 or 24 hour notation variant, with AM / PM,  of the provided time
     */
    public String convertHourTime(String time, boolean convertToTwentyFourHrFormat) {
        SimpleDateFormat date12Format = new SimpleDateFormat("h:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm a");

        try {
            if (convertToTwentyFourHrFormat) {
                time = date24Format.format(date12Format.parse(time));
            } else {
                time = date12Format.format(date24Format.parse(time));
            }
        } catch (ParseException e) {
            LOGGER.info("Unable to parse the provided time string! Exception: " + e);
        }
        return time;
    }

    /**
     * Asserts that a certain page is displayed
     *
     * @param page page you would like to assert is displayed
     */
    public void assertPageDisplayed(String page) {
        LOGGER.info("assertPageDisplayed started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        WebElement pageElement = parentElementsPage.returnPageObjectElement(page);
        Assert.assertTrue("FAIL: The page\"" + page + "\' was not displayed.",
                pageElement.isDisplayed());
        LOGGER.info("assertPageDisplayed completed");
    }

    /**
     * Clicks the 'div' tag with provided text
     *
     * @param page Page of the element
     * @param text Text value of the div element
     */
    public void selectDivTagElement(String page, String text) {
        LOGGER.info("selectDivTagElement started");
        driver.waitForPageToLoad();
        WebElement returnPage = parentElementsPage.returnPageObjectElement(page);
        List<WebElement> divs = returnPage.findElements(divTagBy);
        for (WebElement div : divs) {
            if (div.getText().equalsIgnoreCase(text)) {
                div.click();
                break;
            }
        }
        LOGGER.info("selectDivTagElement completed");
    }

    /**
     * Clicks the row in the specified table with given text
     *
     * @param page      The page to select the row from
     * @param knownText Text that is known to be present to facilitate waiter
     * @param text      Text of the row
     */
    public void selectTableRow(String page, String knownText, String text) {
        LOGGER.info("selectTableRow started");
        boolean foundRow = false;

        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        WebElement pageElement = parentElementsPage.returnPageObjectElement(page);

        try {
            driver.waitForElementVisible(getOVCElementWithText(pageElement, CommonActions.tableHeaderBy, knownText));
        } catch (Exception e) {
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
            driver.waitForElementVisible(getOVCElementWithText(pageElement, CommonActions.tableHeaderBy, knownText));
        }

        WebElement table = driver.getDisplayedElement(tableBy, Constants.ONE_SEC_WAIT);
        List<WebElement> rows = table.findElements(trBy);

        for (WebElement row : rows) {
            if (row.getText().contains(text)) {
                foundRow = true;
                row.click();
                break;
            }
        }

        if (!foundRow)
            Assert.fail("FAIL: Row with text \"" + text + "\" was not found for page \"" + page + "\"");

        LOGGER.info("selectTableRow completed");
    }

    /**
     * Verifies that a dialog window contains specific text
     *
     * @param text Text of the dialog window to verify
     */
    public void assertDialogWindowContains(String text) {
        LOGGER.info("assertDialogWindowContains started");
        driver.waitForPageToLoad();
        WebElement dialog = driver.getDisplayedElement(popupContainerModalBy, Constants.ONE_SEC_WAIT);
        Assert.assertTrue("FAIL: Expected dialog to contain text '" + text + "', contained text was:'"
                + dialog.getText().trim() + "'!", dialog.getText().trim().contains(text.trim()));
        LOGGER.info("assertDialogWindowContains completed");
    }

    /**
     * Switches frame contexts between iframe and parent
     *
     * @param toFrom Switch 'To' or 'From' iframe context
     * @param frame  WebElement of iframe
     */
    public void switchFrameContext(WebElement frame, String toFrom) {
        LOGGER.info("switchFrameContext started");
        driver.switchFrameContext(frame, toFrom);
        LOGGER.info("switchFrameContext completed");
    }

    /**
     * Finds element via passed in By. Then sends a click to 10 pixels below the bottom.
     *
     * @param by By selector to find element
     */
    public void selectElementWithOffset(By by) {
        LOGGER.info("selectElementWithOffset started");
        driver.waitForPageToLoad();
        driver.clickElementYCoordinateOffset(by);
        LOGGER.info("selectElementWithOffset started");
    }

    /**
     * Selects single On/Off toggle switch inside of CSL Options popup
     */
    public void toggleOnOffSwitchOnCSLOptionsPopup() {
        LOGGER.info("toggleOnOffSwitchOnCSLOptionsPopup started");
        driver.waitForPageToLoad();
        webDriver.findElement(cslSendTextMessageToggle).click();
        LOGGER.info("toggleOnOffSwitchOnCSLOptionsPopup completed");
    }

    /**
     * Selects the populated phone number on the popup
     */
    public void selectPhoneNumberCSLPopup() {
        LOGGER.info("selectPhoneNumberCSLPopup started");
        driver.waitForPageToLoad();

        if (cslMobileNumber.isDisplayed()) {
            cslMobileNumber.click();
        } else {
            LOGGER.info("No phone number entered. Adding one now.");
            WebElement phoneInput = getDisplayedElementWithPlaceholder(inputTagBy, ENTER_PHONE_NUMBER);
            phoneInput.click();
            phoneInput.clear();
            phoneInput.sendKeys(ConstantsOvc.BACKUP_PHONE);
            phoneInput.sendKeys(Keys.ENTER);
            cslMobileNumber.click();
        }

        LOGGER.info("selectPhoneNumberCSLPopup completed");
    }   
    
    /**
     * Conducts a vehicle search by selecting specified values from dropdown controls
     *
     * @param year     The year value to select
     * @param make     The make value to select
     * @param model    The model value to select
     * @throws Exception Exception
     */
    public void vehicleSearchYMM(String year, String make, String model) throws Exception {
        LOGGER.info("vehicleSearchYMM started");
        try {
            driver.waitForPageToLoad();

            WebElement yearDrop = driver.getDisplayedElement(fitmentYearBy, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(yearDrop, year);

            WebElement makeDrop = driver.getDisplayedElement(fitmentMakeBy, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(makeDrop, make);

            WebElement modelDrop = driver.getDisplayedElement(fitmentModelBy, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(modelDrop, model);

        } catch (Exception e) {
            Assert.fail("FAIL: Vehicle search FAILED with error: " + e);
        }
        LOGGER.info("vehicleSearchYMM completed");
    }
    
    /**
     * Conducts a license state search by selecting specified values from dropdown control
     *
     * @param state     The state value to select
     * @throws Exception Exception
     */
    public void licenseStateSearch(String state) throws Exception {
        LOGGER.info("licenseStateSearch started");
        try {
            driver.waitForPageToLoad();

            WebElement licenseDrop = driver.getDisplayedElement(licenseState, Constants.ONE_SEC_WAIT);
            driver.selectFromDropdownByVisibleText(licenseDrop, state);

        } catch (Exception e) {
            Assert.fail("FAIL: State search FAILED with error: " + e);
        }
        LOGGER.info("licenseStateSearch completed");
    }

    /**
     * Returns a randomly generated 5 digit number
     */
    public int returnRandomlyGenerated5DigitNumber() {
        LOGGER.info("returnRandomlyGenerated5DigitNumber started");
        Random rand = new Random();
        int numberToReturn = rand.nextInt(99999) + 1;
        LOGGER.info("returnRandomlyGenerated5DigitNumber completed");
        return numberToReturn;
    }

    /**
     * Navigate to passed in URL
     *
     * @param URL Path to the dashboard
     */
    public void navigateToUrlViaPath(String URL) {
        LOGGER.info("navigateToMyStoreThroughUrlPath started");
        driver.getUrl(URL);
        LOGGER.info("navigateToMyStoreThroughUrlPath completed");
    }

    /**
     * Select and clicks an element by the partial link text passed in
     * (5 sec. wait has been added for OVC Dashboard functionality)
     *
     * @param linkText Partial text of a link to click
     */
    public void selectElementWithPartialLinkText(String linkText) {
        LOGGER.info("selectElementWithPartialLinkText started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        driver.clickElementByPartialText(linkText);
        LOGGER.info("selectElementWithPartialLinkText completed");
    }
}