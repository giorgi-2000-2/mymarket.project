package org.example.pages;
import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
//elements
@FindBy(xpath = "(//span[@class='font-tbcx-medium text-sm ml-2'])[1]")
WebElement userloginButton;

@FindBy(id = "_R_b8hanpft5t6ivb_")
WebElement username;

@FindBy(id = "_R_j8hanpft5t6ivb_")
WebElement password;

@FindBy (xpath = "(//button[contains(text(),'შესვლა')])[1]")
WebElement loginbtn;

@FindBy(id = "headlessui-popover-button-:r3:")
    WebElement loginuser;

@FindBy(xpath = "(//p[@class='font-tbcx-regular text-sm w-[160px] truncate'])[1]")
    WebElement loginusername;

@FindBy(xpath = "(//p[@class='font-tbcx-regular text-sm'])[1]")
WebElement userid;

@FindBy(xpath = "/html/body/main/div[1]/div[2]/div[1]/div[1]/div/div/form/div/div/div[1]/span")
WebElement emptymailloginvalid;

@FindBy(xpath = "/html/body/div[2]/div/div/div[2]")
WebElement emptypassloginvalid;

@FindBy(xpath = "/html/body/main/div[1]/div[2]/div[1]/div[1]/div/div/form/div/div/div[2]/span")
WebElement emptypassvalidsec;

@FindBy(xpath = "/html/body/main/div[1]/div[2]/div[1]/div[1]/div/div/form/div/div/div[1]/span")
WebElement onlymailsymbolvalid;

@FindBy(xpath = "(//p[@class='font-tbcx-regular text-sm cursor-pointer'])[1]")
WebElement logoutbtn;


//methods
public LoginPage (WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }


public void ClickLoginBtn(){
userloginButton.click();
}
public void Login(String userlogin, String passwordlogin){
    ClickLoginBtn();
    Sendkeys(username,userlogin);
    Sendkeys(password,passwordlogin);
    Click(loginbtn);
}

public String logincheck(){
Click(loginuser);
return loginusername.getText();

}

public String useridckeck(){
    Click(loginuser);
        return userid.getText();
}


public String emptymaillogin(){
    return emptymailloginvalid.getText();
}

public String emptypasslogin(){
return emptypassloginvalid.getText();
}

public String emptypassvalid(){
    return emptypassvalidsec.getText();
}

public String onlymailvalid (){
    return onlymailsymbolvalid.getText();
}

public void clickLogoutbtn(){;
    Click(loginuser);
    scroll(logoutbtn);
    Click(logoutbtn);
}


}
