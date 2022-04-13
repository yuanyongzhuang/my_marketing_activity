package com.marketing.activity.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.marketing.activity.BaseContextHandler;
import com.marketing.activity.annotation.NoLog;
import com.marketing.activity.constant.RequestConstants;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * aop日志处理
 * @author yyz
 */
@Aspect
@Component
public class GlobalLogAop {

    private final static Logger logger = LoggerFactory.getLogger(GlobalLogAop.class);

    /**
     * AOP 切入点 -- 注解类
     */
    @Pointcut(value = "execution(* com.marketing..*Controller.*(..))")
    public void aroundLogPoint(){}

    /**
     *  日志处理
     * @param joinPoint cutPoint
     * @return Object
     * @throws Throwable ""
     */
    @Around("aroundLogPoint()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable{
        //开始时间
//        long startTime = System.currentTimeMillis();
//        StopWatch watch = new StopWatch();
//        watch.start();
        BaseContextHandler.setAccessTime(new Date());

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        //不输出日志
        NoLog noLog = method.getDeclaredAnnotation(NoLog.class);
        if(noLog != null){
            return joinPoint.proceed();
        }
        Object proceed = null;
        //接受到请求，记录请求内容
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes != null){
            HttpServletRequest request = requestAttributes.getRequest();
            //设置日志的tracedId 方便追踪日志
            String tracedId = request.getHeader(RequestConstants.KEY_TRACE_ID);
            MDC.put(RequestConstants.KEY_TRACE_ID, StringUtils.isBlank(tracedId)? UUID.randomUUID().toString():tracedId);

            String postBody = "";
            StringBuilder sb = new StringBuilder(String.format("start -- [%s]", request.getRequestURI()));
            sb.append(", remoteAddress:").append(request.getRemoteAddr());
            //equalsIgnoreCase判断会忽略大小写（string特有的）
            if(HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())
                || HttpMethod.PUT.name().equalsIgnoreCase(request.getMethod())
                || HttpMethod.PATCH.name().equalsIgnoreCase(request.getMethod())){
                //post相关请求处理
                postBody = this.getPostBody(joinPoint.getArgs());
                sb.append(" postBody:").append(postBody);
            }else{
                sb.append(" parameterMap:").append(JSONObject.toJSONString(request.getParameterMap()));
            }
            sb.append(" header:").append(this.getHeader(request));
//            logger.info("start -- [{}], remoteAddress:{} parameterMap:{} postBody:{}, header:{}",
//                    request.getRequestURI(), request.getRemoteAddr(), JSONObject.toJSONString(request.getParameterMap()), postBody, this.getHeader(request));
            logger.info(sb.toString());

            proceed = joinPoint.proceed();

            //输出执行耗时。
//            long timeCost = System.currentTimeMillis() - startTime;
//            watch.stop();
//            long timeCost = watch.getTotalTimeMillis();
            long timeCost = System.currentTimeMillis() - BaseContextHandler.getAccessTime().getTime();
            logger.info("End -- [{}] speed:{}ms, return:{}",request.getRequestURI(),timeCost, JSON.toJSONString(proceed));
            if(timeCost > RequestConstants.TIME_COST){
                logger.warn("CostTimeExtend  timeCost : {}ms, URI : {} , postBody:{} ", timeCost, request.getRequestURI(),postBody);
            }
        }

        return proceed;
    }

    private String getPostBody(Object[] args) {
        if(args == null || args.length == 0){
            return "";
        }
        Object[] arguments = new Object[args.length];
        for(int i = 0; i < args.length; i++){
            if(args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile){
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        return JSON.toJSONString(arguments);
    }

    private String getHeader(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(RequestConstants.KEY_TOKEN, request.getHeader(RequestConstants.KEY_TOKEN));
        jsonObject.put(RequestConstants.KEY_TRACE_ID, request.getHeader(RequestConstants.KEY_TRACE_ID));

        return jsonObject.toJSONString();
    }
}
