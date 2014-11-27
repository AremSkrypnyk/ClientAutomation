package musicqubed.ui.authentication;

import musicqubed.core.Driver;
import org.openqa.selenium.By;

/**
 * Created by ars on 11/18/14.
 */
public class LoginScreen {

    public static final By JOIN_WITH_FACEBOOK_LOCATOR = By.xpath("//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]");
    public static final By JOIN_WITH_GOOGLE_LOCATOR = By.xpath("//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[1]");

    public NativeFacebookLoginScreen tapOnJoinWithFacebokButton(){
        Driver.findElement(JOIN_WITH_FACEBOOK_LOCATOR).click();
        return new NativeFacebookLoginScreen("tolik.lastochkin@gmail.com", "musicqubed");
    }

    public LoginScreen tapOnJoinWithGoogleButton(){
        Driver.findElement(JOIN_WITH_GOOGLE_LOCATOR).click();
        return this;
    }

}
