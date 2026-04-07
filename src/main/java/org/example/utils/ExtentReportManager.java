package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentReportManager {

    public static ExtentReports extent;
    private static ThreadLocal<ExtentTest>test = new ThreadLocal<>();
    public static String captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    public static  ExtentReports getExtentReports(){
        if(extent==null){
            String reportPatch = System.getProperty("user.dir") + "/report/extentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPatch);
            sparkReporter.config().setReportName("Automation Tester: Giorgi Mikeladze - Reports");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extent=new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environmment ", "QA");
            extent.setSystemInfo("Automation Tester", "Giorgi Mikeladze");

        }

return extent;

    }
public static ExtentTest createTest(String testName){
        ExtentTest extentTest = getExtentReports().createTest(testName);
        test.set(extentTest);
        return extentTest;

}

public static ExtentTest getTest(){
        return test.get();
}

public static void flushReports(){
        if(extent!=null){
            extent.flush();
        }
}



}
