package com.marketing.activity;

import com.marketing.activity.config.TopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
public class MessageRedisTest extends MarketingServierApplicationTest{

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void pub(){
        stringRedisTemplate.convertAndSend(TopicConfig.ASYNC_CALL_TOPIC,"redis pub/sub 发布和订阅 test");
    }
}
