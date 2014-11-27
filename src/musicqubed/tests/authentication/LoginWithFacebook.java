package musicqubed.tests.authentication;

import io.appium.java_client.android.AndroidDriver;
import musicqubed.core.Driver;
import musicqubed.data.FurryDataProvider;
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
        NativeFacebookLoginScreen fbLogin = loginScreen.tapOnJoinWithFacebokButton();
        Driver.pause(3000);
        fbLogin.init();
        Driver.pause(3000);
    }
}

//((JavascriptExecutor)Driver.get()).executeScript("mobile: swipe", new HashMap<String, Double>(){{ put("touchCount", 1); put("startX", 420); put("startY", 440); put("endX", 57); put("endY", 446); put("duration", 0.5); }});

