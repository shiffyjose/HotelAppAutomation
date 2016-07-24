package com.adactin.page.bookingconfirmation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.adactin.page.BookedItenary.BookedItenaryPage;

public class BookingConfirmation {
	WebDriver driver=null;
	
	public BookingConfirmation(WebDriver dr)
	{
		driver=dr;
	}
	
	public String GetGeneratedOrderNumber()
	{
		String Order_no = null;
		Order_no=driver.findElement(By.id("order_no")).getAttribute("value");		
		return Order_no;
	}
	
	public BookedItenaryPage MyItenaryClick()
	{
		driver.findElement(By.id("my_itinerary")).click();
		return PageFactory.initElements(driver, BookedItenaryPage.class);
	}
	
	public String[] GetDisplayedBookedDetails()
	{
		 String[] BookedData=new String[13];
		 
		 BookedData[0]=driver.findElement(By.id("order_no")).getAttribute("value");
		 BookedData[1]=driver.findElement(By.id("hotel_name")).getAttribute("value");
		 BookedData[2]=driver.findElement(By.id("location")).getAttribute("value");
		 BookedData[3]=driver.findElement(By.id("total_rooms")).getAttribute("value");
		 BookedData[4]=driver.findElement(By.id("first_name")).getAttribute("value");
		 BookedData[5]=driver.findElement(By.id("last_name")).getAttribute("value");
		 BookedData[6]=driver.findElement(By.id("arrival_date")).getAttribute("value");
		 BookedData[7]=driver.findElement(By.id("departure_text")).getAttribute("value");
		 //BookedData[8]=driver.findElement(By.id("total_days_dis")).getAttribute("value");	
		 BookedData[9]=driver.findElement(By.id("room_type")).getAttribute("value");
		 BookedData[10]=driver.findElement(By.id("price_night")).getAttribute("value");
		 BookedData[11]=driver.findElement(By.id("final_price")).getAttribute("value");			 
		 return BookedData;
	}
}
