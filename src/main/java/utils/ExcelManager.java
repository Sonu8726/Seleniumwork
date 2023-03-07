package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	private static final String FILE_NAME = "example.xlsx"; // Excel file name

	/**
	 * Method to read data from an Excel sheet
	 *
	 * @param sheetName - Name of the sheet to be read
	 * @return List of maps containing data read from the sheet
	 * @throws IOException
	 */
	public static List<Map<String, String>> readData(String sheetName) throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(FILE_NAME));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Row headerRow = sheet.getRow(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Map<String, String> rowData = new HashMap<String, String>();
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				rowData.put(headerRow.getCell(j).getStringCellValue(), cell.getStringCellValue());
			}
			data.add(rowData);
		}
		workbook.close();
		inputStream.close();
		return data;
	}

	// Method to write data to an Excel file
	public static void writeData(String sheetName, String[][] data) {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);

		int rowNum = 0;
		for (String[] rowData : data) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (String cellData : rowData) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(cellData);
			}
		}

		try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to update data in an Excel file
	public static void updateCell(String filePath, String sheetName, int rowNum, int colNum, String cellValue)
			throws IOException {
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(colNum);
		cell.setCellValue(cellValue);
		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(filePath);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
