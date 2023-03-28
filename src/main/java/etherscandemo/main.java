package etherscandemo;

import org.openqa.selenium.By;
import pages.EtherscanPage;
import org.openqa.selenium.JavascriptExecutor;
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
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.loginButton);
		WebElement userError = pages.EtherscanPage.find(pages.EtherscanPage.usernameError, driver);
		pages.EtherscanPage.elementPresentAssertion(true, userError);
	}

	@Test
	public void validUsername() {
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, "username", driver);
		WebElement userError = pages.EtherscanPage.find(pages.EtherscanPage.usernameError, driver);
		pages.EtherscanPage.elementPresentAssertion(false, userError);
	}

	@Test
	public void shortUsername() {
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, "12", driver);
		WebElement userError = pages.EtherscanPage.find(pages.EtherscanPage.usernameError, driver);
		pages.EtherscanPage.elementPresentAssertion(true, userError);
	}

	@Test
	public void nonAlphanumericUsername() {
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, "+_)()_(", driver);
		WebElement userError = pages.EtherscanPage.find(pages.EtherscanPage.usernameError, driver);
		pages.EtherscanPage.elementPresentAssertion(true, userError);
	}

	@Test
	public void invalidEmail() {
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, "false", driver);
		WebElement invalidEmail = pages.EtherscanPage.find(pages.EtherscanPage.invalidEmailElement, driver);
		pages.EtherscanPage.elementPresentAssertion(true, invalidEmail);
	}

	@Test
	public void validEmail() {
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, "true@gmail.com", driver);
		WebElement invalidEmail = pages.EtherscanPage.find(pages.EtherscanPage.invalidEmailElement, driver);
		pages.EtherscanPage.elementPresentAssertion(false, invalidEmail);
	}

	@Test
	public void invalidEmailWithConfirmation() {
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, "false", driver);
		pages.EtherscanPage.type(pages.EtherscanPage.confirmationEmailField, "false", driver);
		WebElement invalidEmail = pages.EtherscanPage.find(pages.EtherscanPage.invalidEmailElement, driver);
		WebElement invalidEmailConfirmation = pages.EtherscanPage.find(pages.EtherscanPage.confirmationEmailFieldError,
				driver);
		pages.EtherscanPage.elementPresentAssertion(true, invalidEmail);
		pages.EtherscanPage.elementPresentAssertion(true, invalidEmailConfirmation);
	}

	@Test
	public void captchaTest() {
		pages.EtherscanPage.click(pages.EtherscanPage.captchaBox, driver);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.loginButton);
		WebElement captchaErrorPresent = pages.EtherscanPage.find(pages.EtherscanPage.captchaError, driver);
		pages.EtherscanPage.elementPresentAssertion(true, captchaErrorPresent);
	}

	@Test
	public void fullLoginWithoutCaptcha() {
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, "username", driver);
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, "true@gmail.com", driver);
		pages.EtherscanPage.type(pages.EtherscanPage.confirmationEmailField, "true@gmail.com", driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordField, "derventa", driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordFieldConfirmation, "derventa", driver);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.agreementCheckbox);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.loginButton);
	}

	@BeforeMethod
	public void beforeMethod() {
		// webdrivermanager to setup the chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		// i was encountering an error and this resolved it
		options.addArguments("--remote-allow-origins=");
		ChromeDriver driver = new ChromeDriver(options);
		// maximising the webbrowser to exclude any errors due to size and interceptions
		driver.manage().window().maximize();
		driver.get("https:etherscan.ioregister");
		System.out.println("Starting test: ");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Closing browser");
		driver.quit();
	}

}
