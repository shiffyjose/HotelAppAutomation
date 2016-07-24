package com.adactin.Testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.adactin.page.BookAHotel.BookAHotel;
import com.adactin.page.BookedItenary.BookedItenaryPage;
import com.adactin.page.base.Page;
import com.adactin.page.bookingconfirmation.BookingConfirmation;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.page.selecthotel.SelectHotel;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase114 extends Page{
	 SelectHotel  Sh;
	 
	@Test(priority=1,dataProvider="getSearchData")
	public void loginandSearchhotels(Hashtable<String, String> data)
	{
		String checkindate;
		String checkoutdate;
		
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		SearchPage Sp=login.dologin("adactin123", "adactin123");
		//entering data in the Search Page			
		Sp.SelectFromDropDown(data.get("Location"),"Location");
		Sp.SelectFromDropDown(data.get("Hotels"), "Hotels");
		Sp.SelectFromDropDown(data.get("RoomType"), "RoomType");		
		Sp.SelectFromDropDown(data.get("Numberrooms"), "Numberrooms");
		
		checkindate=data.get("CheckInDate");
		checkindate=checkindate.replace("\"", "");
		
		checkoutdate=data.get("CheckOutDate");
		checkoutdate=checkoutdate.replace("\"", "");
				
		Sp.EnterValues("CheckIn_Date",checkindate);		
		Sp.EnterValues("CheckOut_Date",checkoutdate);
		
		Sp.SelectDropdownRooms(data.get("AdultsperRoom"),"AdultsperRoom");
		Sp.SelectDropdownRooms(data.get("ChildrenperRoom"),"ChildrenperRoom");
		
			Sh=Sp.Search("Search");
		;
		//System.out.println("value of sh "+ Sh);
	}
	
	@Test(priority=2,dataProvider="getPersonelData")
	public void OrderNumberVerification(Hashtable<String, String> data)
	{
		String Order_no;	
		//System.out.println("Print Sh"+Sh);
		BookAHotel Bh=Sh.HotelSelection();
		Bh.EnterPersonnelData(data);
		BookingConfirmation Bc=Bh.BookNowClick();
		
		Order_no=Bc.GetGeneratedOrderNumber();
		if(Order_no.equals(null))
			Assert.assertTrue(false, "The order number is null");
		else
		{
			System.out.println("The order number is :"+Order_no);
			Reporter.log("The order number generated is :"+Order_no);			
		}
		
	}
	@DataProvider
	public Object[][] getSearchData()
	{
		return TestUtil.getData("TC_104", xls);
	}

	@DataProvider
	public Object[][] getPersonelData()
	{
		return TestUtil.getData("TC_114", xls);
	}
	@AfterTest
	public void Teardown()
	{
		driver.close();
	}

}
