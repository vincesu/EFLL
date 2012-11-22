package com.vincesu.test.persistence;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import junit.framework.Assert;

import com.vincesu.framework.entity.SysPermissions;
import com.vincesu.framework.entity.SysPermissionsPK;
import com.vincesu.framework.entity.SysRole;
import com.vincesu.persistence.PMF;

public class PMFtest extends TestCase {

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testGet()
	{
		BigInteger count = (BigInteger) PMF.get("select count(1) from sys_role",null).get(0);
		System.out.println(count);
		
		SysRole role = new SysRole(null, "测试");
		PMF.save(role);
		List<SysRole> data = PMF.get(
			"select * from sys_role where roleid="+role.getId(),
			new Class [] {SysRole.class}
		);
		assertEquals(data.get(0).getClass().getName(),SysRole.class.getName());
		assertEquals(data.get(0).getRolename(),"测试");
		PMF.remove(role);
	}
	
	
	
	public void testSave()
	{
		BigInteger count = (BigInteger) PMF.get("select count(1) from sys_role",null).get(0);
		SysRole role = new SysRole(null, "test1");
		PMF.save(role);
		BigInteger actual = (BigInteger) PMF.get("select count(1) from sys_role",null).get(0);
		count = count.add(new BigInteger("1"));
		assertEquals(count,actual);
		PMF.remove(role);
		actual = (BigInteger) PMF.get("select count(1) from sys_role",null).get(0);
		count = count.subtract(new BigInteger("1"));
		assertEquals(count,actual);
		
		SysPermissions premissions = new SysPermissions(new SysPermissionsPK((long)1,(long)1));
		PMF.restore(premissions);
		PMF.remove(premissions);
	}
	
	
	public void testUpdate()
	{
		SysRole role = new SysRole(null, "test");
		PMF.save(role);
		role.setRolename("test2");
		PMF.update(role);
		List<Object []> data = PMF.get("select roleid,rolename from sys_role where roleid="+role.getId(),null);
		assertEquals("test2", data.get(0)[1].toString());
		PMF.remove(role);
	}
	
	public void testRestore()
	{
		//添加
		BigInteger count = (BigInteger) PMF.get("select count(1) from sys_role",null).get(0);
		SysRole role = new SysRole(null, "test1");
		PMF.restore(role);
		BigInteger actual = (BigInteger) PMF.get("select count(1) from sys_role",null).get(0);
		count = count.add(new BigInteger("1"));
		assertEquals(count,actual);
		//修改
		role.setRolename("test2");
		PMF.restore(role);
		List<Object []> data = PMF.get("select roleid,rolename from sys_role where roleid="+role.getId(),null);
		assertEquals("test2", data.get(0)[1].toString());
		//删除
		PMF.remove(role);
//		actual = (BigInteger) PMF.get("select count(1) from sys_role").get(0);
//		count = count.subtract(new BigInteger("1"));
//		assertEquals(count,actual);
	}
	

}
