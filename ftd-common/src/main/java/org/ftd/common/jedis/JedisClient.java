package org.ftd.common.jedis;

import java.util.List;

public interface JedisClient {
	//String
	String set(String key, String value);
	String get(String key);
	Long del(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	
	//Hash
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
	Boolean hexists(String key, String field);
	
	//返回的是hash(key,field,value)中的value的集合
	List<String> hvals(String key);
}
