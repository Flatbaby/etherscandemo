package etherscandemo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;

public class main {
	// for further teardown and setup i initialized the driver here
	WebDriver driver;

	@Test
	public void emptyClick() throws InterruptedException {
		//webdrivermanager to setup the chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		// i was encountering an error and this resolved it
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		//maximising the webbrowser to exclude any errors due to size and interceptions
		driver.manage().window().maximize();
		//fetching the site
		driver.get("https://etherscan.io/register");
		WebElement button = driver.findElement(By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_btnRegister']"));
		WebElement cookies = driver.findElement(By.xpath("//html[@id='html']//button[@id='btnCookie']"));
		WebElement username = driver.findElement(By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_txtUserName']"));
		cookies.click();
		Thread.sleep(3000);
		username.click();
		username.sendKeys("username");
		boolean clicked = false;
		do {
		try {
		button.click();
		}
		catch(WebDriverException e){
			continue;
		}
		
		finally {
			clicked = true;
			System.out.println("button got fucking clicked");
			Thread.sleep(2000);
		}
		} while (!clicked);
		WebElement usernameError = driver.findElement(By.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_txtUserName-error']"));
		String userError = usernameError.getText();
		Assert.assertEquals(null, userError);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Starting test: ");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Closing browser");
		driver.quit();
	}

}
