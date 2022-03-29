package com.marketing.activity.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * redis lock.
 *
 * @author L
 */
public class RedisLock implements Lock {

    private final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private final ThreadLocal<String> local = new ThreadLocal<>();

    private static final String UNLOCK_LUA = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    private final StringRedisTemplate stringRedisTemplate;

    private final TimeUnit timeUnit;

    private final String lockKey;

    private final long time;

    public RedisLock(StringRedisTemplate stringRedisTemplate, String lockKey) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockKey = lockKey;
        this.timeUnit = TimeUnit.SECONDS;
        this.time = 60;
    }

    public RedisLock(StringRedisTemplate stringRedisTemplate, String lockKey, TimeUnit timeUnit, long time) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockKey = lockKey;
        this.timeUnit = timeUnit;
        this.time = time;
    }

    @Override
    public void lock() {
        if (!this.tryLock()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 递归重试 - 自旋
            lock();
        }
    }

    @Override
    public boolean tryLock() {
        String uuid = UUID.randomUUID().toString();

        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(this.lockKey, uuid, this.time, this.timeUnit);

        if (Boolean.TRUE.equals(aBoolean)) {
            local.set(uuid);
            return true;
        }

        return false;
    }

    @Override
    public void unlock() {
        String uuid = local.get();
        if (StringUtils.isEmpty(uuid)) {
            logger.info("RedisLock unlock uuid is null");
            return ;
        }
        stringRedisTemplate.execute((RedisConnection connection) ->
                connection.eval(UNLOCK_LUA.getBytes(), ReturnType.INTEGER, 1, lockKey.getBytes(), uuid.getBytes()));

        local.remove();
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
