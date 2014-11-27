package musicqubed.tests.authentication;

import io.appium.java_client.android.AndroidDriver;
import musicqubed.core.Driver;
import musicqubed.templates.BaseTest;
import musicqubed.ui.authentication.LoginScreen;
import musicqubed.ui.authentication.NativeFacebookLoginScreen;
import org.testng.annotations.Test;

/**
 * Created by ars on 11/18/14.
 */
public class LoginWithFacebook extends BaseTest {

    @Test
    public void verifyAppLaunched(){
        Driver.pause(10000);
        LoginScreen loginScreen = new LoginScreen();
        NativeFacebookLoginScreen fbLogin = loginScreen.tapOnJoinWithFacebookButton();
        Driver.pause(3000);
        fbLogin.init();
        ((AndroidDriver)Driver.get()).runAppInBackground(10000);
    }
}
