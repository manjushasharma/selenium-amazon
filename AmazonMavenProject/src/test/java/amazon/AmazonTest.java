package amazon;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class AmazonTest {
	private WebDriver driver;

	@Test
	public void testAmazon() throws InterruptedException {

		String url = JOptionPane.showInputDialog(null, "Enter URL");

		driver.get(url);
		// driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nikon");

		Keyboard kb = ((RemoteWebDriver) driver).getKeyboard();
		kb.pressKey(Keys.RETURN);

		Select drpSort = new Select(driver.findElement(By.name("sort")));

		drpSort.selectByVisibleText("Price: High to Low");
		Thread.sleep(1000);

		driver.findElements(By.xpath("//*[@id='resultsCol']//li[@id='result_1']//a")).get(0).click();
		Thread.sleep(1000);

		String Actualtext = driver.findElement(By.xpath("//*[@id='productTitle']")).getText();
		

		try {
			
			Assert.assertTrue(Actualtext.contains("Nikon D3X"));
			System.out.println("Product topic Matched");
		} catch (AssertionError e) {
			System.out.println("Product topic mismatch");
		}
	}

	@BeforeTest
	public void beforeTest() {
		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectLocation + "/chromedriver.exe");
		driver = new ChromeDriver();

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
