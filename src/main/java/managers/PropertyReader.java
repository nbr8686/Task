package managers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class PropertyReader {

    Properties properties= new Properties();

    public PropertyReader()  {
        try{
            FileReader reader = new FileReader("src/main/resources/config.properties");
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPropertyValue(String key){
        return properties.getProperty(key);
    }
}
