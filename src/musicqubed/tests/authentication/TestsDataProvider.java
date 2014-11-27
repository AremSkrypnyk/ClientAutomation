package musicqubed.tests.authentication;

import musicqubed.core.EventLogger;
import musicqubed.data.XMLConverter;
import musicqubed.data.FurryDataProvider;
import musicqubed.templates.EmptyTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by ars on 11/25/14.
 */
public class TestsDataProvider extends EmptyTest {

    @Test
    public void getDataForFurry() throws ParserConfigurationException, TransformerException, IOException, SAXException {

        EventLogger.FL_FACEBOOK_STARTED();
        EventLogger.FL_FACEBOOK_SUCCESS();
        /*XMLConverter xmlConverter = new XMLConverter("out.txt");
        xmlConverter.convertToXml("out.xml");

        FurryDataProvider furryDataProvider = new FurryDataProvider();
        */

        /*XMLBuilder xmlBuilder = new XMLBuilder("Flurry");
        xmlBuilder
                .createParentElement("Device")
                .createChildElement("Name", "HTC One")
                .createChildElement("UDID", "123456789")
                .returnToParentElement();

        xmlBuilder
                .createParentElement("Events");

        xmlBuilder
                .createParentElement("Event", 1)
                .createChildElement("Name", "Jgurda1")
                .createChildElement("username", "as1")
                .returnToParentElement();

        xmlBuilder
                .createParentElement("Event", 2)
                .createChildElement("Name", "Jgurda2")
                .createChildElement("username", "as2")
                .returnToParentElement();

        xmlBuilder.build("FlurryTest.xml");*/
/*
        XMLParser xmlParser = new XMLParser("FlurryTest.xml");
        System.out.print(xmlParser
                .getParentElementByTagAndId("Event", 2)
                .getChildElementByTag("Name"));*/

        /*System.out.print(xmlParser
                .getParentElementsByTag("Device")
                .getChildElementByTag("Name"));
*/
        //furryDataProvider.getDataFromServer();
    }
}
