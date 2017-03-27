package com.test.framework;

import com.test.page.objects.DownloadPage;
import com.test.page.objects.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ParentScenario {

    private WebDriver driver;
    protected DownloadPage downloadPage;
    protected MainPage mainPage;

    protected void startBrowser() {
        setChromeDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        downloadPage = new DownloadPage(driver);
        mainPage = new MainPage(driver);
    }

    protected void setChromeDriver() {
        // Initialize a string to store the driver path.
        String driverPath = null;
        // Get the current path.
        String currentPath = System.getProperty("user.dir");
        driverPath = currentPath + File.separator + "drivers" + File.separator;
        // Get the current OS.
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("windows")) {
            driverPath = driverPath + "chromedriver.exe";
        }
        else if (os.contains("mac")) {
            driverPath = driverPath + "chromedriver";
        }
        else if (os.contains("linux")) {
            driverPath = driverPath + "chromedriver_linux";
        }
        else {
            Assert.fail("No driver found.");
        }
        // Set the driver property.
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    protected void navigateTo() {
        driver.get("http://www.seleniumhq.org/download/");
    }

    protected void closeBrowser() {
        driver.quit();
    }

}