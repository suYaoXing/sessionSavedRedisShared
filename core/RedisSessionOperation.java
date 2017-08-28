package com.suyaoxing.core;

import java.util.HashMap;
import java.util.Map;

import com.nubia.oa.util.SerializeUtil;

import redis.clients.jedis.Jedis;

public class RedisSessionOperation {
	
    private RedisConnectionFactory factory = null;
	
	private Jedis jedis = null;
		
	public RedisSessionOperation()
	{
		factory = new RedisConnectionFactory();
		jedis = factory.getRedisConnection();
	}
	
	public void saveSession(String sessionId,Map<String,Object> session)
	{
		byte[] sessionMap = SerializeUtil.serialize(session);
		jedis.set(sessionId.getBytes(), sessionMap);
		jedis.expire(sessionId.getBytes(),3600);		
	}
	
	public Map<String,Object> getSessionMap(String sessionId)
	{
		byte[] object = jedis.get(sessionId.getBytes());
		return object == null ? new HashMap<String, Object>():(Map<String,Object>) SerializeUtil.deserialize(object);
	}
	 
	public void removeSessionMap(String sessionId)
	{
		jedis.del(sessionId.getBytes());
	}
}
