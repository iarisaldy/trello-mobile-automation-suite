package com.automation;

import com.automation.reports.ExtentReportManager;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Starting Trello & Mobile Test Automation Suite");
        System.out.println("==================================================");

        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("src/test/resources/testng.xml");
        testNG.setTestSuites(suites);

        try {
            testNG.run();
        } finally {
            ExtentReportManager.flush();
            System.out.println("==================================================");
            System.out.println("Test Execution Complete! HTML Report Generated.");
            System.out.println("Report Path: target/reports/extent-report.html");
            System.out.println("==================================================");
        }
    }
}
