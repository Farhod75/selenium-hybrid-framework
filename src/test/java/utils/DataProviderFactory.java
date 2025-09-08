package utils;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;
import java.io.*;
import java.util.*;

public class DataProviderFactory {

    @DataProvider(name = "loginData", parallel = true)
    public static Object[][] getLoginData() throws Exception {
        String source = ConfigUtil.getProperty("data.source", "csv");
        if (source.equalsIgnoreCase("excel")) {
            return readExcel(ConfigUtil.getProperty("excel.path", ""));
        } else {
            return readCSV(ConfigUtil.getProperty("csv.path", ""));
        }
    }

    private static Object[][] readCSV(String path) throws IOException {
        List<Object[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                records.add(new Object[]{parts[0], parts[1], Boolean.parseBoolean(parts[2]), "CSV"});
            }
        }
        return records.toArray(new Object[0][]);
    }

    private static Object[][] readExcel(String path) throws Exception {
        List<Object[]> records = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(path));
             Workbook wb = WorkbookFactory.create(fis)) {
            Sheet sheet = wb.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String username = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                boolean isValid = row.getCell(2).getBooleanCellValue();
                records.add(new Object[]{username, password, isValid, "Excel Row " + i});
            }
        }
        return records.toArray(new Object[0][]);
    }
}