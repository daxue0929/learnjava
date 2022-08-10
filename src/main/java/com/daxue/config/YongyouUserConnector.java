//package com.daxue.config;
//
//import com.alibaba.fastjson.JSON;
//import com.yufu.idaas.extension.connector.base.core.model.ConnectorLcmConfig;
//import com.yufu.idaas.extension.connector.base.core.model.ConnectorPayloadContext;
//import com.yufu.idaas.extension.connector.base.core.model.ConnectorReadData;
//import com.yufu.idaas.extension.connector.base.core.model.ConnectorWriteData;
//import com.yufu.idaas.extension.connector.base.exception.ConnectorExtensionException;
//import com.yufu.idaas.extension.connector.impl.yongyou.domain.RequestModel;
//import com.yufu.idaas.extension.rest.base.core.RestClient;
//import com.yufu.idaas.extension.rest.base.excpetion.RestConnectException;
//import com.yufu.idaas.extension.rest.base.excpetion.RestContentException;
//import com.yufu.idaas.extension.rest.base.excpetion.RestExtensionException;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.yufu.idaas.extension.connector.base.core.model.ConnectorPayloadType.PROGRESS;
//import static com.yufu.idaas.kernel.core.util.CollectionsUtils.mapOf;
//
//public class YongyouUserConnector extends AbstractYongyouConnector {
//
//    static final String TYPE = "type";
//    static final String START_TIME = "starttime";
//    static final String END_TIME = "endtime";
//    static final String RESULT = "result";
//
//    static final String CREATE_URL = "/service/QueryPsndocServlet";
//    static final String QUERY_URL = "/service/QueryPsndocServlet";
//
//    @Override
//    protected void read(
//        final ConnectorPayloadContext context, final ConnectorLcmConfig config
//    ) throws ConnectorExtensionException, RestExtensionException {
//        String externalIdKey = config.getExternalIdKey();
//        String baseUrl = (String) config.getConfig().get("baseUrl");
//        String startTime = (String) config.getConfig().get("starttime");
//        String endTime = (String) config.getConfig().get("endtime");
//        List<Map<String, Object>> data = readAll(baseUrl, startTime, endTime);
//        messageSender.streamingRead(
//            context.newInstance(PROGRESS),
//            data.stream().map(m -> ConnectorReadData.of(m.get(externalIdKey), m))
//        );
//
//    }
//
//    private List<Map<String, Object>> readAll(final String baseUrl, final String startTime, final String endTime) throws
//        RestConnectException,
//        RestContentException {
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data", mapOf(
//            TYPE, "psndoc",
//            START_TIME, startTime,
//            END_TIME, endTime
//        ));
//
//        ResponseEntity<String> post = client.post(
//            RestClient.genRequestUri(baseUrl + QUERY_URL),
//            map,
//            ParameterizedTypeReference.forType(String.class)
//        );
//        com.alibaba.fastjson.JSONObject resulObj = JSON.parseObject(post.getBody());
//        List<Map<String, Object>> result =(List<Map<String, Object>>) resulObj.get(RESULT);
//        return result;
//    }
//
//    @Override
//    protected void write(
//        final ConnectorPayloadContext context, final ConnectorLcmConfig config, final ConnectorWriteData data
//    ) throws ConnectorExtensionException, RestExtensionException {
//        String baseUrl = (String) config.getConfig().get("baseUrl");
//        String account = (String) config.getConfig().get("account");
//        String groupcode = (String) config.getConfig().get("groupcode");
//
//
//
//        ConnectorReadData i = data.getLastData();
//        try {
//            switch (data.getOperationType()) {
//                case ConnectorWriteData.CREATE_TYPE:
//                    i.setExternalId(createOne(baseUrl + CREATE_URL, account, groupcode, i.getData()));
//                    break;
//                default:
//            }
//            data.setSuccess(true);
//        } catch (RestExtensionException e) {
//            data.setSuccess(false).setMessage(e.getMessage());
//        }
//        messageSender.write(context, data);
//
//
//    }
//
//    private String createOne(
//        final String url,
//        final String account,
//        final String groupcode,
//        final Map<String, Object> data
//    ) throws RestConnectException, RestContentException {
//        HttpHeaders headers = new HttpHeaders();
//
//        // 身体拼接一个xml出来
//        RequestModel body = new RequestModel();
//        body.setSex("1");
//
//
//        String response = client.responseBaseCheck(
//            client.post(RestClient.genRequestUri(
//                url,
//                mapOf(
//                    "account", account,
//                    "groupcode", groupcode
//                )
//            ), headers, body, ParameterizedTypeReference.forType(String.class))
//        );
//
//        Boolean success = parseResponse(response);
//        if (success) {
//
//        }
//
//        return null;
//    }
//
//    private Boolean parseResponse(final String response) {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//
//
//        DocumentBuilder builder = null;
//        try {
//            builder = factory.newDocumentBuilder();
//
//            Document document = builder.parse(response);
//            NodeList resultcode = document.getElementsByTagName("resultcode");
//            Node item = resultcode.item(0);
//            String nodeValue = item.getNodeValue();
//            if ("1".equals(nodeValue)) {
//                return true;
//            } else return false;
//
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//        return null;
//    }
//}
