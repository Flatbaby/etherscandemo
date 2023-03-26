package etherscandemo;

import org.openqa.selenium.By;
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
	// Finding elements for methods
	// Some of these may repeat as they have the same xpath
	// and will be reused (fix this later)
	By loginButton = By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_btnRegister']");
	By cookiesButton = By.xpath("//html[@id='html']//button[@id='btnCookie']");
	By usernameField = By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_txtUserName']");
	By usernameError = By.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_txtUserName-error']");
	By emailField = By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_txtEmail']");
	By invalidEmailElement = By.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_txtEmail-error']");
	By confirmationEmailField = By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_txtConfirmEmail']");
	By confirmationEmailFieldError = By
			.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_txtConfirmEmail-error']");

	@Test
	public void emptyClick() throws InterruptedException {
		click(cookiesButton);
		forceButton();
		WebElement userError = find(usernameError);
		elementPresentAssertion(true, userError);
	}

	@Test
	public void validUsername() {
		type(usernameField, "username");
		WebElement userError = find(usernameError);
		elementPresentAssertion(false, userError);
	}

	@Test
	public void shortUsername() {
		type(usernameField, "12");
		WebElement userError = find(usernameError);
		elementPresentAssertion(true, userError);
	}

	@Test
	public void nonAlphanumericUsername() {
		type(usernameField, "+_)()_(");
		WebElement userError = find(usernameError);
		elementPresentAssertion(true, userError);
	}

	@Test
	public void invalidEmail() {
		type(emailField, "false");
		WebElement invalidEmail = find(invalidEmailElement);
		elementPresentAssertion(true, invalidEmail);
	}

	@Test
	public void validEmail() {
		type(emailField, "true@gmail.com");
		WebElement invalidEmail = find(invalidEmailElement);
		elementPresentAssertion(false, invalidEmail);
	}

	@Test
	public void invalidEmailWithConfirmation() {
		type(emailField, "false");
		type(confirmationEmailField, "false");
		WebElement invalidEmail = find(invalidEmailElement);
		WebElement invalidEmailConfirmation = find(confirmationEmailFieldError);
		elementPresentAssertion(true, invalidEmail);
		elementPresentAssertion(true, invalidEmailConfirmation);
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

	// Methods as per DRY principlex
	WebElement find(By locator) {
		return driver.findElement(locator);
	}

	private void click(By locator) {
		find(locator).click();
	}

	private void type(By locator, String text) {
		find(locator).sendKeys(text);
	}

	private void forceButton() {
		WebElement button = driver.findElement(loginButton);
		// forcing a button click due to the Cookies message covering up the button.
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", button);
	}

	private void elementPresentAssertion(boolean appearance, WebElement element) {
		try {
			Assert.assertEquals(appearance, element.isDisplayed());
		} catch (AssertionError e) {
			System.out.println("Error");
			throw e;
		}
		System.out.println("Valid test.");

	}

}
