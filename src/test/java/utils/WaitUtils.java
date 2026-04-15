package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.google.common.base.Function;

import java.time.Duration;

public class WaitUtils {

    private AppiumDriver driver;
    private int defaultTimeout;

    public WaitUtils(AppiumDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.defaultTimeout = timeoutInSeconds;
    }

    // ✅ Wait for element to be visible
    public WebElement waitForElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Reporter.getCurrentTestResult().setAttribute("reason",
                    "Element not visible: " + locator + " | " + e.getMessage());
            throw e;
        }
    }

    // ✅ Wait for element to be clickable
    public WebElement waitForElementClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            Reporter.getCurrentTestResult().setAttribute("reason",
                    "Element not clickable: " + locator + " | " + e.getMessage());
            throw e;
        }
    }

    // ✅ Wait for element to disappear
    public boolean waitForElementInvisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            Reporter.getCurrentTestResult().setAttribute("reason",
                    "Element did not disappear: " + locator + " | " + e.getMessage());
            return false;
        }
    }

    // ✅ Generic wait condition
    public <T> T waitForCondition(Function<WebDriver, T> condition, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(condition);
    }

    // ✅ Hard wait (use only for animations)
    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {}
    }

    // ✅ Wait for Toast message
    public String waitForToast(String expectedText, int timeoutInSeconds) {

        long endTime = System.currentTimeMillis() + (timeoutInSeconds * 1000L);

        while (System.currentTimeMillis() < endTime) {
            try {
                WebElement toast = driver.findElement(AppiumBy.xpath("//android.widget.Toast[1]"));
                String toastMessage = toast.getText();

                if (toastMessage.contains(expectedText)) {
                    return toastMessage;
                }
            } catch (Exception ignored) {
                // Toast not found yet, keep polling
            }
        }

        Reporter.getCurrentTestResult().setAttribute("reason",
                "Toast with expected text '" + expectedText + "' not found within "
                        + timeoutInSeconds + "s");

        throw new RuntimeException("Toast with text '" + expectedText + "' not found.");
    }

    // ✅ Overloaded method using default timeout
    public String waitForToast(String expectedText) {
        return waitForToast(expectedText, defaultTimeout);
    }
}
