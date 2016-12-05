package com.ds.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class UserHashMapTest {
	@Test
	public void testForInsertingNewValuesInUserHashMap() throws Exception {
		UserHashMap hashMap=new UserHashMap();
		hashMap.put(1, "one");
		hashMap.put(2, "two");
		hashMap.put(1, "DuplicateOne");
		Map<Integer, String> map=new HashMap<Integer, String>();
		map.put(1, "one");
		map.put(2, "two");
		map.put(1, "DuplicateOne");
		System.out.println("Size is java map "+map.size());
		System.out.println("Size is user map "+hashMap.size());
		Assert.assertEquals(map.size(),hashMap.size());
		//System.out.println(UserHashMap.si);
		
	}
	@Test
	public void testForGettingValuesFromUserHashMap() throws Exception {
		UserHashMap hashMap=new UserHashMap();
		hashMap.put(1, "one");
		hashMap.put(2, "two");
		hashMap.put(1, "DuplicateOne");
		Map<Integer, String> map=new HashMap<Integer, String>();
		map.put(1, "one");
		map.put(2, "two");
		map.put(1, "DuplicateOne");
		System.out.println("Size is java map "+map.size());
		System.out.println("Size is user map "+hashMap.size());
		System.out.println(hashMap.get(1));
		Assert.assertEquals(map.get(1),hashMap.get(1));
		//System.out.println(UserHashMap.si);
		
	}
}
