package com.betsson.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void initializeDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName("emulator-5554")
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setAppPackage("com.swaglabsmobileapp")
                    .setAppActivity("com.swaglabsmobileapp.MainActivity")
                    .setNoReset(false);

            String appiumHost = System.getenv().getOrDefault("APPIUM_HOST", "localhost");
            String appiumPort = System.getenv().getOrDefault("APPIUM_PORT", "4723");
            String appiumUrl = String.format("http://%s:%s/wd/hub", appiumHost, appiumPort);

            AndroidDriver androidDriver = new AndroidDriver(new URL(appiumUrl), options);
            androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.set(androidDriver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}