package faizansayyed.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import faizansayyed.pageobjects.CartPage;
import faizansayyed.pageobjects.CheckOutPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement routerlink;

	@FindBy(css = ".totalRow button")
	WebElement checkOutBtn;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitUntilDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage gotToCartPage() {
		routerlink.click(); // by xpath
		return new CartPage(driver);
	}

	public CheckOutPage gotToCheckoutPage() {
		checkOutBtn.click(); // by class
		return new CheckOutPage(driver);
	}
}
