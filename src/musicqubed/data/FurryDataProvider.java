package musicqubed.data;

import musicqubed.core.XMLBuilder;
import musicqubed.core.XPathBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Created by ars on 11/25/14.
 */
public class FurryDataProvider {

    public static final String LOGIN = "artem.skrypnyk@musicqubed.com";
    private static final String PASSWORD = "Blaugrana1899";


    private static final String BASE_URL = "https://dev.flurry.com/analyticsBehaviorEventsLogsAll.do?projectID=553265&versionCut=versionsAll&intervalCut=30Days";

    private static final By EMAIL_FIELD_LOCATOR = By.id("emailInput");
    private static final By PASSWORD_FIELD_LOCATOR = By.id("passwordInput");
    private static final By LOG_IN_BUTTON_LOCATOR = By.xpath("//button/span");

    WebDriver chromeDriver;

    private void initChromeDriver(){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-web-security");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        chromeDriver = new ChromeDriver(option);
    }

    public void getDataFromServer(){
        initChromeDriver();
        goTo(BASE_URL);
        login(LOGIN, PASSWORD);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goTo(BASE_URL);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> listOfEvents = getListOfEvents("IMEI_354435055298277", "11/25/14 17:36:57 EET");
        parseList(listOfEvents);

        chromeDriver.quit();
    }

    private void parseList(List<WebElement> listOfEvents) {
        XMLBuilder xmlBuilder = new XMLBuilder("Flurry");

        xmlBuilder
                .createParentElement("Device");

        WebElement sessionInfo = listOfEvents.get(0);
        xmlBuilder
                .createChildElement("name", sessionInfo.findElement(By.xpath(".//td[contains(@class, 'yui-dt-col-device')]//a")).getText());
        xmlBuilder
                .createChildElement("time_stamp", sessionInfo.findElement(By.xpath(".//td[contains(@class, 'yui-dt-col-timestamp')]/div")).getText());

        xmlBuilder
                .returnToParentElement();

        xmlBuilder
                .createParentElement("Events");
        int eventCounter = 1;
        for (int i = 1; i < listOfEvents.size(); i++) {
            WebElement element = listOfEvents.get(i);
            List<WebElement> eventField = element.findElements(By.xpath(".//div[contains(@id,'eventLogName')]"));
            if (eventField.size() > 0) {
                xmlBuilder
                        .createParentElement("Event", eventCounter);
                eventCounter++;
                xmlBuilder
                        .createChildElement("name", eventField.get(0).findElement(By.xpath("./a")).getText());
                eventField.get(0).click();
            } else {
                List<WebElement> value = element.findElements(By.xpath(".//div[contains(@class, 'depth2') and not(contains(@class, 'extraRow'))]"));
                if (value.size() > 0) {
                    String key = value.get(0).getText();
                    xmlBuilder
                            .createChildElement(key.substring(0, key.indexOf(":")), key.substring(key.indexOf(" ") + 1));
                }
            }
        }
        xmlBuilder.build("TestFile.xml");

    }

    private void goTo(String url){
        this.chromeDriver.get(url);
    }

    private void login(String userName, String password) {
        chromeDriver.findElement(EMAIL_FIELD_LOCATOR).sendKeys(userName);
        chromeDriver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
        chromeDriver.findElement(LOG_IN_BUTTON_LOCATOR).click();
    }

    private List<WebElement> getListOfEvents(String deviceName, String timeStamp){
        final String deviceIdClass = "yui-dt0-col-device yui-dt-col-device yui-dt-last";
        String sessionLocator = new XPathBuilder().byTag("tr").withChildTag("td").withClassName(deviceIdClass).build();
        String targetSessionLocator = new XPathBuilder().byTag("tr").withChildTextContains(deviceName).and().withChildTextContains(timeStamp).build();

        List<WebElement> sessions = chromeDriver.findElements(By.xpath(sessionLocator));
        WebElement targetSession = chromeDriver.findElement(By.xpath(targetSessionLocator));

        int sessionNumber = 0;
        for (int i = 0; i < sessions.size(); i++)
            if (sessions.get(i).equals(targetSession))
                sessionNumber = i;
        String idOfTargetSession = (sessions.get(sessionNumber).getAttribute("id")).substring(7);
        String idOfNextSession = (sessions.get(sessionNumber + 1).getAttribute("id")).substring(7);

        List<WebElement> listOfSessionEvents = new ArrayList<>();
        String idFormat = "yui-rec";
        for (int j = Integer.parseInt(idOfTargetSession); j < Integer.parseInt(idOfNextSession); j++) {
            listOfSessionEvents.add(chromeDriver.findElement(By.id(idFormat + j)));
        }

        return listOfSessionEvents;
    }

}
