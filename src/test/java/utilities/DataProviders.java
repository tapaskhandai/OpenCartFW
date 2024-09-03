package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {

		String file_Path = ".\\testData\\UsernameAndPassword.xlsx"; // taking xl file from testData
		ExcelUtility xlutil = new ExcelUtility(file_Path); // creating an object of ExcelUtility
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("sheet1", 1);

		String logindata[][] = new String[totalRows][totalCols]; // created for two dimension array which can store

		for (int i = 1; i <= totalRows; i++) { // 1 //read the data from xl storing in two dimensional array

			for (int j = 0; j < totalCols; j++) { // 0 i is row j is col

				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // 1 0
			}
		}

		return logindata;

	}

}
