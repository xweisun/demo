package com.sun.handler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

/**
 * 处理运营平台的token
 */
public class OcmTokenUtil {
    /** 特殊链接的分隔符号 */
    private static final String DELIMITER = "_";
    /** 特殊链接的前缀 */
    private static final String SPECIAL_PREFIX = "specialurl";



    public static SessionData getToonType(String token,String appSecret){
        SessionData sessionData = null;
        if (StringUtils.isNotBlank(token)&&token.startsWith(SPECIAL_PREFIX)){
            sessionData = new SessionData();
            sessionData.setFullName("管理员");
            sessionData.setUserId("1001");
            sessionData.setUsername("管理员");
            String[] strs = token.split(DELIMITER);
            int strsMinLen = 3;
            if (strs!=null && strs.length >= strsMinLen){
                sessionData.setToonType(strs[1]);
                sessionData.setToonName(strs[2]);
            }
        }else{
            sessionData = getSessionData(token,appSecret);
        }
        return sessionData;
    }

    public static String getName(String token,String appSecret){
        SessionData sessionData = getSessionData(token,appSecret);
        if (sessionData != null ){
            return sessionData.getUsername();
        }
        return null;
    }

    public static SessionData getSessionData(String token, String appSecret){
        try {
            int lastIndex = token.lastIndexOf("#/");
            if (lastIndex > 0){
                token = token.substring(0,lastIndex);
            }
            //String md5 = DigestUtils.md5Hex(appSecret);
            String md5 = "123456782345673456";
            String key = md5.substring(0, 16);
            //String sessionData= CipherUtil.aesDecryptFromBase64UrlSafe(key, token);
            String sessionData = "987626787653567876567";
            if (sessionData != null && sessionData.trim().length()>0){
                SessionData resData = JSON.parseObject(sessionData,SessionData.class);
                return resData;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }


    /*  *//**//*
    public static void main(String[] args) {
        String appSecret = "dcdbe489e831e506317d7e59d1861f";
        String md5 = DigestUtils.md5Hex(appSecret);
        String key = md5.substring(0, 16);
        SessionData sessionData = new SessionData();
        sessionData.setToonType("128");
        sessionData.setToonName("e福州");
        sessionData.setUsername("管理员");
        sessionData.setFullName("管理员");
        sessionData.setUserId("1001");
        String ocmToken = CipherUtil.aesEncryptToBase64UrlSafe(key,JSON.toJSONString(sessionData));
        System.out.println(ocmToken);
        SessionData sessionData1 = OcmTokenUtil.getSessionData(ocmToken, appSecret);
        System.out.println(sessionData1);
    }*/



}
