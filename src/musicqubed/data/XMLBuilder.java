package musicqubed.data;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by ars on 11/26/14.
 */
public class XMLBuilder {

    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Document doc;
    private static Node rootElement;

    public XMLBuilder(String name) {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        rootElement = doc.createElement(name);
        doc.appendChild(rootElement);
    }

    public XMLBuilder(FlurryData.Tag name) {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        rootElement = doc.createElement(name.toString());
        doc.appendChild(rootElement);
    }

    public XMLBuilder createParentElement(String tagName){
        Element tag = doc.createElement(tagName);
        rootElement.appendChild(tag);
        rootElement = tag;
        return this;
    }

    public XMLBuilder createParentElement(FlurryData.Tag tagName){
        Element tag = doc.createElement(tagName.toString());
        rootElement.appendChild(tag);
        rootElement = tag;
        return this;
    }

    public XMLBuilder createParentElement(String tagName, int id){
        Element tag = doc.createElement(tagName);

        Attr attr = doc.createAttribute("id");
        attr.setValue(id + "");
        tag.setAttributeNode(attr);

        rootElement.appendChild(tag);
        rootElement = tag;
        return this;
    }

    public XMLBuilder createParentElement(FlurryData.Tag tagName, int id){
        Element tag = doc.createElement(tagName.toString());

        Attr attr = doc.createAttribute("id");
        attr.setValue(id + "");
        tag.setAttributeNode(attr);

        rootElement.appendChild(tag);
        rootElement = tag;
        return this;
    }

    public XMLBuilder createChildElement(String tagName){
        Element tag = doc.createElement(tagName);
        rootElement.appendChild(tag);
        return this;
    }

    public XMLBuilder createChildElement(FlurryData.Tag tagName){
        Element tag = doc.createElement(tagName.toString());
        rootElement.appendChild(tag);
        return this;
    }

    public XMLBuilder createChildElement(String tagName, String tagValue){
        Element tag = doc.createElement(tagName);
        tag.appendChild(doc.createTextNode(tagValue));
        rootElement.appendChild(tag);
        return this;
    }

    public XMLBuilder createChildElement(FlurryData.Parameter tagName, FlurryData.Event tagValue){
        Element tag = doc.createElement(tagName.toString());
        tag.appendChild(doc.createTextNode(tagValue.toString()));
        rootElement.appendChild(tag);
        return this;
    }

    public XMLBuilder createChildElement(FlurryData.Parameter tagName, String tagValue){
        Element tag = doc.createElement(tagName.toString());
        tag.appendChild(doc.createTextNode(tagValue));
        rootElement.appendChild(tag);
        return this;
    }

    public XMLBuilder returnToParentElement(){
        Node parentNode = rootElement.getParentNode();
        rootElement = parentNode;
        return this;
    }

    public void build(String pathToXML){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathToXML));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void buildToConsole(){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
