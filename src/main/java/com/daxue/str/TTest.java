//package com.daxue.str;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.nimbusds.jose.JOSEObjectType;
//import com.nimbusds.jose.JWSAlgorithm;
//import com.nimbusds.jose.JWSHeader;
//import com.nimbusds.jose.crypto.RSASSASigner;
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import jdk.nashorn.internal.parser.JSONParser;
//
//import java.security.interfaces.RSAKey;
//import java.security.interfaces.RSAPrivateKey;
//import java.util.Date;
//
//public class
//TTest {
//
//    static String kid="d9bca951-6f49-4a1f-8e4b-960ca2d3247e";
//
//    public static void main(String[] args) {
//
//        String jwkJson = "{\n" +
//            "    \"clientId\": \"c6dd3d68-8bbb-4529-9290-146e3ded0513\",\n" +
//            "    \"privateKey\": {\n" +
//            "        \"p\": \"zoRHYJiDTpAlEsSugNbTapD9lEFsjmQfB-UwyriryagbRseMTcfUaUbIbtMLoo4AY5nVa60ptzz_On8V-2p4Ue8bJxI06GaJvcNkfzP77XNhcUJtuw2ARACOxUFk4wPZoN8fzBrG8LOLAOUN_9k9OJkY1nXKYzF5HScDyttaVCU\",\n" +
//            "        \"kty\": \"RSA\",\n" +
//            "        \"q\": \"wbIfI8H0HQi7Us7w3w-7aXEYKaoxYfRITNwQmppN6hq71rAuL1wqPadY0pwTqqelzKuMkqwvXwP8cZ7Y0-Vxsj6tHc9v5EW8Yg_AMRXeQyQGIXbHLJ3k5uMJdu21zrqeW3DCvncSIfPbGgOV0rPN_bMkrGnHCwNtz-MgdnnFwF0\",\n" +
//            "        \"d\": \"NZQY3GBHccWziBo8y5uOtY8ZCk4l4AXWpDotv3Cqbv-8Zh70XzNm-QyOv5TyypUL6jJd1EUHhOpxXyq6_11fjQ-t1UDQ5ip8RcpWoQ7AUumzZd-y_OANhmvuURrNTmZooPntkspbwtJHotI5J4cKbJ8jhDiXEv-OTZ09DKrz4mRNJ6JlKvb5XF_WdpX4ktC2fqep3cbk8vlftcIxS88dyIC8WaLS0wT5Qir9htkzwmOJxm7a0UVSDi3GveWBTpvykR6suKkyCgmHQT-HLpG_F97ojGkwkOBrcVUHa5pNeDOyFZGu4rxunM_sosHbnv31znc9zbYka9HM4TNwikwrgQ\",\n" +
//            "        \"e\": \"AQAB\",\n" +
//            "        \"use\": \"sig\",\n" +
//            "        \"kid\": \"d9bca951-6f49-4a1f-8e4b-960ca2d3247e\",\n" +
//            "        \"qi\": \"wrqA404XUui4KRTeRezhW4NfppAqu-3P97g7b2ROsM5u9r4JfRP5BAoEjnv3b4ErK4ps8_GhKyaRCreakwdmTxRMIXLOonBOEiPY-YeWCCCR34OQB8_bXKyOcTt5MaQuy7LxGNwDzmHO1fBxOsjAoRMFUy5PUwA4HyitupP84ME\",\n" +
//            "        \"dp\": \"aSNVB2t95gBir3RsdvFnvuhEATdxoeh9F9LqDTsJUmgAQv-SgYLLtDEyF6HgWYi7z_lgeyo0g7JNKiXLNi1VRjlZ9IFDaFHTrEWOo2f-yTuljzjHjqRn-bbi-K-7QgwSvuD5WjsDr0Vo7jKu9uNNVMQ0rfJX8Does-XJ-3vDN_U\",\n" +
//            "        \"dq\": \"s1EzgucMF9T9lqv-UoM9_V-1_tyQ2x5L0NvqgzZW62PUgeyY1T9925YRpOjZTCPoWpJ2kVLWiCAolQd5tLG1kE519iMUc0AcJqxuQQL9wSqYgmJ0AdorD5UgLfnjVn9uGzlJEQsqvkQDkxG-olHTYgt26CcFHi0OfGbIpuYVLQ\",\n" +
//            "        \"n\": \"nEFq6FFWJYPvBhSnBu4p72Npu7rr1pNZ4h3qPK2ifbNkxYd0uUazUINS-weXXvYj5hkQBjrTXZ5fzUjAOpq8Z7Qd8txvoSqWjyiDusxXgljWh0fTH0tOLU-diiyja33BkKlQQMvx7tgcT2Z7U5LXfIJTVhQtsyg0pOebInME-3ORn9bZzvwpvod7ERNhdXknHdw9wSgKVxvPaeDIAFHwi1Jiwf6eMsGj0mHGsgBYXnhNuvR2d5tKDwnyD07y5uUEazjMByr7XwgTXu53w0Fre8SJSH3ewwKy-Xj78MmBMn9kxJTadHLs2McXBT1GwjGihrc51sXbl32Uw2yLrGVRcQ\"\n" +
//            "    }\n" +
//            "}";
//
//        generateJWT(jwkJson);
//    }
//
//
//    public static String generateJWT(String jwkJson) {
//        try {
//            long now = System.currentTimeMillis();
//            JSONObject jwkJsonObj = JSON.parseObject(jwkJson);
//            String clientId = jwkJsonObj.getString("clientId");
//
//            RSAPrivateKey privateKey = getPrivateKey(jwkJsonObj.getString("privateKey"));
//
//            JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
//                .audience("contacts")
//                .expirationTime(new Date(now + 600000))
//                .issueTime(new Date(now))
//                .issuer(clientId)
//                //添加任意数据。
//                .claim("account_type","serviceAccount");
//            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.parse("RS256"))
//                .keyID(kid)
//                .type(JOSEObjectType.JWT)
//                .build();
//            SignedJWT signedJWT = new SignedJWT(header, claimsBuilder.build());
//            signedJWT.sign(new RSASSASigner(privateKey));
//            System.err.println(signedJWT.serialize());
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 将秘钥字符串转换成秘钥对象。
//     * @param privateKey 秘钥字符串
//     * @return  秘钥对象
//     * @throws Exception
//     */
//    public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
//        Object privateKeyObj = new JSONParser(JSONParser.USE_HI_PRECISION_FLOAT |
//            JSONParser.ACCEPT_TAILLING_SPACE).parse(privateKey);
//        if (privateKeyObj instanceof com.nimbusds.jose.shaded.json.JSONObject) {
//            com.nimbusds.jose.shaded.json.JSONObject jwtObject = (com.nimbusds.jose.shaded.json.JSONObject) privateKeyObj;
//            // Find the RSA signing key
//            if (jwtObject.get("use").equals("sig") && jwtObject.get("kty").equals("RSA")) {
//                return RSAKey.parse(jwtObject).toRSAPrivateKey();
//            }
//        }
//        return null;
//    }
//}
