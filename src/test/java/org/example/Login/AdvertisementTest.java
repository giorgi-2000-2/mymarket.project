package org.example.Login;
import org.example.BaseTest;
import org.example.pages.AdvertisementPage;
import org.example.pages.LoginPage;
import org.example.utils.AssertHelpperManager;
import org.example.utils.ConfigReader;
import org.example.utils.HelperFunctions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.example.utils.ExtentReportManager.*;

@Listeners(value = org.example.utils.TestListener.class)
public class AdvertisementTest extends BaseTest {

    @Test
   public void testAdvertisementcheck() {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
       loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        getTest().pass("ავტორიზაცია წარმატებით დასრულდა");
        getTest().info("ნავიგაცია განცხადების დამატების გვერდზე");
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info("მომხმარებლის სახელის ვალიდაცია");
        assertHelpperManager.hardAssertWithLog(advertisementPage.getUserNamecheck(), advertisementPage.usernameCheck()," username შემოწმება ");

    }

    @Test
    public void testCheckSellBtn() {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        getTest().info("დალოგინება");
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        getTest().info("ნავიგაცია განცხადების დამატების გვერდზე");
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info("ღილაკის შემოწმება");
        assertHelpperManager.assertTrueWithLog(softAssert,advertisementPage.checkSellBTNIfClickable(),"ღილაკის შემოწმება");
        getTest().info("ღილაკზე დაკლიკვა");
        advertisementPage.clickSellBTN();
        getTest().info("გაყიდვის ღილაკზე დაჭერის შემდეგომი შემოწმება ");
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("sell.url"), "გაყიდვის");
        softAssert.assertAll();
    }

    @Test
    public void testChechBuyBtn() {
        SoftAssert softAssert = new SoftAssert();
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        getTest().info("დალოგინება");
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        getTest().info("ნავიგაცია განცხადების დამატების გვერდზე");
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info("ღილაკის შემოწმება");
        assertHelpperManager.assertTrueWithLog(softAssert,advertisementPage.checkBuyBTNIfClickable(),"ღილაკის შემოწმება");
        getTest().info("ღილაკზე დაკლიკვა");
        advertisementPage.clickBuyBTN();
        getTest().info("გაყიდვის ღილაკზე დაჭერის შემდეგომი შემოწმება ");
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("buy.url"),"ყიდვის ");
softAssert.assertAll();
    }

    @Test
    public void testCheckRentBtn() {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        getTest().info("დალოგინება");
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        getTest().info("ნავიგაცია განცხადების დამატების გვერდზე");
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info("ღილაკის შემოწმება");
        assertHelpperManager.assertTrueWithLog(softAssert,advertisementPage.checkRentBTNIfClickable(),"ღილაკის შემოწმება");
        getTest().info("ღილაკზე დაკლიკვა");
        advertisementPage.clickRent();
        getTest().info("გაყიდვის ღილაკზე დაჭერის შემდეგომი შემოწმება ");
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("rent.url"), "გაქირავების ");
        softAssert.assertAll();
    }

   @Test
   public void testCheckServiceBtn(){
       SoftAssert softAssert = new SoftAssert();
       AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
       LoginPage loginPage = new LoginPage(driver);
       AdvertisementPage advertisementPage = new AdvertisementPage(driver);
       getTest().info("დალოგინება");
       loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
       getTest().info("ნავიგაცია განცხადების დამატების გვერდზე");
       assertHelpperManager.navigateToAdvertisementPage(softAssert);
       getTest().info("ღილაკის შემოწმება");
       assertHelpperManager.assertTrueWithLog(softAssert,advertisementPage.checkServiceBTNIfClickable(),"ღილაკის შემოწმება");
       getTest().info("ღილაკზე დაკლიკვა");
        advertisementPage.clickService();
       getTest().info("სერვისების ღილაკზე დაჭერის შემდეგომი შემოწმება ");
       assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("service.url"),"სერვისების ");
softAssert.assertAll();
    }



    @Test(groups = "no-screenshot")
    public void testCheckSellCategory(){
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        getTest().info("დალოგინება");
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        getTest().info("ნავიგაცია განცხადების დამატების გვერდზე");
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" გაყიდვის ღილაკზე დაკლიკება ");
        advertisementPage.clickSellBTN();
        getTest().info(" დროპდაუნ მენიუს შემოწმება და დაკლიკება ");
        Assert.assertTrue(advertisementPage.ClickDropdown());
        getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
        Assert.assertTrue(assertHelpperManager.checkCategoryIsVisible());
        getTest().info(" დროპდაუნ მენიუს კატეგორიების list-is შემოწმება ");
        Assert.assertTrue(assertHelpperManager.checkMainCategories());
softAssert.assertAll();
    }

    @Test(groups = "no-screenshot")
    public void testCheckCategoryNavigationClick(){
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" გაყიდვის ღილაკზე დაკლიკება ");
        advertisementPage.clickSellBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("sell.url"),"გაყიდვის ");
        getTest().info(" დროპდაუნ მენიუზე დაკლიკება ");
        advertisementPage.ClickDropdown();
        getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
        Assert.assertTrue(assertHelpperManager.checkCategoryIsVisible());
        getTest().info(" დროპდაუნ მენიუს back - ღილაკის შემოწმება შემოწმება ");
        assertHelpperManager.checkBackClickInCategories(softAssert);
softAssert.assertAll();
    }


    @Test(groups = "no-screenshot")
    public void testCheckSellTypeAllItems() {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" გაყიდვის ღილაკზე დაკლიკება ");
        advertisementPage.clickSellBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("sell.url"),"გაყიდვის ");
        getTest().info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება ");
        assertHelpperManager.checkAllCategoryItems(softAssert);
softAssert.assertAll();

    }


    @Test(groups = "no-screenshot")
    public void testCheckSellCategoryData()  {
        SoftAssert softAssert = new SoftAssert();
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" ყიდვა ღილაკზე დაკლიკება ");
        advertisementPage.clickSellBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("sell.url"),"გაყიდვის ");
        getTest().info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება მონაცემებთან ");
        assertHelpperManager.checkAllCategoryItems(softAssert);

softAssert.assertAll();
    }


    @Test(groups = "no-screenshot")
    public void testCheckSellTypeCategoryDataBrands() {
     SoftAssert softAssert=new SoftAssert();
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" გაყიდვის ღილაკზე დაკლიკება ");
        advertisementPage.clickSellBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("sell.url")," გაყიდვა ");
        getTest().info(" დროპდაუნ მენიუზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება მონაცემებთან და ბრენდებთან ");
        assertHelpperManager.checkAllCategoryItemsDataWithBrands(softAssert);
         softAssert.assertAll();

    }



    @Test(groups = "no-screenshot")
    public void testCheckBuyCategories(){
    LoginPage loginPage = new LoginPage(driver);
    SoftAssert softAssert = new SoftAssert();
   AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
    AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
    getTest().info(" ყიდვის ღილაკზე დაკლიკება ");
    advertisementPage.clickBuyBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("buy.url"),"ყიდვის ");
    getTest().info(" დროპდაუნ მენიუზე დაკლიკება ");
    advertisementPage.clickDropdownCategory();
    getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
    Assert.assertTrue(assertHelpperManager.checkCategoryIsVisible());
    getTest().info(" დროპდაუნ მენიუს მთავარი კატეგორიების შემოწმება ");
        assertHelpperManager.checkMainCategories();
    softAssert.assertAll();
}



    @Test(groups = "no-screenshot")
    public void testCheckBuyCategoryNavigationClick(){
        SoftAssert softAssert =new SoftAssert();
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
    getTest().info(" ყიდვის ღილაკზე დაკლიკება ");
        advertisementPage.clickBuyBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("buy.url"),"ყიდვის ");
    getTest().info(" დროპდაუნ მენიუზე დაკლიკება ");
        advertisementPage.ClickDropdown();
    getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
        Assert.assertTrue(assertHelpperManager.checkCategoryIsVisible());
    getTest().info(" დროპდაუნ მენიუს back - ღილაკის შემოწმება შემოწმება ");
        assertHelpperManager.checkBackClickInCategories(softAssert);
softAssert.assertAll();
    }




    @Test(groups = "no-screenshot")
    public void TestCheckBuyTypeAllItems()  {
        SoftAssert softAssert =new SoftAssert();
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" ყიდვის ღილაკზე დაკლიკება ");
        advertisementPage.clickBuyBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("buy.url"),"ყიდვის ");
        getTest().info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება ");
        assertHelpperManager.checkAllCategoryItems(softAssert);

softAssert.assertAll();
    }




    @Test(groups = "no-screenshot")
    public void testCheckBuyTypeCategoryData() {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert =new SoftAssert();
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" ყიდვა ღილაკზე დაკლიკება ");
        advertisementPage.clickBuyBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("buy.url"),"ყიდვის ");
        getTest().info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება მონაცემებთან ");
        assertHelpperManager.checkAllCategoryItems(softAssert);

softAssert.assertAll();
    }




    @Test(groups = "no-screenshot")
    public void testCheckBuyTypeCategoryDataBrands()  {
        SoftAssert softAssert = new SoftAssert();
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" ყიდვა ღილაკზე დაკლიკება ");
        advertisementPage.clickBuyBTN();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("buy.url"),"ყიდვის ");
        getTest().info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება მონაცემებთან და ბრენდებთან ერთად : ");
        assertHelpperManager.checkAllCategoryItemsDataWithBrands(softAssert);
softAssert.assertAll();

    }




    @Test(groups = "no-screenshot")
    public void testCheckRentCategories(){
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" გაქირავების ღილაკზე დაკლიკება ");
        advertisementPage.clickRent();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("rent.url"),"გაქირაავების ");
        getTest().info(" დროპდაუნ მენიუზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
        Assert.assertTrue(assertHelpperManager.checkCategoryIsVisible());
        getTest().info(" დროპდაუნ მენიუს მთავარი კატეგორიების შემოწმება ");
        assertHelpperManager.checkMainCategories();
        softAssert.assertAll();
    }




    @Test(groups = "no-screenshot")
    public void testcheckRentCategoryNavigationClick(){
        SoftAssert softAssert = new SoftAssert();
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" გაქირავება ღილაკზე დაკლიკება ");
        advertisementPage.clickRent();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("rent.url"),"გაქირავების ");
        getTest().info(" დროპდაუნ მენიუზე დაკლიკება ");
        advertisementPage.ClickDropdown();
        getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
        Assert.assertTrue(assertHelpperManager.checkCategoryIsVisible());
        getTest().info(" დროპდაუნ მენიუს back - ღილაკის შემოწმება ");
        assertHelpperManager.checkBackClickInCategories(softAssert);
softAssert.assertAll();
    }




    @Test(groups = "no-screenshot")
    public void testcheckRentTypeAllItems()  {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        SoftAssert softAssert = new SoftAssert();
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" გაქირავება ღილაკზე დაკლიკება ");
        advertisementPage.clickRent();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("rent.url"),"გაქირავების ");
        getTest().info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება ");
        assertHelpperManager.checkAllCategoryItems(softAssert);
softAssert.assertAll();

    }

    @Test(groups = "no-screenshot")
    public void testCheckRentTypeCategoryData()  {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        SoftAssert softAssert = new SoftAssert();
        HelperFunctions helperFunctions = new HelperFunctions(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" გაქირავება ღილაკზე დაკლიკება ");
        advertisementPage.clickRent();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("rent.url"),"გაქირავების ");
        getTest().info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება მონაცემებთან ");
        helperFunctions.checkAllCategoryItemsData(softAssert);

softAssert.assertAll();
    }


    @Test(groups = "no-screenshot")
    public void testCheckServiceCategories(){
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        SoftAssert softAssert = new SoftAssert();
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" სერვისების ღილაკზე დაკლიკება ");
        advertisementPage.clickService();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("service.url"),"სერვისების ");
        getTest().info(" დროპდაუნ მენიუზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
        Assert.assertTrue(assertHelpperManager.checkCategoryIsVisible());
        getTest().info(" დროპდაუნ მენიუს მთავარი კატეგორიების შემოწმება ");
        assertHelpperManager.checkMainCategories();
        softAssert.assertAll();
    }


    @Test(groups = "no-screenshot")
    public void checkServiceCategoryNavigationClick(){
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        SoftAssert softAssert = new SoftAssert();
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" სერვისების ღილაკზე დაკლიკება ");
        advertisementPage.clickService();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("service.url"),"სერვისების ");
        getTest().info(" დროპდაუნ მენიუზე დაკლიკება ");
        advertisementPage.ClickDropdown();
        getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
        Assert.assertTrue(assertHelpperManager.checkCategoryIsVisible());
        getTest().info(" დროპდაუნ მენიუს back - ღილაკის შემოწმება შემოწმება ");
        assertHelpperManager.checkBackClickInCategories(softAssert);
softAssert.assertAll();
    }


    @Test(groups = "no-screenshot")
    public void checkServiceTypeCategory() {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        SoftAssert softAssert = new SoftAssert();
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info(" სერვისების ღილაკზე დაკლიკება ");
        advertisementPage.clickService();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("service.url"),"სერვისების ");
        getTest().info(" დროპდაუნ მენიუს კატეგორიების შემოწმება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" კატეგორიების ნახვა ");
        assertHelpperManager.checkAllCategoryItems(softAssert);
softAssert.assertAll();
    }


    @Test(groups = "no-screenshot")
    public void testCheckServiceTypeCategoryData()  {
        AssertHelpperManager assertHelpperManager = new AssertHelpperManager(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisementPage advertisementPage = new AdvertisementPage(driver);
        SoftAssert softAssert = new SoftAssert();
        HelperFunctions helperFunctions = new HelperFunctions(driver);
        loginPage.Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        assertHelpperManager.navigateToAdvertisementPage(softAssert);
        getTest().info("სერვისების ღილაკზე დაკლიკება ");
        advertisementPage.clickService();
        assertHelpperManager.CheckMainAsserts(softAssert, ConfigReader.get("service.url"),"სერვისების ");
        getTest().info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        advertisementPage.clickDropdownCategory();
        getTest().info(" ყველა კატეგორიის შემოწმება მონაცემებთან ");
        helperFunctions.checkAllCategoryItemsData(softAssert);
softAssert.assertAll();

    }






}



