package musicqubed.cucumber.ui.authentication;

import cucumber.api.java.en.Given;
import musicqubed.core.Driver;

/**
 * Created by ars on 11/24/14.
 */
public class LoginWithFacebook{

    @Given("^I open MTV_trax$")
    public void initApp(){
        Driver.pause(5000);
        System.out.println("JGURDA - Given" + Driver.get().getPageSource());
    }
}
