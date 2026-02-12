package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelUtils;

@Listeners(utils.TestListener.class)
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void endtoEndTest(String username,
                             String password,
                             String firstName,
                             String lastName,
                             String zip,
                             String userType) {

        // Create local ExtentTest for this user
        ExtentTest test = BaseTest.extent.createTest("End-to-End Test: " + username + " | Type: " + userType);

        try {
            test.info("Starting test for user: " + username);

            // Login
            LoginPage login = new LoginPage(driver);
            login.login(username, password);

            boolean loginSuccess = login.isDashboardVisible();

            if (userType.equalsIgnoreCase("valid")) {
                if (loginSuccess) {
                    test.pass("Valid user login successful: " + username);

                    // Add to cart + checkout
                    HomePage home = new HomePage(driver);
                    home.addItemToCart();
                    home.goToCart();
                    test.info("Added item to cart and navigated to Cart page");

                    CartPage cart = new CartPage(driver);
                    cart.clickCheckout();
                    test.info("Clicked checkout button");

                    CheckoutPage checkout = new CheckoutPage(driver);
                    checkout.enterCheckoutInfo(firstName, lastName, zip);
                    test.info("Entered checkout info: " + firstName + " " + lastName + " " + zip);
                    checkout.finishCheckout();

                    if (checkout.isOrderCompleted()) {
                        test.pass("Order completed successfully for: " + username);
                    } else {
                        test.fail("Order failed for: " + username);
                        Assert.fail("Order failed for: " + username);
                    }

                } else {
                    test.fail("Valid user failed to login: " + username);
                    Assert.fail("Valid user failed to login: " + username);
                }

            } else if (userType.equalsIgnoreCase("invalid")) {
                if (!loginSuccess && login.isLoginErrorVisible()) {
                    test.pass("Invalid user correctly failed to login: " + username);
                } else {
                    test.fail("Invalid user login passed but should have failed: " + username);
                    Assert.fail("Invalid user login passed: " + username);
                }
            }

        } catch (Exception e) {
            test.fail("Test encountered an exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/LoginData.xlsx";

        Object[][] data = ExcelUtils.getTestData(path, "Sheet1");

        // Convert ZIP to string properly and handle nulls
        for (int i = 0; i < data.length; i++) {
            if (data[i][4] != null) {
                data[i][4] = data[i][4].toString().replace(".0", "");
            } else {
                data[i][4] = ""; // empty if null
            }
            System.out.println("Prepared ZIP for user " + data[i][0] + ": " + data[i][4]);
        }

        return data;
    }
}
