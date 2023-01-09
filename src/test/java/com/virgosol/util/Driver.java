package com.virgosol.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {
    }


    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            String browser = "chrome-headless";

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driverPool.set(new ChromeDriver(options));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    driverPool.set(new EdgeDriver(edgeOptions));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    SafariOptions safariOptions = new SafariOptions();
                    driverPool.set(new SafariDriver(safariOptions));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "sauce":
                    ChromeOptions browserOptions = new ChromeOptions();
                    browserOptions.setPlatformName("Windows 10");
                    browserOptions.setBrowserVersion("latest");
                    browserOptions.setImplicitWaitTimeout(Duration.ofMillis(12));
                    Map<String, Object> sauceOptions = new HashMap<>();
                    sauceOptions.put("build", "osmanbuild");
                    sauceOptions.put("name", "osman");
                    sauceOptions.put("screenResolution", "1920x1080");
                    sauceOptions.put("maxDuration", 15);
                    sauceOptions.put("commandTimeout", 15);
                    sauceOptions.put("parallelsPerPlatform",2);
                    browserOptions.setCapability("sauce:options", sauceOptions);

                    URL url = null;
                    try {
                        url = new URL("https://oauth-test.equals.quality-67b3d:d5f8158b-8af0-4454-848a-b89d2da6d8a5@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driverPool.set(new RemoteWebDriver(url, browserOptions));
                    break;

                case "browser":
                    MutableCapabilities capabilities = new MutableCapabilities();
                    capabilities.setCapability("browserName", "Chrome");
                    HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                    browserstackOptions.put("os", "OS X");
                    browserstackOptions.put("osVersion", "Ventura");
                    browserstackOptions.put("browserVersion", "latest");
                    browserstackOptions.put("resolution", "1920x1080");
                    browserstackOptions.put("projectName", "osman");
                    browserstackOptions.put("buildName", "osman12");
                    browserstackOptions.put("local", "false");
                    browserstackOptions.put("seleniumVersion", "4.4.0");
                    browserstackOptions.put("parallelsPerPlatform",2);
                    capabilities.setCapability("bstack:options", browserstackOptions);

                    url = null;
                    try {
                        url = new URL("https://yasindeer_MsNSro"+":"+"vxjLt5qxLyNaXDX3eg8K"+"@hub-cloud.browserstack.com/wd/hub");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driverPool.set(new RemoteWebDriver(url, capabilities));
                    break;


            }

        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();

        }
    }
}