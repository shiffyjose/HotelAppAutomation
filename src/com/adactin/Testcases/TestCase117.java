package com.adactin.Testcases;

//import java.util.Hashtable;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.adactin.page.BookedItenary.BookedItenaryPage;
import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase117 extends Page {

@Test(dataProvider="Orderdata")
public void SearchOrderId(Hashtable<String, String> data) throws InterruptedException
{
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	SearchPage Sp=login.dologin("adactin123", "adactin123");
	BookedItenaryPage Bip=Sp.GotoBookedItenary();	
	String[] BeforeSearch=new String[15];
	String[] AfterSearch=new String[15];
	//BeforeSearch=Bip.MyItenarydetails(data.get("OrderId"));
	//AfterSearch=Bip.SearchOrder(data.get("OrderId"));
	//String OrderId="G353VBMMJX";
	BeforeSearch=Bip.MyItenarydetails("Cancel "+ data.get("OrderNumber"));
	AfterSearch=Bip.SearchOrder(data.get("OrderNumber"));
	SoftAssert softAssert=new SoftAssert();
	for(int i=1;i<14;i++)
	{
		System.out.println("Result is :"+ "Values are "+AfterSearch[i]+","+BeforeSearch[i]);
		softAssert.assertTrue(BeforeSearch[i].equals(AfterSearch[i]), "The details do not match.Values are "+AfterSearch[i]+","+BeforeSearch[i]);
	}
	softAssert.assertAll();

}
@AfterTest
public void Teardown()
{
	driver.close();
}

@DataProvider
public Object[][] Orderdata()
{
	return TestUtil.getData("TC_117", xls);
}
}
