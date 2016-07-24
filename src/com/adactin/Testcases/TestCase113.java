package com.adactin.Testcases;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.adactin.page.BookAHotel.BookAHotel;
import com.adactin.page.base.Page;
import com.adactin.page.bookingconfirmation.BookingConfirmation;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.page.selecthotel.SelectHotel;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase113 extends Page {

	
	@Test(dataProvider="getSearchData")
	public void PastDateEntry(Hashtable<String, String> data)
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
		
		SelectHotel Sh=Sp.Search("Search");	
		String[] datacontent=new String[10];
		datacontent=Sh.GetDataInSelectPage();
		BookAHotel Bh=Sh.HotelSelection();
		String[] datadisplayed=new String[8];
		datadisplayed=Bh.GetData();
		BookingConfirmation Bc=Bh.BookNowClick();
		System.out.println(datacontent[1]+","+datadisplayed[1]);
		System.out.println(datacontent[7]+","+datadisplayed[3]);
		System.out.println(datacontent[3]+","+datadisplayed[4]);
		Assert.assertTrue(datacontent[1].equals(datadisplayed[1]),datacontent[1]+ " is expected and actual result is "+datadisplayed[1]);
		Assert.assertTrue(datacontent[7].equals(datadisplayed[3]), datacontent[7]+ " is expected and actual result is "+datadisplayed[3]);
		Assert.assertTrue(datacontent[3].equals(datadisplayed[4]), datacontent[3]+ " is expected and actual result is "+datadisplayed[4]);
			
	}	
	
	@DataProvider
	public Object[][] getSearchData()
	{
		List<Object[]> result = Lists.newArrayList();
		  result.addAll(Arrays.asList(dp1()));
		 // result.addAll(Arrays.asList(dp2()));
		  return result.toArray(new Object[result.size()][]);
		
	}
	
	
	public Object[][] dp1() {
		
		return TestUtil.getData("TC_104", xls);
	}

	//public Object[][] dp2() {
		//return TestUtil.getData("TC_113", xls);
	//}
	
	@AfterTest
	public void Teardown()
	{
		driver.close();
	}
}

	
