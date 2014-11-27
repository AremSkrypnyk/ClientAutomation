package musicqubed.core;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by ars on 11/20/14.
 */

public class TestListener extends TestListenerAdapter {

    private static final String SYSTEM_PROPERTIES_FILE_NAME = "test.properties";
    private List<String> environmentProperties = new ArrayList<>(Arrays.asList(
            "appPackage",
            "appActivity",
            "appBundleId",
            "appPath",
            "device",
            "udid",
            "username",
            "password",
            "timeout",
            "minApiLevel",
            "minAppiumApiLevel",
            "maxApiLevel"));

    LinkedList<String> environmentPropertyKeys = new LinkedList<>();


    @Override
    public void onStart(ITestContext tc){
        setupGlobalProperties(SYSTEM_PROPERTIES_FILE_NAME);
        setupEnvironmentProperties();

        tc.getSuite().getXmlSuite().setConfigFailurePolicy(XmlSuite.CONTINUE);

        Logger.init(tc.getSuite().getName());
        EventLogger.init();
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        Logger.logMessage("Test [" + Logger.getTestName() + "] has passed within [" + StringHelper.durationToTimeStr(tr.getEndMillis() - tr.getStartMillis()) + "].");
        EventLogger.saveSession();
    }

    @Override
    public void onTestStart(ITestResult tr){
        Logger.logTestStarted(tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        Logger.logError(tr);
    }

    @Override
    public void onConfigurationFailure(ITestResult tr) {
        Logger.logError(tr);
        Driver.tearDown();
        throw new SkipException("Skipping the test due to configuration failure");
    }

    private void setupGlobalProperties(String fileToReadName) {
        Properties properties = new Properties();
        FileInputStream propertyFile;
        try {
            propertyFile = new FileInputStream(fileToReadName);
            properties.load(propertyFile);
        } catch (IOException e) {
            throw new Error("Failed to read properties file '" + fileToReadName+"': " + e.getMessage(), e);
        }

        @SuppressWarnings("unchecked")
        Enumeration<String> e = (Enumeration<String>) properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            System.setProperty(key, properties.getProperty(key));
        }
    }

    private void setupEnvironmentProperties() {
        String specificKey = "." + System.getProperty("test.product") + "." + System.getProperty("test.platform");
        String productKey = "." + System.getProperty("test.product");
        String platformKey = "." + System.getProperty("test.platform");
        String commonKey = "";

        environmentPropertyKeys.add(specificKey);
        environmentPropertyKeys.add(platformKey);
        environmentPropertyKeys.add(productKey);
        environmentPropertyKeys.add(commonKey);

        for (String prop : environmentProperties) {
            try {
                System.setProperty("test." + prop, getFieldValue(prop));
            } catch (NullPointerException e) {
                throw new Error("Property '"+prop+"' is missing.");
            }
        }
    }

    private String getFieldValue(String fieldName){
        String value = "";
        for (int i = 0; i < environmentPropertyKeys.size(); i++) {
            value = System.getProperty("test." + fieldName + environmentPropertyKeys.get(i));
            if (value != null)
                break;
        }
        return value;
    }
}

