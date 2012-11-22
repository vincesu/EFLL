package com.vincesu.framework.remote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RemoteUtil {

	public static int getIndex(Map.Entry entry)
	{
		String key = entry.getKey().toString().trim();
		return RemoteUtil.getIndex(key);
	}
	
	public static int getIndex(String key)
	{
		int result = -1;
		try
		{
			result = Integer.parseInt(
					key.substring(key.indexOf("[")+1, key.indexOf("]")));
		} catch(Exception e)
		{
			result = -1;
		}
		return result;
	}

	public static String getProperty(String key) 
	{
		String result = null;
		try
		{
			result = key.substring(key.lastIndexOf("[")+1, key.lastIndexOf("]"));
		} catch(Exception e)
		{
			result = "";
		}
		return result;
	}
	
	public static String getProperty(Map.Entry entry) 
	{
		String key = entry.getKey().toString().trim();
		return RemoteUtil.getProperty(key);
	}
	
	/*
	 * 格式化从web端传来的参数
	 */
	public static List setData(Map map)
	{
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		int index = -1;
		String propertyName = null;
		Map.Entry entry = null;
		
		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext())
		{
			entry = (Map.Entry) iter.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			if(entry.getKey().toString().trim().startsWith("data"))
			{
				index = RemoteUtil.getIndex(entry);
				if(index < 0)
					continue;
				propertyName = RemoteUtil.getProperty(entry);
				while(result.size()<=index)
				{
					result.add(new HashMap<Object,Object>());
				}
				result.get(index).put(propertyName, ((Object [])entry.getValue())[0]);
				
			} else
			{
				propertyName = RemoteUtil.getProperty(entry);
				if(result.size()==0)
				{
					result.add(new HashMap<Object,Object>());
				}
				result.get(0).put(entry.getKey().toString().trim(), ((Object [])entry.getValue())[0]);
			}
		}
		
		return result;
	}
	
}
