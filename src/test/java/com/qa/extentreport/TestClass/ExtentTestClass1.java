package com.qa.extentreport.TestClass;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestClass1 {
	
	
	ExtentHtmlReporter e1;
	ExtentReports e2;
	ExtentTest e3;
	File file;
	
	String extentReportPath=System.getProperty("user.dir")+"/ExtentReport/"+ this.getClass().getSimpleName() + new Date().getTime() +".html";

	
	@BeforeSuite
	public void InitializeExtentReport()
	{
		try
		{
			file=new File(extentReportPath);
			e1=new ExtentHtmlReporter(file);
			e2=new ExtentReports();
			e2.attachReporter(e1);	
			
			e2.setSystemInfo("Tester Name", "Dheeraj Pratap Singh");
			e2.setSystemInfo("UserName", "dheeraj.singh@google.com");
			e2.setSystemInfo("Environment", "Production");
			e2.setSystemInfo("Sprint Name", "Flash");
			e2.setSystemInfo("Version Number", "ASHSJ1324_234");
			e2.setSystemInfo("Host", "127.0.0.1");

			e1.config().setDocumentTitle("This is automation testing module");
			e1.config().setReportName("Extent Report generator testing demo.");
			e1.config().setTheme(Theme.DARK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void CreateReport(Method method)
	{
		try
		{
			e3=e2.createTest(this.getClass().getSimpleName() + " : : "+ method.getName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void GetReportResult(ITestResult result)
	{
		try
		{
			if (result.getStatus() == ITestResult.SUCCESS) {
				e3.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN));
				e3.log(Status.PASS, result.getMethod().getMethodName() + " test passed");
			}
			
			if (result.getStatus() == ITestResult.FAILURE) {
				e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED));
				e3.log(Status.FAIL, result.getMethod().getMethodName() + " test failed");
				e3.log(Status.FAIL, result.getThrowable().getCause());
				e3.log(Status.FAIL, result.getThrowable().getMessage());
			}
			
			if (result.getStatus() == ITestResult.SKIP) {
				e3.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.YELLOW));
				e3.log(Status.SKIP, result.getMethod().getMethodName() + " test skipped");
				e3.log(Status.SKIP, result.getThrowable());
			}
			
			if (result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE) {
				e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.ORANGE));
				e3.log(Status.FAIL, result.getThrowable());
				e3.log(Status.FAIL, result.getMethod().getMethodName()
						+ " module passed but with on Test Failed But Within Success Percentage.");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterSuite	
	public void FlushExtentReport()
	{
		try
		{
			e2.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=0)
	public void Login()
	{
		System.out.println("Login successful....");
		
	}
	
	@Test(priority=1)
	public void Register()
	{
		System.out.println("Registration successful....");
	}
	
	@Test(priority=2)
	public void ResetPassword()
	{
		System.out.println("Reset password successful....");
	}
	
	@Test(priority=3)
	public void SearchResult()
	{
		System.out.println("Searched result successful....");
	}

}
