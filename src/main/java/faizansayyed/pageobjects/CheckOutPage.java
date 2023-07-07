package faizansayyed.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import faizansayyed.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryInput;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement taItem;

	@FindBy(css = ".action__submit")
	WebElement submitBtn;

	By taResults = By.cssSelector("section.ta-results");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(countryInput, countryName).build().perform();
		waitForElementToAppear(taResults);
		taItem.click();
	}

	public ConfirmationPage placeOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	}

}
