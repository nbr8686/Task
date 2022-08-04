package util;

import managers.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebActions {
    WebDriver driver;

    public WebActions() {
        driver = WebDriverManager.getDriver();
    }
    public void launchApp(String url) {
        if(driver == null) {
            driver = WebDriverManager.getDriver();

        }
        driver.get(url);
    }

    public void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public void click(WebElement element) {
        element.click();
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public int getElementHeight(WebElement element){
        Dimension size = element.getSize();
        return size.getHeight();
    }

    public int getElementWidth(WebElement element) {
        Dimension size = element.getSize();
        return size.getWidth();
    }

    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
