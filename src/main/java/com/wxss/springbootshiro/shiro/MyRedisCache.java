package com.wxss.springbootshiro.shiro;

import com.wxss.springbootshiro.config.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;


public class MyRedisCache<K,V> implements Cache<K,V> {

    private String cacheKey;
    public MyRedisCache(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    @Override
    public V get(K k) throws CacheException {

        return (V) getRedisTemplate().opsForHash().get(cacheKey,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        getRedisTemplate().opsForHash().put(cacheKey,k.toString(),v.toString());
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        getRedisTemplate().opsForHash().delete(cacheKey,k.toString());

        return null;
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().opsForHash().delete(cacheKey);
    }

    @Override
    public int size() {
       return Math.toIntExact(getRedisTemplate().opsForHash().size(cacheKey));

    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = ApplicationContextUtils.getBeanOfName("redisTemplate", RedisTemplate.class);

        return redisTemplate;
    }

}
