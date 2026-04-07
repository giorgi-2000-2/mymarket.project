package org.example;
import com.aventstack.extentreports.ExtentTest;
import org.example.pages.AdvertisementPage;
import org.example.pages.LoginPage;
import org.example.utils.AssertHelpperManager;
import org.example.utils.DriverManager;
import org.example.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.example.utils.ExtentReportManager.getTest;


public class BaseTest {
    protected static WebDriver driver;
    @BeforeMethod
    public void setUp(){
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        advertisementPage.CloseExtraContent();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement dialog = driver.findElement(By.xpath("/html/body/dialog[2]"));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].close();", dialog
            );
            js.executeScript("document.querySelector('dialog').close();");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quit();

    }

    public void assertstring(String act, String exp){

        if (act.equals(exp)) {
            getTest().pass(( act +" და " + exp + "— შედარება წარმატებულია "));
        } else {
            getTest().fail(act +" და " + exp + "— შედარება წარუმატებელია ");
        }
        Assert.assertEquals(act,exp);
    }

public void loginAndNavigate(){
    LoginPage loginPage = new LoginPage(driver);
    SoftAssert softAssert = new SoftAssert();
    AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
    AdvertisementPage advertisementPage = new AdvertisementPage(driver);
    loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
    assertHelpperManager.navigateToAdvertisementPage(softAssert);

}

    }


