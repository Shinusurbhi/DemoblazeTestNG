package demoblazeversion1.demoblazeversion1;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase {

	public WebDriver driver;

	@BeforeTest
	public void setUp() {
		//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//driver//geckodriver.exe");
		System.setProperty("webdriver.chrome.driver","C://Users//Aditya D. Suratkar//Downloads//demoblazeversion1//demoblazeversion1//browser//chromedriver.exe");
		driver= new ChromeDriver();	
		//driver.manage().window().maximize();

	}

	//@BeforeMethod
	public void navigateToUrl() {
		driver.get("https://demoblaze.com/");
	}
	@Test
	public void testCase() throws InterruptedException {
		navigateToUrl();
		assertEquals("STORE",driver.getTitle());
		driver.findElement(By.id("signin2")).click();
		Thread.sleep(3000);		
		String UsernamePassword = getAlphaNumericString(6); //keeping both username and password same
		WebElement element = driver.findElement(By.id("sign-username"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		element.sendKeys(UsernamePassword);							
		driver.findElement(By.id("sign-password")).sendKeys(UsernamePassword);
		driver.findElement(By.xpath("//button[text()='Sign up']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);			
		wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
		driver.findElement(By.id("login2")).click();
		Thread.sleep(3000);		
		driver.findElement(By.id("loginusername")).sendKeys(UsernamePassword);							
		driver.findElement(By.id("loginpassword")).sendKeys(UsernamePassword);
		WebElement logInConfirmButton = driver.findElement(By.cssSelector("#logInModal > div > div > div.modal-footer > button.btn.btn-primary"));
		logInConfirmButton.click();
		Thread.sleep(3000);			
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#itemc")));	
		
		WebElement laptopMenuButton = driver.findElement(By.cssSelector("a.list-group-item:nth-child(3)"));
		executor.executeScript("arguments[0].click();", laptopMenuButton);
		Thread.sleep(3000);	
		WebElement product =driver.findElement(By.cssSelector("div.col-md-6:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h4:nth-child(1) > a:nth-child(1)"));	

		executor.executeScript("arguments[0].click();", product);

		Thread.sleep(3000);			
		WebElement ImageFile = driver.findElement(By.cssSelector("#imgp > div > img"));		       
		Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		if (!ImagePresent)
		{
			System.out.println("Image not displayed.");
		}
		else
		{
			System.out.println("Image displayed.");
		}
		WebElement addToCart = driver.findElement(By.cssSelector("#tbodyid > div.row > div > a"));
		addToCart.click();
		wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}	
	// function to generate a random string of length n
	static String getAlphaNumericString(int n)
	{

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index
			= (int)(AlphaNumericString.length()
					* Math.random());

			sb.append(AlphaNumericString
					.charAt(index));
		}

		return sb.toString();
	}


	@AfterTest
	public void endTest() {
		driver.quit();
	}


}
