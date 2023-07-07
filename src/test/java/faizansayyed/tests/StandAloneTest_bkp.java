package faizansayyed.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest_bkp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.findElement(By.id("userEmail")).sendKeys("faizansayyed999@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("FAizan@999");

		driver.findElement(By.name("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

//		HashMap<String, Object> defaultObj = new HashMap<>();
//		defaultObj.put("Zara", 2);
//		defaultObj.put("ADIDAS", 2);

		if (products.size() > 0) {
			// Loop through the map using keySet()
//			String[] keysArray = defaultObj.keySet().toArray(new String[0]);

//			List<WebElement> filteredProduct = products.stream().filter(product -> Arrays.stream(keysArray).anyMatch(
//					key -> product.findElement(By.cssSelector(".card .card-body h5 b")).getText().contains(key)))
//					.toList();
			String toAddString = "ZARA";
			WebElement filteredProduct = products.stream().filter(product -> product
					.findElement(By.cssSelector(".card .card-body h5 b")).getText().contains(toAddString)).findFirst()
					.orElse(null);

//		    Loop through the map using keySet()
//			for (String key : keysArray) {
//				Object value = defaultObj.get(key);
//			}

			filteredProduct.findElement(By.xpath("(//button[contains(text(),'Add To Cart')])[1]")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
			driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click(); // by xpath
//			driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); // by css

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart")));

			List<WebElement> cartWrap = driver.findElements(By.className("cartWrap"));

			Boolean isvalidData = cartWrap.stream().anyMatch(
					product -> product.findElement(By.cssSelector(".cartSection h3")).getText().contains(toAddString));

			Assert.assertTrue(isvalidData);

			driver.findElement(By.cssSelector(".totalRow button")).click();

			Actions a = new Actions(driver);
			a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
			driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
			driver.findElement(By.cssSelector(".action__submit")).click();

			String confirMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
			Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
			System.out.println("Test was successfull!!");
			driver.close();

		} else {
			System.out.println("No Products!!");
			Assert.assertTrue(false);
		}
	}
}
