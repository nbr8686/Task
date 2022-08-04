package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager {

    public static PropertyReader reader = new PropertyReader();
    public static WebDriver driver;
    private LoggingPreferences loggingPreferences;
    public static WebDriver getDriver() {
        if(driver==null) {
            driver = createDriver(reader.getPropertyValue("browser"));
        }
        return driver;
    }

    public static void quitDriver(){
        driver.quit();
        driver=null;
    }

    public static WebDriver createDriver(String driverName) {
        if(driverName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",reader.getPropertyValue("browserPath"));
            driver = new ChromeDriver();
        }
        return driver;
    }

}
