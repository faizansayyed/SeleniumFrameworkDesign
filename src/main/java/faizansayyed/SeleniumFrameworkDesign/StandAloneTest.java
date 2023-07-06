package faizansayyed.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("faizansayyed999@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("FAizan@999");

		driver.findElement(By.name("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3 "));

		for (WebElement product : products) {
			String productText = product.findElement(By.cssSelector(".card .card-body h5 b")).getText();
			if (productText.contains("ZARA")) {
				product.findElement(By.xpath("(//button[contains(text(),'Add To Cart')])[1]")).click();
			}
		}

	}

}
