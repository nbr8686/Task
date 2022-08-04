import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.de.Wenn;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import managers.CaptureRequestsSent;
import managers.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.Map;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"stepDefinitions"}, tags = "@E2E",
        plugin = {"pretty", "html:target/test-report.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
    WebDriver driver;
    public static Map<String, Map<String, String>> networkLogs = new HashMap<>();

    @BeforeMethod
    public void beforeMethod() {
        driver = WebDriverManager.getDriver();
        CaptureRequestsSent captureRequestsSent = new CaptureRequestsSent();
        captureRequestsSent.captureHttpRequests(driver);

    }

    @AfterMethod
    public void doAfter(ITestResult result) {
        /*if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }*/
        WebDriverManager.quitDriver();
    }

    @AfterTest
    public void quite() {
        driver.quit();
    }
}
