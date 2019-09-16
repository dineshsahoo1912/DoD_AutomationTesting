package com.consumercredits.dod;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import junit.framework.Assert;

import static org.testng.Assert.assertTrue;


public class TestNG_DoDTestSuite{
	public WebDriver driver;
	public WebDriverWait wait;
	public String baseUrl;
	public String msecUrl;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	
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
		
		baseUrl = "http://vm400020480.nl.eu.abnamro.com:12982/dtt/#/accountDetail";
		msecUrl = "http://vm00006298.nl.eu.abnamro.com/schemes/ATT_2_2/msecis.html?startpage= ";

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
	public void beforeMethod() throws Exception {
		driver.get(baseUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountNumber")));

	}
	
	@Test
	public void languageChangeCheck() throws Exception {
		test = extent.createTest("Language Change Check");
		driver.findElement(By.xpath(".//button[@class = 'btn btn-default' and text()='EN']")).click();
		wait.until(ExpectedConditions.textToBe(By.xpath(".//span[@class = 'ng-scope' and text()='Name']"), "Name"));

		String testResponse = driver.findElement(By.tagName("h2")).getText();
		Assert.assertEquals("UTP Trigger Registration", testResponse);

		testResponse = driver.findElement(By.xpath(".//span[@class = 'ng-scope' and text()='Account Number']")).getText();
		Assert.assertEquals("Account Number", testResponse);

		testResponse = driver.findElement(By.xpath(".//span[@class = 'ng-scope' and text()='T24 Id']")).getText();
		Assert.assertEquals("T24 Id", testResponse);

		testResponse = driver.findElement(By.xpath(".//span[@class = 'ng-scope' and text()='Matera ID']")).getText();
		Assert.assertEquals("Matera ID", testResponse);

		testResponse = driver.findElement(By.xpath(".//span[@class = 'ng-scope' and text()='AAC@Code']")).getText();
		Assert.assertEquals("AAC@Code", testResponse);

		testResponse = driver.findElement(By.xpath(".//span[@class = 'ng-scope' and text()='BCID']")).getText();
		Assert.assertEquals("BCID", testResponse);

		testResponse = driver.findElement(By.xpath(".//span[@class = 'ng-scope' and text()='Name']")).getText();
		Assert.assertEquals("Name", testResponse);
	}
	
	@Test (priority = 1)
	public void boReachCheck() throws Exception {
		test = extent.createTest("BO Reach Check");
		driver.findElement(By.id("accountNumber")).sendKeys("0496254847");
		driver.findElement(By.id("accountNumber")).sendKeys(Keys.ENTER);

		wait.until(ExpectedConditions.textToBe(By.tagName("h3"),"You are not allowed to use this functionality"));
		String testResponse = driver.findElement(By.tagName("h3")).getText();
		Assert.assertEquals("You are not allowed to use this functionality", testResponse);
		Thread.sleep(2000);
	}
	
	@Test (priority = 2)
	public void successSearch() throws Exception {
		test = extent.createTest("Success Search Test");
		driver.findElement(By.id("accountNumber")).clear();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("accountNumber")).sendKeys("101192592");
		driver.findElement(By.id("accountNumber")).sendKeys(Keys.ENTER);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		String testResponse = driver.findElement(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(), 'Legal problems')]")).getText();
		Assert.assertEquals("J26    Legal problems", testResponse);
	
	}
	
	@DataProvider(name = "TestData") 
	public Object[][] dataAccountGroupTest() {

		return new Object[][] {
				{"101192592","CSPL"},
				{"107362252","CRPL"},
				{"490969917","CCPL"},
				{"213319101","CRCPL"},
				{"213116782","CRCNPL"},
				{"213101882","CCNPL"},
				{"12345888","ALFAM"}
		};
	}
	
	@Test (priority = 3, dataProvider = "TestData") 
	public void accountGroupVerification(String accountNumber, String accountGroup) throws Exception {

		int i = 0;
		String testName = "Account Group Verification Test" + i;
		System.out.println(testName);
		i = i+1;

		test = extent.createTest(testName);

		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountNumber")));
		driver.findElement(By.xpath(".//button[@class = 'btn btn-default' and text()='EN']")).click();
		wait.until(ExpectedConditions.textToBe(By.xpath(".//span[@class = 'ng-scope' and text()='Name']"), "Name"));
		driver.findElement(By.id("accountNumber")).sendKeys(accountNumber);
		driver.findElement(By.id("accountNumber")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		WebElement triggerElement;

		if (accountGroup == "CSPL") {
			triggerElement = driver.findElement(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(), 'Legal problems')]"));
			String fontWeight = triggerElement.getCssValue("font-weight");
			Assert.assertEquals("700", fontWeight);
		}

		else if (accountGroup == "CRPL") {
			triggerElement = driver.findElement(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(), 'Lack of trust')]"));
			String fontWeight = triggerElement.getCssValue("font-weight");
			Assert.assertEquals("700", fontWeight);
		}

		else if (accountGroup == "CCPL") {
			triggerElement = driver.findElement(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(), 'Sale of claim')]"));
			String fontWeight = triggerElement.getCssValue("font-weight");
			Assert.assertEquals("700", fontWeight);
		}

		else if (accountGroup == "CRCPL") {
			triggerElement = driver.findElement(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(), 'Reputation Risk')]"));
			String fontWeight = triggerElement.getCssValue("font-weight");
			Assert.assertEquals("700", fontWeight);
		}

		else if (accountGroup == "CRCNPL") {
			triggerElement = driver.findElement(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(), 'No active market')]"));
			String fontWeight = triggerElement.getCssValue("font-weight");
			Assert.assertEquals("700", fontWeight);
		}

		else if (accountGroup == "CCNPL") {
			triggerElement = driver.findElement(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(), 'Reputation Risk')]"));
			String fontWeight = triggerElement.getCssValue("font-weight");
			Assert.assertEquals("700", fontWeight);
		}

		else if (accountGroup == "ALFAM") {
			triggerElement = driver.findElement(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(), 'Legal problems')]"));
			String fontWeight = triggerElement.getCssValue("font-weight");
			assertTrue(fontWeight.equals("bold") || fontWeight.equals("700"));
		}
	}


	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
			
			String screenshotPath = TestNG_DoDTestSuite.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
			Thread.sleep(1000);
			}
		else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName());
			Thread.sleep(1000);
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "TEST CASE PASSED IS " + result.getName());
			
			String screenshotPath = TestNG_DoDTestSuite.getScreenshot(driver, result.getName());
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