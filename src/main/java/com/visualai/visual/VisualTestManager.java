package com.visualai.visual;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import com.visualai.config.FrameworkConfig;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; // ✅ IMPORTANT
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisualTestManager {

    private static final Logger logger =
            LoggerFactory.getLogger(VisualTestManager.class);

    private Eyes eyes;
    private ClassicRunner runner;

    private final MatchLevel matchLevel;

    private boolean sessionOpen = false;
    private String dashboardUrl = "N/A";

    public VisualTestManager(MatchLevel matchLevel) {
        this.matchLevel = matchLevel;
    }

    // =============================

    public void openVisualSession(WebDriver driver,
                                  String testName,
                                  String url) {

        runner = new ClassicRunner();

        Configuration config = new Configuration();

        config.setApiKey(FrameworkConfig.getApplitoolsApiKey());
        config.setAppName(FrameworkConfig.getApplitoolsAppName());
        config.setTestName(testName);

        config.setViewportSize(new RectangleSize(
                FrameworkConfig.getViewportWidth(),
                FrameworkConfig.getViewportHeight()
        ));

        config.setMatchLevel(matchLevel);

        if (FrameworkConfig.isFullPageScreenshot()) {
            config.setStitchMode(StitchMode.CSS);
        }

        eyes = new Eyes(runner);
        eyes.setConfiguration(config);

        eyes.open(driver);

        sessionOpen = true;

        logger.info("Visual session started");

        Allure.description(
                "Visual Test -> " + url + " | Match: " + matchLevel
        );
    }

    // =============================
    // FULL PAGE
    // =============================

    public void checkFullPage(String name) {

        if (!sessionOpen) {
            throw new IllegalStateException("Session not open");
        }

        eyes.check(name, Target.window().fully());

        logger.info("Full page checkpoint: {}", name);
    }

    // =============================
    // REGION
    // =============================

    public void checkRegion(String name, WebElement element) {

        if (!sessionOpen) {
            throw new IllegalStateException("Session not open");
        }

        eyes.check(name, Target.region(element));

        logger.info("Region checkpoint: {}", name);
    }

    // =============================

    public VisualTestResult closeVisualSessionAndGetResult() {

        if (!sessionOpen) {
            return VisualTestResult.notRun();
        }

        try {

            eyes.closeAsync();

            TestResultsSummary summary =
                    runner.getAllTestResults(false);

            return processResults(summary);

        } catch (Exception e) {

            logger.error("Error closing session", e);
            return VisualTestResult.withError(e.getMessage());

        } finally {
            sessionOpen = false;
        }
    }

    public void abortVisualSession() {

        if (sessionOpen && eyes != null) {
            eyes.abortAsync();
            sessionOpen = false;
            logger.warn("Visual session aborted");
        }
    }

    // =============================

    private VisualTestResult processResults(TestResultsSummary summary) {

        int matched = 0;
        int mismatched = 0;
        int missing = 0;
        int newSteps = 0;

        for (TestResultContainer c : summary) {

            TestResults r = c.getTestResults();
            if (r == null) continue;

            dashboardUrl = r.getUrl();

            matched += r.getMatches();
            mismatched += r.getMismatches();
            missing += r.getMissing();

            if (r.isNew()) {
                newSteps += r.getSteps();
            }
        }

        return new VisualTestResult(
                matched,
                mismatched,
                missing,
                newSteps,
                dashboardUrl
        );
    }
}