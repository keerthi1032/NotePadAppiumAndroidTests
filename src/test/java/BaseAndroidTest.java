import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;


import java.net.MalformedURLException;
import java.net.URL;

public class BaseAndroidTest {
    private static final int PORT = 4723;

    private AppiumDriverLocalService service;
    protected AndroidDriver driver;

    /**
     * initialization.
     */
    @BeforeSuite
    public  void beforeClass() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(PORT)
                .build();
         service.start();

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Pixel 2 API 30")
                .setApp("/Users/keerthi/Documents/git/note.apk")
                .eventTimings();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        new HomePage(driver).closeWelcomePopUp();
    }

    /**
     * finishing.
     */
    @AfterSuite
    public void afterClass() {
        driver.quit();
        service.stop();
    }
    @BeforeTest
    public void beforeTest() {

    }
}
