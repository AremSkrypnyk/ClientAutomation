package musicqubed.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by ars on 11/26/14.
 */
public class XMLParser {

    private static File fXmlFile;
    private static DocumentBuilderFactory dbFactory;
    private static DocumentBuilder dBuilder;
    private static Document doc;

    private static NodeList listOfElements;
    private static Node targetElement = null;

    public XMLParser(String pathToFile) throws IOException, SAXException, ParserConfigurationException {
        fXmlFile = new File(pathToFile);
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
    }

    public XMLParser getParentElementsByTag(String tag){
        listOfElements = doc.getElementsByTagName(tag);
        return this;
    }

    public XMLParser getParentElementByTagAndId(String tag, int id){
        listOfElements = doc.getElementsByTagName(tag);
        for (int i=0;i<listOfElements.getLength(); i++) {
            Node node = listOfElements.item(i);
            if ( (node.getNodeType() == Node.ELEMENT_NODE) && (((Element)node).getAttribute("id").equalsIgnoreCase(id + "")) ){
                this.targetElement = node;
            }
        }
        return this;
    }

    public String getChildElementByTag(String tag){

        return ((Element)targetElement).getElementsByTagName(tag).item(0).getTextContent();
    }

    public String getElementByTag(String tag){
        listOfElements = doc.getElementsByTagName(tag);
        targetElement = listOfElements.item(0);
        return ((Element)targetElement).getElementsByTagName(tag).item(0).getTextContent();
    }


}
