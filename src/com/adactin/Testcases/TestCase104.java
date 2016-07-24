package com.adactin.Testcases;

import java.util.Hashtable;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.page.selecthotel.SelectHotel;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase104 extends Page{
	@Test(dataProvider="getSearchData")
	public void ValidationOfData(Hashtable<String, String> data)
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
		System.out.println(Sh.ValidateTableContent(data.get("Locat")));
		//Validation after  submitting the request
		//Assert.assertTrue("Not displayed the Item:"+ "Location", Sh.ValidateTableContent(data.get("Locat")));	
		SoftAssert displayasserts=new SoftAssert();
		displayasserts.assertTrue(Sh.ValidateTableContent(data.get("Location")), "Not displayed the Item:"+ "Location");
		displayasserts.assertTrue(Sh.ValidateTableContent(data.get("Hotels")),"Not displayed the Item:"+ "Hotels");			
		displayasserts.assertTrue(Sh.ValidateTableContent(data.get("RoomType")),"Not displayed the Item: "+ "RoomType");
		displayasserts.assertTrue(Sh.ValidateTableContent(checkindate),"Mismatch in the expected item: "+ "CheckInDate");
		displayasserts.assertTrue(Sh.ValidateTableContent(checkoutdate),"Mismatch in the expected item:"+ "checkoutdate");
		String[] Res=Sh.CalcuateTotalPrice();	
		displayasserts.assertTrue(Boolean.valueOf(Res[2]),"Total Price calculated is not proper.Actual Price:"+Res[0]+" Expected Price:"+Res[1]);
		//Assert.assertTrue("Total Price calculated is not proper.Actual Price:"+Res[0]+" Expected Price:"+Res[1], Boolean.valueOf(Res[2]));
		displayasserts.assertAll();
	}
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return TestUtil.getData("TC_104", xls);
	}
	@AfterTest
	public void Teardown()
	{
		//driver.close();
	}
}
