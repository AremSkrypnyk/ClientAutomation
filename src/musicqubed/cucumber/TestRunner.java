package musicqubed.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import musicqubed.core.Driver;
import musicqubed.core.TestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

/**
 * Created by ars on 11/24/14.
 */

@Listeners(TestListener.class)
@CucumberOptions(features = "src/musicqubed/cucumber/resources/authentication/LoginWithFacebook.feature")
        //, format = {"pretty", "html:target/cucumber-html-report", "json-pretty:target/cucumber-report.json"})
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeMethod
    public void init(){
        Driver.init();
    }

    @AfterMethod
    public void cleanup() {
        Driver.tearDown();
    }

}
