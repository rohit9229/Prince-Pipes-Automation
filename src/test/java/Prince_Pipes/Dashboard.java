package Prince_Pipes;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import utils.BaseTest;
import utils.FullLogListener;
import utils.OnboardingHelper;
import utils.WaitUtils;

@Listeners(FullLogListener.class)
public class Dashboard extends BaseTest {

    private WaitUtils wait;

    @BeforeClass
    public void setUpLogin() {

        wait = getWait(500);

        OnboardingHelper onboarding = new OnboardingHelper();
        onboarding.completeOnboarding();
    }


    @Test(priority = 20)
    public void verifydashboardScreen() {
    	//Check that dashboard screen is correctly visible
       // Verify title text (use contains instead of exact match)

    	WebElement verifydashboardScreen = wait.waitForElementClickable(
    			AppiumBy.androidUIAutomator("new UiSelector().text(\"TS Enterprises\")")
    			);
    	Assert.assertTrue(verifydashboardScreen.isDisplayed(),
                "TS Enterprises text is not visible");
    	
    }
    @Test(priority = 21)
    public void clickforqueriesbutton() {

        // Locate Next button
    	WebElement forqueriesbutton = wait.waitForElementClickable(
    		    AppiumBy.androidUIAutomator(
    		    		"new UiSelector().text(\"For Queries: Contact Sales\")"
    		    		)
    		);


        Assert.assertTrue(forqueriesbutton.isDisplayed(),
                "for queries button is not visible");

        forqueriesbutton.click();
    }

    @Test(priority = 22)
    public void clickonviewmorebutton() {

        WebElement viewmorebutton = scrollToText("View More ▼");

        Assert.assertTrue(viewmorebutton.isDisplayed(),
                "View more button is not visible");

        viewmorebutton.click();
    }
    @Test(priority = 23)
    public void clickonviewlessbutton(){

        WebElement viewlessbutton = scrollToText("View Less ▲");

        Assert.assertTrue(viewlessbutton.isDisplayed(),
                "View less button is not visible");
    }
    
    @Test(priority = 24)
    public void verifygrowthsummaryscreen() {

        WebElement verifygrowthsummaryScreen = scrollToText("Growth Summary");

        Assert.assertTrue(verifygrowthsummaryScreen.isDisplayed(),
                "Growth Summary text is not visible");
    }
    
    @Test(priority = 25)
    public void clickongraphbutton() {

        WebElement clickongraphbutton = scrollToText("Growth Summary");

        Assert.assertTrue(clickongraphbutton.isDisplayed(),
                "Graph section not visible");
    }
@Test(priority = 26)
public void clickonquarterlybutton() {
	//To check that quarterly button is clickable or not
	WebElement clickonquarterlybutton = wait.waitForElementClickable(
			AppiumBy.androidUIAutomator("new UiSelector().description(\"Quarterly\")")
			);
	
	Assert.assertTrue(clickonquarterlybutton.isDisplayed(),
			"Quaterly button is not visible");
}

@Test(priority = 27)
public void clickonmonthlybutton() {
	//To check that monthly button is clickable or not
	WebElement clickonmonthlybutton = wait.waitForElementClickable(
			AppiumBy.androidUIAutomator("new UiSelector().description(\"Monthly\")")
			);
	
	Assert.assertTrue(clickonmonthlybutton.isDisplayed(),
			"Monthly button is not visible");
}

@Test(priority = 28)
public void verifyproductcategoryscreen() {

    WebElement verifyproductcategoryscreen = scrollToText("Product Category");

    Assert.assertTrue(verifyproductcategoryscreen.isDisplayed(),
            "Product category column is not visible");
}
@Test(priority = 29)
public void clickoncurrentyearbutton() {
    //To check that current year button is clickable or not
	WebElement clickoncurrentyearbutton = wait.waitForElementClickable(
			AppiumBy.androidUIAutomator("new UiSelector().description(\"Current Year\")")
			);
	
	Assert.assertTrue(clickoncurrentyearbutton.isDisplayed(),
			"Current year button is not visible");
}

@Test(priority = 30)
public void clickonlastyearbutton() {
	//To check that last year button is clickable or not
	WebElement clickonlastyearbutton = wait.waitForElementClickable(
			AppiumBy.androidUIAutomator("new UiSelector().text(\"Last Year\")")
			);

	Assert.assertTrue(clickonlastyearbutton.isDisplayed(),
			"Last year button is not visible");
}
@Test(priority = 31)
public void verifysalesscreen() {

    WebElement verifysalesscreen = scrollToText("Sale of New Products");

    Assert.assertTrue(verifysalesscreen.isDisplayed(),
            "Sale of new product is not displayed");
}

public WebElement scrollToText(String text) {
    return driver.findElement(
        AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"
        )
    );
}
};


