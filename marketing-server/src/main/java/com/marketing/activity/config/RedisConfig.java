package com.marketing.activity.config;

import com.marketing.activity.queue.redisPubAndSubQueue.RedisChannelListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * redis 配置
 * @author yyz
 */
@Component
public class RedisConfig {

    @Bean
    public MessageListenerAdapter messageListenerAdapter(){
        return new MessageListenerAdapter(new RedisChannelListener());
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //添加消费监听
        container.addMessageListener(messageListenerAdapter, new PatternTopic(TopicConfig.ASYNC_CALL_TOPIC));
        return container;
    }
}
