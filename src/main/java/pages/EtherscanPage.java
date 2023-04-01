package pages;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class EtherscanPage {
	// Variables and web element paths
	private static WebElement element = null;
	public static By loginButton = By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_btnRegister']");
	public static By cookiesButton = By.xpath("//html[@id='html']//button[@id='btnCookie']");
	public static By usernameField = By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_txtUserName']");
	public static By usernameError = By.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_txtUserName-error']");
	public static By emailField = By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_txtEmail']");
	public static By invalidEmailElement = By
			.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_txtEmail-error']");
	public static By confirmationEmailField = By
			.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_txtConfirmEmail']");
	public static By confirmationEmailFieldError = By
			.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_txtConfirmEmail-error']");
	public static By captchaError = By
			.xpath("//html[@id='html']//main[@id='content']//form[@action='./register']//a[@href='/register']");
	public static By existingUsernameError = By
			.xpath("//html[@id='html']//main[@id='content']//form[@action='./register']/div[@role='alert']");
	public static By passwordField = By.xpath("//html[@id='html']//input[@id='ContentPlaceHolder1_txtPassword']");
	public static By passwordFieldConfirmation = By.xpath(
			"//html[@id='html']//div[@id='ContentPlaceHolder1_maindiv']//input[@name='ctl00$ContentPlaceHolder1$txtPassword2']");
	public static By agreementCheckbox = By.cssSelector("input#ContentPlaceHolder1_MyCheckBox");
	public static By vertifyEmail = By.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_divHeaderReg']//i");
	public static By captchaBox = By.xpath("//div[@class='recaptcha-checkbox-border']");
	public static By captchaFrame = By.xpath("//iframe[@title='reCAPTCHA']");
	public static By passwordError = By.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_txtPassword-error']");
	public static By passwordConfirmationError = By
			.xpath("//html[@id='html']//div[@id='ContentPlaceHolder1_maindiv']/div[5]/div[@class='invalid-feedback']");
	public static String validUsername = RandomStringUtils.randomAlphabetic(10);
	public static String shortUsername = RandomStringUtils.randomAlphanumeric(2);
	public static String nonAlphanumericUsername = RandomStringUtils.random(8, "!@#$%^&*()_+");
	public static String invalidEmailString = "false";
	public static String validEmailString = "true@gmail.com";
	public static String validUsernameString = "username";
	public static String validPasswordString = "password";
	public static String invalidPasswordString = "asdf";

// functions
	public static WebElement find(By locator, WebDriver driver) {
		element = driver.findElement(locator);
		return element;
	}

	public static void click(By locator, WebDriver driver) {
		find(locator, driver).click();
	}

	public static void type(By locator, String text, WebDriver driver) {
		find(locator, driver).sendKeys(text);
	}

	public static void forceButton(WebDriver driver, By loginButton) {
		WebElement button = driver.findElement(loginButton);
		// forcing a button click due to the Cookies message covering up the button.
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", button);
	}

	public static void elementPresentAssertion(boolean appearance, WebElement element) {
		try {
			Assert.assertEquals(appearance, element.isDisplayed());
		} catch (AssertionError e) {
			System.out.println("Error");
			throw e;
		}
		System.out.println("Valid test.");
	}

}
