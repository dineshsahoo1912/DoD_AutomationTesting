package com.consumercredits.commonsteps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.consumercredits.dod.SearchPageLangBOReachTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class commonSteps {
    public WebDriver driver;
    public ExtentTest test;
    public ExtentReports extent;
    public ExtentHtmlReporter htmlReporter;
    public WebDriverWait wait;

    public String baseUrl = "http://vm400020480.nl.eu.abnamro.com:12982/dtt/#/accountDetail";
    public String msecUrl = "http://vm00006298.nl.eu.abnamro.com/schemes/ATT_2_2/msecis.html?startpage= ";


    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/Screenshots" + screenshotName + ".png";
        File finalDestination = new File (destination);
        FileUtils.copyFile(source,finalDestination);
        return destination;
    }

    @BeforeTest
    public void beforeTest() throws  Exception{

        //Prepare Report Data

        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        driver = new FirefoxDriver();

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/DoDTestReport.html");
        htmlReporter.config().setDocumentTitle("DoD Trigger Tool Test Report");
        htmlReporter.config().setReportName("Functional Test Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        //Prepare Tester Data
        extent.setSystemInfo("Tester Name", "Dinesh");
        extent.setSystemInfo("Browser", "Mozilla Firefox");

        //Do MSec Login for ST/UT
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.get(msecUrl);

        Robot robot = new Robot();
        robot.setAutoDelay(2000);
        StringSelection stringuser = new StringSelection("launchert\\s04500");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringuser, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        StringSelection stringpass = new StringSelection("testen#1");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringpass, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(5000);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountNumber")));

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException, InterruptedException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

            String screenshotPath = SearchPageLangBOReachTest.getScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
            Thread.sleep(1000);
        }
        else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName());
            Thread.sleep(1000);
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "TEST CASE PASSED IS " + result.getName());

            String screenshotPath = SearchPageLangBOReachTest.getScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
            Thread.sleep(1000);
        }
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        driver.quit();
        extent.flush();
    }

}
