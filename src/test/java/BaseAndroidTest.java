/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
