package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitialClass {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Properties locatorString;
	public static Properties dataString;
	public static ExtentTest logger;
	public static ExtentReports report;
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
		driver.manage().window().maximize();
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
	
	public String getElementText(String label) {
		String returnVal = null;
		try {
			WebElement ele = createLocator(label);
			returnVal = ele.getText();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return returnVal;
	}
	
	public Boolean checkIfSelected(String label)
	{
		WebElement ele = createLocator(label);
		return ele.isSelected();
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
	
	public void selectbyDropDown(String locator, String text) throws InterruptedException {
		try {
			WebElement ele = createLocator(locator);
			Select sel = new Select(ele);
			sel.selectByVisibleText(text);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void selectbyDropDownValue(String label, String text) throws InterruptedException {
		try {
			WebElement ele = createLocator(label);
			Select sel = new Select(ele);
			sel.selectByValue(text);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public String getInputTextFieldValue(String label) {
		String returnVal = null;
		try {
			WebElement ele = createLocator(label);
			returnVal = ele.getAttribute("value");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return returnVal;
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
	
	public void reports() throws Exception {

		ExtentSparkReporter extent = new ExtentSparkReporter(
				new File(System.getProperty("user.dir") + "/Html Reports/extent.html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		extent.config().setDocumentTitle("My Automation Framework Report Testing");
	}
	
	public static String getScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-ms");
		String path = System.getProperty("user.dir") + "/Screenshot//" + sdf.format(new Date()) + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);

		} catch (IOException e) {
			//logger.log(Status.FAIL, "Screen Shot Capture Failed: " + e.getMessage());
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}
	
	public void TakeScreenShot(String status, String str) throws IOException {
		if (status.equalsIgnoreCase("pass"))
			logger.log(Status.PASS, str, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
		else if (status.equalsIgnoreCase("fail"))
			logger.log(Status.FAIL, str, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
		else
			logger.log(Status.INFO, str, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
	}
}
