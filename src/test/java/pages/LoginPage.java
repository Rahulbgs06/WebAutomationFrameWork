package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	WebElement usernameField;

	@FindBy(id = "password")
	WebElement passwordField;

	@FindBy(name = "login-button")
	WebElement loginButton;

	@FindBy(id = "react-burger-menu-btn")
	WebElement menuButton;

	@FindBy(css = "[data-test='error']")
	WebElement loginError;

	public void login(String user, String pass) {
		usernameField.clear();
		usernameField.sendKeys(user);
		passwordField.clear();
		passwordField.sendKeys(pass);
		loginButton.click();
	}

	public boolean isDashboardVisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // increased wait
			wait.until(ExpectedConditions.visibilityOf(menuButton));
			return menuButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLoginErrorVisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(loginError));
			return loginError.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}

/*
 * package pages;
 * 
 * import java.time.Duration;
 * 
 * import org.openqa.selenium.WebDriver; import org.openqa.selenium.WebElement;
 * import org.openqa.selenium.support.FindBy; import
 * org.openqa.selenium.support.PageFactory; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import
 * org.openqa.selenium.support.ui.WebDriverWait; import
 * org.testng.annotations.DataProvider;
 * 
 * import utils.ExcelUtils;
 * 
 * public class LoginPage {
 * 
 * WebDriver driver;
 * 
 * public LoginPage(WebDriver driver) { this.driver = driver;
 * PageFactory.initElements(driver, this); }
 * 
 * @FindBy(id = "user-name") WebElement usernameField;
 * 
 * @FindBy(id = "password") WebElement passwordField;
 * 
 * @FindBy(name = "login-button") WebElement loginButton;
 * 
 * @FindBy(id = "react-burger-menu-btn") WebElement menuButton;
 * 
 * 
 * @DataProvider(name = "loginData") public Object[][] getData() {
 * 
 * String path = System.getProperty("user.dir") +
 * "/src/test/resources/testdata/LoginData.xlsx";
 * 
 * return ExcelUtils.getTestData(path, "Sheet1"); }
 * 
 * public void enterUsername(String user) { usernameField.sendKeys(user); }
 * 
 * public void enterPassword(String pass) { passwordField.sendKeys(pass); }
 * 
 * public void clickLogin() { loginButton.click(); }
 * 
 * public void login(String user, String pass) { enterUsername(user);
 * enterPassword(pass); clickLogin(); }
 * 
 * public boolean isDashboardVisible() { try { WebDriverWait wait = new
 * WebDriverWait(driver, Duration.ofSeconds(05));
 * wait.until(ExpectedConditions.visibilityOf(menuButton)); return
 * menuButton.isDisplayed(); } catch (Exception e) { return false; }
 * 
 * }
 * 
 * }
 */
