package musicqubed.ui.authentication;

import musicqubed.core.Driver;
import org.openqa.selenium.By;

/**
 * Created by ars on 11/18/14.
 */
public class NativeFacebookLoginScreen {

    private static final By EMAIL_FIELD_LOCATOR = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]");
    private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[2]");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]");
    private static final By FACEBOOK_LOGO_LOCATOR = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ImageView[1]");

    private String username;
    private String password;

    public NativeFacebookLoginScreen() {
    }

    public NativeFacebookLoginScreen(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public void init(){
        Driver.findElement(EMAIL_FIELD_LOCATOR).clear();
        Driver.findElement(EMAIL_FIELD_LOCATOR).sendKeys(username);
        Driver.findElement(FACEBOOK_LOGO_LOCATOR).click();
        Driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
        Driver.findElement(LOGIN_BUTTON_LOCATOR).click();
    }
}
