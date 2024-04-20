package trialTestPack;

import java.io.IOException;

import org.testng.annotations.Test;

import utility.BasicMethodForAutomation;

public class BasicFrameworkTest extends BasicMethodForAutomation{
  @Test
  public void Demoworkshop1() throws NumberFormatException, InterruptedException, IOException {
	  
	  StartReportReader("Demo Workshop1....Selenium Testing");
	  login();
	  scrollToWebElement("MyAccountLink");
	  TakeScreenShot("info", "Account Link Check");
	  logger.info("Inside main class....");
	  clickOnElement("MyAccountLink");
	  Thread.sleep(Integer.valueOf(dataString.getProperty("FIVE_SECOND")));
	  logout();  
	  EndReportReader("Demo Workshop1....Selenium Testing");
  }
  
  @Test
  public void Demoworkshop2() throws NumberFormatException, InterruptedException, IOException {
	  
	  StartReportReader("Demo Workshop2....Selenium Testing");
	  login();
	  scrollToWebElement("MyAccountLink");
	  TakeScreenShot("info", "Account Link Check");
	  logger.info("Inside main class....");
	  clickOnElement("MyAccountLink");
	  Thread.sleep(Integer.valueOf(dataString.getProperty("FIVE_SECOND")));
	  logout();  
	  EndReportReader("Demo Workshop2....Selenium Testing");
  }
}
