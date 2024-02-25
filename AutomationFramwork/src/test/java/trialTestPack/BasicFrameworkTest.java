package trialTestPack;

import java.io.IOException;

import org.testng.annotations.Test;

import utility.BasicMethodForAutomation;

public class BasicFrameworkTest extends BasicMethodForAutomation{
  @Test
  public void Demoworkshop() throws NumberFormatException, InterruptedException, IOException {
	  
	  StartReportReader("Demo Workshop....Selenium Testing");
	  login();
	  scrollToWebElement("MyAccountLink");
	  TakeScreenShot("info", "Account Link Check");
	  logger.info("Inside main class....");
	  clickOnElement("MyAccountLink");
	  Thread.sleep(Integer.valueOf(dataString.getProperty("FIVE_SECOND")));
	  logout();  
	  EndReportReader("Demo Workshop....Selenium Testing");
  }
}
