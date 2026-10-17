package com.visualai.driver;

import com.visualai.config.FrameworkConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public final class DriverFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(DriverFactory.class);

    private static final ThreadLocal<WebDriver> driverThreadLocal =
            new ThreadLocal<>();

    private DriverFactory() {}

    // =============================

    public static WebDriver initDriver() {

        String browser = FrameworkConfig.getBrowser();
        boolean headless = FrameworkConfig.isHeadless();

        logger.info("Initializing WebDriver → Browser: {}", browser);

        WebDriver driver;

        switch (browser.toLowerCase().trim()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(buildChromeOptions(headless));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(buildFirefoxOptions(headless));
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(buildEdgeOptions(headless));
                break;

            default:
                logger.warn("Invalid browser. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(buildChromeOptions(headless));
        }

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(FrameworkConfig.getPageLoadTimeout())
        );

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();

        driverThreadLocal.set(driver);

        logger.info("WebDriver initialized successfully");

        return driver;
    }

    public static WebDriver getDriver() {

        WebDriver driver = driverThreadLocal.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver not initialized. Call initDriver() first."
            );
        }

        return driver;
    }

    public static void quitDriver() {

        WebDriver driver = driverThreadLocal.get();

        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver closed");
            } catch (Exception e) {
                logger.warn("Error closing WebDriver: {}", e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    // =============================

    private static ChromeOptions buildChromeOptions(boolean headless) {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");

        options.setPageLoadStrategy(
                org.openqa.selenium.PageLoadStrategy.EAGER
        );

        if (headless) {
            options.addArguments("--headless=new");
        }

        return options;
    }

    private static FirefoxOptions buildFirefoxOptions(boolean headless) {

        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("--headless");
        }

        return options;
    }

    private static EdgeOptions buildEdgeOptions(boolean headless) {

        EdgeOptions options = new EdgeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        return options;
    }
}