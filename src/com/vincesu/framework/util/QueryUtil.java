package com.vincesu.framework.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import com.vincesu.framework.model.EncodingModel;
import com.vincesu.framework.remote.RequestEntity;
import com.vincesu.framework.remote.ResponseEntity;
import com.vincesu.persistence.PMF;
import com.vincesu.persistence.SessionFactoryHelper;

public class QueryUtil {
	
	/*
	 * 查询出结果以jqgrid的json数据格式输出
	 * 参数：
	 * 	req---页面查询条件
	 * 	sqlstr---sql语句
	 * 	fields---查询出的字段名数组
	 * 	selectFields---显示字段名数组
	 * 	formatFields---需要日期格式化字段名数组
	 * 	dateFormatStr---日期格式化字符串
	 */
	public static ResponseEntity queryBySQLAsJqgrid(RequestEntity req,String sqlstr,Object [] fields,String [] selectFields,String [] formatFields,String [] dateFormatStr) throws Exception
	{
		System.out.println(sqlstr);
		//获取其他参数替换sql
		sqlstr = getSQLParam(req, sqlstr);
		System.out.println(sqlstr);
		//起始位页 数 为 1
		ResponseEntity resp = new ResponseEntity();
		List<Map<Object, Object>> sourceData = null;
		int rows = 0,page = 0;
		try {
			//获得 参数 行数rows page 当前页
			Map data = (Map) req.getData().get(0);
			rows = Integer.parseInt( data.get("rows").toString());
			page = Integer.parseInt( data.get("page").toString());
			//获得总数据量
			BigInteger _count = (BigInteger) PMF.get(QueryUtil.getCountSQL(sqlstr),null).get(0);
			int count = _count.intValue();
			//验证page正确性，小于正确范围设置为第1页，大于正确范围设置为最后一页
			while(((page-1)*rows)>count)
			{
				page--;
			}
			if(page <= 0 )	page =1;
			//计算总页数
			int total = count%rows==0?count/rows:count/rows+1;
			
			//获得数据并转成JSON
			
			if(fields[0] instanceof String)
			{
				sourceData = (List<Map<Object,Object>>) getData(QueryUtil.getPagerSQL(sqlstr, page, rows),(String [])fields,formatFields,dateFormatStr);
				
			} else if(fields[0] instanceof Class)
			{
				sourceData = (List<Map<Object,Object>>) getData(QueryUtil.getPagerSQL(sqlstr, page, rows),(Class [])fields,formatFields,dateFormatStr);
			}
			
			List<JSONObject> respdata = new ArrayList<JSONObject>();
			JSONObject jsonobj = null;
			JSONArray jsonarray = null;
			//这里有bug ！只返回了select fields 的字段，没有全部返还。应该返回fields参数的所有字段。
			for(Map m: sourceData)
			{
				jsonarray = new JSONArray();
				for(int i=0;i<selectFields.length;i++)
				{
					jsonarray.put(i,m.get(selectFields[i]));
				}
				jsonobj = new JSONObject();
				jsonobj.put("cell",jsonarray);
				respdata.add(jsonobj);
			}
			
			//设置返回数据
			resp.setTotal(total);
			resp.setPage(page);
			resp.setRecords(count);
			resp.setRows(respdata);
			
//			System.out.println(new JSONObject(resp));
			
		} catch(Exception e)
		{
			throw e;
		}
		
		return resp;
		
	}
	
//	public static List<Map<Object, Object>> getDataBy(String str,int page,int rows,String [] fields,String [] formatFields,String [] dateFormatStr) throws Exception
//	{
//		Object obj = PMF.get(getPagerSQL(str,page,rows));
//		return formatData(obj, fields, formatFields, dateFormatStr);
//	}
	
	/*
	 * 获得数据
	 * 入参：req requestentity，str sql语句，fields 字段名，formatFields 格式化字段名，dateFormatStr 格式化字符串
	 * 返回  List<Map<Object, Object>>
	 */
	public static List<Map<Object, Object>> getData (RequestEntity req,String str,String [] fields,String [] formatFields,String [] dateFormatStr) throws Exception
	{
		str = getSQLParam(req, str);
		Object obj = PMF.get(str);
		return formatData(obj,fields,formatFields,dateFormatStr);
	}
	
	/*
	 * 获得数据
	 * 入参：req requestentity，str sql语句，tables 表名
	 * 返回  List<T> 实体类数组
	 */
	public static List getData (RequestEntity req,String str,Class [] tables) throws Exception
	{
		str = getSQLParam(req, str);
		return PMF.get(str,tables);
	}
	
	/*
	 * 获得数据
	 * 入参：str sql语句，fields 字段名，formatFields 格式化字段名，dateFormatStr 格式化字符串
	 * 返回  List<Map<Object, Object>>
	 */
	public static List<Map<Object, Object>> getData (String str,String [] fields,String [] formatFields,String [] dateFormatStr) throws Exception
	{
		Object obj = PMF.get(str,null);
		return formatData(obj,fields,formatFields,dateFormatStr);
	}
	
	/*
	 * 获得数据
	 * 入参：str sql语句，entityClasses 实体类名，formatFields 格式化字段名，dateFormatStr 格式化字符串
	 * 返回  List<Map<Object, Object>>
	 */
	public static List<Map<Object, Object>> getData (String str,Class [] entityClasses,String [] formatFields,String [] dateFormatStr) throws Exception
	{
		Object obj = PMF.get(str,entityClasses);
		return formatData(obj,null,formatFields,dateFormatStr);
	}

	/*
	 * 格式化数据，转化为List<Map<Object, Object>>
	 */
	public static List<Map<Object, Object>> formatData(Object data,String [] fields,String [] formatFields,String [] dateFormatStr) throws Exception
	{
		List<Map<Object, Object>> result = new ArrayList<Map<Object,Object>>();
		int [] formatFieldsIndex = null;
		int m =0, j = 0;
		Map<Object,Object> map = null;
		
		//如果有需要格式化的字段，创建 formatFieldsIndex
		if(fields!=null && formatFields!=null)
		{
			formatFieldsIndex = new int[formatFields.length];
			for(j=0;j<formatFields.length;j++)
			{
				for(m = 0;m<fields.length;m++)
				{
					if(formatFields[j].equals(fields[m]))
						formatFieldsIndex[j] = m;
				}
			}
		}
		
		if(data instanceof List)
		{
			List list = (List)data;
			if(list.size() == 0)
			{//结果为空
				return new ArrayList<Map<Object,Object>>();
				
			} else
			{
				result = new ArrayList<Map<Object,Object>>();
				//通过fields 判断是dto还是基本数据类型
				if(fields != null)
				{//基本数据类型
					if(list.get(0) instanceof Object [])
					{
						
						List<Object []> arrayList = (List<Object []>)list;
						for(Object [] i : arrayList)
						{
							//格式化  date 类型
							if(formatFieldsIndex!=null)
							{
								for(j=0;j<formatFieldsIndex.length;j++)
								{
									if(i[formatFieldsIndex[j]] instanceof Date)
										i[formatFieldsIndex[j]] = TimeUtil.toString((Date)i[formatFieldsIndex[j]],dateFormatStr[j]);
								}
							}
							result.add(BeanUtil.Array2Map(fields,i));
						}
						return result;
						
					} else if(list.get(0) instanceof Object)
					{
						for(Object i : list)
						{
							//格式化 date 类型
							if(formatFieldsIndex!=null && formatFieldsIndex.length == 1 && 
									formatFieldsIndex[0] == 0 && i instanceof Date)
								i = TimeUtil.toString((Date)i,dateFormatStr[0]);
							
							map = new HashMap<Object,Object>();
							map.put(fields[0],i);
							result.add(map);
						}
						return result;
					}
					
				} else
				{//dto
					if(list.get(0) instanceof Object [])
					{
						throw new Exception("输入参数有误");

					} else if(list.get(0) instanceof Object)
					{
						for(Object i : list)
						{
							map = BeanUtil.Object2Map(i);
							if(formatFields!=null)
							{
								for(j=0;j<formatFields.length;j++)
								{
									map.put(formatFields[j], TimeUtil.toString((Date) map.get(formatFields[j]), dateFormatStr[j]));
								}
							}
							result.add(map);
						}
						return result;
					}
				}
			}
			
		} else
		{//可能是count，id等数据，可转换为int 或 long
			map = new HashMap<Object, Object>();
			map.put(fields[0], data);
			result.add(map);
			return result;
		}
		return null;
	}
	
	public static ResponseEntity queryBySQL(
			RequestEntity req,
			String sqlstr,
			Object [] fields,
			String [] formatFields,
			String [] dateFormatStr 
	) throws Exception
	{
		//获取其他参数替换sql
		sqlstr = getSQLParam(req, sqlstr);
		
		ResponseEntity resp =  new ResponseEntity();
		
		int start = 0,rows = 0;
		try {
			//获得 参数 行数rows start 起始数
			Map data = (Map) req.getData().get(0);
			start = Integer.parseInt( data.get("start").toString());
			rows = Integer.parseInt( data.get("rows").toString());
			sqlstr = getSQLPagerBy(sqlstr,start,rows);
		} catch(Exception e) { }
		
		List<Map<Object,Object>> data = null;
		if(fields[0] instanceof String)
		{
			String [] f = (String [])fields;
			data = getData(sqlstr,f,formatFields,dateFormatStr);
			
		} else if(fields[0] instanceof Class)
		{
			Class [] c = (Class [])fields;
			data = getData(req,sqlstr,c);
			
		} else
		{
			throw new Exception("fields参数有误");
		}
		
		resp.setData(data);
		
		return resp;
	}
	
	public static String getCountSQL(String sql)
	{
		return "select count(1) from ("+sql+") tmp_table";
	}
	
	public static String getPagerSQL(String sql,int page,int rows)
	{
		//起始位页 数 为 1
		return "select * from ("+sql+") tmp_table limit "+(page-1)*rows+","+rows;
	}
	
	public static String getSQLPagerBy(String sql,int start,int rows)
	{
		//起始项为0
		return "select * from ("+sql+") tmp_table limit "+start+","+rows;
	}
	
	public static String getSQLParam2(String sqlStr) throws Exception
	{
		Query query = SessionFactoryHelper.getSession().createSQLQuery(sqlStr);
		query.setEntity("id", (long)1);
		query.setEntity("name", "vince");
		return query.getQueryString();
	}
	
	public static String getSQLParam(RequestEntity req, String sqlStr) throws Exception
	{
		int index = 0,end1 = 0,end2 = 0;
		index = sqlStr.indexOf(":", index);
		Map data = (Map) req.getData().get(0);
		String tmp = null;
		try
		{
			while(index >=0 )
			{
				end1 = sqlStr.indexOf(" ",index);
				end2 = sqlStr.indexOf("'",index);
				if(end1 == -1 && end2 == -1)
				{
					end1 = sqlStr.length();
					
				} else
				{
					if(end1 == -1)	end1 = sqlStr.length();
					if(end2 == -1)	end2 = sqlStr.length();
					end1 = (end1<end2)?end1:end2;
				}
				
				end2 = sqlStr.indexOf("%",index);
				if(end2 != -1)
				{
					end1 = (end1<end2)?end1:end2;
				}
				
				end2 = sqlStr.indexOf(")",index);
				if(end2 != -1)
				{
					end1 = (end1<end2)?end1:end2;
				}
				
				String keyWord = sqlStr.substring(index+1,end1);
				if(data.get(keyWord) == null || data.get(keyWord).toString().trim().equals(""))
				{
					tmp = sqlStr.substring(0, index).trim();
					end2 = tmp.lastIndexOf(" where ");
					end1 = tmp.lastIndexOf(" or ");
					end1 = end1>tmp.lastIndexOf(" and ")?end1:tmp.lastIndexOf(" and ");
					if(end2 == -1 && end1 == -1)	throw new Exception("解析sql语句出错");
					if(end1>end2)
					{	//条件前面有 and 或者 or
						end2 = sqlStr.indexOf(" ", index);
						if(end2 == -1)
							end2 = sqlStr.length();
//						sqlStr = sqlStr.replaceFirst(":"+keyWord, "");
						sqlStr = sqlStr.substring(0,end1)+sqlStr.substring(end2);
						
					} else
					{	//条件前面是   where
						end1 = sqlStr.indexOf(" and ", index)>sqlStr.indexOf(" or ", index)?
								sqlStr.indexOf(" and ", index):sqlStr.indexOf(" or ", index);
						if(end1 == -1)
						{	//只有这一个条件，整个where删除
							end1 =  sqlStr.indexOf(" ", index);
							if(end1 == -1)
								end1 = sqlStr.length();
							if(sqlStr.indexOf(")", index)>-1)
								end1 = end1<sqlStr.indexOf(")", index)?end1:sqlStr.indexOf(")", index);
							
//							sqlStr = sqlStr.replaceFirst(":"+keyWord, "");
							sqlStr = sqlStr.substring(0,end2)+sqlStr.substring(end1);
							
						} else
						{	//有多个条件，删除此条件和后面的连接词（and 或 or）
							end1 = sqlStr.indexOf(" ", end1+1);
							sqlStr = sqlStr.substring(0,end2+7)+sqlStr.substring(end1);
						}
					}
					
				} else
				{
//					if(data.get(keyWord) instanceof String)
//						sqlStr = sqlStr.replaceFirst(":"+keyWord, "'"+data.get(keyWord).toString()+"'");
//					else 
						sqlStr = sqlStr.replaceFirst(":"+keyWord, data.get(keyWord).toString().trim());
				}
				index = sqlStr.indexOf(":");
			}
			
		} catch(Exception e)
		{
			throw new Exception("参数有误，无法获得参数");
		}
		return sqlStr;
	}
	
	public static void Encoding(RequestEntity req,List<Map> data,String [] fields)
	{
		if(req.getEncodingTable() == null)	return;
		EncodingModel em = req.getEncodingTable();
		QueryUtil.Encoding(em, data, fields);
	}
	
	public static void Encoding(EncodingModel em,List<Map> data,String [] fields)
	{
		for(Map m : data)
		{
			for(String f : fields)
			{
				m.put(f,em.getCodingValue(f, m.get(f).toString()));
			}
		}
	}
	
}
