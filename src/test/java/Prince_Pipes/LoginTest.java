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
public class LoginTest extends BaseTest {

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
    public void enterPhoneNumber() {
    	
    	//Verify that user is able to add phone number.
        WebElement phoneField = wait.waitForElementClickable(
                AppiumBy.xpath("//android.widget.EditText[@text=\"write here...\"]")
        );
        
        Assert.assertTrue(phoneField.isDisplayed(),
                "phonefield is not displayed");

        phoneField.sendKeys("9876543210");

        WebElement requestOtpButton = wait.waitForElementClickable(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().textContains(\"Request OTP\")"
                )
              );
                Assert.assertTrue(requestOtpButton.isDisplayed(),
            			"requestOtpButton is not visible");

        requestOtpButton.click();
    }

     @Test(priority = 11)
      public void validateUserNotAvailablePopup() {

    // Wait for popup message
    WebElement errorMessage = wait.waitForElementVisible(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"User not available\")"
            )
    );

    Assert.assertTrue(errorMessage.isDisplayed(),
            "Error popup message not displayed");

    // Click OK button
    WebElement okButton = wait.waitForElementClickable(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"Ok\")"
            )
    );
    Assert.assertTrue(okButton.isDisplayed(),
            "okButton is not displayed");

    okButton.click();
}

@Test(priority = 12)
public void validatePleaseenter10digitnoPopup() {
//Wait for popup message
    WebElement phoneField = wait.waitForElementClickable(
            AppiumBy.className("android.widget.EditText")
            );
    Assert.assertTrue(phoneField.isDisplayed(),
            "phoneField is not displayed");
    
    phoneField.clear();
    phoneField.sendKeys("987654");

    WebElement requestOtpButton = wait.waitForElementClickable(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().description(\"Request OTP, \")")
            
            );
    Assert.assertTrue(requestOtpButton.isDisplayed(),
            "requestOtpButton is not displayed");
    
    requestOtpButton.click();
}


@Test(priority = 13)
public void validatemorethan10digitno() {
	//Check that if user is able to add more than 10 digit number
	WebElement phoneField = wait.waitForElementClickable(
			AppiumBy.className("android.widget.EditText")
			);
	
	Assert.assertTrue(phoneField.isDisplayed(),
            "phoneField is not displayed");
	
	phoneField.clear();
	phoneField.sendKeys("987654321012");
	//field should not accept more than 10 digit number\
}


@Test(priority = 14)
public void validatealphabeticcharacteracceptance() {
	//Check that if user is able to add alphabetic character into it
	WebElement phoneField = wait.waitForElementClickable(
			AppiumBy.className("android.widget.EditText")
			);
	
	Assert.assertTrue(phoneField.isDisplayed(),
            "phoneField is not displayed");
	
	phoneField.clear();
	phoneField.sendKeys("ABCDabcdfdf");
	//Phone no field should not accept any alphabetic character
	

}

@Test(priority = 15)
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


@Test(priority = 16)
public void validatemissingOTP() {
	//To check that if user tries to login with blank OTP field
	//wait for popup message
	WebElement OTP = wait.waitForElementClickable(
			AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[2]")
			);
	OTP.click();
	OTP.clear();
	
	 Assert.assertTrue(OTP.isDisplayed(),
	            "OTP is not displayed");
	
	 // Click submit button, now user will get attention popup
    WebElement submitButton = wait.waitForElementClickable(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().description(\"Submit, \")"
            )
    );
    
    Assert.assertTrue(submitButton.isDisplayed(),
            "submitButton is not displayed");

    submitButton.click();
}


@Test(priority = 17)
public void validateincorrectOTP() {
	//To check that if user add random number in OTP field
	//Attention pop-up will appear
	WebElement OTP = wait.waitForElementClickable(
			AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")
			);
	OTP.click();
	OTP.clear();
	OTP.sendKeys("0");
	
	Assert.assertTrue(OTP.isDisplayed(),
            "OTP is not displayed");

	 // Click submit button, now user will get attention popup
    WebElement submitButton = wait.waitForElementClickable(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().description(\"Submit, \")"
            )
    );
    Assert.assertTrue(submitButton.isDisplayed(),
            "submitButton is not displayed");
    
    submitButton.click();
	// Click submit button, user should not be able to submit
    WebElement okButton = wait.waitForElementClickable(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"button\")")
            );
    okButton.click();

}

@Test(priority = 18)
public void validateResendAndSubmitFlow() {

    WebElement resendButton = wait.waitForElementClickable(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"Resend\")"
            )
    );

    resendButton.click();

    // Wait for timer to restart (example 00:30)
    WebElement timer = wait.waitForElementVisible(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"00:\")"
            )
    );

    Assert.assertTrue(timer.isDisplayed(),
            "Timer did not restart after clicking Resend");

    // Now enter OTP
    WebElement otpField = wait.waitForElementClickable(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.widget.EditText\").instance(1)"
            )
    );

    otpField.sendKeys("1234");

    WebElement submitButton = wait.waitForElementClickable(
            AppiumBy.androidUIAutomator(
                    "new UiSelector().descriptionContains(\"Submit\")"
            )
    );

    submitButton.click();
}
@BeforeClass
public void setUpLogin1() {

    wait = getWait(500);

    OnboardingHelper onboarding = new OnboardingHelper();
    onboarding.completeOnboarding();
}

@Test(priority = 19)
public void hamburgermenu() {
	//Check that Hamburger menu button is clickable
	WebElement hamburgermenu = wait.waitForElementClickable(
			AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(58)")
			);
	Assert.assertTrue(hamburgermenu.isDisplayed(),
            "hamburgermenu is not visible");
	
	hamburgermenu.click();
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