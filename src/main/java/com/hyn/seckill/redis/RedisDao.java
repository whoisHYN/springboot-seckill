package com.hyn.seckill.redis;

import com.hyn.seckill.entity.Seckill;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: HYN
 * @Description: Redis缓存操作
 * @Modified By:
 */
@Repository
public class RedisDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    private final JedisPool jedisPool;

    @Autowired
    public RedisDao(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Seckill getSeckill(long seckillId) {
        //redis操作逻辑
        try {
            try (Jedis jedis = jedisPool.getResource()) {
                String key = "seckill:" + seckillId;
                //redis内部并没有实现序列化的功能
                // get --> byte[]-->反序列化-->Object
                //使用protostuff进行序列化
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    //创建空对象，等待protostuff赋值
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    //seckill被赋值
                    return seckill;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        //Object(Seckill) -->序列化 --> byte[]
        try {
            try (Jedis jedis = jedisPool.getResource()) {
                String key = "seckill:" + seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //设置过期时间
                int timeout = 60 * 60;
                return jedis.setex(key.getBytes(), timeout, bytes);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
