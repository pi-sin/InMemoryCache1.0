package com.hike.cache.action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.hike.cache.helper.ExpiryCache;

public class ExpiryCacheAction<K,V> implements ExpiryCache<K, V>{
	private Map<K, CacheEntry> cacheMap;
	
	public ExpiryCacheAction() {
		this.cacheMap = new ConcurrentHashMap<K, CacheEntry>();
	}

	@Override
	public void put(K key, V value, long ttl, TimeUnit timeUnit) {
		if (key == null) {
			throw new IllegalArgumentException("Invalid Key");
		}
		
		long timeToLive = System.currentTimeMillis() + timeUnit.toMillis(ttl);
		
		this.cacheMap.put(key, new CacheEntry(timeToLive, value));
	}



	@Override
	public V get(K key) {
		if (key == null){
			throw new IllegalArgumentException("Invalid Key");
		}
		CacheEntry entry = this.cacheMap.get(key);
		if(entry == null)
			return null;
		long ttl = entry.getExpireBy();
		if(System.currentTimeMillis() > ttl) {
			this.cacheMap.remove(key);
			return null;
		}
		
		return entry.getValue();
	}	
	
	private class CacheEntry {
		private long ttl;
		private V value;
		
		public CacheEntry(long ttl, V entry){
			this.ttl = ttl;
			this.value = entry;
		}
		
		public long getExpireBy(){
			return this.ttl;
		}
		
		public V getValue() {
			return this.value;
		}
		
	}
}
