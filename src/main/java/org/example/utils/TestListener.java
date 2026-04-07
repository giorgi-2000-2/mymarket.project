package org.example.utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;


public class TestListener implements ITestListener {

    @Override
    public void onTestStart (ITestResult result){
        System.out.println("Test Started : "+ result.getClass());
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.createTest(testName);
        ExtentReportManager.getTest().info("Test Started : " + testName);

    }

    @Override
    public void onTestSuccess (ITestResult result){
        System.out.println("Test Success : "+ result.getName());
        ExtentReportManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed : " + result.getName());
        //Object currentClass = result.getInstance();
        WebDriver driver = DriverManager.getDriver();
        boolean skipScreenshot = false;
        String[] groups = result.getMethod().getGroups();
        for (String group : groups) {
            if (group.equals("no-screenshot")) {
                skipScreenshot = true;
                break;
            }
        }
        if (!skipScreenshot && driver != null) {
            try {

                String base64Code = ExtentReportManager.captureScreenshot(driver);
                ExtentReportManager.getTest().fail("Test failed: " + result.getThrowable().getMessage(),
                        com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("screenshots/" + result.getName() + ".png"));

            } catch (Exception e) {
                System.out.println("სქრინშოთის გადაღება ვერ მოხერხდა: ");
                ExtentReportManager.getTest().fail("Test failed without screenshot: ");
            }
        }
    }
    @Override
    public void onTestSkipped (ITestResult result){
        ExtentReportManager.getTest().skip("Test Skipped");
        System.out.println("Test Skipped : "+ result.getName());
    }

    @Override
    public void onStart (ITestContext context){
        System.out.println("Test suite Started : "+ context.getName());
    }

    @Override
    public void onFinish (ITestContext context){
        System.out.println("Test suite finished : "+ context.getName());
        ExtentReportManager.flushReports();
    }


}
