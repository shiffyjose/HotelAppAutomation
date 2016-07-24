package com.adactin.Testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.adactin.page.BookAHotel.BookAHotel;
import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.page.selecthotel.SelectHotel;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase111 extends Page {
	@Test(priority=1,dataProvider="getSearchData")
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
		String[] sData=Sh.GetDataInSelectPage();
		
		BookAHotel Bh=Sh.HotelSelection();
		String[] sSelectedData=Bh.GetData();
		SoftAssert displayassert=new SoftAssert();
		//in order to override assert run the test from the testng.xml,only then listeners willbe enabled.
		System.out.println(sData[1] +"= "+sSelectedData[1]);
		displayassert.assertTrue(sData[1].equals(sSelectedData[1]), "The expected data is:"+ sData[1]+". But the Actaul data:"+sSelectedData[1]);
		displayassert.assertTrue(sData[2].equals(sSelectedData[2]), "The expected data is:"+ sData[2]+". But the Actaul data:"+sSelectedData[2]);
		displayassert.assertTrue(sData[7].equals(sSelectedData[3]), "The expected data is:"+ sData[7]+". But the Actaul data:"+sSelectedData[3]);
		sData[3]=sData[3].replaceAll("[\\D]", "");
		sSelectedData[4]=sSelectedData[4].replaceAll("[\\D]", "");	
		displayassert.assertTrue(sData[3].equals(sSelectedData[4]), "The expected data is:"+ sData[3]+". But the Actaul data:"+sSelectedData[4]);
		sData[6]=sData[6].replaceAll("[\\D]", "");
		sSelectedData[5]=sSelectedData[5].replaceAll("[\\D]", "");		
		displayassert.assertTrue(sData[6].equals(sSelectedData[5]), "The expected data is:"+ sData[6]+". But the Actaul data:"+sSelectedData[5]);
		displayassert.assertTrue(sData[8].equals(sSelectedData[6]), "The expected data is:"+ sData[8]+". But the Actaul data:"+sSelectedData[6]);
		displayassert.assertTrue(sData[9].equals(sSelectedData[7]), "The expected data is:"+ sData[9]+". But the Actaul data:"+sSelectedData[7]);
		
		
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
