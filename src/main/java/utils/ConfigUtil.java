package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    private static Properties prop;

    public static Properties loadConfig() {
        if (prop == null) {
            prop = new Properties();
            try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
                prop.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config file", e);
            }
        }
        return prop;
    }

    public static String getProperty(String key, String defaultValue) {
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.isBlank()) return sysProp;
        return loadConfig().getProperty(key, defaultValue);
    }
}