package utility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BasicMethodForAutomation extends InitialClass{
	InitialClass init = new InitialClass();
	
	@Parameters({"browser"})
	@BeforeTest
	public void startBrowserURL(String browser)
	{
		System.out.println("Starting Browser....");
		init.initProperty();
		init.start(browser);
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
		//init.stop();
	}
	
	
}
