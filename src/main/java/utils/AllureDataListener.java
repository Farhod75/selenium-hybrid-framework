package utils;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.*;

public class AllureDataListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        String csvPath = ConfigUtil.getProperty("csv.path", "");
        attachFileIfExists(csvPath, "CSV Data");

        String excelPath = ConfigUtil.getProperty("excel.path", "");
        attachFileIfExists(excelPath, "Excel Data");
    }

    @Override
    public void onTestStart(ITestResult result) {
        Object[] params = result.getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                Allure.parameter("param" + i, String.valueOf(params[i]));
            }
        }
    }

    private void attachFileIfExists(String path, String name) {
        File f = new File(path);
        if (f.exists()) {
            try (FileInputStream fis = new FileInputStream(f)) {
                Allure.addAttachment(name, fis);
            } catch (IOException e) { }
        }
    }
}