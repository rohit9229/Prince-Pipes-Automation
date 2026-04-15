package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class FullLogListener implements ITestListener {

    private Logger logger;
    private final Map<String, ClassStats> classStatsMap = new HashMap<>();

    private static class ClassStats {
        int passed = 0;
        int failed = 0;
        int skipped = 0;
        List<String> failedReasons = new ArrayList<>();
    }

    @Override
    public void onStart(ITestContext context) {
        LogManager.getLogger("TestExecution")
                .info("=== TEST SUITE STARTED: " + context.getName() + " ===");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        ThreadContext.put("className", className);
        logger = LogManager.getLogger("Eazy_Retail." + className);
        logger.info("=== STARTED: {} ===", result.getMethod().getMethodName());
        classStatsMap.putIfAbsent(className, new ClassStats());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        ClassStats stats = classStatsMap.get(className);
        stats.passed++;

        String reason = result.getThrowable() == null ? "Test passed successfully." : result.getThrowable().getMessage();
        logger.info("=== PASSED: {} | Reason: {} ===", result.getMethod().getMethodName(), reason);
        ThreadContext.remove("className");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        ClassStats stats = classStatsMap.get(className);
        stats.failed++;

        String reason = (String) result.getAttribute("reason");
        if (reason == null || reason.isEmpty()) {
            reason = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error occurred.";
            if (reason != null && reason.contains("\n")) {
                reason = reason.split("\n")[0]; // first line only
            }
        }
        reason = truncate(reason);
        stats.failedReasons.add(result.getMethod().getMethodName() + " -> " + reason);

        String screenshotPath = captureScreenshot(result);
        if (screenshotPath != null) {
            logger.error("=== FAILED: {} | Reason: {} | Screenshot: {} ===",
                    result.getMethod().getMethodName(), reason, screenshotPath);
        } else {
            logger.error("=== FAILED: {} | Reason: {} ===",
                    result.getMethod().getMethodName(), reason);
        }
        ThreadContext.remove("className");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        ClassStats stats = classStatsMap.get(className);
        stats.skipped++;

        String reason = (String) result.getAttribute("reason");
        if (reason == null || reason.isEmpty()) reason = "Test skipped.";
        logger.warn("=== SKIPPED: {} | Reason: {} ===", result.getMethod().getMethodName(), reason);
        ThreadContext.remove("className");
    }

    @Override
    public void onFinish(ITestContext context) {
        Logger suiteLogger = LogManager.getLogger("TestExecution");
        suiteLogger.info("=== TEST SUITE FINISHED: {} ===", context.getName());
        suiteLogger.info("=== TEST SUMMARY CLASS-WISE ===");

        int totalPassed = 0, totalFailed = 0, totalSkipped = 0;

        for (Map.Entry<String, ClassStats> entry : classStatsMap.entrySet()) {
            String className = entry.getKey();
            ClassStats stats = entry.getValue();

            suiteLogger.info("Class: {}", className);
            suiteLogger.info("Passed : {}", stats.passed);
            suiteLogger.info("Failed : {}", stats.failed);
            suiteLogger.info("Skipped: {}", stats.skipped);

            if (!stats.failedReasons.isEmpty()) {
                suiteLogger.info("Failed Reasons:");
                for (String failReason : stats.failedReasons) {
                    suiteLogger.info(" - {}", failReason);
                }
            }
            suiteLogger.info("------------------------------");

            totalPassed += stats.passed;
            totalFailed += stats.failed;
            totalSkipped += stats.skipped;
        }

        int total = totalPassed + totalFailed + totalSkipped;
        suiteLogger.info("=== TOTAL TESTS ===");
        suiteLogger.info("Total  : {}", total);
        suiteLogger.info("Passed : {}", totalPassed);
        suiteLogger.info("Failed : {}", totalFailed);
        suiteLogger.info("Skipped: {}", totalSkipped);
        suiteLogger.info("===================");
    }

    private String truncate(String msg) {
        if (msg != null && msg.length() > 250) return msg.substring(0, 250) + "...";
        return msg;
    }

    private String captureScreenshot(ITestResult result) {
        Object testClass = result.getInstance();
        if (!(testClass instanceof BaseTest)) return null;

        WebDriver driver = ((BaseTest) testClass).getDriver();
        if (driver == null) return null;

        try {
            String folderPath = "screenshots";
            Files.createDirectories(Paths.get(folderPath));

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String className = result.getTestClass().getRealClass().getSimpleName();
            String methodName = result.getMethod().getMethodName();
            String filePath = folderPath + "/" + className + "_" + methodName + "_" + timestamp + ".png";

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            return filePath;
        } catch (IOException e) {
            if (logger != null) logger.warn("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }

}
