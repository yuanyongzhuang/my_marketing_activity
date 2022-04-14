package com.marketing.activity;

import cn.hutool.core.map.MapUtil;
import com.marketing.activity.constant.RequestConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/13
 */
public class BaseContextHandler {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_CONTEXT = ThreadLocal.withInitial(HashMap::new);

    public static Map<String, Object> getContextMap() {
        return BaseContextHandler.THREAD_LOCAL_CONTEXT.get();
    }
    public static void remove() {
        THREAD_LOCAL_CONTEXT.remove();
    }
    public static Object remove(String key) {
        return getContextMap().remove(key);
    }
    public static void clear() {
        getContextMap().clear();
    }

    // 日志
    public static void setTraceId(String scene) {
        getContextMap().put(RequestConstants.KEY_TRACE_ID, scene);
    }
    public static String getTraceId() {
        return MapUtil.getStr(getContextMap(), RequestConstants.KEY_TRACE_ID);
    }

    // 访问时间
    public static void setAccessTime(Date accessTime) {
        getContextMap().put(RequestConstants.KEY_ACCESS_TIME, accessTime);
    }
    public static Date getAccessTime() {
        Date accessTime = MapUtil.getDate(getContextMap(), RequestConstants.KEY_ACCESS_TIME);
        return accessTime == null ? new Date() : accessTime;
    }

}
