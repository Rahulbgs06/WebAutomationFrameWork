package pages;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

	WebDriver driver;

	@FindBy(id = "first-name")
	WebElement firstNameField;

	@FindBy(id = "last-name")
	WebElement lastNameField;

	@FindBy(id = "postal-code")
	WebElement postalCodeField;

	@FindBy(id = "continue")
	WebElement continueBtn;

	@FindBy(id = "finish")
	WebElement finishBtn;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void ensureUserLoggedIn() {
		if (!driver.getCurrentUrl().contains("inventory")) { // assuming inventory page is after login
			throw new IllegalStateException("User is not logged in, cannot checkout!");
		}
	}

	public void enterCheckoutInfo(String firstName, String lastName, String postalCode) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(05));
		wait.until(ExpectedConditions.visibilityOf(firstNameField));
		wait.until(ExpectedConditions.elementToBeClickable(firstNameField));

		firstNameField.clear();
		firstNameField.sendKeys(firstName);

		lastNameField.clear();
		lastNameField.sendKeys(lastName);

		postalCodeField.clear();
		postalCodeField.sendKeys(postalCode);

		continueBtn.click();

		// WAIT FOR OVERVIEW PAGE
		wait.until(ExpectedConditions.urlContains("checkout-step-two"));

	}

	public void finishCheckout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(05));
		wait.until(ExpectedConditions.visibilityOf(finishBtn));
		System.out.println("Finishing checkout...");
		finishBtn.click();

		// Wait for checkout complete page
		wait.until(ExpectedConditions.urlContains("checkout-complete"));
		System.out.println("Checkout complete page loaded: " + driver.getCurrentUrl());

	}

	@FindBy(className = "complete-header")
	WebElement orderConfirmation;

	public boolean isOrderCompleted() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(orderConfirmation));
			System.out.println("Order completed! Message: " + orderConfirmation.getText());
			return true;
		} catch (TimeoutException e) {
			System.out.println("Order completion message not found!");
			System.out.println("Current URL: " + driver.getCurrentUrl());
			return false;
		}
	}
//	public boolean isOrderCompleted() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(05));
//		wait.until(ExpectedConditions.visibilityOf(orderConfirmation));
//		return orderConfirmation.getText().contains("Thank you");
//	}

}
