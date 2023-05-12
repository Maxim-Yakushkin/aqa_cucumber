package com.yakushkin.cucumber.testng.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/CucumberTestRunner.html",
                "json:target/cucumber-reports/CucumberTestRunner.json",
                "junit:target/cucumber-reports/CucumberTestRunner.xml"
        },
        monochrome = true,
        tags = "@smoke",
        glue = "com.yakushkin.cucumber.testng",
        features = "classpath:com/yakushkin/cucumber/testng/feature"
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

}
