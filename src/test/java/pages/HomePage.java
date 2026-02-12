package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".inventory_item:first-child .btn_inventory")
	WebElement firstItemAddBtn;
	@FindBy(id = "shopping_cart_container")
	WebElement cartIcon;

//    // Locator for an item (demo website example)
//    By item1 = By.cssSelector(".inventory_item:first-child .btn_inventory");
//    By cartIcon = By.id("shopping_cart_container");

	// Add item to cart
	public void addItemToCart() {
//        driver.findElement(item1).click();
		firstItemAddBtn.click();

	}

	// Go to cart
	public void goToCart() {
//        driver.findElement(cartIcon).click();
		cartIcon.click();
	}
}
