package com.adactin.page.selecthotel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.adactin.page.BookAHotel.BookAHotel;



public class SelectHotel {
	WebDriver driver;
	Properties OR=null;
	public SelectHotel(WebDriver dr) {
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

	
	public boolean ValidateTableContent(String item)
	{
		System.out.println("Entered validate table content");
		WebElement wTable=driver.findElement(By.xpath((OR.getProperty("SearchTable"))));
		
		List<WebElement> Col_list=wTable.findElements(By.tagName("td"));
		String values;
		for(int i=1;i<Col_list.size();i++)
		{
			values=Col_list.get(i).findElement(By.tagName("input")).getAttribute("value");
			System.out.println(values);
			if(values.equals(item))
				return true;
		}
		return false;
	}
	
	public String[] CalcuateTotalPrice()
	{
		String[] PriceRes=new String[3];
		
		String Rooms =driver.findElement(By.id("rooms_0")).getAttribute("value");
		int nRooms=Integer.parseInt(Rooms.replaceAll("[\\D]", ""));
		
		String pricepernight=driver.findElement(By.id("price_night_0")).getAttribute("value");
		int nPrice=Integer.parseInt(pricepernight.replaceAll("[\\D]", ""));
		
		String Nightno=driver.findElement(By.id("no_days_0")).getAttribute("value");
		int nNights=Integer.parseInt(Nightno.replaceAll("[\\D]", ""));
		
		int Expected_TotalPrice=nRooms*nPrice*nNights;
				
		String TotalPrice=driver.findElement(By.id("total_price_0")).getAttribute("value");
		int ActualTotalPrice=Integer.parseInt(Nightno.replaceAll("[\\D]", ""));	
		
		PriceRes[0]=TotalPrice;
		PriceRes[1]="AUD $"+Integer.toString(Expected_TotalPrice);
		if(ActualTotalPrice==Expected_TotalPrice)
			PriceRes[2]="true";
		else
			PriceRes[2]="false";
		
		return PriceRes;
		
	}
	
	public BookAHotel HotelSelection()
	{
		driver.findElement(By.id("radiobutton_0")).click();
		driver.findElement(By.id("continue")).click();
		System.out.println("driver is "+ driver);
		return PageFactory.initElements(driver, BookAHotel.class);
		
	}
	
	/*public String[] GetDisplayeddata()
	{
		//xpath="//form[@id='select_form']/table/tbody/tr[2]/td/table/tbody/tr[2]"
		String [] data=new String[5];
		data[0]=driver.findElement(By.id("hotel_name_0")).getAttribute("value");
		data[1]=driver.findElement(By.id("room_type_0")).getAttribute("value");
		data[2]=driver.findElement(By.id("rooms_0")).getAttribute("value");
		data[3]=driver.findElement(By.id("arr_date_0")).getAttribute("value");
		data[4]=driver.findElement(By.id("dep_date_0")).getAttribute("value");
		return data;
	}*/
	
	public String[] GetDataInSelectPage()
	{
		String[] SetData=new String[10];
		WebElement wTable=driver.findElement(By.xpath((OR.getProperty("SearchTable"))));
		List<WebElement> Col_list=wTable.findElements(By.tagName("td"));
		String values;
		for(int i=1;i<Col_list.size();i++)
		{
			SetData[i]=values=Col_list.get(i).findElement(By.tagName("input")).getAttribute("value");			
			System.out.println("####"+SetData[i]+"###");
		}
		return SetData;
	}
}
