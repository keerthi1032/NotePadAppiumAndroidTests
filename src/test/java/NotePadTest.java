import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class NotePadTest extends BaseAndroidTest{

    @Test
    //verifying the menu options are displayed
    public void verifyMenuOptions() {
       final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       Activity activity = new Activity("com.farmerbb.notepad", "com.farmerbb.notepad.MainActivity");
       driver.startActivity(activity);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("More options"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.className("android.widget.ListView")));
        List<WebElement> listElements = driver.findElements(AppiumBy.id("com.farmerbb.notepad:id/title"));
        assertEquals(listElements.size(), 3);
        assertEquals(listElements.get(0).getText(), "Settings");
        assertEquals(listElements.get(1).getText(), "Import notes");
        assertEquals(listElements.get(2).getText(), "About Notepad");
    }

    @Test
    //verify selecting checkbox in setting option
    public void verifySettings() {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Activity activity = new Activity("com.farmerbb.notepad", "com.farmerbb.notepad.MainActivity");
        driver.startActivity(activity);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("More options"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
               AppiumBy.className("android.widget.ListView")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//*[@text='Settings']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.className("android.widget.ListView")));
        List<WebElement> listElements = driver.findElements(AppiumBy.id("android:id/checkbox"));
        assertEquals(listElements.size(), 4);
        Iterator<WebElement> elementIterator = listElements.iterator();
        while(elementIterator.hasNext()) {
            WebElement element = elementIterator.next();
            if (element.getAttribute("enabled").equals("true")) {
                element.click();
            }
        }
        driver.navigate().back();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("More options"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.className("android.widget.ListView")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//*[@text='Settings']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.className("android.widget.ListView")));
        listElements = driver.findElements(AppiumBy.id("android:id/checkbox"));
        elementIterator = listElements.iterator();
        while(elementIterator.hasNext()) {
            WebElement element = elementIterator.next();
            if (element.getAttribute("enabled").equals("true")) {
                assertEquals(element.getAttribute("checked"), "true");
            }
        }
    }

    @Test
    //verify creating notes
    public void verifyCreateNotes() {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Activity activity = new Activity("com.farmerbb.notepad", "com.farmerbb.notepad.MainActivity");
        driver.startActivity(activity);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.farmerbb.notepad:id/button_floating_action"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.farmerbb.notepad:id/editText1"))).sendKeys("mynotes 1");
        driver.navigate().back();
        driver.navigate().back();
        String expectedText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.farmerbb.notepad:id/noteTitle"))).getText();
        assertEquals(expectedText,"mynotes 1");
    }

    @Test
    //same verifySettings test using page objects
    public void verifySettingsUsingPageObject() {
        Activity activity = new Activity("com.farmerbb.notepad", "com.farmerbb.notepad.MainActivity");
        driver.startActivity(activity);
        HomePage homePage = new HomePage(driver);
        homePage.openSettings();
        SettingsPage settingsPage = new SettingsPage(driver);
        assertEquals(settingsPage.getCheckBoxesCount(), 4);
        settingsPage.selectAllCheckBoxes();
        driver.navigate().back();
        homePage.openSettings();
        settingsPage.verifyAllCheckBoxesAreSelected();
    }


}