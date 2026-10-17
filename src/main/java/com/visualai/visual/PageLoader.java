package com.visualai.visual;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageLoader {

    private static final Logger logger =
            LoggerFactory.getLogger(PageLoader.class);

    private final WebDriver driver;

    public PageLoader(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
    }

    // =============================

    @Step("Load website: {url}")
    public void loadWebsite(String url) {

        logger.info("Navigating to: {}", url);

        try {
            driver.get(url);

            waitForPageReady();

            String title = driver.getTitle();

            logger.info("Page loaded successfully → {}", title);

            Allure.addAttachment("URL", url);
            Allure.addAttachment("Title", title);

        } catch (Exception e) {
            logger.error("Failed to load URL: {}", url);
            throw new RuntimeException("Page load failed", e);
        }
    }

    // =============================

    @Step("Prepare page for visual capture")
    public void prepareForVisualCapture() {

        try {

            // scroll down
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");

            Thread.sleep(800);

            // scroll back up
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, 0)");

            Thread.sleep(500);

            logger.info("Page prepared for visual capture");

        } catch (Exception e) {
            logger.warn("Page preparation issue: {}", e.getMessage());
        }
    }

    // =============================

    private void waitForPageReady() {

        int timeout = 30;
        int waited = 0;

        while (waited < timeout * 1000) {

            try {
                String state = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("return document.readyState");

                if ("complete".equals(state)) {
                    return;
                }

                Thread.sleep(500);
                waited += 500;

            } catch (Exception e) {
                return;
            }
        }

        logger.warn("Page did not reach complete state");
    }
}