package DemoWorkShop;

import org.testng.annotations.Test;

import utility.BasicMethodForAutomation;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class OrderItem extends BasicMethodForAutomation{
  @Test
  public void OrderBlueJeans() throws NumberFormatException, InterruptedException, IOException {
	  
	  String item = "Blue Jeans";
	  String quantity = "2";
	  StartReportReader("Add an item to the Shopping Cart");
	  
	  login();
	  sendKeys("SearchBar", item);
	  clickOnElement("SearchButton");
	  Thread.sleep(Integer.valueOf(dataString.getProperty("THREE_SECOND")));
	  clickOnElement("BlueJeansLink");
	  Thread.sleep(Integer.valueOf(dataString.getProperty("THREE_SECOND")));
	  sendKeys("AddQuantity", quantity);
	  clickOnElement("AddtoCartBtn");
	  Thread.sleep(Integer.valueOf(dataString.getProperty("TWO_SECOND")));
	  String ExpectedStatusMsg = "The product has been added to your shopping cart";
	  String ActualStatusMsg = getElementText("OrderStatusMsg");
	  Assert.assertEquals(ActualStatusMsg, ExpectedStatusMsg, "Error while adding item to cart......");
	  TakeScreenShot("pass", "The Order has been added to Shopping Cart Successfully....");
	  logger.info("Order moved to shipping cart....");
	  
	  EndReportReader("Add an item to the Shopping Cart");
  }
 
  @Test
  public void ShoppingCart() throws IOException, NumberFormatException, InterruptedException {
	  
	  StartReportReader("Place Order from the Cart....");
	  
	  clickOnElement("ShoppingCartLink");
	  clickOnElement("TermsCheckBox");
	  TakeScreenShot("info", "Shopping Cart Checkout!!!");
	  clickOnElement("CheckoutBtn");
	  clickOnElement("BillingAddressContinueBtn");
	  clickOnElement("InstorePickUpCheckBox");
	  clickOnElement("ShippingAddressContinueBtn");
	  if(checkIfSelected("CODRadioBtn"))
	  {
		  System.out.println("Already COD method Selected....");
	  }
	  else
	  {
		  clickOnElement("CODRadioBtn");
	  }
	  clickOnElement("PaymentMethodContinueBtn");
	  clickOnElement("PaymentInfoContinueBtn");
	  String TotalAmount = getElementText("TotalAmount");
	  scrollToWebElement("ConfirmCartBtn");
	  TakeScreenShot("info", "Total Price Checkout!!!");
	  clickOnElement("ConfirmCartBtn");
	  String ExpectedOrderMsg = "Your order has been successfully processed!";
	  String ActualOrderMsg = getElementText("OrderConfirmMsg");
	  Assert.assertEquals(ActualOrderMsg, ExpectedOrderMsg, "Order has not been successfully placed");
	  String OrderIDString = getElementText("OrderNumber");
	  String OrderID = OrderIDString.substring(14, 21);
	  TakeScreenShot("pass", "Your Order: "+OrderID+" has been successfully placed....");
	  clickOnElement("ContinueBtn");
	  logger.info("Order Number: "+OrderID+" Total Price: "+TotalAmount);
	  Thread.sleep(Integer.valueOf(dataString.getProperty("THREE_SECOND")));
	  EndReportReader("Place Order from the Cart....");
	  
	  
}
}
