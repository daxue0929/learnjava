package com.daxue.model;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @author Admin
 * @date 2020/03/11
 **/
public class XmlTest {


    @Test
    public void getParamterFromXml() {
        JSONObject jsonObject = new JSONObject();
        try {
            Document document = readFile();
            Element rootElement = document.getRootElement();
            Element element = rootElement.element("details");
            Iterator details = element.elementIterator("detail");
            while(details.hasNext()) {
                DeliverRequest itemDeliver = new DeliverRequest();
                Element detail = (Element) details.next();


                String taskId = detail.element("taskId").getTextTrim();
                String msisdn = detail.element("msisdn").getTextTrim();
                String status = detail.element("status").getTextTrim();
                String download_status = detail.element("download_status").getTextTrim();

                itemDeliver.setMsgId(taskId);
                itemDeliver.setUsrId(msisdn);
                if (status == "1") { // 发送状态0表示发送失败  1 表示发送成功
                    itemDeliver.setStat("DELIVRD");
                    itemDeliver.setExt(download_status); //下载状态

                }
            }
            // 流水号
            // 手机号
            // 回执状态
            //
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }


    public Document readFile() throws DocumentException {
        File file = new File("E:/test/request.xml");
        Document document = null;
        if (file.isFile()) {
            SAXReader reader = new SAXReader();
            document = reader.read(file);
        }
        return document;
    }


}
