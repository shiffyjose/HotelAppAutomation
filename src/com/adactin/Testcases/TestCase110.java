package com.adactin.Testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.adactin.page.BookAHotel.BookAHotel;
import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.page.selecthotel.SelectHotel;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase110 extends Page{
	@Test(dataProvider="getSearchData")
	public void PriceWithGSTValidation(Hashtable<String, String> data)
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
		
		SelectHotel Sh=Sp.Search("Search");
		BookAHotel Bh=Sh.HotelSelection();
		
		String[] Res=Bh.CalacuateTotalPrice();		
		Assert.assertTrue(Boolean.valueOf(Res[2]), "Final Billed Price calculated is not proper.Actual Price:"+Res[0]+" Expected Price:"+Res[1]);
	}
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return TestUtil.getData("TC_104", xls);
	}

	@AfterTest
	public void Teardown()
	{
		driver.close();
	}
}
