package com.github.ican2056.doit;

import java.util.HashMap;
import java.util.Map;

public class ParamsU {

    public static String DEFAULT_SEPARATOR = "&";

    public static Map<String, String> paramsStr2Map(String params){
        return paramsStr2Map(params, DEFAULT_SEPARATOR);
    }

    /**
     * 将用 分隔符 连起来的key=value字符串转成map
     */
    public static Map<String, String> paramsStr2Map(String params, String separator){
        Map<String, String> map = new HashMap<>();
        String[] kvsArr = params.split(RegU.escapeStr(separator));
        for (String kv : kvsArr) {
            String[] kvArr = kv.split("=");
            if(kvArr.length >= 2){
                map.put(kvArr[0], kvArr[1]);
            }
        }
        return map;
    }

    public static String map2ParamsStr(Map<String, String> map){
        return map2ParamsStr(map, DEFAULT_SEPARATOR);
    }

    public static String map2ParamsStr(Map<String, String> map, String separator){
        if(map.isEmpty()){
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> encrypt : map.entrySet()) {
            sb.append(encrypt.getKey());
            sb.append("=");
            sb.append(encrypt.getValue());
            sb.append(separator);
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }


}
