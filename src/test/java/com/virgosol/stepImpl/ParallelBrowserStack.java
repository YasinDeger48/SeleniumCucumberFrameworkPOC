package com.virgosol.stepImpl;

import org.junit.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

    public class ParallelBrowserStack {

        public WebDriver driver;
        public static final String USERNAME = "yasindeger_kGBZqu";
        public static final String AUTOMATE_KEY = "xDpF4cxydNWcSPLstT7k";
        public final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

        public static final String SUSERNAME = "oauth-testduckwithmock-fae1a";
        public static final String SAUTOMATE_KEY = "*****8b40";
        public final String SURL = "https://"+SUSERNAME+":"+SAUTOMATE_KEY+"@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

        @Test(dataProvider = "envDetails")
        public void openBS(Platform platform, String browserName, String browserVersion) throws MalformedURLException, IOException, InterruptedException {

            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setPlatform(platform);
            capability.setBrowserName(browserName);
            capability.setVersion(browserVersion);
           // capability.setCapability("browserstack.debug", "true");
            URL browserStackUrl = new URL(URL);

            driver = new RemoteWebDriver(browserStackUrl, capability);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
            driver.get("https://virgosol.com");
            String actualURL = driver.getCurrentUrl();
            System.out.println("URL is "+actualURL);
            driver.quit();
        }



        @DataProvider(name= "envDetails", parallel=true)
        public Object[][] getData(){
            Object[][] testData = new Object[][]{
                    {Platform.MAC, "safari", "16"},
                    {Platform.WINDOWS, "chrome", "107"},
                    {Platform.WINDOWS, "firefox", "107"}
            };
            return testData;
        }
    }

