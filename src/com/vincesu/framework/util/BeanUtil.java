package com.vincesu.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vincesu.test.bean.testBean;

public class BeanUtil 
{
	public static JSONObject Object2JSON(Object obj) throws JSONException, IllegalArgumentException, IllegalAccessException
	{
		JSONObject result = new JSONObject(obj);
		return result;
	}
	
	public static JSONObject Map2JSON(Map<Object,Object> map) throws JSONException, IllegalArgumentException, IllegalAccessException
	{
		JSONObject result = new JSONObject(map);
		return result;
	}
	
	public static Map Object2Map(Object obj)
	{
		Class localClass = obj.getClass();
		Method[] arrayOfMethod = localClass.getMethods();
		Map result = new HashMap<Object, Object>();
		
		for (int i = 0; i < arrayOfMethod.length; i++) 
		{
			try {
				Method localMethod = arrayOfMethod[i];
				String str1 = localMethod.getName();
				String str2 = "";
				if (str1.startsWith("get"))
					str2 = str1.substring(3);
				else if (str1.startsWith("is"))
					str2 = str1.substring(2);
				if ((str2.length() > 0)
						&& (Character.isUpperCase(str2.charAt(0)))
						&& (localMethod.getParameterTypes().length == 0)) {
					if (str2.length() == 1)
						str2 = str2.toLowerCase();
					else if (!Character.isUpperCase(str2.charAt(1)))
						str2 = str2.substring(0, 1).toLowerCase()
								+ str2.substring(1);
					result.put(str2, localMethod.invoke(obj, (Object[]) null));
				}
			} catch (Exception localException) {
			}
		}
		
		return result;
	}
	
	public static JSONArray Object2JSONArray(Object obj) throws JSONException
	{
		Class localClass = obj.getClass();
		Method[] arrayOfMethod = localClass.getMethods();
		JSONArray result = new JSONArray();
		
		for (int i = 0; i < arrayOfMethod.length; i++) 
		{
			try {
				Method localMethod = arrayOfMethod[i];
				String str1 = localMethod.getName();
				String str2 = "";
				if (str1.startsWith("get"))
					str2 = str1.substring(3);
				else if (str1.startsWith("is"))
					str2 = str1.substring(2);
				if ((str2.length() > 0)
						&& (Character.isUpperCase(str2.charAt(0)))
						&& (localMethod.getParameterTypes().length == 0)) {
					if (str2.length() == 1)
						str2 = str2.toLowerCase();
					else if (!Character.isUpperCase(str2.charAt(1)))
						str2 = str2.substring(0, 1).toLowerCase()
								+ str2.substring(1);
//					result.put(str2, localMethod.invoke(obj, (Object[]) null));
					result.put(i, localMethod.invoke(obj, (Object[]) null));
				}
			} catch (Exception localException) {
			}
		}
		
		return result;
	}
	
	public static Map Array2Map(String [] fields,Object [] array) throws Exception
	{
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		if(fields.length!=array.length)
			throw new Exception("数组参数长度有误");
		
		for(int i=0;i<fields.length;i++)
		{
			result.put(fields[i], array[i]);
		}
		
		return result;
	}
	
//	public static void copyProperty(Map source,Object target)
//	{
//		Class localClass = target.getClass();
//		Method[] arrayOfMethod = localClass.getMethods();
//		
//		for (int i = 0; i < arrayOfMethod.length; i++) 
//		{
//			try {
//				Method localMethod = arrayOfMethod[i];
//				String str1 = localMethod.getName();
//				String str2 = "";
//				if (str1.startsWith("set"))
//					str2 = str1.substring(3);
////				else if (str1.startsWith("is"))
////					str2 = str1.substring(2);
//				if ((str2.length() > 0)
//						&& (Character.isUpperCase(str2.charAt(0)))
//						&& (localMethod.getParameterTypes().length == 1)) 
//				{
//					if (str2.length() == 1)
//						str2 = str2.toLowerCase();
//					else if (!Character.isUpperCase(str2.charAt(1)))
//						str2 = str2.substring(0, 1).toLowerCase()
//								+ str2.substring(1);
////					result.put(str2, localMethod.invoke(obj, (Object[]) null));
////					result.put(i, localMethod.invoke(obj, (Object[]) null));
//					Object value = null;
//					if(localMethod.getParameterTypes()[0].equals(Long.class))
//						value = Long.parseLong(source.get(str2).toString());
//					else if(localMethod.getParameterTypes()[0].equals(Integer.class))
//						value = Integer.parseInt(source.get(str2).toString());
//					else
//						value = source.get(str2);
//					localMethod.invoke(target,new Object [] {value});
//				}
//			} catch (Exception localException) {
//			}
//		}
//		
//	}
	
	public static void copyProperty(Map source, Object target,String[] formatStr) {
		Class localClass = target.getClass();
		Method[] arrayOfMethod = localClass.getMethods();
		int j = 0;
		for (int i = 0; i < arrayOfMethod.length; i++) {
			try {
				Method localMethod = arrayOfMethod[i];
				String str1 = localMethod.getName();
				String str2 = "";
				if (str1.startsWith("set"))
					str2 = str1.substring(3);
				if ((str2.length() > 0)
						&& (Character.isUpperCase(str2.charAt(0)))
						&& (localMethod.getParameterTypes().length == 1)) {
					
					if (str2.length() == 1)
						str2 = str2.toLowerCase();
					else if (!Character.isUpperCase(str2.charAt(1)))
						str2 = str2.substring(0, 1).toLowerCase()
								+ str2.substring(1);
					
					Object value = null;
					
					if (localMethod.getParameterTypes()[0].equals(Long.class) &&
							source.get(str2)!=null && !source.get(str2).equals("")) {
						value = Long.parseLong(source.get(str2).toString());
					} else if (localMethod.getParameterTypes()[0].equals(Integer.class) &&
							source.get(str2)!=null && !source.get(str2).equals("")) {
						value = Integer.parseInt(source.get(str2).toString());
					} else if (localMethod.getParameterTypes()[0].equals(Date.class)) {
						if (formatStr != null && formatStr.length > j) 
						{
							if(source.get(str2)!=null && !source.get(str2).equals(""))
							{
								value = TimeUtil.toDate(source.get(str2).toString(), formatStr[j]);
							}
							j++;
						} 
						else 
						{
							throw new Exception("输入格式化参数有误");
						}
					} else {
						value = source.get(str2);
					}

					localMethod.invoke(target, new Object[] { value });
				}
			} catch (Exception localException) { }
		}
	}
	
	public static void copyProperty(Map source,Object target)
	{
		BeanUtil.copyProperty(source, target, null);
	}
}
