package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;

public class TestListener implements ITestListener {

	private ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String params = "";
		Object[] data = result.getParameters();
		if (data.length > 0) {
			params = " - " + data[0].toString();
		}

		if (BaseTest.extent != null) {
			BaseTest base = (BaseTest) result.getInstance();
			base.test = BaseTest.extent.createTest(result.getMethod().getMethodName() + params);

		} else {
			System.out.println("ExtentReports not initialized!");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    BaseTest base = (BaseTest) result.getInstance();
	    WebDriver driver = base.driver;

	    Object[] params = result.getParameters();
	    String username = params.length > 0 ? params[0].toString() : "Unknown";
	    String userType = params.length > 5 ? params[5].toString() : "Unknown";

	    String failureMessage = "❌ Test failed for user: " + username + " | Type: " + userType;

	    if (base.test != null) {
	        if (driver != null) {
	            String path = ScreenshotUtil.captureScreenshot(driver, result.getName());
	            base.test.fail(failureMessage + " - " + result.getThrowable())
	                     .addScreenCaptureFromPath(path);
	        } else {
	            base.test.fail(failureMessage + " - " + result.getThrowable());
	        }
	    } else {
	        System.out.println("ExtentTest is null, cannot log failure!");
	    }
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    BaseTest base = (BaseTest) result.getInstance();
	    Object[] params = result.getParameters();
	    String username = params.length > 0 ? params[0].toString() : "";
	    String userType = params.length > 5 ? params[5].toString() : "N/A";

	    if (base.test != null) {
	        if(userType.equalsIgnoreCase("valid")) {
	            base.test.pass("✅ Valid user '" + username + "' logged in and completed order successfully.");
	        } else if(userType.equalsIgnoreCase("invalid")) {
	            base.test.pass("❌ Invalid user '" + username + "' was correctly blocked from logging in.");
	        } else {
	            base.test.pass("Test passed for user: " + username);
	        }
	    } else {
	        System.out.println("ExtentTest is null, cannot log success!");
	    }
	}


}

