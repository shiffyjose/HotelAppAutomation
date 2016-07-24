package com.adactin.Testcases;
	import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

	import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

	import com.adactin.page.BookAHotel.BookAHotel;
import com.adactin.page.BookedItenary.BookedItenaryPage;
import com.adactin.page.base.Page;
import com.adactin.page.bookingconfirmation.BookingConfirmation;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.page.selecthotel.SelectHotel;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

	public class TestCase115 extends Page{		
		@Test(dataProvider="OrderNumber")
		public void loginandSearchhotels(Hashtable<String, String> orderNo)
		{
			LoginPage login=PageFactory.initElements(driver, LoginPage.class);
			SearchPage Sp=login.dologin("adactin123", "adactin123");
			BookedItenaryPage Bip=Sp.GotoBookedItenary();
			//String OrderNo="Cancel LI5R917TTA";
			Map<String,Boolean> data=new HashMap<String, Boolean>();
			data=Bip.FindOrderDetailsEditable("Cancel "+orderNo.get("OrderNumber"));			
			SoftAssert dataassert=new SoftAssert();
			for (Entry<String, Boolean> entry : data.entrySet()) 
			{	  
				System.out.println(entry.getKey()+ ","+ entry.getValue());
				dataassert.assertFalse(entry.getValue(), "The iternary details for the order number: "+orderNo.get("OrderNumber")+" is editable for the field value "+entry.getKey());	                
	        }	
			dataassert.assertAll();
		}
		
		@DataProvider()
		public Object[][] OrderNumber()
		{
			return TestUtil.getData("TC_115", xls);
		}
		
		@AfterTest
		public void Teardown()
		{
			driver.close();
		}

	}



