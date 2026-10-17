package com.visualai.utils;

import com.visualai.visual.PageLoader;
import com.visualai.visual.VisualTestManager;
import com.visualai.visual.VisualTestResult;
import org.openqa.selenium.WebDriver;

public class TestContext {

    private WebDriver driver;
    private VisualTestManager visualTestManager;
    private PageLoader pageLoader;
    private VisualTestResult lastVisualResult;

    private String targetUrl;
    private String scenarioName;

    // =============================

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public VisualTestManager getVisualTestManager() {
        return visualTestManager;
    }

    public void setVisualTestManager(VisualTestManager visualTestManager) {
        this.visualTestManager = visualTestManager;
    }

    public PageLoader getPageLoader() {
        return pageLoader;
    }

    public void setPageLoader(PageLoader pageLoader) {
        this.pageLoader = pageLoader;
    }

    public VisualTestResult getLastVisualResult() {
        return lastVisualResult;
    }

    public void setLastVisualResult(VisualTestResult lastVisualResult) {
        this.lastVisualResult = lastVisualResult;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }
}