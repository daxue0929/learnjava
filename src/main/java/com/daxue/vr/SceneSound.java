package com.daxue.vr;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;

public class SceneSound {
    public static void main(String[] args) {
        Document tourXmldoc = null;
        Element tourRoot = null;
        String baseUrl = "C:\\server\\data\\ccga\\29352609\\vtour\\";

        File file = new File(baseUrl + "sound");
        boolean isSoundDir = file.mkdir();
        if (isSoundDir) {
            System.out.println("创建成功");

//            创建成功声音文件夹之后，移动声音文件到此目录 sound
        }

        try {
            tourXmldoc = NaviUtil.getXMLFile(baseUrl + "tour.xml");

            tourRoot = tourXmldoc.getDocumentElement();
            System.out.println(tourRoot.getTagName());

            Element plugin = tourXmldoc.createElement("plugin");
            plugin.setAttribute("name","soundinterface");
            plugin.setAttribute("url.flash","%SWFPATH%/plugins/soundinterface.swf");
            plugin.setAttribute("url.html5","%SWFPATH%/plugins/soundinterface.js");
            plugin.setAttribute("rootpath","%SWFPATH%");
            plugin.setAttribute("preload","true");
            plugin.setAttribute("keep","true");

            // 找到要添加的节点位置
            Element firstNode = (Element) NaviUtil.selectSingleNode("/krpano/scene", tourRoot);

            tourRoot.insertBefore(plugin,firstNode);

            NaviUtil.saveXml(baseUrl + "tour.xml",tourXmldoc);


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }



    }



}

class NaviUtil{


    public static void copy(File source, File fil) throws IOException {
        // 判断源目录是不是一个目录
        if(!source.isDirectory()){
        //如果不是目录就不复制
            return;
        }
        //创建目标目录的file对象
        if (!fil.exists()) {
        //不存在就创建文件夹
            fil.mkdir();
        } else {
            return;
        }
        //如果源文件存在就复制
        if (source.exists()) {
            // 获取源目录下的File对象列表
            File[] files = source.listFiles();

            for (File file2 : files) {
//新文件夹的路径
                File file4 = new File(fil + File.separator + file2.getName());

                if (file2.isDirectory()) {
                    copy(file2, file4);
                }
                if (file2.isFile()) {
                    FileInputStream in = new FileInputStream(file2);
                    FileOutputStream out = new FileOutputStream(file4);

                    byte[] bs = new byte[1026];

                    int count = 0;
//循环把源文件的内容写入新文件
                    while ((count = in.read(bs, 0, bs.length)) != -1) {
                        out.write(bs, 0, count);
                    }
//关闭流
                    out.flush();
                    out.close();
                    in.close();
                }
            }
        }
    }



    public static Document getXMLFile(String file) throws SAXException, IOException, ParserConfigurationException {
        System.out.println("读取xml文件：" + file);
        // 读取xml 文件
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder db = factory.newDocumentBuilder();
        Document xmlDoc = db.parse(new File(file));

        return xmlDoc;
    }


    /**
     * 查找节点，并返回第一个符合条件节点
     */
    public static Node selectSingleNode(String express, Object source) {
        Node result = null;
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        try {
            result = (Node) xpath.evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            System.err.println(e);
        }

        return result;
    }


    /**
     * 将Document输出到文件
     * @param fileName	输出的文件名（可以使用绝对路径）
     * @param doc	输出的文件
     */
    public static void saveXml(String fileName, Document doc) {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source = new DOMSource();
            source.setNode(doc);
            StreamResult result = new StreamResult();
            result.setOutputStream(new FileOutputStream(fileName));

            System.out.println(result.toString());

            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            System.err.println(e);
        } catch (TransformerException e) {
            System.err.println(e);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
}

