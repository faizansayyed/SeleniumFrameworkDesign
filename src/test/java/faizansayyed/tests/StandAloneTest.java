package faizansayyed.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import faizansayyed.pageobjects.CartPage;
import faizansayyed.pageobjects.CheckOutPage;
import faizansayyed.pageobjects.ConfirmationPage;
import faizansayyed.pageobjects.LandingPage;
import faizansayyed.pageobjects.ProductCatalogue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		ProductCatalogue productCatalogue = landingPage.loginApplication("faizansayyed999@gmail.com", "FAizan@999");
		List<WebElement> products = productCatalogue.getProductList();

		if (products.size() > 0) {

			String toAddString = "ZARA";
			productCatalogue.getProductByName(toAddString);
			productCatalogue.addProductToCart(toAddString);
			CartPage cartPage = productCatalogue.gotToCartPage();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart")));
			Boolean isvalidData = cartPage.verifyProductDisplay(toAddString);
			Assert.assertTrue(isvalidData);
			CheckOutPage checkOutPage = cartPage.gotToCheckoutPage();
			checkOutPage.selectCountry("india");
			ConfirmationPage confirmationPage = checkOutPage.placeOrder();

			String confirMessage = confirmationPage.getConfirmationMsg();
			Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
			System.out.println("Test was successfull!!");
			driver.close();

		} else {
			System.out.println("No Products!!");
			Assert.assertTrue(false);
		}
	}
}
