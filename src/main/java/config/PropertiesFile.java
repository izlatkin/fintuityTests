package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

    static Properties prop = new Properties();

    public static void readPropertiesFile(){
        try {
            InputStream input = new FileInputStream("./test.properties.txt");
            prop.load(input);

        } catch (java.io.IOException e) {
            System.out.println("no properties file");
        }
    }

    public static String getBrowser(){
        readPropertiesFile();
        return prop.getProperty("browser");
    }

    public static String getFintuity_front(){
        readPropertiesFile();
        return prop.getProperty("fintuity_front");
    }

    public static String getFintuity_backoffice(){
        readPropertiesFile();
        return prop.getProperty("fintuity_backoffice");
    }

    public static String getDriverPath(String driverName){
        readPropertiesFile();
        return prop.getProperty(driverName);
    }
}
