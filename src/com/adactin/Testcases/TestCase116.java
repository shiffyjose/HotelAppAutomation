package com.adactin.Testcases;

import java.util.Hashtable;

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

public class TestCase116 extends Page {
	
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
		
		//System.out.println("value of sh "+ Sh);
	}
	
	@Test(priority=2,dataProvider="getPersonelData")
	public void CompareBookedWithItenary(Hashtable<String, String> data)
	{
		
				
		BookAHotel Bh=Sh.HotelSelection();
		Bh.EnterPersonnelData(data);
		BookingConfirmation Bc=Bh.BookNowClick();
		
		String[] Bookdetails=new String[13];
		Bookdetails=Bc.GetDisplayedBookedDetails();
		BookedItenaryPage Bip=Bc.MyItenaryClick();
		
		String OrderNo="Cancel "+Bookdetails[0];
		String[] Itenarydetails=new String[15];
		Itenarydetails=Bip.MyItenarydetails(OrderNo);
		SoftAssert softassert=new SoftAssert();
		softassert.assertTrue(Bookdetails[0].equals(Itenarydetails[1]), "Order Number does not match:Bookdetails="+Bookdetails[0]+" Itenarydetails="+Itenarydetails[1]);
		softassert.assertTrue(Bookdetails[1].equals(Itenarydetails[3]), "HotelName does not match:Bookdetails="+Bookdetails[1]+" Itenarydetails="+Itenarydetails[3]);
		softassert.assertTrue(Bookdetails[2].equals(Itenarydetails[4]), "Location Number does not match:Bookdetails="+Bookdetails[2]+" Itenarydetails="+Itenarydetails[4]);
		softassert.assertTrue(Bookdetails[3].equals(Itenarydetails[5]), "Rooms does not match:Bookdetails="+Bookdetails[3]+" Itenarydetails="+Itenarydetails[5]);
		softassert.assertTrue(Bookdetails[4].equals(Itenarydetails[6]), "First name does not match:Bookdetails="+Bookdetails[4]+" Itenarydetails="+Itenarydetails[6]);
		softassert.assertTrue(Bookdetails[5].equals(Itenarydetails[7]), "Last name does not match:Bookdetails="+Bookdetails[5]+" Itenarydetails="+Itenarydetails[7]);
		softassert.assertTrue(Bookdetails[6].equals(Itenarydetails[8]), "Arrival date date does not match:Bookdetails="+Bookdetails[6]+" Itenarydetails="+Itenarydetails[8]);
		softassert.assertTrue(Bookdetails[7].equals(Itenarydetails[9]), "Departure date does not match:Bookdetails="+Bookdetails[7]+" Itenarydetails="+Itenarydetails[9]);
		softassert.assertTrue(Bookdetails[9].equals(Itenarydetails[11]), "Room type does not match:Bookdetails="+Bookdetails[9]+" Itenarydetails="+Itenarydetails[11]);
		softassert.assertTrue(Bookdetails[10].equals(Itenarydetails[12]), "Price per night does not match:Bookdetails="+Bookdetails[10]+" Itenarydetails="+Itenarydetails[12]);
		softassert.assertTrue(Bookdetails[11].equals(Itenarydetails[13]), "Total price with gst does not match:Bookdetails="+Bookdetails[11]+" Itenarydetails="+Itenarydetails[13]);
		softassert.assertAll();				
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
