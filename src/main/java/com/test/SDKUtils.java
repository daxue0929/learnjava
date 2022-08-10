package com.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SDKUtils {

    public static String idpName = "";
    public static String aiId = "";



    public static void main(String[] args) throws IOException {
        String tn = inputString("请输入租户：");
        idpName = inputString("请输入idp url（https://fish3-idp.idaas.tencentcs.com）：");
        aiId = inputString("请输入sdk ai id：");

        String filePath = "/Users/daxue0929/openSourceCodes/learnjava/src/m" +
        "ain/resources/" + tn + "/" + tn + "_dump_file";

        File file = new File(filePath + "/AppInstance");

        String s = SDKUtils.fromFileGetString(file);
        String[] split = s.split("\n");
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            String line = split[i];
            String item = "";
            String reg = "\".+\"";
            Matcher matcher = Pattern.compile(reg).matcher(line);
            if (matcher.find() && line.indexOf("ap-a3636a4d1ba847fdb937f84b27cd4d76") != -1) {
                String group = matcher.group();
                String s1 = group.replaceAll("\"\"", "\""); //首先处理字符串-双引号替换为单引号
                JSONObject jsonObject = JSONObject.parseObject(s1.substring(1, s1.length() - 1));
                if (isUpdate(jsonObject)) {
                    line = line.replace(
                        "ap-a3636a4d1ba847fdb937f84b27cd4d76",
                        "ap-cf7a3fdc235043c182ca969fbd208667"
                    );
                    jsonObject.put("type", "CUSTOM");
                    JSONObject config = jsonObject.getJSONObject("config");
                    config.put("adaptorId", "ar-yufusdk");
                    config.put("user_key", "sub");
                    config.put("issuer", "test");
                    JSONObject gen = new JSONObject();
                    gen.put("generatedRedirectUrl", true);
                    config.put("statusCache", gen);
                    if (StringUtils.isEmpty(idpName) || StringUtils.isEmpty(aiId)) {
                        System.out.println("idpName or aiId is empty");
                    }
                    config.put("redirect_uri", idpName + "/cidp/custom/" + aiId);

                    config.put("public_key", config.getString("key"));
                    config.put("token_key", config.getString("tokenParamKey"));

                    String s2 = jsonObject.toJSONString();
                    String s3 = s2.replaceAll("\"", "\"\"");
                    item = line.replaceAll("\".+\"", "\"" + s3 + "\"");

                }
            }

            if (!"".equals(item)) {
                result.append(item + "\n");
            } else {
                result.append(line + "\n");
            }
        }
        writeFile(result);
    }

    public static void writeFile(StringBuffer fileString) throws IOException {

        File file = new File("/Users/daxue0929/openSourceCodes/learnjava/src/main/resources");
        if (!file.exists()) {
            file.mkdir();
        }
        File file1 = new File("/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/" + "AppInstance1");
        if (!file1.exists()) {
            file1.createNewFile();
        }

        FileWriter writer;
        try {
            writer = new FileWriter("/Users/daxue0929/openSourceCodes/learnjava/src/main/resources/" + "AppInstance1");
            writer.write(String.valueOf(fileString));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isUpdate(JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        if ("SDK".equals(type)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

    public static String fromFileGetString(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        String result = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String s;
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s + "\n");
            }
            br.close();
            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String inputString(String tips) {
        Scanner scan = new Scanner(System.in);
        System.out.print(tips + "：");
        if (scan.hasNext()) {
            return scan.next();
        }
        return "";
    }
}
