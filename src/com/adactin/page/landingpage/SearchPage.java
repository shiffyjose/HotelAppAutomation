package com.adactin.page.landingpage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.adactin.page.BookedItenary.BookedItenaryPage;
import com.adactin.page.base.Page;
import com.adactin.page.selecthotel.SelectHotel;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class SearchPage {
	public WebDriver driver;
	public Properties OR=null;
	
	public SearchPage(WebDriver dr)
	{
		driver=dr;
		OR=new Properties();
		FileInputStream Fo;
		try {
			Fo = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\adactin\\config\\OR.properties");
			OR.load(Fo);
			} 
		
	     catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void SelectFromDropDown(String Element,String Id)
	{
		WebElement dropbox=driver.findElement(By.id(OR.getProperty(Id)));
		Select Sel=new Select(dropbox);
		List<WebElement> box_options=Sel.getOptions();
		String text;
		for(int i=0;i<box_options.size();i++)
		{
			text=box_options.get(i).getText();
			System.out.println(text);
			if(text.equals(Element))
			{
				Sel.selectByValue(text);
				System.out.println("Got the text"+text);
				break;
			}
		}
	}
	
	public void EnterValues(String xpath,String Element) 
	{
		try{
			driver.findElement(By.xpath(OR.getProperty(xpath))).clear();
			//commented for check in date
			//driver.findElement(By.xpath(OR.getProperty(xpath))).sendKeys(OR.getProperty(Element));
			driver.findElement(By.xpath(OR.getProperty(xpath))).sendKeys(Element);
		}
		catch(ElementNotFoundException e)
		{
			System.out.println("Could not find the element");
			e.addSuppressed(e);
			
		}		
	}
	
	public void Click(String button)
	{
	try{
		 driver.findElement(By.xpath(OR.getProperty(button))).click();
		}
		catch(ElementNotFoundException e)
		{
			System.out.println("Could not find the element");
			e.addSuppressed(e);
			
		}
	}
	public void SelectDropdownRooms(String text,String Id)
	{
		WebElement dropbox=driver.findElement(By.id(OR.getProperty(Id)));
		Select Sel=new Select(dropbox);
		Sel.selectByVisibleText(text);
		
		
	}
	public boolean DateValidation(String checkindate,String checkoutdate)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		//Date checkindateParsed;
		boolean flag;
		try {
			Date checkindateParse = sdf.parse(checkindate);
			Date checkoutdateParse=sdf.parse(checkoutdate);			
			flag=checkoutdateParse.after(checkindateParse);
			System.out.println(flag +"Date validation");
			return flag;
			
		} catch (ParseException e) {
			e.printStackTrace();	
			return false;
		}	
		
	}
	public boolean PageValidation(String urlbefore,String urlafter)
	{
		 if(urlbefore.equals(urlafter))			 	
			 return true;
		 else
			 return false;
	}	
	public String GetErrorMessage(String Id)
	{
		String Err_Msg;
		try{
		Err_Msg=driver.findElement(By.id(Id)).getText();
		return Err_Msg;
		}
		catch(NoSuchElementException e)
		{			
			e.printStackTrace();
			return Err_Msg="The error message is not displayed and naviagted to next page";			
		}
		
	}
	
	public boolean PastDateValidation(String checkindate,String checkoutdate) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		//Date checkindateParsed;
		boolean flag=true;
		try {
			Date checkindateParse = sdf.parse(checkindate);
			Date checkoutdateParse=sdf.parse(checkoutdate);
			Date Today=Calendar.getInstance().getTime();			
			String Todaysformated=sdf.format(Today);
			Date TodaysDate=sdf.parse(Todaysformated);
			if(!checkindateParse.after(TodaysDate) && !checkoutdateParse.after(TodaysDate))
				return flag=false;
			
			else if(!checkindateParse.after(TodaysDate) || !checkoutdateParse.after(TodaysDate))
				return flag=false;
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return flag;
					
	}
	
	
	public SelectHotel Search(String button)
	{
		try{
			 driver.findElement(By.xpath(OR.getProperty(button))).click();
			 return PageFactory.initElements(driver, SelectHotel.class);
			}
			catch(ElementNotFoundException e)
			{
				System.out.println("Could not find the element");
				e.addSuppressed(e);
				
			}
		
		return PageFactory.initElements(driver, SelectHotel.class);
	}
	public BookedItenaryPage GotoBookedItenary()
	{
		//driver.findElement(By.xpath("//a[@href='BookedItinerary.php']")).click();
		driver.findElement(By.xpath("//a[@href='BookedItinerary.php']")).click();
		return PageFactory.initElements(driver, BookedItenaryPage.class);
	}
	

}
