package etherscandemo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class main {
	// for further teardown and setup i initialized the driver here

	WebDriver driver;

	@Test(groups = { "Other" })
	public void emptyClick() throws InterruptedException {
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.loginButton);
		WebElement userError = pages.EtherscanPage.find(pages.EtherscanPage.usernameError, driver);
		pages.EtherscanPage.elementPresentAssertion(true, userError);
	}

	@Test(groups = { "Username" })
	public void validUsername() {
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, pages.EtherscanPage.validUsername, driver);
		WebElement userError = pages.EtherscanPage.find(pages.EtherscanPage.usernameError, driver);
		pages.EtherscanPage.elementPresentAssertion(false, userError);
	}

	@Test(groups = { "Username" })
	public void shortUsername() {
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, pages.EtherscanPage.shortUsername, driver);
		WebElement userError = pages.EtherscanPage.find(pages.EtherscanPage.usernameError, driver);
		pages.EtherscanPage.elementPresentAssertion(true, userError);
	}

	@Test(groups = { "Username" })
	public void nonAlphanumericUsername() {
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, pages.EtherscanPage.nonAlphanumericUsername,
				driver);
		WebElement userError = pages.EtherscanPage.find(pages.EtherscanPage.usernameError, driver);
		pages.EtherscanPage.elementPresentAssertion(true, userError);
	}

	@Test(groups = { "Email" })
	public void invalidEmail() {
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, pages.EtherscanPage.invalidEmailString, driver);
		WebElement invalidEmail = pages.EtherscanPage.find(pages.EtherscanPage.invalidEmailElement, driver);
		pages.EtherscanPage.elementPresentAssertion(true, invalidEmail);
	}

	@Test(groups = { "Email" })
	public void validEmail() {
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, pages.EtherscanPage.validEmailString, driver);
		WebElement invalidEmail = pages.EtherscanPage.find(pages.EtherscanPage.invalidEmailElement, driver);
		pages.EtherscanPage.elementPresentAssertion(false, invalidEmail);
	}

	@Test(groups = { "Email" })
	public void invalidEmailWithConfirmation() {
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, pages.EtherscanPage.invalidEmailString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.confirmationEmailField, pages.EtherscanPage.invalidEmailString,
				driver);
		WebElement invalidEmail = pages.EtherscanPage.find(pages.EtherscanPage.invalidEmailElement, driver);
		WebElement invalidEmailConfirmation = pages.EtherscanPage.find(pages.EtherscanPage.confirmationEmailFieldError,
				driver);
		pages.EtherscanPage.elementPresentAssertion(true, invalidEmail);
		pages.EtherscanPage.elementPresentAssertion(true, invalidEmailConfirmation);
	}

	@Test(groups = { "Registration" })
	public void fullLoginWithoutCaptcha() {
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, pages.EtherscanPage.validUsernameString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, pages.EtherscanPage.validEmailString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.confirmationEmailField, pages.EtherscanPage.validEmailString,
				driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordField, pages.EtherscanPage.validPasswordString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordFieldConfirmation, pages.EtherscanPage.validPasswordString,
				driver);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.agreementCheckbox);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.loginButton);
		WebElement captchaErrorPresent = pages.EtherscanPage.find(pages.EtherscanPage.captchaError, driver);
		pages.EtherscanPage.elementPresentAssertion(true, captchaErrorPresent);
	}

	@Test(groups = { "Registration" })
	public void fullLoginFalseEmail() throws InterruptedException {
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, pages.EtherscanPage.validUsername, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, pages.EtherscanPage.invalidEmailString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.confirmationEmailField, pages.EtherscanPage.invalidEmailString,
				driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordField, pages.EtherscanPage.validPasswordString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordFieldConfirmation, pages.EtherscanPage.validPasswordString,
				driver);
		//this is for clicking on the captcha
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(pages.EtherscanPage.captchaFrame));
		wait.until(ExpectedConditions.elementToBeClickable(pages.EtherscanPage.captchaBox));
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.captchaBox);

		System.out.println("Clicked the checkbox");
		driver.switchTo().parentFrame();
		Thread.sleep(25000);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.agreementCheckbox);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.loginButton);
		WebElement invalidEmail = pages.EtherscanPage.find(pages.EtherscanPage.invalidEmailElement, driver);
		WebElement invalidEmailConfirmation = pages.EtherscanPage.find(pages.EtherscanPage.confirmationEmailFieldError,
				driver);
		pages.EtherscanPage.elementPresentAssertion(true, invalidEmail);
		pages.EtherscanPage.elementPresentAssertion(true, invalidEmailConfirmation);
	}

	@Test(groups = { "Registration" })
	public void fullLogin() throws InterruptedException {
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, pages.EtherscanPage.validUsername, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, pages.EtherscanPage.validEmailString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.confirmationEmailField, pages.EtherscanPage.validEmailString,
				driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordField, pages.EtherscanPage.validPasswordString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordFieldConfirmation, pages.EtherscanPage.validPasswordString,
				driver);
		//this is for clicking on the captcha
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(pages.EtherscanPage.captchaFrame));
		wait.until(ExpectedConditions.elementToBeClickable(pages.EtherscanPage.captchaBox));
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.captchaBox);

		System.out.println("Clicked the checkbox");
		driver.switchTo().parentFrame();
		Thread.sleep(25000);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.agreementCheckbox);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.loginButton);
		WebElement vertifyEmail = pages.EtherscanPage.find(pages.EtherscanPage.vertifyEmail, driver);
		pages.EtherscanPage.elementPresentAssertion(true, vertifyEmail);
	}

	@Test(groups = { "Password" })
	public void shortPassword() {
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordField, pages.EtherscanPage.invalidPasswordString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordFieldConfirmation,
				pages.EtherscanPage.invalidPasswordString, driver);
		WebElement passwordError = pages.EtherscanPage.find(pages.EtherscanPage.passwordError, driver);
		WebElement passwordConfirmationError = pages.EtherscanPage.find(pages.EtherscanPage.passwordConfirmationError,
				driver);
		pages.EtherscanPage.elementPresentAssertion(true, passwordConfirmationError);
		pages.EtherscanPage.elementPresentAssertion(true, passwordError);
	}

	@Test(groups = { "Password" })
	public void wrongPasswordConfirmation() {
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordField, pages.EtherscanPage.validPasswordString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordFieldConfirmation, "123456789", driver);
		WebElement passwordError = pages.EtherscanPage.find(pages.EtherscanPage.passwordError, driver);
		WebElement passwordConfirmationError = pages.EtherscanPage.find(pages.EtherscanPage.passwordConfirmationError,
				driver);
		pages.EtherscanPage.elementPresentAssertion(true, passwordConfirmationError);
		pages.EtherscanPage.elementPresentAssertion(false, passwordError);
	}

	@Test(groups = { "Registration" })
	public void registrationWithExistingUsername() throws InterruptedException {
		pages.EtherscanPage.click(pages.EtherscanPage.cookiesButton, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.usernameField, pages.EtherscanPage.validUsernameString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.emailField, "xxflatbabyxx@gmail.com", driver);
		pages.EtherscanPage.type(pages.EtherscanPage.confirmationEmailField, "xxflatbabyxx@gmail.com", driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordField, pages.EtherscanPage.validPasswordString, driver);
		pages.EtherscanPage.type(pages.EtherscanPage.passwordFieldConfirmation, pages.EtherscanPage.validPasswordString,
				driver);
		//this is for clicking on the captcha
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(pages.EtherscanPage.captchaFrame));
		wait.until(ExpectedConditions.elementToBeClickable(pages.EtherscanPage.captchaBox));
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.captchaBox);

		System.out.println("Clicked the checkbox");
		driver.switchTo().parentFrame();
		Thread.sleep(25000);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.agreementCheckbox);
		pages.EtherscanPage.forceButton(driver, pages.EtherscanPage.loginButton);
		pages.EtherscanPage.elementPresentAssertion(true,
				pages.EtherscanPage.find(pages.EtherscanPage.existingUsernameError, driver));
	}

	@BeforeMethod
	public void beforeMethod() {
		// webdrivermanager to setup the chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		// i was encountering an error and this resolved it
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		// maximising the webbrowser to exclude any errors due to size and interceptions
		driver.manage().window().maximize();
		driver.get("https://etherscan.io/register");
		System.out.println("Starting test: ");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Closing browser");
		driver.quit();
	}

}
