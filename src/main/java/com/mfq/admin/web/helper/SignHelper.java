package com.mfq.admin.web.helper;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.mfq.admin.web.constants.AdminConstants;
import com.mfq.admin.web.utils.Md5;

public class SignHelper {

    private static final Logger logger = LoggerFactory
            .getLogger(SignHelper.class);

    // 规则：map内的key自然排序，之后进行MD5，末尾加入appKeyValue
    public static boolean validateSign(Map<String, Object> map) {
        String sign = makeSign(map);
        String outSign = fetchSign(map);
        if(StringUtils.equalsIgnoreCase(sign, outSign)){
            logger.info("sign ok!");
            return true;
        }else{
            logger.warn("sign bad! local:{}, from req:{}", sign, outSign);
            return false;
        }
    }
    
    public static String makeSign(Map<String, Object> map){
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("Map is null or size is little than 1");
            return null;
        }
        SortedMap<String, Object> sort = new TreeMap<String, Object>(map);
        StringBuffer signBuffer = new StringBuffer("");
        for (String key : sort.keySet()) {
            if (StringUtils.equalsIgnoreCase(key, "sign")) {
                continue;
            }
            signBuffer.append(key).append("=").append(sort.get(key));
        }
        String sign = Md5.crypt(
                Md5.crypt(signBuffer.toString()) + AdminConstants.SEC_KEY);
        logger.info("Make sign:{}, org string is:{}", sign, signBuffer.toString());
        return sign;
    }
    
    // 规则：map内的key自然排序，之后进行MD5，末尾加入appKeyValue
    public static boolean validateWxSign(Map<String, Object> map, String secKey) {
        String sign = makeWxSign(map, secKey);
        String outSign = fetchSign(map);
        if(StringUtils.equalsIgnoreCase(sign, outSign)){
            logger.info("sign ok!");
            return true;
        }else{
            logger.warn("sign bad! local:{}, from req:{}", sign, outSign);
            return false;
        }
    }
    
    public static String makeWxSign(Map<String, Object> map, String secKey){
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("Map is null or size is little than 1");
            return null;
        }
        SortedMap<String, Object> sort = new TreeMap<String, Object>(map);
        StringBuffer signBuffer = new StringBuffer("");
        for (String key : sort.keySet()) {
            if (StringUtils.equalsIgnoreCase(key, "sign")) {
                continue;
            }
            signBuffer.append(key).append("=").append(sort.get(key)).append("&");
        }
        signBuffer.append("key=").append(secKey);
        String sign = Md5.crypt(signBuffer.toString()).toUpperCase();
        logger.info("Make sign:{}, org string is:{}", sign, signBuffer.toString());
        return sign;
    }
    
    public static String fetchSign(Map<String, Object> map){
        return (String) map.get("sign");
    }
}