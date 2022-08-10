package com.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApUtils {

    public static String THREE_FOUR_FILE_NAME = null;
    public static String CIG_FILE_NAME = null;
    public static String NEW_FOLDER = "/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/result";
    public static String JSON_PATH = "/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/3.4_cig_new.json";


    public static String PATH = null;

    public static String DATA = "data";


    public static void main(String[] args) throws IOException {
        PATH = inputString("请输入sql原文件夹路径");

        ApUtils apUtils = new ApUtils();
        List<File> files = readFolder(PATH); // NEW_FOLDER
        Iterator<File> fileIterator = files.iterator();
        String jsonString = fromFileGetString(new File(JSON_PATH));

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Set<String> keySet = jsonObject.keySet();
        Iterator<String> iterator = keySet.iterator();

        while (fileIterator.hasNext()) {
            File next = fileIterator.next();
            String s = fromFileGetString(next);
            if (s.indexOf("ap-") != -1 ){
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Object value = jsonObject.get(key);
                    if (StringUtils.isNotEmpty((String)value) && !key.equals(value)) {
                        s = s.replaceAll(key, (String) value);
                    }
                }
            }
            if (next.getName().equals("Tenant"))
                s = replaceEachTenantName(s);
            writeFile(s, next.getName());
        }
    }


    static List<File> readFolder(String path) {
        File file = new File(path);
        List<File> list = null;
        if (file.isDirectory()) {
            NEW_FOLDER = file.getParent() + "/result-" + file.getName();
            File[] files = file.listFiles();
//            ArrayList<File> fileArrayList = new ArrayList<>(Arrays.asList(files));
            list = new ArrayList<File>(files.length);
            Collections.addAll(list, files);
        }
        return list;
    }

//    @Test
//    public void test() throws FileNotFoundException {
//        THREE_FOUR_FILE_NAME = "/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/3.4_appList.json";
//        CIG_FILE_NAME = "/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/cig_appList.json";
//
//        HashMap apMap1 = getAPMapWithList();
//        System.out.println(apMap1);
//
//    }

//    @Test
//    public void test3() throws FileNotFoundException {
//        File appInstance = new File("/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/AppInstance");
//        String s = fromFileGetString(appInstance);
//        String[] split = s.split("\n");
//        int i = 0;
//        int k = 0;
//        String result = "";
//        for (String item : split){
//            if (item.indexOf("SDK") != -1 && item.indexOf("tokenParamKey") != -1
//            && item.indexOf("workbenchHome") != -1) {
//                result = item;
//                System.out.println(++k);
//                int key = item.indexOf("key");
//                item.replaceAll("SDK", "CUSTOM"); // 把sdk替换成CUSTOM
//            }
//            System.out.println(++i + "-------  " + item);
//        }
//
//        String s1 = result.replaceAll("SDK", "CUSTOM");
//        String pattern = "(ai-.*)',";
//        Matcher matcher = Pattern.compile(pattern).matcher(s1);
//        matcher.find();
//        String group = matcher.group();
//        int i1 = group.indexOf("'");
//        String substring = group.substring(0, i1);
//        System.out.println(substring);
//    }


    public HashMap getAPMapWithList() throws FileNotFoundException {
        ApUtils apUtils = new ApUtils();
        String fileStr = apUtils.fromFileGetString(new File(THREE_FOUR_FILE_NAME));
        JSONObject threeFourJSON = JSONObject.parseObject(fileStr);
        JSONArray threeFourData = threeFourJSON.getJSONArray(DATA);

        String cigFileStr  = apUtils.fromFileGetString(new File(CIG_FILE_NAME));
        JSONObject cigJSON = JSONObject.parseObject(cigFileStr);
        JSONArray cigJSONData = cigJSON.getJSONArray(DATA);
        HashMap<Object, Object> resultObjectHashMap = new HashMap<>();
        for (int i = 0; i< threeFourData.size(); i++) {
            JSONObject json = (JSONObject)threeFourData.get(i);
            String id = json.getString("id");
            String name = json.getString("name");
            resultObjectHashMap.put(id, "");
            for (int j = 0; j< cigJSONData.size(); j++) {
                JSONObject o = (JSONObject)cigJSONData.get(j);
                String id1 = o.getString("id");
                String name1 = o.getString("name");
                if (name!=null && name1 != null && name.equals(name1)) {
                    if (resultObjectHashMap.get(id) == "") {
                        resultObjectHashMap.put(id, id1);
                    }else {
                        Object item = resultObjectHashMap.get(id);
                        if (item instanceof ArrayList) {
                            ArrayList itemArray = (ArrayList) item;
                            itemArray.add(id1);
                            resultObjectHashMap.put(id, itemArray);
                        }else if (item instanceof String){
                            ArrayList<String> itemArray = new ArrayList<>();
                            itemArray.add((String) item);
                            itemArray.add(id1);
                            resultObjectHashMap.put(id, itemArray);
                        }
                    }
                }
            }
        }
        return resultObjectHashMap;
    }

    public HashMap getAPMap() throws FileNotFoundException {
        AppMain appMain = new AppMain();
        String fileStr = appMain.fromFileGetString(new File(THREE_FOUR_FILE_NAME));
        JSONObject threeFourJSON = JSONObject.parseObject(fileStr);
        JSONArray threeFourData = threeFourJSON.getJSONArray(DATA);

        String cigFileStr  = appMain.fromFileGetString(new File(CIG_FILE_NAME));
        JSONObject cigJSON = JSONObject.parseObject(cigFileStr);
        JSONArray cigJSONData = cigJSON.getJSONArray(DATA);
        HashMap<String, String> result = new HashMap<>();
        for (int i = 0; i< threeFourData.size(); i++) {
            JSONObject json = (JSONObject)threeFourData.get(i);
            String id = json.getString("id");
            result.put(id, "");
            String name = json.getString("name");
            for (int j = 0; j< cigJSONData.size(); j++) {
                JSONObject o = (JSONObject)cigJSONData.get(j);
                String id1 = o.getString("id");
                String name1 = o.getString("name");
                if (name!=null && name1 != null && name.equals(name1)) {
                    result.put(id, id1);
                    break;
                }
            }
        }
        return result;
    }

    public static void writeFile(String fileString, String newFileName) throws IOException {

        File file = new File(NEW_FOLDER);
        if (!file.exists()) {
            file.mkdir();
        }
        File file1 = new File(NEW_FOLDER + "/" + newFileName);
        if (!file1.exists()) {
            file1.createNewFile();
        }

        FileWriter writer;
        try {
            writer = new FileWriter(NEW_FOLDER + "/" + newFileName);
            writer.write(fileString);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fromFileGetString(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        String result = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String s;
            while((s = br.readLine()) != null) {
                stringBuilder.append(s + "\n");
            }
            br.close();
            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static StringBuilder fromFileGetStringBuild(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuilder stringBuilder = new StringBuilder();
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String s;
            while((s = br.readLine()) != null) {
                stringBuilder.append(s + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    public static String replaceEachTenantName(String str) {
        String pattern = "('[A-Za-z0-9]+-idp.)(idaas)(.tencentcs.com')";
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        int flag = 0;
        while (matcher.find(flag)) {
            flag = matcher.end();
            String cigDomain = matcher.group(1)+ "cig" +matcher.group(3);
            str = str.replaceAll(matcher.group(), cigDomain);
        }
        return str;
    }

    public static String inputString(String tips) {
        Scanner scan = new Scanner(System.in);
        System.out.print(tips + "：");
        if (scan.hasNext()) {
            return scan.next();
        }
        return "";
    }



    //
//        HashMap<String, Object> mapArray = new HashMap<>();
//        HashMap<String, Object> mapString = new HashMap<>();
//        HashMap<String, Object> mapString_no = new HashMap<>();
//        HashMap<String, Object> mapString_same = new HashMap<>();
//
//        while (iterator.hasNext()) {
//            String key = (String) iterator.next();
//            Object value = map.get(key);
//            if (value instanceof ArrayList) {
//                mapArray.put(key, value);
//            }else if (value instanceof String) {
//                if (!key.equals(value)) {
//                    mapString_no.put(key, value);
//                }else {
//                    mapString_same.put(key, value);
//                }
//                mapString.put(key, value);
//            }
//        }
//        String s2 = JSONObject.toJSONString(mapArray);
//        writeFile(s2, "file_3.4-1对cig-duo.json");
//
//        String s3 = JSONObject.toJSONString(mapString);
//        writeFile(s3, "file_3.4-1对cig-1_all.json");
//
//        String s4 = JSONObject.toJSONString(mapString_no);
//        writeFile(s4, "file_3.4-1对cig-1_no_different.json");
//
//        String s5 = JSONObject.toJSONString(mapString_same);
//        writeFile(s5, "file_3.4-1对cig-1_same.json");
}
