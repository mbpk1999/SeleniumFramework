package utility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Utility.initialization;

public class BasicMethodForAutomation extends InitialClass{
	InitialClass init = new InitialClass();
	
	@Parameters({"browser"})
	@BeforeTest
	public void startBrowserURL(String browser) throws Exception
	{
		System.out.println("Starting Browser....");
		init.initProperty();
		init.reports();
		init.start(browser);
	}
	
	public void StartReportReader(String mtdName) {
		logger = InitialClass.report.createTest(mtdName);
		logger.info(mtdName + " - Validation started");
	}

	public void EndReportReader(String mtdName) {
		logger.info(mtdName + " - Validation Ends");
	}
	
	public void login()
	{
		String uemail = System.getProperty("UserEmail");
		String passcode = System.getProperty("UserPassCode");
		
		clickOnElement("LogIn");
		sendKeys("EmailText", uemail);
		sendKeys("PasswordText", passcode);
		clickOnElement("LogInBtn");
		
	}
	
	public void logout()
	{
		clickOnElement("LogOutBtn");
	}
	
	@AfterTest
	public void endTest()
	{
		System.out.println("Quiting Browser.....");
		InitialClass.report.flush();
		//init.stop();
	}
	
	
}
