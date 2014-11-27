package musicqubed.ui.authentication;

import musicqubed.core.Driver;
import musicqubed.core.EventLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by ars on 11/18/14.
 */
public class LoginScreen {

    public static final By JOIN_WITH_FACEBOOK_LOCATOR = By.xpath("//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]");
    public static final By JOIN_WITH_GOOGLE_LOCATOR = By.xpath("//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[1]");

    public NativeFacebookLoginScreen tapOnJoinWithFacebookButton(){
        WebElement joinWithFacebookButton = Driver.findElement(JOIN_WITH_FACEBOOK_LOCATOR);
        joinWithFacebookButton.click();
        EventLogger.FL_FACEBOOK_STARTED();
        return new NativeFacebookLoginScreen();
    }

    public LoginScreen tapOnJoinWithGoogleButton(){
        WebElement joinWithGoogleButton = Driver.findElement(JOIN_WITH_GOOGLE_LOCATOR);
        joinWithGoogleButton.click();
        EventLogger.FL_GOOGLE_PLUS_STARTED();
        return this;
    }

}
