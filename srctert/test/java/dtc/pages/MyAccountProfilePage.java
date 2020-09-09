package dtc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;
import java.util.logging.Logger;

/**
 * Created by @ankitarora on 03/18/18.
 */
public class MyAccountProfilePage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(MyAccountProfilePage.class.getName());

    public MyAccountProfilePage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    private static By myProfileDetailsLabelBy = By.className("my-profile__details-label");
    private static By profileEditLinkBy = By.className("my-profile__details-edit");

    

    /**
     * Click edit link 
     */
    public void clickEditLink(String fieldType) {
        LOGGER.info("clickEditLink started");
        driver.waitForPageToLoad();
        
        WebElement editLabelElement = driver.getElementWithText(myProfileDetailsLabelBy, fieldType);
        driver.getParentElement(editLabelElement).findElement(profileEditLinkBy).click();
        LOGGER.info("clickEditLink completed");
    }
}