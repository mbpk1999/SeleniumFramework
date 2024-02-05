package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitialClass {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Properties locatorString;
	public static Properties dataString;
	String browser, username, password = "";

	public void initProperty() {
		try {
			File locFile = new File(
					System.getProperty("user.dir") + "/src/main/resources/propertyfiles/locators.properties");
			FileInputStream loc_fis = new FileInputStream(locFile);
			locatorString = new Properties();
			locatorString.load(loc_fis);
			loc_fis.close();
			
			File dataFile = new File(
					System.getProperty("user.dir")+ "/src/main/resources/propertyfiles/datafile.properties");
			FileInputStream data_fis = new FileInputStream(dataFile);
			dataString = new Properties();
			dataString.load(data_fis);
			data_fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * public boolean waitForJSandJQueryToLoad() {
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	 * 
	 * // wait for jQuery to load ExpectedCondition<Boolean> jQueryLoad = new
	 * ExpectedCondition<Boolean>() {
	 * 
	 * @Override public Boolean apply(WebDriver driver) { try { return ((Long)
	 * (((JavascriptExecutor) driver)).executeScript("return jQuery.active") == 0);
	 * } catch (Exception e) { // no jQuery present return true; } } };
	 * 
	 * // wait for Javascript to load ExpectedCondition<Boolean> jsLoad = new
	 * ExpectedCondition<Boolean>() {
	 * 
	 * @Override public Boolean apply(WebDriver driver) { return
	 * (((JavascriptExecutor)
	 * driver)).executeScript("return document.readyState").toString()
	 * .equals("complete"); } };
	 * 
	 * return wait.until(jQueryLoad) && wait.until(jsLoad); }
	 */

	public WebDriver launchbrowser(String browser) {
		switch (browser) {
			case "chrome": {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}
			case "edge": {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			}
			default: {
				System.out.println("Invalid Browser Option....");
				break;
			}
		}
		/* URL of tricentis DemoWork Shop */
		driver.get("https://demowebshop.tricentis.com/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		js = (JavascriptExecutor) driver;
		return driver;
	}

	public WebDriver start(String browser) {
		return launchbrowser(browser);
	}
	
	

	public void stop() {
		driver.quit();
	}

	public WebElement createLocator(String label) {
		WebElement ele = null;
		String labelText = locatorString.getProperty(label);
		String labelTextArray[] = labelText.split("~");
		String method = labelTextArray[0];
		String path = labelTextArray[1];
		/*
		 * for(String s: labelTextArray) { System.out.println(s); }
		 */

		switch (method) {
			case "id": {

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(path)));
				wait.until(ExpectedConditions.elementToBeClickable(By.id(path)));
				ele = driver.findElement(By.id(path));
				break;
			}
			case "name": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(path)));
				wait.until(ExpectedConditions.elementToBeClickable(By.name(path)));
				ele = driver.findElement(By.name(path));
				break;
			}
			case "linkText": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(path)));
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(path)));
				ele = driver.findElement(By.linkText(path));
				break;
			}
			case "partialLinkText": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(path)));
				wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(path)));
				ele = driver.findElement(By.partialLinkText(path));
				break;
			}
			case "css": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(path)));
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(path)));
				ele = driver.findElement(By.cssSelector(path));
				break;
			}
			case "xpath": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
				ele = driver.findElement(By.xpath(path));
				break;
			}
			default: {
				System.out.println(
						"Invalid Locator format....Check the method createLocator under utility.InitialClass: " + label);
				break;
			}
		}
		return ele;
	}
	
	public void clickOnElement(String locator)
	{
		try {
			WebElement ele = createLocator(locator);
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
			ele.click();
			
		} catch (Exception e) {
			System.out.println("Error on ClickOnElement Method under InitailClass :"+locator+"Error: "+e);
		}
	}
	
	public void scrollToWebElement(String locator)
	{
		try {
			WebElement ele = createLocator(locator);
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
			Thread.sleep(Integer.valueOf(dataString.getProperty("TWO_SECOND")));
		} catch (Exception e) {
			System.out.println("Error on scrollToWebElement Method under InitailClass :"+locator+"Error: "+e);
		}
	}
	
	public void sendKeys(String locator, String text)
	{
		try {
			WebElement ele = createLocator(locator);
			ele.clear();
			ele.sendKeys(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error on SendKeys Method under InitailClass :"+locator+"Error: "+e);
		}	
	}
	
	public void sendKeys_Enter(String locator, String text)
	{
		try {
			WebElement ele = createLocator(locator);
			ele.clear();
			ele.sendKeys(text);
			ele.sendKeys(Keys.RETURN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error on SendKeys_Enter Method under InitailClass :"+locator+"Error: "+e);
		}
	}
}
