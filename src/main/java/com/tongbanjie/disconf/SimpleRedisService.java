package com.tongbanjie.disconf;


import com.tongbanjie.disconf.utils.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;


/**
 * 一个简单的Redis服务
 *
 * @author liaoqiqi
 * @version 2014-6-17
 */
@Service
@Scope("singleton")
public class SimpleRedisService implements InitializingBean, DisposableBean {

    protected static final Logger LOGGER = LoggerFactory
            .getLogger(SimpleRedisService.class);

    // jedis 实例
    private Jedis jedis = null;

    /**
     * 分布式配置
     */
    @Autowired
    private JedisConfig jedisConfig;

    /**
     * 关闭
     */
    public void destroy() throws Exception {

        if (jedis != null) {
            jedis.disconnect();
        }
    }

    /**
     * 进行连接
     */
    public void afterPropertiesSet() throws Exception {

        jedis = JedisUtil.createJedis(jedisConfig.getHost(),
                jedisConfig.getPort());
    }

    /**
     * 获取一个值
     *
     * @param key
     * @return
     */
    public String getKey(String key) {
        if (jedis != null) {
            return jedis.get(key);
        }

        return null;
    }
}