package com.visualai.runner;

import com.visualai.config.FrameworkConfig;
import com.visualai.utils.ReportingUtils;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.visualai.stepdefinitions"},
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "pretty",
                "html:target/cucumber-reports/cucumber-native.html"
        },
        tags = "@visual",
        monochrome = true,
        publish = false
)
public class TestRunner {

    private static final Logger logger =
            LoggerFactory.getLogger(TestRunner.class);

    @AfterClass
    public static void generateReports() {

        logger.info("Generating Cucumber HTML report...");

        String reportDir = FrameworkConfig.getCucumberReportPath();
        String jsonReport = "target/cucumber-reports/cucumber.json";

        ReportingUtils.generateCucumberHtmlReport(
                reportDir,
                jsonReport,
                "Visual AI BDD Framework"
        );

        logger.info("Reports generated successfully");
    }
}