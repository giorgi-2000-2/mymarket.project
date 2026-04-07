package org.example;
import org.example.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected static WebDriver driver;
    public WebDriverWait wait;


    public BasePage (WebDriver driver){
        this.driver= driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getlong("wait")));
        PageFactory.initElements(driver,this);
    }
    public void refreshPage(){
        driver.navigate().refresh();
    }
    public void Sendkeys (WebElement locator, String text){
        waitelementtobevisible(locator);
        locator.sendKeys(text);
    }


    public void waitelementtoboclickable (WebElement locator){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitelementtobevisible (WebElement locator){
        WebElement element = wait.until(ExpectedConditions.visibilityOf( locator));
    }

    public void Click (WebElement locator){
        waitelementtoboclickable(locator);
        waitelementtobevisible(locator);

        locator.click();
    }

    public String Gettext(WebElement locator){
        waitelementtobevisible(locator);
        return locator.getText();

    }
    public String getcurrentURL(){
        return driver.getCurrentUrl();
    }
    public void scroll(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

    }

}
