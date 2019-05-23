package com.qa.extentreport.TestClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.*;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestBaseClass {
	
	
	String reportPath=System.getProperty("user.dir")+"/src/ExtentReport/"+getClass().getSimpleName()+".html";

	protected WebDriver driver;
	public ExtentHtmlReporter e1;
	public ExtentReports e2;
	public ExtentTest e3;
	
	@BeforeSuite
	public void InitialisingExtentReports()
	{
		e1=new ExtentHtmlReporter(reportPath);
		e2=new ExtentReports();
		e2.attachReporter(e1);
		
		e2.setSystemInfo("User Name", "DheerajPratapSingh@gmail.com");
		e2.setSystemInfo("Tester Name", "Dheeraj Pratap Singh");
		e2.setSystemInfo("Enviroment", "QA");
		e2.setSystemInfo("Sprint Version", "V.23123");
		e2.setSystemInfo("Sprint Name", "Auqaman");
		e2.setSystemInfo("Host Name", "KJKLEKWLKDJ23123");
		e2.setSystemInfo("Host Number", "127.0.0.1");
		
		e1.config().setDocumentTitle(this.getClass().getSimpleName() + " Secenario Test for the WesterUnion site");
		e1.config().setReportName(this.getClass().getSimpleName());		
		e1.config().setTheme(Theme.DARK);
	}
	
	@AfterSuite
	public void GeneratingReport()
	{
		e2.flush();
	}
	@BeforeTest
	public void OpeningBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dheeraj.singh\\eclipse-workspace\\ExtentReportDemoFrameWork\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
		driver.get("https://www.softwaretestingmaterial.com");

	}
	
	@AfterTest
	public void ClosingBrowser() throws Exception
	{
		Thread.sleep(2000);
		driver.close();
	}
}

