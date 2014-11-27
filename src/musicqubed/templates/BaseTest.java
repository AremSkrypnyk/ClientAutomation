package musicqubed.templates;

import musicqubed.core.Driver;
import musicqubed.core.TestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

/**
 * Created by ars on 11/18/14.
 */

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    public void init(){
        Driver.init();
    }

    @AfterMethod
    public void cleanup() {
        Driver.tearDown();
    }

}
