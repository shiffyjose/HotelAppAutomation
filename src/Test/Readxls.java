package Test;

import com.adactin.util.TestUtil;
import com.adactin.util.Xls_Reader;

public class Readxls {

	public static void main(String[] args) {
		Xls_Reader xls=new Xls_Reader("C:\\SHIFFY\\FromHp\\Automation\\Shiffy\\Selenium\\AdactinPOM\\TestCases.xlsx");
		TestUtil.getData("TC_101", xls);
		TestUtil.getData("TC_102", xls);
		

	}

}
