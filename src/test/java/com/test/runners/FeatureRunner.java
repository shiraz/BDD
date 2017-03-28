package com.test.runners;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * <p>
 * The test runner class.
 * </p>
 */
/*@CucumberOptions(features = "src/test/resources/features", glue = "com.test.glue", format = {"pretty"})*/
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
        retryCount = 1,
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = true,
        //coverageReport = true,
        jsonUsageReport = "target/cucumber-usage.json",
        usageReport = true,
        toPDF = true,
        outputFolder = "target")
@CucumberOptions(plugin = { "html:target/cucumber-html-report",
        "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
        "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" },
        features = { "src/test/resources/features" },
        glue = { "com.test.glue" })
public class FeatureRunner extends AbstractTestNGCucumberTests {}