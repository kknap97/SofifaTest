package helpers;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;


public class ExcelHelper {

    public static Object[][] readExcelFile(File file) throws IOException {
        InputStream inputStream = new FileInputStream("src\\test\\resources\\PlayersData.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        Sheet sheet = xssfWorkbook.getSheetAt(0);
        int colNumbers = sheet.getRow(0).getLastCellNum();
        int rowNumbers = sheet.getLastRowNum();

        String data[][] = new String[rowNumbers][colNumbers];
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 0; i<rowNumbers; i++){
            Row row = sheet.getRow(i + 1);
            for(int j = 0;  j<row.getLastCellNum(); j++ ){
                Cell cell = row.getCell(j);
                data[i][j] = dataFormatter.formatCellValue(cell);
                //data[i][j] = row.getCell(j).getStringCellValue();
            }
        }
        return data;
    }
}
