package org.ftd.content.test;

import org.ftd.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJedisClient {
	
	@Test
	public void testJedisClient(){
		//初始化Spring容器
		ApplicationContext applicationContext = new 
				ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		//从容器中获得jedisclient对象
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.hset("89", "朱本宗", "1");
		System.out.println(jedisClient.hvals("89"));
	}
}
