package com.marketing.activity.queue.redisPubAndSubQueue;

import com.marketing.activity.config.TopicConfig;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 消费者 服务
 * @author yyz
 */
public class RedisChannelListener implements MessageListener {

    private static Map<String, Consumer<String>> RULE = new HashMap<>();
    {
        RULE.put(TopicConfig.ASYNC_CALL_TOPIC, this::sendAsyncCall);
    }


    @Override
    public void onMessage(Message messge, byte[] patten) {
        //管道
        byte[] b_channel = messge.getChannel();
        //消息体
        byte[] b_body = messge.getBody();
        try{
            String channel = new String(b_channel);
            String body = new String(b_body);
            System.out.println("channel is:" + channel + " , body is: " + body);
            RULE.get(channel).accept(body);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendAsyncCall(String s) {
        System.out.println("sendAsyncCall exec params is :" + s);
    }
}
