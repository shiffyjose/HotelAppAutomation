package com.adactin.page.BookAHotel;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.adactin.page.base.Page;
import com.adactin.page.bookingconfirmation.BookingConfirmation;
import com.adactin.util.TestUtil;

public class BookAHotel {
	public WebDriver driver;
	
	public BookAHotel(WebDriver dr)
	{
		driver=dr;
	}
	
	public String[] CalacuateTotalPriceWithGST()
	{
		String[] PriceRes=new String[3];
		
		String Rooms =driver.findElement(By.id("room_num_dis")).getAttribute("value");
		int nRooms=Integer.parseInt(Rooms.replaceAll("[\\D]", ""));
		
		String pricepernight=driver.findElement(By.id("price_night_dis")).getAttribute("value");
		int nPrice=Integer.parseInt(pricepernight.replaceAll("[\\D]", ""));
		
		String Nightno=driver.findElement(By.id("total_days_dis")).getAttribute("value");
		int nNights=Integer.parseInt(Nightno.replaceAll("[\\D]", ""));
		
		float nGST=(float) (nRooms*nPrice*nNights*0.1);
		float TotalValue=(nRooms*nPrice*nNights)+nGST;
		
		String Expected_FinalPrice=String.valueOf(TotalValue);
				
		String FinalPrice=driver.findElement(By.id("final_price_dis")).getAttribute("value");		
		String ActualFinalPrice=FinalPrice.replaceAll("[\\D]", "");
		
		PriceRes[0]=FinalPrice;
		PriceRes[1]="AUD $"+ Expected_FinalPrice;
		if(ActualFinalPrice.equals(Expected_FinalPrice))
			PriceRes[2]="true";
		else
			PriceRes[2]="false";
		
		return PriceRes;
	}
	
	public String[] CalacuateTotalPrice()
	{
		String[] PriceRes=new String[3];
		
		String Rooms =driver.findElement(By.id("room_num_dis")).getAttribute("value");
		int nRooms=Integer.parseInt(Rooms.replaceAll("[\\D]", ""));
		
		String pricepernight=driver.findElement(By.id("price_night_dis")).getAttribute("value");
		int nPrice=Integer.parseInt(pricepernight.replaceAll("[\\D]", ""));
		
		String Nightno=driver.findElement(By.id("total_days_dis")).getAttribute("value");
		int nNights=Integer.parseInt(Nightno.replaceAll("[\\D]", ""));		
		
		String Expected_FinalPrice=String.valueOf(nRooms*nPrice*nNights);
				
		String FinalPrice=driver.findElement(By.id("total_price_dis")).getAttribute("value");		
		String ActualFinalPrice=FinalPrice.replaceAll("[\\D]", "");
		
		PriceRes[0]=FinalPrice;
		PriceRes[1]="AUD $"+ Expected_FinalPrice;
		if(ActualFinalPrice.equals(Expected_FinalPrice))
			PriceRes[2]="true";
		else
			PriceRes[2]="false";
		
		return PriceRes;
	}
	
	public String[] GetData()
	{
		String[] sData=new String[10];
		sData[1]=driver.findElement(By.id("hotel_name_dis")).getAttribute("value");
		sData[2]=driver.findElement(By.id("location_dis")).getAttribute("value");
		sData[3]=driver.findElement(By.id("room_type_dis")).getAttribute("value");
		sData[4]=driver.findElement(By.id("room_num_dis")).getAttribute("value");
		sData[5]=driver.findElement(By.id("total_days_dis")).getAttribute("value");
		sData[6]=driver.findElement(By.id("price_night_dis")).getAttribute("value");
		sData[7]=driver.findElement(By.id("total_price_dis")).getAttribute("value");
		return sData;		
	}
	
	public void EnterPersonnelData(Hashtable<String, String> data)
	{	
		System.out.println("Started entering details"+ data.get("FirstName"));
		
		driver.findElement(By.id("first_name")).sendKeys(data.get("FirstName"));
		driver.findElement(By.id("last_name")).sendKeys(data.get("LastName"));
		driver.findElement(By.id("address")).sendKeys(data.get("BillingAddr"));
		driver.findElement(By.id("cc_num")).sendKeys(data.get("CreditCardNo"));
		
		Select sel=new Select(driver.findElement(By.id("cc_type")));
		sel.selectByIndex(2);
		
		Select Exp_month=new Select(driver.findElement(By.id("cc_exp_month")));
		Exp_month.selectByIndex(2);
		
		Select Exp_year=new Select(driver.findElement(By.id("cc_exp_year")));
		Exp_year.selectByIndex(10);
		
		driver.findElement(By.id("cc_cvv")).sendKeys(data.get("CVV"));
	}
	
	public BookingConfirmation BookNowClick()
	{
		driver.findElement(By.id("book_now")).click();
		return PageFactory.initElements(driver, BookingConfirmation.class);
	}
}
