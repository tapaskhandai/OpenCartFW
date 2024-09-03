package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String file_Path;

	public ExcelUtility(String file_Path) {

		this.file_Path = file_Path;
	}

	public int getRowCount(String sheetName) throws IOException {

		fis = new FileInputStream(file_Path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {

		fis = new FileInputStream(file_Path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;

	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {

		fis = new FileInputStream(file_Path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;

		try {
			data = formatter.formatCellValue(cell); // Returns the formatted value of cell as a String
		} catch (Exception e) {
			data = "";

		}
		workbook.close();
		fis.close();
		return data;

	}

	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

		File newFile = new File(file_Path);
		if (!newFile.exists()) { // If file does not exist then create new file
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(file_Path);
			workbook.write(fos);
		}

		fis = new FileInputStream(file_Path);
		workbook = new XSSFWorkbook(fis);
		if (workbook.getSheetIndex(sheetName) == -1) // If sheet not exist then create new
			workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);

		if (sheet.getRow(rownum) == null) // if row not exist create new row
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);

		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(file_Path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}

	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {

		fis = new FileInputStream(file_Path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();

	}
	
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {

		fis = new FileInputStream(file_Path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();

	}
}
