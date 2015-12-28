package com.mfq.admin.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.StdSerializerProvider;
import org.codehaus.jackson.map.ser.std.NullSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.mfq.admin.web.constants.AdminConstants;

public class JSONUtil {


    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

    private static final ObjectMapper objectMapper;

    private static final ObjectMapper objectMapperWithNull;
    
    static {
        StdSerializerProvider sp = new StdSerializerProvider();
        sp.setNullValueSerializer(NullSerializer.instance);
        objectMapper = new ObjectMapper(null, sp, null);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        StdSerializerProvider spRaw = new StdSerializerProvider();
        objectMapperWithNull = new ObjectMapper(null, spRaw, null);
        objectMapperWithNull.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapperWithNull.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }
    
    public static String writeToJson(Object object) {
        return toJson(object, false, false);
    }
    
    public static String toJson(Object obj, boolean withIndent, boolean withNull) {
        ObjectMapper mo = withNull ? objectMapperWithNull : objectMapper;
        try{
            if (withIndent) {
                return mo.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } else {
                return mo.writeValueAsString(obj);
            }
        }catch(Exception e){
            logger.error("ToJsonError", e);
            return null;
        }
    }
    
    public static String toJson(int code, String msg, Object data) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("code", code);
        result.put("msg", msg);
        if (data != null) {
            result.put("data", data);
        }
        return writeToJson(result);
    }
    
    public static String successResultJson(Object data) {
        return toJson(AdminConstants.SUCCESS, "success", data);
    }
    
    public static String successResultJson() {
        return toJson(AdminConstants.SUCCESS, "success", null);
    }
    
    /**
     * 将json字符串转换为map
     * 
     * @param jsonStr
     *            要转换的字符串
     * @return 转换成的map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonStr(String jsonStr) {
        try {
            if(StringUtils.isBlank(jsonStr)){
                return null;
            }
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Can not getMapFromJsonStr which jsonStr:" + jsonStr, e);
        }
    }
    
    /**
     * json格式字符串转换为java对象
     * 
     * @param content
     * @param valueType
     * @return
     * @throws IOException
     */
    public static <T> T toBean(String content, Class<T> valueType) throws IOException {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonParseException jpe) {
            logger.error(content);
            throw jpe;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String content) throws IOException {
        if (StringUtils.isBlank(content)) {
            return new HashMap<String, Object>();// 不要使用EMPTY_MAP
        }
        return objectMapper.readValue(content, Map.class);
    }
    
    public static Map<String, Object> readMapFromReq(HttpServletRequest request){
        String str = readRequestString(request);
        return getMapFromJsonStr(str);
    }
    
    public static String readRequestString(HttpServletRequest request) {
        StringBuffer builder = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            logger.error("从request中解析requestbody失败", e);
        }
        logger.info("request body:{}", builder.toString());
        return builder.toString();
    }
    
    public static void main(String[] args) {
		String json = "[{\"a\":123},{\"b\":321}]";
		try {
			List<Map<String,Object>> list = JSONUtil.toBean(json, List.class);
			System.out.println(list.get(0).get("a"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
