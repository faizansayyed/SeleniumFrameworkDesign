package faizansayyed.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import faizansayyed.TestComponents.BaseTest;
import faizansayyed.pageobjects.CartPage;
import faizansayyed.pageobjects.CheckOutPage;
import faizansayyed.pageobjects.ConfirmationPage;
import faizansayyed.pageobjects.LandingPage;
import faizansayyed.pageobjects.OrderPage;
import faizansayyed.pageobjects.ProductCatalogue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";;

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();

		if (products.size() > 0) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			productCatalogue.getProductByName(input.get("product"));
			productCatalogue.addProductToCart(input.get("product"));
			CartPage cartPage = productCatalogue.gotToCartPage();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart")));
			Boolean isvalidData = cartPage.verifyProductDisplay(input.get("product"));
			Assert.assertTrue(isvalidData);
			CheckOutPage checkOutPage = cartPage.gotToCheckoutPage();
			checkOutPage.selectCountry("india");
			ConfirmationPage confirmationPage = checkOutPage.placeOrder();

			String confirMessage = confirmationPage.getConfirmationMsg();
			Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
			System.out.println("Test was successfull!!");

		} else {
			System.out.println("No Products!!");
			Assert.assertTrue(false);
		}
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//faizansayyed//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}
