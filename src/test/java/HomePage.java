import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage{
    protected AndroidDriver driver;
    final WebDriverWait wait;
    private By closeButton = By.xpath(".//*[@text='CLOSE']");
    private By moreOptions = AppiumBy.accessibilityId("More options");
    private By listView = AppiumBy.className("android.widget.ListView");
    private By settings = By.xpath(".//*[@text='Settings']");
    public HomePage(AndroidDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void closeWelcomePopUp(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeButton)).click();
    }

    public void openSettings(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(moreOptions)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(listView));
        wait.until(ExpectedConditions.visibilityOfElementLocated(settings)).click();
    }
}
