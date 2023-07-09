package faizansayyed.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import faizansayyed.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkEle;

	@FindBy(css = ".cartWrap")
	List<WebElement> cartWrap;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

}
