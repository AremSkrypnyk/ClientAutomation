package musicqubed.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.DoubleClickAction;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.SystemClock;
import org.testng.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ars on 11/14/14.
 */
public class Driver {

    private static final String platform               = System.getProperty("test.platform");
    private static final String apiLevel               = System.getProperty("test.apiLevel");

    private static WebDriver driver;

    public static WebDriver get(){
        return driver;
    }

    public static void set(WebDriver driverInput){
        driver = driverInput;
    }

    public static boolean isInitialized() {
        return driver != null;
    }

    public static void pause(int timeout){
        try {
            Thread.sleep(timeout);
        }
        catch (InterruptedException e){ }
    }

    public static WebElement findElement(By by){
        List<WebElement> elements;
        Assert.assertNotNull(by, "Cannot find element: locator in null");
        Assert.assertNotNull(get(), "Driver is not initialized");
        elements = get().findElements(by);
        if (elements.isEmpty())
            throw new Error("No element was found.");
        if (elements.size() > 1)
            throw new Error("More than 1 element for found using giving locator.");
        return elements.get(0);
    }

    public static File getScreenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }

    public static void init(){
        final double MIN_API_LEVEL          = Double.parseDouble(System.getProperty("test.minApiLevel"));
        final double MIN_APPIUM_API_LEVEL   = Double.parseDouble(System.getProperty("test.minAppiumApiLevel"));
        final double MAX_API_LEVEL          = Double.parseDouble(System.getProperty("test.maxApiLevel"));

        WebDriver driverInput;

        switch (platform) {
            case "ios":
                if (isBetween(apiLevel, MIN_API_LEVEL, MAX_API_LEVEL))
                    driverInput = initIosDriver();
                else throw new Error("Unsupported API level: " + apiLevel);
                break;
            case "android":
                if (isBetween(apiLevel, MIN_APPIUM_API_LEVEL, MAX_API_LEVEL))
                    driverInput = initAndroidDriver();
                else
                    if (isBetween(apiLevel, MIN_API_LEVEL, MIN_APPIUM_API_LEVEL - 1))
                        driverInput = initSelendroidDriver();
                    else throw new Error("Unsupported API level: " + apiLevel);
                break;
            default:
                throw new Error("Unsupported platform: " + platform);
        }
        driverInput.manage().timeouts().implicitlyWait(
                Integer.parseInt(System.getProperty("test.timeout")),
                TimeUnit.SECONDS
        );

        Driver.set(driverInput);
    }

    private static IOSDriver initIosDriver() {
        final String APP_BUNDLE_ID = System.getProperty("test.appPath");

        IOSDriver iosDriver;
        DesiredCapabilities iosCapabilities = new DesiredCapabilities();
        iosCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        iosCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, apiLevel);
        iosCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        iosCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        iosCapabilities.setCapability(MobileCapabilityType.APP, APP_BUNDLE_ID);
        if (Boolean.parseBoolean(System.getProperty("test.resetApp")))
            iosCapabilities.setCapability("fullReset", true);
        else
            iosCapabilities.setCapability("noReset", true);
        try {
            iosDriver =  new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), iosCapabilities);
        } catch (MalformedURLException e) {
            throw new Error("Unexpected error during iOS Driver setup: " + e.getMessage(), e);
        }
        return iosDriver;
    }

    private static AndroidDriver initAndroidDriver(){
        final String APP_PATH     = System.getProperty("test.appPath");
        final String APP_PACKAGE  = System.getProperty("test.appPackage");
        final String APP_ACTIVITY = System.getProperty("test.appActivity");
        AndroidDriver androidDriver;
        DesiredCapabilities androidCapabilities = new DesiredCapabilities();
        androidCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        androidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "LGD85598138009");//BH908C850G LGD85598138009
        androidCapabilities.setCapability(MobileCapabilityType.APP, APP_PATH);
        androidCapabilities.setCapability(MobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
        androidCapabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
        if (Boolean.parseBoolean(System.getProperty("test.resetApp")))
            androidCapabilities.setCapability("fullReset", true);
        else
            androidCapabilities.setCapability("noReset", true);
        try {
            androidDriver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), androidCapabilities);
        } catch (MalformedURLException e) {
            throw new Error("Unexpected error during Android Driver setup: " + e.getMessage(), e);
        }
        return androidDriver;
    }

    private static WebDriver initSelendroidDriver(){
        final String APP_PATH     = System.getProperty("test.appPath");
        final String APP_PACKAGE  = System.getProperty("test.appPackage");
        final String APP_ACTIVITY = System.getProperty("test.appActivity");
        SwipeableWebDriver selendroidDriver;
        DesiredCapabilities selendroidCapabilities = new DesiredCapabilities();
        selendroidCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        selendroidCapabilities.setCapability("automationName", "Selendroid");
        selendroidCapabilities.setCapability("platformName", "Android");
        selendroidCapabilities.setCapability("deviceName", "HT1C8VZ02336");
        selendroidCapabilities.setCapability("app", APP_PATH);
        selendroidCapabilities.setCapability("appPackage", APP_PACKAGE);
        selendroidCapabilities.setCapability("appActivity", APP_ACTIVITY);
        if (Boolean.parseBoolean(System.getProperty("test.resetApp")))
            selendroidCapabilities.setCapability("fullReset", true);
        else
            selendroidCapabilities.setCapability("noReset", true);
        try {
            selendroidDriver = new SwipeableWebDriver(new URL("http://localhost:4723/wd/hub"), selendroidCapabilities);
        } catch (MalformedURLException e) {
            throw new Error("Unexpected error during Swipeable WebDriver setup: " + e.getMessage(), e);
        }
        return selendroidDriver;
    }

    public static void tearDown() {
        if (Driver.isInitialized())
            Driver.get().quit();
        else
            throw new Error("Driver was not initialized and cannot be terminated.");
    }

    private static boolean isBetween(String value, double lower, double upper) {
        return (lower <= Double.parseDouble(value)) && (Double.parseDouble(value) <= upper);
    }





    public static class SwipeableWebDriver extends RemoteWebDriver implements HasTouchScreen {
        private RemoteTouchScreen touch;

        public SwipeableWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
            super(remoteAddress, desiredCapabilities);
            touch = new RemoteTouchScreen(getExecuteMethod());
        }

        public TouchScreen getTouch() {
            return touch;
        }
    }
}
