package com.adactin.page.BookedItenary;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookedItenaryPage {
	public WebDriver driver=null;
	public BookedItenaryPage(WebDriver dr)
	{
		driver=dr;
	}
	
	public Map FindOrderDetailsEditable(String OrderNo)
	{
		WebElement CancelBtnTable=driver.findElement(By.xpath("//form[@id='booked_form']/table/tbody/tr[2]/td/table/tbody"));
		List<WebElement> Btns=CancelBtnTable.findElements(By.xpath("//*[@type='button']"));
		int count=Btns.size();
		
		for(int i=0;i<Btns.size();i++)
		{
			String BtnName=Btns.get(i).getAttribute("value");
			System.out.println("Button Names "+i+":"+BtnName);
			if(BtnName.equals(OrderNo))
			{
				Boolean flag;
				Map<String,Boolean> buffer=new HashMap<String,Boolean>();
				System.out.println("hurray:"+BtnName+"The ith element is "+i);
				String TableElement=null;
				//locating the row
				int k;
				k=i+2;
				WebElement nrow=driver.findElement(By.xpath("//form[@id='booked_form']/table/tbody/tr[2]/td/table/tbody/tr["+k+"]"));
				List<WebElement> all_rows= nrow.findElements(By.tagName("td"));
				for(int j=0;j<all_rows.size();j++)
				{
					
					flag=all_rows.get(j).findElement(By.tagName("input")).isEnabled();
					TableElement=all_rows.get(j).findElement(By.tagName("input")).getAttribute("value");
					buffer.put(TableElement, flag);
					
				}
				System.out.println("The editable field validation set result is"+buffer);
				return buffer;
			}
		}
		return null;
	}

	
	public String[] MyItenarydetails(String Order_no){
		
		WebElement CancelBtnTable=driver.findElement(By.xpath("//form[@id='booked_form']/table/tbody/tr[2]/td/table/tbody"));
		List<WebElement> Btns=CancelBtnTable.findElements(By.xpath("//*[@type='button']"));
		int count=Btns.size();
		String[] itenry=new String[15];
		
		for(int i=0;i<Btns.size();i++)
		{
			String BtnName=Btns.get(i).getAttribute("value");
			//System.out.println("Button Names "+i+":"+BtnName);
			if(BtnName.equals(Order_no))
			{
				int k;
				System.out.println("hurray");
				//locating the row
				k=i+2;
				WebElement nrow=driver.findElement(By.xpath("//form[@id='booked_form']/table/tbody/tr[2]/td/table/tbody/tr["+k+"]"));
				List<WebElement> all_rows= nrow.findElements(By.tagName("td"));
				for(int j=0;j<all_rows.size();j++)
				{					
					itenry[j]=all_rows.get(j).findElement(By.tagName("input")).getAttribute("value");
					System.out.println("col number;-"+j+"="+itenry[j]);
				}
				return itenry;				
			}
		}
		return null;
	}
	
	public String[] SearchOrder(String orderid) throws InterruptedException
	{
		String[] searchoutput=new String[14];
		try
		{
			driver.findElement(By.xpath("//input[@id='order_id_text']")).sendKeys(orderid);
			driver.findElement(By.xpath("//input[@id='search_hotel_id']")).click();
			Thread.sleep(1000);
			
			WebElement data=driver.findElement(By.xpath("//form[@id='booked_form']/table/tbody/tr[2]/td/table/tbody/tr[2]"));
			List<WebElement> datalist=data.findElements(By.tagName("td"));	
			for(int i=1;i<datalist.size();i++)
			{			
				searchoutput[i]=datalist.get(i).findElement(By.tagName("input")).getAttribute("value");
				System.out.println("searchoutput "+i+"="+searchoutput[i]);			   		
			}
		return searchoutput;	
		}
		catch(NoSuchElementException exception )
		{
			System.out.println("Order Id not found");
			return searchoutput=null;
		}	
}
	
	public void CancelOrder(String OrderNum) throws Exception
	{
		String initialwndhandle=driver.getWindowHandle();
		WebElement CancelBtnTable=driver.findElement(By.xpath("//form[@id='booked_form']/table/tbody/tr[2]/td/table/tbody"));
		List<WebElement> Btns=CancelBtnTable.findElements(By.xpath("//*[@type='button']"));
		int count=Btns.size();
		String[] itenry=new String[15];		
		for(int i=0;i<Btns.size();i++)
		{
			String BtnName=Btns.get(i).getAttribute("value");
			//System.out.println("Button Names "+i+":"+BtnName);
			if(BtnName.equals(OrderNum))
			{
				//clicking on the cancel button
				Btns.get(i).click();
				Thread.sleep(5000);

				Alert alert=driver.switchTo().alert();
				System.out.println( alert.getText());
		        alert.dismiss();	
		        driver.switchTo().window(initialwndhandle);		        
				break;
			}			
		}		
	}
}
