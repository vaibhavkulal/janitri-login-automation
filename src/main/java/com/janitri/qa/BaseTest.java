package com.janitri.qa;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-logging");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        // Avoid implicit wait conflicts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        System.out.println("Navigating to login page...");
        driver.get("https://dev-dash.janitri.in/");

        // Wait for React root to render
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d -> d.findElement(By.id("root")).getText().length() > 0);
        System.out.println("Page loaded and ready for interaction.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot on test failure
        if (!result.isSuccess()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String screenshotDir = "screenshots";
            new File(screenshotDir).mkdirs(); // create folder if it doesn't exist

            String timestamp = String.valueOf(System.currentTimeMillis());
            try {
                FileUtils.copyFile(src, new File(screenshotDir + "/" + result.getName() + "_" + timestamp + ".png"));
                System.out.println("Saved screenshot: " + screenshotDir + "/" + result.getName() + "_" + timestamp + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Quit browser
        if (driver != null) {
            driver.quit();
        }
    }
}
