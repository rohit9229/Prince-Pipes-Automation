package utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected Logger logger;
    protected WaitUtils wait;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws MalformedURLException, URISyntaxException {

        if (driver == null) {

            String className = this.getClass().getSimpleName();
            logger = LogManager.getLogger("BIZ." + className);
            logger.info("===== Starting {} Test =====", className);

            // Load app path
            File app = new File(ConfigReader.get("appPath"));

            // Appium options
            UiAutomator2Options options = new UiAutomator2Options();
            options.setApp(app.getAbsolutePath());
            options.setDeviceName(ConfigReader.get("deviceName"));
            options.setNewCommandTimeout(Duration.ofSeconds(2000));

            logger.info("Initializing AndroidDriver...");
            driver = new AndroidDriver(new URI("http://localhost:4723").toURL(), options);

            // Default wait
            wait = new WaitUtils(driver, 10);
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public WaitUtils getWait(int seconds) {
        return new WaitUtils(driver, seconds);
    }

    // ============================================================
    // ✅ GENERIC TAP USING W3C (STABLE)
    // ============================================================
    public void tapByCoordinates(int x, int y) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));

        logger.info("Tapped at coordinates: ({}, {})", x, y);
    }

    // ============================================================
    // ✅ SMART BOTTOM NAV TAP (DEVICE INDEPENDENT)
    // ============================================================
    public void tapBottomNav(String position) {

        Dimension size = driver.manage().window().getSize();

        int width = size.width;
        int height = size.height;

        int y = (int) (height * 0.93); // bottom navigation level
        int x;

        switch (position.toLowerCase()) {
            case "left":
                x = (int) (width * 0.15);
                break;
            case "middle":
                x = (int) (width * 0.50);
                break;
            case "right": // 👉 Profile
                x = (int) (width * 0.85);
                break;
            default:
                throw new IllegalArgumentException("Invalid nav position: " + position);
        }

        logger.info("Tapping bottom nav [{}] at ({}, {})", position, x, y);

        tapByCoordinates(x, y);
    }

    // ============================================================
    // ✅ OPTIONAL: CENTER TAP (FOR DEBUG)
    // ============================================================
    public void tapBottomCenter() {

        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int y = (int) (size.height * 0.95);

        tapByCoordinates(x, y);
    }

    // ============================================================
    // ✅ TEARDOWN (DEBUG MODE - KEEP APP OPEN)
    // ============================================================
    @AfterClass(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            logger.info("Keeping app open after execution (Debug Mode).");
        } else {
            logger.warn("Driver already null.");
        }
    }
}