package com.adactin.util;

import java.util.Hashtable;

public class TestUtil {
	
	
	public static Boolean IsExecutable(Xls_Reader xlsRead,String nSheetName,String Testcase)
	{
		int count=xlsRead.getRowCount(nSheetName);		
		System.out.println(count);
		for(int x=2;x<=count;x++)
		{
			//System.out.println("Data is: "+xlsRead.getCellData("TestSuites", colname, x));
			if(xlsRead.getCellData("TestSuites", "TCID", x).equals(Testcase))
			{
				if(xlsRead.getCellData("TestSuites","Runmode", x).equals("Y"))
				return true;
			}					
		}
		return false;	
	}
	
	public static Object[][] getData(String testname,Xls_Reader xls)
	{
		
		int testname_count=0;
		for(int i=1;i<=xls.getRowCount("TestData");i++ )
		{
			if((xls.getCellData("TestData", 0, i)).equals(testname))
			{
				testname_count=i;
				break;
			}
		}
		System.out.println("Test:"+testname +"starts from-" +testname_count);
		int totCol=0;
		int colnum_start=testname_count+1;
		while(!xls.getCellData("TestData", totCol, colnum_start).equals(""))
		{
			totCol++;
		}
		System.out.println("Total cols="+totCol);
		int datarownum_start=testname_count+2;
		int totrows=0;
		while(!xls.getCellData("TestData", 0, datarownum_start+totrows).equals("")){
			totrows++;
		}
		System.out.println("Total rows="+totrows);
		 Object[][] data=new Object[totrows][1];
		 int index=0;
		//iterate through cols and rows
		Hashtable<String,String> table=null;
		for(int row=datarownum_start;row<totrows+datarownum_start;row++)
		{
			table=new Hashtable<String, String>();
			for(int col=0;col<totCol;col++)
			{
				table.put(xls.getCellData("TestData", col, datarownum_start-1), xls.getCellData("TestData", col, row));
				System.out.println(xls.getCellData("TestData", col, row)+"--");
			}
			data[index][0]=table;
			index++;
		}	
			
		return data;
	}


}
