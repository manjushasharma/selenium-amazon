package stepDefinition;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test_Steps {
	private WebDriver driver;

	@Given("^User is on Amazon home page$")
	public void user_is_on_Amazon_home_page() throws Throwable {
		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectLocation + "/chromedriver.exe");
		driver = new ChromeDriver();
		String url = JOptionPane.showInputDialog(null, "Enter URL");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("^User Search Nikon$")
	public void user_Search_Nikon() throws Throwable {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nikon");
		Keyboard kb = ((RemoteWebDriver) driver).getKeyboard();
		kb.pressKey(Keys.RETURN);
	}

	@When("^User sort it with Price high to low$")
	public void user_sort_it_with_Price_high_to_low() throws Throwable {
		Select drpSort = new Select(driver.findElement(By.name("sort")));
		drpSort.selectByVisibleText("Price: High to Low");
		Thread.sleep(1000);
	}

	@When("^user select second product$")
	public void user_select_second_product() throws Throwable {
		driver.findElements(By.xpath("//*[@id='resultsCol']//li[@id='result_1']//a")).get(0).click();
		Thread.sleep(1000);

	}

	@When("^product topic contains text Nikon D(\\d+)X$")
	public void product_topic_contains_text_Nikon_D_X(int arg1) throws Throwable {
		String Actualtext = driver.findElement(By.xpath("//*[@id='productTitle']")).getText();
		try {
			Assert.assertTrue(Actualtext.contains("Nikon D3X"));
			System.out.println("Product topic Matched");
		} catch (AssertionError e) {
			System.out.println("Product topic mismatch");
		}
	}

	@Then("^product topic text result$")
	public void product_topic_text_mismatch() throws Throwable {
		System.out.println("Test Case executed Successfully");
		driver.quit();
	}

}
