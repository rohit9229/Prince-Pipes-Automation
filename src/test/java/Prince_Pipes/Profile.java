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
public class Profile extends BaseTest {

    private WaitUtils wait;

    @BeforeClass
    public void setUpLogin() {

        wait = getWait(30);

        OnboardingHelper onboarding = new OnboardingHelper();
        onboarding.completeOnboarding();
    }
    @Test(priority = 1)
    public void verifyOnboardingScreen() {

        // Verify title text (use contains instead of exact match)
        WebElement titleText = wait.waitForElementVisible(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().textContains(\"Place Orders\")"
                )
        );

        Assert.assertTrue(titleText.isDisplayed(),
                "Place Orders Anytime text is not visible");

        // Verify description text
        WebElement descriptionText = wait.waitForElementVisible(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().textContains(\"Use app or portal\")"
                )
        );

        Assert.assertTrue(descriptionText.isDisplayed(),
                "Description text is not visible");
    }


    @Test(priority = 2)
    public void clickNextButton() {

        // Locate Next button
    	WebElement nextButton = wait.waitForElementClickable(
    		    AppiumBy.androidUIAutomator(
    		    		"new UiSelector().description(\"Next, \")")
    		);


        Assert.assertTrue(nextButton.isDisplayed(),
                "Next button is not visible");

        nextButton.click();
    }
 
    @Test(priority = 3)
    public void verifySecondaryScreen() {

        // Verify title text
        WebElement titleText = wait.waitForElementVisible(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().textContains(\"Automate Primary/Secondary\")"
                )
        );

        Assert.assertTrue(titleText.isDisplayed(),
                "Automate Primary/Secondary Claims text is not visible");

        // Verify description text
        WebElement descriptionText = wait.waitForElementVisible(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().textContains(\"Claims backed by real sales\")"
                )
        );

        Assert.assertTrue(descriptionText.isDisplayed(),
                "Secondary screen description text is not visible");
    }

@Test(priority = 4)
public void clickNextButton2() {

	WebElement nextButton = wait.waitForElementClickable(
		    AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Next, \"]")
		);

    Assert.assertTrue(nextButton.isDisplayed(),
            "Next button is not visible");

    nextButton.click();
}


@Test(priority = 5)
public void verifyThirdScreen() {

    // Verify Title Text (use contains instead of exact)
    WebElement titleText = wait.waitForElementVisible(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"Track, Compare\")"
            )
    );

    Assert.assertTrue(titleText.isDisplayed(),
            "Track, Compare & Achieve title not visible");

    // Verify description text
    WebElement descriptionText = wait.waitForElementVisible(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"Measure what matters\")"
            )
    );

    Assert.assertTrue(descriptionText.isDisplayed(),
            "Third screen description text is not visible");
}

@Test(priority = 6) 
public void clickNextButton3() {
	WebElement nextButton = wait.waitForElementClickable(
		    AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Next, \"]")
		    );
	Assert.assertTrue(nextButton.isDisplayed(),
            "Next button is not visible");
	nextButton.click();
}


@Test(priority = 7)
public void verifyFourthScreen() {

    WebElement titleText = wait.waitForElementVisible(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"Get Ageing\")"
            )
    );

    Assert.assertTrue(titleText.isDisplayed(),
            "Get Ageing Summary title not visible");

    WebElement descriptionText = wait.waitForElementVisible(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"Stay ahead of dues\")"
            )
    );

    Assert.assertTrue(descriptionText.isDisplayed(),
            "Fourth screen description text not visible");
}

@Test(priority = 8)
public void clickNextButton4() {
	WebElement nextButton = wait.waitForElementClickable(
			AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Next, \"]")
			);
	Assert.assertTrue(nextButton.isDisplayed(),
			"Next Button is not visible");
	nextButton.click();
	
}


    @Test(priority = 9)
    public void verifyLoginScreen() {

        WebElement welcomeText = wait.waitForElementVisible(
                AppiumBy.xpath("//android.widget.TextView[@text=\"Welcome\"]")
                );

        Assert.assertTrue(welcomeText.isDisplayed(),
                "Welcome text not visible");
    }
    
    @Test(priority = 10)
    public void validatecorrectuserisloggedinsuccessfully() {
    	//Check that user is able to login properly with correct user
    	WebElement phoneField = wait.waitForElementClickable(
    			AppiumBy.className("android.widget.EditText")
    			);
    	phoneField.sendKeys("8877458741");
    	Assert.assertTrue(phoneField.isDisplayed(),
                "phoneField is not displayed");
    	
    	//Now user will be able to login properly
    	 WebElement requestOtpButton = wait.waitForElementClickable(
                 AppiumBy.androidUIAutomator(
                         "new UiSelector().textContains(\"Request OTP\")"
                 )
         );
    	 Assert.assertTrue(requestOtpButton.isDisplayed(),
    	            "requestOtpButton is not displayed");

         requestOtpButton.click();
    }
    
    @Test(priority = 11)
    public void validatesubmitbutton() {
    	//Check that submit button is clickable after OTP received.
    	WebElement submitbutton = wait.waitForElementClickable(
    			AppiumBy.androidUIAutomator("new UiSelector().description(\"Submit, \")")
    			);
    	Assert.assertTrue(submitbutton.isDisplayed(),
    			"submitbutton is not displayed");
    	
    	submitbutton.click();
    	
    }
    @BeforeClass
    public void setUpLogin1() {

        wait = getWait(200);

        OnboardingHelper onboarding = new OnboardingHelper();
        onboarding.completeOnboarding();
    }
    
    @Test(priority = 12)
    public void Verifyprofilebutton() {
     // Check that profile button is clickable
    	WebElement profilebutton = wait.waitForElementClickable(
    			AppiumBy.xpath("//android.view.View[@content-desc=\"Profile\"]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")
    			);
    	profilebutton.click();
    	
    	Assert.assertTrue(profilebutton.isDisplayed(),
    			"profilebutton is not displayed");
    	
    	
    }

@Test(priority = 13)
public void validatepersonaldetails() {
	//check that personal details button is clickable.
	WebElement personaldetails = wait.waitForElementClickable(
			AppiumBy.androidUIAutomator("new UiSelector().text(\"Personal Details\")")
			);
	
	personaldetails.click();
  }
};

