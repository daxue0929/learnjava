package com.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用帮助：
 * 1：把sql文件夹整体复制到resources目录下
 * 2: 修改FOLDER_NAME为文件夹的名字
 *
 */

public class AppMain {

    public static final String BASE_PATH = "/Users/daxue0929/openSourceCodes/learnjava/src/main/resources";

    public static final String THREE_FOUR_FILE_NAME = BASE_PATH + "/" + "3.4_appList.json";
    public static final String CIG_FILE_NAME = BASE_PATH + "/" + "cig_appList.json";
    public static final String NEW_FOLDER = BASE_PATH + "/" + "result";

    public static final String FOLDER_NAME = "tn-10541f5dbcfb4f85a58a4e2d2e0f4b31_dump_file";
    public static final String PATH = BASE_PATH + "/" + FOLDER_NAME;

    public static final String DATA = "data";


    @Test
    public void start() throws IOException {
        AppMain appMain = new AppMain();

        List<File> files = readFolder(PATH);
        Iterator<File> fileIterator = files.iterator();

        HashMap map = appMain.getAPMap();
        Set set = map.keySet();
        Iterator iterator = set.iterator();

        while (fileIterator.hasNext()) {
            File next = fileIterator.next();
            String s = fromFileGetString(next);
            if (s.indexOf("ap-") != -1 ){
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    String value = (String) map.get(key);
                    if (StringUtils.isNotEmpty(value) && !key.equals(value)) {
                        s = s.replaceAll(key, value);
                    }
                }
            }
            if (next.getName().equals("Tenant"))
                s = replaceEachTenantName(s);
            writeFile(s, next.getName());
        }

    }

    List<File> readFolder(String path) {
        File file = new File(path);
        List<File> list = null;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
//            ArrayList<File> fileArrayList = new ArrayList<>(Arrays.asList(files));
            list = new ArrayList<File>(files.length);
            Collections.addAll(list, files);
        }
        return list;
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


    public void writeFile(String fileString, String newFileName) throws IOException {

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

    public String fromFileGetString(File file) throws FileNotFoundException {
//        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
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

    public String replaceTanentName(String str) {
        String pattern = "('[A-Za-z0-9]+)-idp.idaas.tencentcs.com'";
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        matcher.find();
        String aa = matcher.group();
        String[] split = aa.split("\\.");
        String bb = split[0] + ".cig.tencentcs.com'";
        return str.replaceAll(aa, bb);
    }

    public String replaceEachTenantName(String str) {
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


//        public void writeFile02() throws IOException {
//        String[] arrs={
//            "zhangsan,23,福建",
//            "lisi,30,上海",
//            "wangwu,43,北京",
//            "laolin,21,重庆",
//            "ximenqing,67,贵州"
//        };
//        //写入中文字符时解决中文乱码问题
//        FileOutputStream fos=new FileOutputStream(new File(NEW_SQL_FILE_LOCATION));
//        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
//        BufferedWriter  bw=new BufferedWriter(osw);
//        //简写如下：
//        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//        //        new FileOutputStream(new File(NEW_SQL_FILE_LOCATION)), "UTF-8"));
//
//        for(String arr:arrs){
//            bw.write(arr+"\t\n");
//        }
//        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
//        bw.close();
//        osw.close();
//        fos.close();
//    }


    @Test
    public void test003() throws FileNotFoundException {
        HashMap apMap = getAPMap();
    }

    @Test
    public void test000() {

        String str = "INSERT INTO public.\"Tenant\" (id, \"createdOn\", \"modifiedOn\", \"displayName\", domain, name, \"logoUrl\", \"primaryIdpId\", configuration, \"customizedIdpDomain\", \"customizedPortalDomain\", \"customizedAdminDomain\", \"ICP\") VALUES ('tn-10541f5dbcfb4f85a58a4e2d2e0f4b31', '2020-11-10 06:04:52.595', '2020-11-10 06:15:11.188', 'fish', 'fish-idp.idaas.tencentcs.com', 'fish', 'yufu', '_', NULL, 'https://fish-idp.idaas.tencentcs.com', 'https://fish.idaas.tencentcs.com', 'https://fish-admin.idaas.tencentcs.com', NULL);";
        String pattern = "('[A-Za-z0-9]+)-idp.idaas.tencentcs.com'";

        Matcher matcher = Pattern.compile(pattern).matcher(str);


        String s = str.replaceAll(pattern, "test");
        System.out.println(s);

    }

    @Test
    public void test001() {
        String str = "INSERT INTO public.\"Tenant\" (id, \"createdOn\", \"modifiedOn\", \"displayName\", domain, name, \"logoUrl\", \"primaryIdpId\", configuration, \"customizedIdpDomain\", \"customizedPortalDomain\", \"customizedAdminDomain\", \"ICP\") VALUES ('tn-10541f5dbcfb4f85a58a4e2d2e0f4b31', '2020-11-10 06:04:52.595', '2020-11-10 06:15:11.188', 'fish', 'fish-idp.idaas.tencentcs.com', 'fish', 'yufu', '_', NULL, 'https://fish-idp.idaas.tencentcs.com', 'https://fish.idaas.tencentcs.com', 'https://fish-admin.idaas.tencentcs.com', NULL);\n" +
            "INSERT INTO public.\"Tenant\" (id, \"createdOn\", \"modifiedOn\", \"displayName\", domain, name, \"logoUrl\", \"primaryIdpId\", configuration, \"customizedIdpDomain\", \"customizedPortalDomain\", \"customizedAdminDomain\", \"ICP\") VALUES ('tn-10541f5dbcfb4f85a58a4e2d2e0f4b31', '2020-11-10 06:04:52.595', '2020-11-10 06:15:11.188', 'fish', 'daxue-idp.idaas.tencentcs.com', 'fish', 'yufu', '_', NULL, 'https://fish-idp.idaas.tencentcs.com', 'https://fish.idaas.tencentcs.com', 'https://fish-admin.idaas.tencentcs.com', NULL);";

        String pattern = "('[A-Za-z0-9]+-idp.)(idaas)(.tencentcs.com')";

        Pattern compile = Pattern.compile(pattern);
        boolean matches = Pattern.matches(pattern, str);

        Matcher matcher = Pattern.compile(pattern).matcher(str);
        boolean b = matcher.find();



        int start = matcher.start();
        int end = matcher.end();
        String cigDomain = matcher.group(1)+ "cig" +matcher.group(3);

//        boolean matches1 = matcher.matches(); //全字符串匹配
//
//        boolean b1 = matcher.lookingAt(); //从字符串最开头匹配满足的子串
//
//        String[] split = aa.split("\\.");
//        String bb = split[0] + ".cig.tencentcs.com'";

        String s1 = matcher.replaceAll(cigDomain);
//        String s1 = str.replaceAll(matcher.group(), cigDomain);

        System.out.println(s1);
    }

    @Test
    public void test002() {
        String str = "INSERT INTO public.\"Tenant\" (id, \"createdOn\", \"modifiedOn\", \"displayName\", domain, name, \"logoUrl\", \"primaryIdpId\", configuration, \"customizedIdpDomain\", \"customizedPortalDomain\", \"customizedAdminDomain\", \"ICP\") VALUES ('tn-10541f5dbcfb4f85a58a4e2d2e0f4b31', '2020-11-10 06:04:52.595', '2020-11-10 06:15:11.188', 'fish', 'fish-idp.idaas.tencentcs.com', 'fish', 'yufu', '_', NULL, 'https://fish-idp.idaas.tencentcs.com', 'https://fish.idaas.tencentcs.com', 'https://fish-admin.idaas.tencentcs.com', NULL);\n" +
            "INSERT INTO public.\"Tenant\" (id, \"createdOn\", \"modifiedOn\", \"displayName\", domain, name, \"logoUrl\", \"primaryIdpId\", configuration, \"customizedIdpDomain\", \"customizedPortalDomain\", \"customizedAdminDomain\", \"ICP\") VALUES ('tn-10541f5dbcfb4f85a58a4e2d2e0f4b31', '2020-11-10 06:04:52.595', '2020-11-10 06:15:11.188', 'fish', 'daxue-idp.idaas.tencentcs.com', 'fish', 'yufu', '_', NULL, 'https://fish-idp.idaas.tencentcs.com', 'https://fish.idaas.tencentcs.com', 'https://fish-admin.idaas.tencentcs.com', NULL);";

        // 预期的结果
        String result = "INSERT INTO public.\"Tenant\" (id, \"createdOn\", \"modifiedOn\", \"displayName\", domain, name, \"logoUrl\", \"primaryIdpId\", configuration, \"customizedIdpDomain\", \"customizedPortalDomain\", \"customizedAdminDomain\", \"ICP\") VALUES ('tn-10541f5dbcfb4f85a58a4e2d2e0f4b31', '2020-11-10 06:04:52.595', '2020-11-10 06:15:11.188', 'fish', 'fish-idp.cig.tencentcs.com', 'fish', 'yufu', '_', NULL, 'https://fish-idp.idaas.tencentcs.com', 'https://fish.idaas.tencentcs.com', 'https://fish-admin.idaas.tencentcs.com', NULL);\n" +
            "INSERT INTO public.\"Tenant\" (id, \"createdOn\", \"modifiedOn\", \"displayName\", domain, name, \"logoUrl\", \"primaryIdpId\", configuration, \"customizedIdpDomain\", \"customizedPortalDomain\", \"customizedAdminDomain\", \"ICP\") VALUES ('tn-10541f5dbcfb4f85a58a4e2d2e0f4b31', '2020-11-10 06:04:52.595', '2020-11-10 06:15:11.188', 'fish', 'daxue-idp.cig.tencentcs.com', 'fish', 'yufu', '_', NULL, 'https://fish-idp.idaas.tencentcs.com', 'https://fish.idaas.tencentcs.com', 'https://fish-admin.idaas.tencentcs.com', NULL);";

        String s = replaceEachTenantName(str);
        Assert.assertEquals(result, s);

    }


}
