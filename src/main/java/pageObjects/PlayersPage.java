package pageObjects;

import managers.PropertyReader;
import managers.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlayersPage {

    WebDriver driver;
    WebDriverWait wait;
    PropertyReader reader;

    public PlayersPage() {
        driver = WebDriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        reader = new PropertyReader();
    }

    private String classicPlayer = "//div[@id='right-player']//div[contains(@id,'sr-player')]";
    private String classicPlayerSkipBtn = "//div[@id='right-player']//div[contains(@class,'sr-blade-skip-button-active')]";

    private String stickyPlayer = "//div[@id='left-player']//div[contains(@id,'sr-player')]";
    private String stickyPlayerSkipBtn = "//div[@id='left-player']//div[contains(@class,'sr-blade-skip-button-active')]";
    private String stickyPlayerCloseBtn = "//div[@id='left-player']//div[contains(@class,'sr-bladex-sticky-close-button')]";

    private String stickedStickyPlayer = "//div[@id='left-player']//div[@class='sr-bladex-sticky']";

    public WebElement getClassicPlayer() {
        By byStrategy = By.xpath(classicPlayer);
        wait.until(ExpectedConditions.presenceOfElementLocated(byStrategy));
        return driver.findElement(byStrategy);
    }

    public WebElement getClassicPlayerSkipBtn() {
        By byStrategy = By.xpath(classicPlayerSkipBtn);
        wait.until(ExpectedConditions.presenceOfElementLocated(byStrategy));
        return driver.findElement(byStrategy);
    }

    public WebElement getStickyPlayer() {
        By byStrategy = By.xpath(stickyPlayer);
        wait.until(ExpectedConditions.presenceOfElementLocated(byStrategy));
        return driver.findElement(byStrategy);
    }

    public WebElement getStickyPlayerSkipBtn() {
        By byStrategy = By.xpath(stickyPlayerSkipBtn);
        WebElement element = null;
        int retry = Integer.parseInt(reader.getPropertyValue("retry_for_skip_btn"));
        while (retry>0) {
            try {
                retry--;
                wait.until(ExpectedConditions.presenceOfElementLocated(byStrategy));
                element =  driver.findElement(byStrategy);
            } catch (Exception e) {
                continue;
            }
        }
        return element;
    }

    public WebElement getStickyPlayerCloseBtn() {
        By byStrategy = By.xpath(stickyPlayerCloseBtn);
        wait.until(ExpectedConditions.presenceOfElementLocated(byStrategy));
        return driver.findElement(byStrategy);
    }

    public WebElement getStickedPlayer() {
        By byStrategy = By.xpath(stickedStickyPlayer);
        wait.until(ExpectedConditions.presenceOfElementLocated(byStrategy));
        return driver.findElement(byStrategy);
    }
}
