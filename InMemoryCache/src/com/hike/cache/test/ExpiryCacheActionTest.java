package com.hike.cache.test;

import java.util.concurrent.TimeUnit;

import com.hike.cache.action.ExpiryCacheAction;

public class ExpiryCacheActionTest {
	public static void main(String[] args) throws InterruptedException{
		ExpiryCacheAction<String, String> cache = new ExpiryCacheAction<>();
		cache.put("piyush", "sinha", 10, TimeUnit.SECONDS);
		cache.put("bhavya", "swaroop", 20, TimeUnit.SECONDS);
		
		System.out.println(cache.get("piyush"));
		System.out.println(cache.get("bhavya"));
		
		Thread.sleep(TimeUnit.SECONDS.toMillis(10));
		
		System.out.println(cache.get("piyush"));
		System.out.println(cache.get("bhavya"));
		
		Thread.sleep(TimeUnit.SECONDS.toMillis(10));
		
		System.out.println(cache.get("piyush"));
		System.out.println(cache.get("bhavya"));
		
		
	}
}
