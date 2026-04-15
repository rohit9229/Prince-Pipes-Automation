package utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
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
        // Initialize logger based on subclass name
    	if (driver == null) {
        String className = this.getClass().getSimpleName();
        logger = LogManager.getLogger("BIZ." + className);
        logger.info("===== Starting {} Test =====", className);
        
        File app = new File(ConfigReader.get("appPath"));
        
        // Load configuration
        logger.info("Loading configuration values...");
        
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(app.getAbsolutePath());
        options.setDeviceName(ConfigReader.get("deviceName"));
        //options.setAppPackage(ConfigReader.get("appPackage"));
        //options.setAppActivity(ConfigReader.get("appActivity"));
        options.setNewCommandTimeout(Duration.ofSeconds(2000));
        
        //options.setNoReset(Boolean.parseBoolean(ConfigReader.get("noReset")));
        //options.setFullReset(Boolean.parseBoolean(ConfigReader.get("fullReset")));

        // Initialize driver
        logger.info("Initializing AndroidDriver...");
        driver = new AndroidDriver(new URI("http://localhost:4723").toURL(), options);
    }
    }
 
    public AppiumDriver getDriver() {
        return driver;
    }

    public WaitUtils getWait(int seconds) {
        return new WaitUtils(driver, seconds);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            logger.info("Keeping app open after execution (Debug Mode).");
            // Do nothing
        } else {
            logger.warn("Driver was already null at tearDown.");
        }

    }
}
