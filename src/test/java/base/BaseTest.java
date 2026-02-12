package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
	
    public static ExtentReports extent; // keep static for report
    public ExtentTest test; // instance variable, per test
    public WebDriver driver;
	public Logger log = LoggerFactory.getLogger(BaseTest.class);

	@BeforeSuite
	public void startReport() {
		extent = ExtentManager.getInstance();
	}

	@AfterSuite
	public void endReport() {
		if (extent != null) {
			extent.flush();
		}
	}

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		log.info("Browser opened and navigated to login");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			if (driver != null) {
				ScreenshotUtil.captureScreenshot(driver, result.getName());
			} else {
				System.out.println("Driver is null, cannot capture screenshot.");
			}
		}

		if (driver != null) {
			driver.quit();
		}
		log.info("Browser closed");
	}
}

/*
 * package base;
 * 
 * import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.chrome.ChromeDriver; import org.testng.ITestResult;
 * import org.testng.annotations.AfterMethod; import
 * org.testng.annotations.AfterSuite; import
 * org.testng.annotations.BeforeMethod; import
 * org.testng.annotations.BeforeSuite;
 * 
 * import com.aventstack.extentreports.ExtentReports;
 * 
 * import io.github.bonigarcia.wdm.WebDriverManager; import utils.ExtentManager;
 * import utils.ScreenshotUtil;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory;
 * 
 * public class BaseTest { public static ExtentReports extent; public static
 * WebDriver driver; public Logger log =
 * LoggerFactory.getLogger(BaseTest.class);
 * 
 * @BeforeSuite public void startReport() {
 * 
 * extent = ExtentManager.getInstance(); }
 * 
 * @AfterSuite public void endReport() { extent.flush(); }
 * 
 * @BeforeMethod public void setup() { WebDriverManager.chromedriver().setup();
 * driver = new ChromeDriver(); driver.manage().window().maximize();
 * driver.get("https://www.saucedemo.com/");
 * log.info("Browser opened and navigated to login"); }
 * 
 * @AfterMethod public void tearDown(ITestResult result) {
 * 
 * if (ITestResult.FAILURE == result.getStatus()) {
 * ScreenshotUtil.captureScreenshot(driver, result.getName()); }
 * 
 * driver.quit(); log.info("Browser closed"); } }
 */
