package com.vincesu.framework.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vincesu.framework.entity.SysEncoding;
import com.vincesu.framework.entity.SysModule;
import com.vincesu.framework.entity.SysUser;
import com.vincesu.framework.model.EncodingModel;
import com.vincesu.framework.model.ModuleModel;
import com.vincesu.framework.model.UserModel;
import com.vincesu.framework.remote.RequestEntity;
import com.vincesu.framework.remote.ResponseEntity;
import com.vincesu.framework.util.BeanUtil;
import com.vincesu.framework.util.QueryUtil;
import com.vincesu.persistence.PMF;

public class UserService {

	public ResponseEntity islogin(RequestEntity req) throws Exception
	{
		ResponseEntity resp =  new ResponseEntity();
		if(req.getUser() == null)
			resp.setMessage("notlogin");
		else
			return QueryUtil.queryBySQL(req,
					"select * from sys_module where id in (select moduleid from sys_permissions where roleid="+req.getUser().getRoleId()+")",
					new Class[] {SysModule.class},
					null,
					null
				);
		
		return resp;
	}
	
	public ResponseEntity login(RequestEntity req) throws Exception
	{
		ResponseEntity resp =  new ResponseEntity();
		List<SysUser> data = QueryUtil.getData(req,"select * from sys_user where username=':username' and pwd=':pwd'", 
				new Class[] {SysUser.class});
		if(data.isEmpty())
		{
			resp.setType(ResponseEntity.RESPONSE_TYPE_ERROR);
		}
		else
		{
//			resp.setMessage(data.get(0).getUsername());
			SysUser user = data.get(0);
			user.setLastLoginTime(new Date());
			PMF.restore(user);
			req.setUser(data.get(0));
			req.setEncodingTable(new EncodingModel());
			return QueryUtil.queryBySQL(req,
					"select * from sys_module where id in (select moduleid from sys_permissions where roleid="+data.get(0).getRoleId()+")",
					new Class[] {SysModule.class},
					null,
					null
				);
		}
		return resp;
	}
	
	public ResponseEntity getUsers(RequestEntity req) throws Exception
	{
		return QueryUtil.queryBySQLAsJqgrid(req,
				"select user.uid,user.username,user.role_id,role.rolename from sys_user user,sys_role role where user.role_id=role.roleid and user.username like '%:username%' and role.rolename like '%:rolename%'",
				new String [] {"id","username","roleid","rolename"},
				new String [] {"id","username","roleid","rolename"},
				null,null
		);
	}
	
	public ResponseEntity getUser(RequestEntity req) throws Exception
	{
		return QueryUtil.queryBySQL(req, "select * from sys_user where uid=:id",
				new Class [] {SysUser.class},
				null,null);
	}
	
	public ResponseEntity restoreUser(RequestEntity req) throws Exception
	{
		SysUser m = new SysUser();
		BeanUtil.copyProperty((Map)req.getData().get(0), m, new String [] {"yyyy-MM-dd","yyyy-MM-dd"});
		if(m.getJoinedTime() == null)
			m.setJoinedTime(new Date());
		UserModel mm = new UserModel();
		mm.restore(m);
		return new ResponseEntity();
	}
	
	public ResponseEntity removeUser(RequestEntity req) throws Exception
	{
		SysUser m = new SysUser();
		BeanUtil.copyProperty((Map)req.getData().get(0), m);
		UserModel mm = new UserModel();
		mm.remove(m);
		return new ResponseEntity();
	}
	
	public ResponseEntity getEncoding(RequestEntity req) throws Exception
	{
		ResponseEntity resp = new ResponseEntity();
		List<Map<Object,Object>> respdata = new ArrayList<Map<Object,Object>>();
		EncodingModel em = req.getEncodingTable();
		List<SysEncoding> data = em.getEncodingList();
		for(SysEncoding se : data)
		{
			respdata.add(BeanUtil.Object2Map(se));
		}
		resp.setData(respdata);
		return resp;
	}
	
}
