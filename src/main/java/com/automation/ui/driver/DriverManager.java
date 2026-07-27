package com.automation.ui.driver;

import com.automation.config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver<MobileElement>> driverThreadLocal = new ThreadLocal<>();

    public static AppiumDriver<MobileElement> getDriver() {
        return driverThreadLocal.get();
    }

    public static void initializeDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigManager.get("appium.platform.name", "Android"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.get("appium.device.name", "emulator-5554"));
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ConfigManager.get("appium.app.package", "com.example.app"));
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ConfigManager.get("appium.app.activity", ".MainActivity"));

        String serverUrl = ConfigManager.get("appium.server.url", "http://127.0.0.1:4723/wd/hub");
        AppiumDriver<MobileElement> driver = new AndroidDriver<>(new URL(serverUrl), caps);
        
        int implicitWait = ConfigManager.getInt("appium.implicit.wait", 10);
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        AppiumDriver<MobileElement> driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error quitting Appium driver: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
