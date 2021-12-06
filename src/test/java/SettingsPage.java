import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SettingsPage {
    protected AndroidDriver driver;
    final WebDriverWait wait;
    private By checkBox = AppiumBy.id("android:id/checkbox");
    private By listView = AppiumBy.className("android.widget.ListView");
    public SettingsPage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void selectAllCheckBoxes() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(listView));
        List<WebElement> listElements = driver.findElements(checkBox);
        Iterator<WebElement> elementIterator = listElements.iterator();
        while(elementIterator.hasNext()) {
            WebElement element = elementIterator.next();
            if (element.getAttribute("enabled").equals("true") &&
                    element.getAttribute("checked").equals("false")) {
                element.click();
            }
        }
    }

    public int getCheckBoxesCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(listView));
        List<WebElement> listElements = driver.findElements(checkBox);
        return listElements.size();
    }

    public void verifyAllCheckBoxesAreSelected() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(listView));
        List<WebElement> listElements = driver.findElements(checkBox);
        Iterator<WebElement> elementIterator = listElements.iterator();
        while(elementIterator.hasNext()) {
            WebElement element = elementIterator.next();
            if (element.getAttribute("enabled").equals("true")) {
                assertEquals(element.getAttribute("checked"), "true");
            }
        }
    }
}
