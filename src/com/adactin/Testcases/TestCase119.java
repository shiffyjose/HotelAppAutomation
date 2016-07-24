package com.adactin.Testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.adactin.page.BookedItenary.BookedItenaryPage;
import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.pages.login.LoginPage;

public class TestCase119 extends Page {
	@Test//(dataProvider="Orderdata")
	//public void SearchOrderId(Hashtable<String, String> data) throws InterruptedException
	public void SearchOrderId() throws Exception
	{
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		//SearchPage Sp=login.dologin("adactin123", "adactin123");
		SearchPage Sp=login.dologin(CONFIG.getProperty("user_name"), CONFIG.getProperty("pwd"));
		BookedItenaryPage Bip=Sp.GotoBookedItenary();	
		String[] BeforeSearch=new String[15];
		String[] AfterSearch=new String[15];
		//BeforeSearch=Bip.MyItenarydetails(data.get("OrderId"));
		//AfterSearch=Bip.SearchOrder(data.get("OrderId"));
		String OrderId="9JCT6N36WR";
		Bip.CancelOrder("Cancel "+OrderId);	//change alert to "accept" for the TC 119
		AfterSearch=Bip.SearchOrder("OrderId");
		//if(AfterSearch==null)
		 Assert.assertNull(AfterSearch, "Cancellation of order not success");
		 System.out.println("Cancel check success:");
		
	}
	
	@AfterTest
	public void Teardown()
	{
		driver.close();
	}
		
}
