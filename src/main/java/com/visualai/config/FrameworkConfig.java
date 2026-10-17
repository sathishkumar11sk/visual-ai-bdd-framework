package com.visualai.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public final class FrameworkConfig {

    private static final Logger logger =
            LoggerFactory.getLogger(FrameworkConfig.class);

    private static final String CONFIG_FILE = "framework.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = FrameworkConfig.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (input == null) {
                throw new RuntimeException(
                        "Configuration file not found: " + CONFIG_FILE
                );
            }

            properties.load(input);
            logger.info("Configuration loaded successfully");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    private FrameworkConfig() {}

    // =============================

    public static String getApplitoolsApiKey() {
        return getRequiredProperty("applitools.api.key");
    }

    public static String getApplitoolsAppName() {
        return getProperty("applitools.app.name", "VisualAI-BDD-Framework");
    }

    public static String getMatchLevel() {
        return getProperty("applitools.match.level", "STRICT");
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(
                getProperty("browser.headless", "false")
        );
    }

    public static int getViewportWidth() {
        return Integer.parseInt(
                getProperty("viewport.width", "1366")
        );
    }

    public static int getViewportHeight() {
        return Integer.parseInt(
                getProperty("viewport.height", "768")
        );
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(
                getProperty("page.load.timeout", "30")
        );
    }

    public static String getCucumberReportPath() {
        return getProperty(
                "report.cucumber.output",
                "target/cucumber-reports"
        );
    }

    public static boolean isFullPageScreenshot() {
        return Boolean.parseBoolean(
                getProperty("visual.full.page", "true")
        );
    }

    // =============================

    private static String getRequiredProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException(
                    "Missing required configuration: " + key
            );
        }

        return value.trim();
    }

    private static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue).trim();
    }
}