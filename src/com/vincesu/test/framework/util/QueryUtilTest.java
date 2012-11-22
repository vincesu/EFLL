package com.vincesu.test.framework.util;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import junit.framework.TestCase;
import junit.framework.Assert;

import com.vincesu.framework.entity.SysEncoding;
import com.vincesu.framework.entity.SysRole;
import com.vincesu.framework.model.EncodingModel;
import com.vincesu.framework.remote.RequestEntity;
import com.vincesu.framework.util.QueryUtil;
import com.vincesu.framework.util.TimeUtil;
import com.vincesu.persistence.PMF;

public class QueryUtilTest extends TestCase {

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testGetCountSQL()
	{
		String sql = "select * from sys_role";
		BigInteger count = (BigInteger) PMF.get(QueryUtil.getCountSQL(sql),null).get(0);
		System.out.println(count);
	}
	
	public void testGetPagerSQL()
	{
		SysRole role1 = new SysRole(null, "testrole1");
		SysRole role2 = new SysRole(null, "testrole2");
		SysRole role3 = new SysRole(null, "testrole3");
		PMF.save(role1);
		PMF.save(role2);
		PMF.save(role3);
		try
		{
			String sql = "select roleid,rolename from sys_role where roleid >="+role1.getId();
			List<Object []> data = PMF.get(QueryUtil.getPagerSQL(sql, 1, 1));
			assertEquals("testrole1", data.get(0)[1]);
			data = PMF.get(QueryUtil.getSQLPagerBy(sql, 1, 1));
			assertEquals("testrole2", data.get(0)[1]);
		} catch(Exception e)
		{
			e.printStackTrace();
		} finally
		{
			PMF.remove(role1);
			PMF.remove(role2);
			PMF.remove(role3);
		}
	}
	
	/*
	public void testGetSQLParam() throws Exception
	{
		String sql = "select * from sys_role where roleid=:id and rolename=:name";
		RequestEntity req = new RequestEntity(null);
		List<Map<Object,Object>> data = new ArrayList<Map<Object,Object>>();
		Map map = new HashMap<Object, Object>();
		data.add(map);
		map.put("id", (long)1);
		map.put("name", "test1");
		req.setData(data);
		String result = QueryUtil.getSQLParam(req, sql);
		assertEquals("select * from sys_role where roleid=1 and rolename='test1'", result);
		
		sql = "select * from sys_role where roleid=:id and rolename=:name";
		req = new RequestEntity(null);
		data = new ArrayList<Map<Object,Object>>();
		map = new HashMap<Object, Object>();
		data.add(map);
		map.put("id", "");
//		map.put("name", "test1");
		req.setData(data);
		result = QueryUtil.getSQLParam(req, sql);
		assertEquals("select * from sys_role", result);
		
		sql = "select * from sys_role where roleid=:id and rolename=:name";
		req = new RequestEntity(null);
		data = new ArrayList<Map<Object,Object>>();
		map = new HashMap<Object, Object>();
		data.add(map);
		map.put("id", 1);
		map.put("name", "");
		req.setData(data);
		result = QueryUtil.getSQLParam(req, sql);
		assertEquals("select * from sys_role where roleid=1", result);
		
		sql = "select * from sys_role where roleid=:id and rolename=:name";
		req = new RequestEntity(null);
		data = new ArrayList<Map<Object,Object>>();
		map = new HashMap<Object, Object>();
		data.add(map);
		map.put("id", null);
		map.put("name", "vince");
		req.setData(data);
		result = QueryUtil.getSQLParam(req, sql);
		assertEquals("select * from sys_role where  rolename='vince'", result);
	}
	*/
	/*
	public void testGetSQLParam2() throws Exception
	{
		String result = QueryUtil.getSQLParam2("select * from sys_role where roleid=:id and rolename=:name");
		System.out.println(result);
	}
	*/
	public void testFormatData() throws Exception
	{
		List<Map<Object,Object>> result = null;
		
		// case 1
		List<Object []> data1 = new ArrayList<Object[]>();
		data1.add(new Object[] {1,"test1","age1",new Date()});
		data1.add(new Object[] {2,"test2","age2",new Date()});
		data1.add(new Object[] {3,"test3","age3",new Date()});
		result = QueryUtil.formatData(
				data1,
				new String [] {"id","name","age","birthday"},
				new String [] {"birthday"},
				new String [] {"yyyy-MM-dd hh:mm"});
		assertEquals("test1", result.get(0).get("name"));
		assertEquals(TimeUtil.toString(new Date(), "yyyy-MM-dd"), result.get(2).get("birthday").toString().substring(0,10));
		
		// case 2
		List<BigInteger> data2 = new ArrayList<BigInteger>();
		data2.add(new BigInteger("1"));
		data2.add(new BigInteger("2"));
		data2.add(new BigInteger("3"));
		result = QueryUtil.formatData(
				data2,
				new String [] {"count"},
				null,
				null);
		assertEquals(new BigInteger("1"), result.get(0).get("count"));
	}
	
	public void testGetData() throws Exception
	{
		SysRole role1 = new SysRole(null, "testrole1");
		SysRole role2 = new SysRole(null, "testrole2");
		SysRole role3 = new SysRole(null, "testrole3");
		PMF.save(role1);
		PMF.save(role2);
		PMF.save(role3);
		
		List<Map<Object,Object>> result = null;
		result = QueryUtil.getData(
				"select * from sys_role where roleid>="+role1.getId(),
				new String [] {"roleid","rolename","reserved"},
				null,
				null);
		assertEquals("testrole1", result.get(0).get("rolename"));
		
		result = QueryUtil.getData(
				"select * from sys_role where roleid>="+role1.getId(),
				new Class [] {SysRole.class},
				null,
				null);
		assertEquals("testrole1", result.get(0).get("rolename"));
		
		PMF.remove(role1);
		PMF.remove(role2);
		PMF.remove(role3);
	}
	
	public void testEncoding()
	{
		SysEncoding se1 = new SysEncoding(null,"testcode1","code1","value1");
		SysEncoding se2 = new SysEncoding(null,"testcode1","code2","value2");
		SysEncoding se3 = new SysEncoding(null,"testcode2","code1","value1");
		SysEncoding se4 = new SysEncoding(null,"testcode2","code2","value2");
		
		PMF.restore(se1);
		PMF.restore(se2);
		PMF.restore(se3);
		PMF.restore(se4);
		
		EncodingModel em = new EncodingModel();
		
		List<Map> data = new ArrayList<Map>();
		
		Map m = new HashMap();
		m.put("testcode1", "code1");
		m.put("testcode2", "code1");
		data.add(m);
		
		m = new HashMap();
		m.put("testcode1", "code2");
		m.put("testcode2", "code2");
		data.add(m);
		
		QueryUtil.Encoding(em, data, new String [] {"testcode1","testcode2"});
		
		assertEquals("value1", data.get(0).get("testcode1"));
		assertEquals("value1", data.get(0).get("testcode2"));
		assertEquals("value2", data.get(1).get("testcode1"));
		assertEquals("value2", data.get(1).get("testcode2"));
		
		PMF.remove(se1);
		PMF.remove(se2);
		PMF.remove(se3);
		PMF.remove(se4);
		
	}
	
	
	

}
