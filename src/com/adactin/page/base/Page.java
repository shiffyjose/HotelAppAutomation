package com.adactin.page.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.adactin.page.landingpage.SearchPage;
import com.adactin.page.landingpage.TopMenu;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.Xls_Reader;

public class Page {

	public static Properties CONFIG=null;
	public static Properties OR=null;
	public static WebDriver driver=null;
	public WebDriver mozilla=null;
	public WebDriver chrome=null;
	public static boolean IsloggedIn=false;
	public static TopMenu topmenu=null;
	public static Xls_Reader xls=null;
		
	public Page()
	{
		if(driver==null)
		{
			try {
				//CONFIG load
				xls=new Xls_Reader(System.getProperty("user.dir")+"\\TestCases.xlsx");
				CONFIG=new Properties();				
				FileInputStream Fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\adactin\\config\\config.properties");				
				CONFIG.load(Fs);
				//OR Load
				OR=new Properties();
				FileInputStream Fo=new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\adactin\\config\\OR.properties");
				OR.load(Fo);
				System.out.println(OR.getProperty("username"));
				if(CONFIG.getProperty("browser").equals("Mozilla"))				
					driver=new FirefoxDriver();
				else if(CONFIG.getProperty("browser").equals("IE"))
					driver=new InternetExplorerDriver();
				else if(CONFIG.getProperty("browser").equals("Chrome"))
					{
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\ChromeDriver\\chromedriver.exe");
						driver=new ChromeDriver();	
					}
					
				} 
			
			catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}						
		}
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(CONFIG.getProperty("URL"));
		topmenu=new TopMenu();
		
	}
	
	
			//Verification
			public boolean IsElementPresent(String xPathKey){
				try{
					driver.findElement(By.xpath(OR.getProperty(xPathKey)));
				}
				catch(Exception e){
					return false;
				}
				return true;
			}
			//finds the link
			public boolean IsLinkPresent(String linktext)
			{
				try{
					driver.findElement(By.linkText(linktext));
				}
				catch(Exception e){
					return false;
				}
				return true;
			}
			
			/*public SearchPage GotoLandingPage()
			{
				SearchPage Sp=lp.dologin(user_name, pwd)
				
				return null;
				
			}*/
			
			public void quit()
			{
				driver.close();
			}
			
}
