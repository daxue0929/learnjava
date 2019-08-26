package com.daxue.IO.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document parse = documentBuilder.parse("http://192.168.0.100:15420/29442470/tour.xml");
            System.out.println();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
