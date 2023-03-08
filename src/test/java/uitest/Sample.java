package uitest;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import utils.ExcelManager;

public class Sample {

	@Test
	public void testExcel() {

		try {
			ExcelManager ex = new ExcelManager();
			Map<Integer, Map<String, String>> allData = ex.readAllData("api", "API Test");
			for (Map.Entry<Integer, Map<String, String>> rowEntry : allData.entrySet()) {
				Map<String, String> rowData = rowEntry.getValue();
				for (Map.Entry<String, String> cellEntry : rowData.entrySet()) {
					System.out.println(cellEntry.getKey() + " = " + cellEntry.getValue());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
