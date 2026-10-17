package com.visualai.utils;

import com.visualai.visual.VisualTestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ReportingUtils {

    private static final Logger logger =
            LoggerFactory.getLogger(ReportingUtils.class);

    private static final String ALLURE_CMD =
            "mvn allure:report -> open target/allure-report/index.html";

    private static final String CUCUMBER_REPORT =
            "target/cucumber-reports/cucumber-html-report.html";

    private ReportingUtils() {}

    public static void printExecutionSummary(VisualTestResult result,
                                             String url,
                                             String scenario) {

        String time = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );

        boolean passed = result.isPassed();

        String status = passed ? "PASSED" : "FAILED";

        System.out.println();
        System.out.println("======================================================");
        System.out.println("EXECUTION SUMMARY");
        System.out.println("======================================================");

        System.out.println("Time      : " + time);
        System.out.println("Scenario  : " + scenario);
        System.out.println("URL       : " + url);

        System.out.println("------------------------------------------------------");

        System.out.println("Status    : " + status);
        System.out.println("Matched   : " + result.getMatchedSteps());
        System.out.println("Failed    : " + result.getMismatchedSteps());
        System.out.println("Missing   : " + result.getMissingSteps());
        System.out.println("New       : " + result.getNewSteps());

        System.out.println("------------------------------------------------------");

        System.out.println("Applitools:");
        System.out.println(result.getDashboardUrl());

        System.out.println();

        System.out.println("Allure:");
        System.out.println(ALLURE_CMD);

        System.out.println();

        System.out.println("Cucumber:");
        System.out.println(CUCUMBER_REPORT);

        System.out.println("======================================================");
        System.out.println();

        logger.info("Execution completed -> {}", status);
    }

    // 🔥 ADD THIS METHOD (fix for ERROR 2 also)
    public static void generateCucumberHtmlReport(String outputDir,
                                                  String jsonFile,
                                                  String projectName) {
        // simple safe stub (no failure)
        logger.info("Cucumber HTML report generation skipped (safe mode)");
    }
}