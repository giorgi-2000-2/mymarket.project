package org.example.pages;
import org.example.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class AdvertisementPage extends BasePage {
    @FindBy(xpath = "/html/body/div[2]/main/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div[2]/div/div/div[2]/div")
    public List<WebElement> mainElements;
    @FindBy(xpath = "//*[@id=\"content\"]")
    WebElement Extracontent;
    @FindBy(xpath = "/html/body/div[2]/main/header/nav/div/div[3]/div[1]/a")
    public WebElement advertisementBtn;
    @FindBy(xpath = "(//div[@class='font-bold font-size-16 text-truncate user-name'][contains(text(),'გიორგი მიქელაძე')])[2]")
    WebElement username;
    @FindBy(xpath = "(//div[contains(@class,'d-flex align-items-center')])[4]")
    WebElement usernameBtn;
    @FindBy(xpath = "(//div[@class='font-bold font-size-16 text-truncate user-name'][contains(text(),'გიორგი მიქელაძე')])[1]")
    WebElement getusername;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div[2]/div/div/div[1]/div[1]")
    public WebElement dropdowncategory;
    @FindBy(xpath = "//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]")
    public WebElement title;
    @FindBy(xpath = "(//label[contains(text(),'გაყიდვა')])[1]")
    WebElement sellbtn;
    @FindBy(xpath = "(//label[contains(text(),'შეძენა')])[1]")
    WebElement buybtn;
    @FindBy(xpath = "(//label[contains(text(),'გაქირავება')])[1]")
    WebElement rentbtn;
    @FindBy(xpath = "(//label[contains(text(),'მომსახურება')])[1]")
    WebElement servicebtn;
    @FindBy(xpath = "//*[contains(@id,'react-select-') and contains(@id,'-placeholder')]")
    public WebElement branddropdown;
    @FindBy(xpath = "//*[@id=\"BrandID\"]/div/div/div[1]/div[2]")
    public WebElement Finddropdownbrand;
    @FindBy(xpath = "//span[contains(@class,'pr-preview-title')]")
    WebElement titletxt;
@FindBy(xpath = "(//h1[contains(text(),'განცხადების დამატება')])[1]")
public WebElement mainTitle;
@FindBy(xpath = "(//div[@class='font-bold font-size-16 text-truncate user-name'][contains(text(),'გიორგი მიქელაძე')])[2]")
public WebElement pageusername;
@FindBy(xpath = "(//div[contains(text(),'ID 9060160')])[2]")
public WebElement usernameID;
@FindBy(xpath = "//*[@id=\"content\"]/button")
        WebElement Extracontentclosebutton;
@FindBy(xpath = "//*[@id=\"content\"]/div[3]/button")
        WebElement Extracookiebutton;

    public AdvertisementPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void CloseExtraContent() {
        try {
            if (Extracontent.isDisplayed()) {
                waitClick(Extracontentclosebutton);
            }


        } catch (Exception e) {
        }
        try {
            if (Extracookiebutton.isDisplayed()){
                waitClick(Extracookiebutton);
            }

        } catch (Exception e) {
        }


    }

    public String usernameCheck() {
        return username.getText();
    }

    public String getUserNamecheck() {
        scroll(usernameBtn);
        Click(usernameBtn);
        return getusername.getText();
    }

    public List<WebElement> createList() {

         wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By optionLocator = By.xpath("//div[contains(@id,'react-select-3-option-')]");
        List<WebElement> options = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(optionLocator)
        );
        return options;


    }

    public static void Scroll(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

    }

    public boolean checkSellBTNIfClickable() {
        return sellbtn.isEnabled();
    }

    public void clickSellBTN() {
        Click(sellbtn);
    }

    public void clickRent() {
        Click(rentbtn);
    }

    public void clickService() {
        Click(servicebtn);
    }

    public boolean checkBuyBTNIfClickable() {
        return buybtn.isEnabled();
    }

    public void clickBuyBTN() {
        Click(buybtn);
    }

    public boolean checkRentBTNIfClickable() {
        return rentbtn.isEnabled();
    }

    public boolean checkServiceBTNIfClickable() {
        return servicebtn.isEnabled();
    }

    public boolean ClickDropdown() {
        boolean pass = dropdowncategory.isEnabled();
        if (pass) {
            Click(dropdowncategory);
        }
        return pass;
    }

    public List<WebElement> createBrandList() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<WebElement> optionsList = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//*[contains(@id,'react-select') and contains(@id,'-option')]")
                )
        );
        return optionsList;
    }

    public void backClick() {
        createList();
        scroll(createList().get(0));
        waitClick(createList().get(0));
    }

    public void waitString(WebElement element) {
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, "აირჩიე/ჩაწერე კატეგორია")
        ));
    }

    public String getTextTitle(){
        try{
      return titletxt.getText();} catch (Exception e) {
            return "";
        }
    }

    public void clickDropdownCategory() {
        scroll(dropdowncategory);
        waitClick(dropdowncategory);

    }

    public void waitClick(WebElement element) {
         wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> {
            Map<String, Object> rect = (Map<String, Object>) ((JavascriptExecutor) driver)
                    .executeScript(
                            "var rect = arguments[0].getBoundingClientRect();" +
                                    "return {top: rect.top, bottom: rect.bottom, height: window.innerHeight};",
                            element
                    );
            long top = ((Number) rect.get("top")).longValue();
            long bottom = ((Number) rect.get("bottom")).longValue();
            long height = ((Number) rect.get("height")).longValue();

            return top >= 0 && bottom <= height;
        });
        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
    }

    public String getSplitString(String str){
        String[] arr = str.split(" -> ");
   return arr[arr.length-1];
    }

}











