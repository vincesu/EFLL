package com.vincesu.test.framework.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;
import junit.framework.Assert;

import com.vincesu.framework.entity.SysRole;
import com.vincesu.framework.remote.RemoteUtil;

public class JSONUtilTest extends TestCase {

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testBean2JSON() throws IllegalArgumentException, JSONException, IllegalAccessException
	{
		SysRole role = new SysRole((long)1, "testrole");
		role.setReserved("reserved");
		JSONObject result = com.vincesu.framework.util.BeanUtil.Object2JSON(role);
		Assert.assertEquals("testrole", result.getString("rolename"));
		Assert.assertEquals("reserved", result.getString("reserved"));
		Assert.assertEquals((long)1, result.getLong("id"));
	}
	
	public void testMap2JSON() throws IllegalArgumentException, IllegalAccessException, JSONException
	{
		Map map = new HashMap();
		map.put("name", "vince");
		map.put("age", 22);
		
		JSONObject result = com.vincesu.framework.util.BeanUtil.Map2JSON(map);
		Assert.assertEquals("vince", result.getString("name"));
		Assert.assertEquals(22, result.getInt("age"));
		
		ArrayList<Map> list = new ArrayList<Map>();
		list.add(map);
		
		Map map2 = new HashMap();
		map2.put("page", 1);
		map2.put("total", 2);
		map2.put("records", 3);
		map2.put("rows",list );
		
		result = com.vincesu.framework.util.BeanUtil.Map2JSON(map2);
		System.out.println(result.toString());
		
	}
	
	public void testMap2JSON_II() throws IllegalArgumentException, JSONException, IllegalAccessException
	{
		ArrayList<Map> list = new ArrayList<Map>();
		
		Map map = new HashMap();
		map.put("name", "vince");
		map.put("age", 22);
		
		list.add(map);
		
		map = new HashMap();
		map.put("name", "eyrie");
		map.put("age", 23);
		
		list.add(map);
		
		map = new HashMap();
		map.put("name", "eros");
		map.put("age", 24);
		
		list.add(map);
		
		JSONObject result = com.vincesu.framework.util.BeanUtil.Object2JSON(list);
		
		System.out.println(result.toString());
		
	}

	public void testObject2Map()
	{
		SysRole role = new SysRole((long)1, "testrole");
		role.setReserved("reserved");
		Map result = com.vincesu.framework.util.BeanUtil.Object2Map(role);
		Assert.assertEquals("testrole", result.get("rolename"));
		Assert.assertEquals("reserved", result.get("reserved"));
		Assert.assertEquals((long)1, result.get("id"));
	}
}
