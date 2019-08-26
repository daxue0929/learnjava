package com.daxue.IO.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Test {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document xmlDoc = documentBuilder.parse("C:\\server\\data\\ccgat\\vtour\\tour.xml");

            Element element = xmlDoc.createElement("aini");

            element.setAttribute("ath","-139.327");
            element.setAttribute("atv","9.232");
            element.setAttribute("linkedscene","scene_p222");


            NodeList sceneList = xmlDoc.getElementsByTagName("scene");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //DOMSource source = new DOMSource(doc);
            Source source = new DOMSource(xmlDoc);
            //StreamResult result = new StreamResult();
            File file = new File("C:\\server\\data\\ccgat\\vtour\\tour.xml");
            Result result = new StreamResult(file);
            transformer.transform(source, result);//将 XML==>Source 转换为 Result




            System.out.println("1234");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
}
