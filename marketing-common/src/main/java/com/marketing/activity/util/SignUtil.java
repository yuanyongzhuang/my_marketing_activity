package com.marketing.activity.util;

import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>
 *  签名工具
 * </p>
 *
 * @author yyz
 * @since 2022/4/18
 */
public class SignUtil {

    public static LinkedHashMap<String,String> generateSignParam(String data,String key){
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("data", data);
        SortedMap<String, String> paramMapTwo = new TreeMap<>();
        String encodeSignTwo = CrmSignUtil.encodeSign(paramMapTwo, key);
        params.put("key",key);
        params.put("sign",encodeSignTwo);
        return params;
    }
}
