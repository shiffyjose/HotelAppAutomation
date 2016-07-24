package Test;

import com.adactin.util.Xls_Reader;

public class GetRunmode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Xls_Reader rd=new Xls_Reader(System.getProperty("user.dir")+"\\TestCases.xlsx");
	//System.out.println(rd.getCellData("TestSuites","Test1", 3));
	
	IsExecutable(rd,"TestSuites","TCID","Test1");
	}
	
	public static Boolean IsExecutable(Xls_Reader xlsRead,String nSheetName,String colname,String Testcase)
	{
		int count=xlsRead.getRowCount(nSheetName);		
		System.out.println(count);
		for(int x=2;x<=count;x++)
		{
			System.out.println("Data is: "+xlsRead.getCellData("TestSuites", colname, x));
			if(xlsRead.getCellData("TestSuites", "TCID", x).equals("Test1"))
			{
				if(xlsRead.getCellData("TestSuites","Runmode", x).equals("Y"))
				return true;
			}					
		}
		return false;	
	}

}
