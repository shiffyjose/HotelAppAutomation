package com.adactin.Testcases;



import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import com.adactin.page.BookedItenary.BookedItenaryPage;
import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.Constants;

public class TestCase120 extends Page {
	
@Test	
public void checkTitles() throws Exception
{
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	String loginTitle=driver.getTitle();
	System.out.println("Login title is "+loginTitle);
	Assert.assertTrue(loginTitle.equals(Constants.LoginTitle), "The login page titles do not match.The actual title is "+loginTitle+" .But the expected is "+Constants.LoginTitle);
	
	SearchPage Sp=login.dologin("adactin123", "adactin123");	
	String landingPageTitle=Sp.driver.getTitle();
	System.out.println("landingPage Title is"+ landingPageTitle);
	Assert.assertTrue(landingPageTitle.equals(Constants.SearchPageTitle), "The landing/SearchPage titles do not match.The actual title is "+landingPageTitle+" .But the expected is "+Constants.SearchPageTitle);
	
	BookedItenaryPage Bip=Sp.GotoBookedItenary();
	String BookItenaryTitle=driver.getTitle();
	System.out.println("BookedItenary Title is "+BookItenaryTitle);
	Assert.assertTrue(BookItenaryTitle.equals(Constants.BookedItenaryTitle), "The booked iternary page title do not match.The actual title is "+BookItenaryTitle+" .But the expected is "+Constants.BookedItenaryTitle);
}
@AfterTest
public void Teardown()
{
	driver.close();
}

}
