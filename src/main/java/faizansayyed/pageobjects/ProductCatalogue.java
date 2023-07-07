package faizansayyed.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import faizansayyed.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ngx-spinner-overlay")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By toastContainer = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement filteredProduct = getProductList().stream().filter(
				product -> product.findElement(By.cssSelector(".card .card-body h5 b")).getText().contains(productName))
				.findFirst().orElse(null);

		return filteredProduct;
	}

	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(By.xpath("(//button[contains(text(),'Add To Cart')])[1]")).click();
		waitForElementToAppear(toastContainer);
		waitUntilDisappear(spinner);
	}

}
