package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

    static Properties prop = new Properties();

    public static void readPropertiesFile(){
        try {
            InputStream input = new FileInputStream("./test.properties");
            prop.load(input);

        } catch (java.io.IOException e) {
            System.out.println("no properties file");
        }
    }

    public static String getBrowser(){
        readPropertiesFile();
        return prop.getProperty("browser");
    }
}
