
package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ExcelUtils {

	public static Object[][] getTestData(String path, String sheetName) {
	    try (Workbook workbook = WorkbookFactory.create(new File(path))) {
	        Sheet sheet = workbook.getSheet(sheetName);
	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int colCount = sheet.getRow(0).getLastCellNum();

	        Object[][] data = new Object[rowCount - 1][colCount]; // skip header row

	        for (int i = 1; i < rowCount; i++) {
	            Row row = sheet.getRow(i);
	            if (row == null) { // empty row
	                for (int j = 0; j < colCount; j++) data[i - 1][j] = "";
	                continue;
	            }

	            for (int j = 0; j < colCount; j++) {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

	                switch (cell.getCellType()) {
	                    case STRING:
	                        data[i - 1][j] = cell.getStringCellValue();
	                        break;
	                    case NUMERIC:
	                        if (DateUtil.isCellDateFormatted(cell)) {
	                            data[i - 1][j] = cell.getDateCellValue().toString();
	                        } else {
	                            data[i - 1][j] = String.valueOf((long) cell.getNumericCellValue());
	                        }
	                        break;
	                    case BOOLEAN:
	                        data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
	                        break;
	                    case BLANK:
	                        data[i - 1][j] = "";
	                        break;
	                    case FORMULA:
	                        data[i - 1][j] = cell.getCellFormula();
	                        break;
	                    default:
	                        data[i - 1][j] = cell.toString();
	                        break;
	                }
	            }
	        }

	        return data;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return new Object[0][0]; // safe fallback
	    }
	}

}

