package ovc.pages;

import common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mginevan on 8/22/2017.
 */
public class MainMenuPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(MainMenuPage.class.getName());

    public MainMenuPage(Driver driver) {
    this.driver = driver;
    webDriver = driver.getDriver();
    PageFactory.initElements(webDriver, this);
    }

    //TODO REFACTOR: based on discussion will update this accordingly

    public static final By orderDetailsContainerBy = By.className("orderDetailsContainer");

    private static final By itemReturnTableRowBy = By.className("ng-scope");

    private static final By itemCheckBoxBy = By.className("fa-square-o");

    private static final By quantityInputBy = By.className("form-control");

    /**
     * takes arguments for a specific item and quantity to select and set quantity to return
     *
     * @param item item number that is being returned
     * @param quantity  quantity of the item to be returned
     */
    public void selectItemReturnCheckBox(String item, String quantity) {
        LOGGER.info("selectItemReturnCheckBox started");
        WebElement container = driver.getDisplayedElement(orderDetailsContainerBy, Constants.ONE_SEC_WAIT);
        List<WebElement> rows = container.findElements(itemReturnTableRowBy);

        for (WebElement row : rows) {
            if (row.getText().contains(item)) {
                WebElement itemCheckBox = row.findElement(itemCheckBoxBy);
                WebElement quantityInput = row.findElement(quantityInputBy);
                itemCheckBox.click();
                quantityInput.click();
                quantityInput.clear();
                quantityInput.sendKeys(quantity);
                break;
            } else {
                LOGGER.info("FAIL: unable to adjust quantity of " + item + " to " + quantity + ".");
            }
        }

        LOGGER.info("selectItemReturnCheckBox completed");
    }

}
