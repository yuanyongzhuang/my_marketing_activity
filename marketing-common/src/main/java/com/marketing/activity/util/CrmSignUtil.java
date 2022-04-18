package com.marketing.activity.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * <p>
 * 调用中业crm后台系统shiroConfig中开放的接口，需要签名校验的封装
 * </p>
 *
 * @author yyz
 * @since 2022/4/18
 */
public class CrmSignUtil {

    /**
     * sign 签名（参数名按ASCII码从小到大排序（字典序）+key+MD5+转大写签名）
     * @param map
     * @param key
     * @return
     */
    public static String encodeSign(SortedMap<String, String> map, String key){
        if(StringUtils.isEmpty(key)){
            throw new RuntimeException("签名key不能为空");
        }
        Set<Map.Entry<String,String>> entries = map.entrySet();
        Iterator<Map.Entry<String,String>> iterator = entries.iterator();
        List<String> values = Lists.newArrayList();
        while(iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            String k = entry.getKey();
            String v = entry.getValue();
            if(StringUtils.isNotEmpty(v) && !"sign".equals(k) && !"key".equals(k)){
                values.add(k + ":" + v);
            }
        }
        values.add("key=" + key);
        String sign = StringUtils.join(values,"&");
        return toMD5COde(sign.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

    /**
     * MD5加密
     * @param bytes 值
     * @return string
     */
    private static String toMD5COde(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(bytes);
            byte[] after = md5.digest();
            for(int i = 0; i < after.length; i++){
                String hex = Integer.toHexString(0xff & after[i]);
                if(hex.length() == 1){
                    hex = "0" + hex;
                }
                sb.append(hex);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
