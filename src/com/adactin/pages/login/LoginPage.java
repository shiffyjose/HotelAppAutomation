package com.adactin.pages.login;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.util.Constants;

public class LoginPage extends Page {	
	
	public WebDriver driver;
	//For login-xpath
	@FindBy(xpath=Constants.username)	
	public WebElement username;
	
	@FindBy(xpath=Constants.password)	
	public WebElement password;
	
	@FindBy(xpath=Constants.login)	
	public WebElement Login;
	
	@FindBy(xpath=Constants.logout)
	public WebElement Logout;
		
	public LoginPage(WebDriver dr)
	{
		driver=dr;
	}
	
	public SearchPage dologin(String user_name,String pwd) 
	{
		System.out.println(username +" value");				
		username.sendKeys(user_name);
		password.sendKeys(pwd);
		Login.click();	
		WebDriverWait wait=new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty("logout"))));
		if(driver.findElement(By.xpath(OR.getProperty("logout"))).isDisplayed())
		{	
			System.out.println("LOG OUT displayed");
			IsloggedIn=true;
			SearchPage sp=PageFactory.initElements(this.driver,SearchPage.class); 
			return sp; 					
		}
		else
			return null;
	}	
}
