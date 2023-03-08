package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelManager {

   private static String fileSeparator = System.getProperty("file.separator");

   /**
    * This Function will Read All Row's data from an Excel file
    * 
    * @param fileName
    *           - Excel File name (It should be present in testdata folder)
    * @param sheetName
    *           - Excel Sheet Name
    * @return - HashMap Dataset
    * @throws IOException
    *            - IO Exception.
    */
   public Map<Integer, Map<String, String>> readAllData(String fileName, String sheetName) throws IOException {
      // Open Excel file
      File file = new File(System.getProperty("user.dir") + fileSeparator + "testdata" + fileSeparator + fileName + ".xlsx");

      FileInputStream inputStream = new FileInputStream(file);
      Workbook workbook = WorkbookFactory.create(inputStream);
      Sheet sheet = workbook.getSheet(sheetName);

      // Initialize map to hold all data
      Map<Integer, Map<String, String>> allData = new HashMap<Integer, Map<String, String>>();

      // Iterate through each row and column
      Iterator<Row> rowIterator = sheet.rowIterator();
      int rowIdx = 0;
      while (rowIterator.hasNext()) {
         Row row = rowIterator.next();
         Map<String, String> rowData = new HashMap<String, String>();
         Iterator<Cell> cellIterator = row.cellIterator();
         while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            rowData.put(sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue(), cell.getStringCellValue());
         }
         allData.put(rowIdx, rowData);
         rowIdx++;
      }

      // Close Excel file
      workbook.close();
      inputStream.close();

      return allData;
   }

   /**
    * This Function will Read All Row's data from an Excel file
    * 
    * @param sheetName
    *           - Shreetname
    * @return Object data
    */
   public static Object[][] getTestData(String fileName, String sheetName) {

      Object[][] data = null;
      try {
         File file = new File(System.getProperty("user.dir") + fileSeparator + "testdata" + fileSeparator + fileName + ".xlsx");
         FileInputStream inputStream = new FileInputStream(file);
         Workbook workbook = WorkbookFactory.create(inputStream);
         Sheet sheet = workbook.getSheet(sheetName);
         int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
         data = new Object[rowCount][sheet.getRow(0).getLastCellNum()];
         for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
               Cell cell = row.getCell(j);
               data[i - 1][j] = cell.toString();
            }
         }
         workbook.close();
         inputStream.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return data;
   }

   /**
    * This Function will Write All Row's data from an Excel file
    * 
    * @param fileName
    *           - fileName
    * @param sheetName
    *           - Sheet Name
    * @param rowNum
    *           - row Number
    * @param colNum
    *           - Column number
    * @param value
    *           - value which want to Store
    */
   public static void writeData(String fileName, String sheetName, int rowNum, int colNum, String value) {
      try {
         File file = new File(System.getProperty("user.dir") + fileSeparator + "testdata" + fileSeparator + fileName + ".xlsx");
         FileInputStream inputStream = new FileInputStream(file);
         Workbook workbook = WorkbookFactory.create(inputStream);
         Sheet sheet = workbook.getSheet(sheetName);
         Row row = sheet.getRow(rowNum);
         if (row == null) {
            row = sheet.createRow(rowNum);
         }
         Cell cell = row.getCell(colNum);
         if (cell == null) {
            cell = row.createCell(colNum);
         }
         cell.setCellValue(value);
         FileOutputStream outputStream = new FileOutputStream(file);
         workbook.write(outputStream);
         workbook.close();
         inputStream.close();
         outputStream.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
