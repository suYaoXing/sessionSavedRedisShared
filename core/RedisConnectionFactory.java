package com.suyaoxing.core;

import redis.clients.jedis.Jedis;

public class RedisConnectionFactory {
	
	private final static String  DEFALT_ADDRESS = "127.0.0.1";
	
	private final static int DEFALT_PORT = 6379;
	
	private Jedis jedis;
	
	private String address;
	
	private int port;
	
	public RedisConnectionFactory(String address,int port)
	{
		this.address = address;
		this.port = port;
	}
	
	public RedisConnectionFactory(String address)
	{
		this(address,DEFALT_PORT);
	}
	
	public RedisConnectionFactory(int port)
	{
		this(DEFALT_ADDRESS,port);
	}
	
	public RedisConnectionFactory()
	{
		this(DEFALT_ADDRESS,DEFALT_PORT);
	}
	
	public Jedis getRedisConnection()
	{
		if(jedis == null)
			jedis = new Jedis(address,port);
		return jedis;
	}

}
