package trialTestPack;

import org.testng.annotations.Test;

import utility.BasicMethodForAutomation;

public class BasicFrameworkTest extends BasicMethodForAutomation{
  @Test
  public void Demoworkshop() throws NumberFormatException, InterruptedException {
	  
	  login();
	  scrollToWebElement("MyAccountLink");
	  clickOnElement("MyAccountLink");
	  Thread.sleep(Integer.valueOf(dataString.getProperty("FIVE_SECOND")));
	  logout();  
  }
}
