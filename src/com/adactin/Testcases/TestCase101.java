package com.adactin.Testcases;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;
import com.adactin.util.Xls_Reader;

public class TestCase101 extends Page {
	
	@BeforeTest
	public void CheckRunMode()
	{
		System.out.println("###################33");
		//Xls_Reader xls=new Xls_Reader(System.getProperty("user.dir")+"\\TestCases.xlsx");
		if(!TestUtil.IsExecutable(xls, "TestSuites", "LoginTest"))
			throw new SkipException("Skipping the test");
	}
	@Test
	public void LoginTest()
	{
		System.out.println("In Test");
		//LoginPage login=new LoginPage(driver);
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		login.dologin("adactin123", "adactin123");
		SearchPage Sp=PageFactory.initElements(driver,SearchPage.class );
		Assert.assertNotNull(Sp,"Login not successfull" );
	}
	@AfterTest
	public void Teardown()
	{
		driver.close();
	}
	
	

}
