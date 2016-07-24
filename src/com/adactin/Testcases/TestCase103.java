package com.adactin.Testcases;

import java.util.Hashtable;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase103 extends Page{
	
	@Test(dataProvider="getSearchData")
	public void PastDateEntry(Hashtable<String, String> data) throws InterruptedException
	{
		String checkindate;
		String checkoutdate;
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		SearchPage Sp=login.dologin("adactin123", "adactin123");
		//entering data in the Search Page			
		Sp.SelectFromDropDown(data.get("Location"),"Location");
		Sp.SelectFromDropDown(data.get("Hotels"), "Hotels");
		Sp.SelectFromDropDown(data.get("RoomType"), "RoomType");
		//Sp.SelectDropdownRooms("Numberrooms");
		Sp.SelectFromDropDown(data.get("Numberrooms"), "Numberrooms");
		
		checkindate=data.get("CheckInDate");
		checkindate=checkindate.replace("\"", "");
		
		checkoutdate=data.get("CheckOutDate");
		checkoutdate=checkoutdate.replace("\"", "");
				
		Sp.EnterValues("CheckIn_Date",checkindate);		
		Sp.EnterValues("CheckOut_Date",checkoutdate);
		
		Sp.SelectDropdownRooms(data.get("AdultsperRoom"),"AdultsperRoom");
		Sp.SelectDropdownRooms(data.get("ChildrenperRoom"),"ChildrenperRoom");
		
		boolean flag=Sp.PastDateValidation(checkindate, checkoutdate);
		
		String url=driver.getTitle();
		System.out.println(" URL before Search is -"+url);
		Sp.Click("Search");
		Thread.sleep(3000);
		//Validation after  submitting the request
		String urlsearch;
		urlsearch=driver.getTitle();
		System.out.println("URL after Search is -"+urlsearch);
		boolean page=Sp.PageValidation(url, urlsearch);
		String err_message;  
		if(page)
		{
		err_message=Sp.GetErrorMessage("checkin_span");			  
	    if(!flag)		   	 
			Assert.assertTrue("Test Failed.The actual err message is :"+err_message+" .The expected error message is : "+data.get("Expected_massage"), err_message.equals(data.get("Expected_massage")));				
		else
			Assert.assertTrue("The dates entered are proper", true);	
		}
		else
			Assert.assertTrue("Error message not displayed even if dates are already past", false);
	}
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return TestUtil.getData("TC_103", xls);
	}
	
	@AfterTest
	public void Teardown()
	{
		//driver.close();
	}
	

}
