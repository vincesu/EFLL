package com.vincesu.framework.service;

import java.util.List;
import java.util.Map;

import org.infinispan.util.SysPropertyActions;

import com.vincesu.framework.entity.SysModule;
import com.vincesu.framework.entity.SysPermissions;
import com.vincesu.framework.entity.SysPermissionsPK;
import com.vincesu.framework.entity.SysRole;
import com.vincesu.framework.model.ModuleModel;
import com.vincesu.framework.model.RoleModel;
import com.vincesu.framework.remote.RequestEntity;
import com.vincesu.framework.remote.ResponseEntity;
import com.vincesu.framework.util.BeanUtil;
import com.vincesu.framework.util.QueryUtil;
import com.vincesu.persistence.PMF;

public class RoleService {

	public ResponseEntity getRoleList(RequestEntity req) throws Exception
	{
		return QueryUtil.queryBySQLAsJqgrid(req,
				"select * from sys_role where roleid like '%:id%' and rolename like '%:rolename%'",
				new Class[] { SysRole.class }, 
				new String[] { "id", "rolename",	"reserved" }, null, null);
	}
	
	public ResponseEntity getRole(RequestEntity req) throws Exception
	{
		ResponseEntity resp = QueryUtil.queryBySQL(req,
				"select * from sys_role where roleid=':id'", 
				new Class[] { SysRole.class }, 
				null,null);
		return resp;
	}
	
	public ResponseEntity restoreRole(RequestEntity req) throws Exception
	{
		SysRole m = new SysRole();
		BeanUtil.copyProperty((Map)req.getData().get(0), m);
		RoleModel mm = new RoleModel();
		mm.restore(m);
		return new ResponseEntity();
	}
	
	public ResponseEntity removeRole(RequestEntity req)
	{
		SysRole m = new SysRole();
		BeanUtil.copyProperty((Map)req.getData().get(0), m);
		RoleModel mm = new RoleModel();
		mm.remove(m);
		return new ResponseEntity();
	}
	
	public ResponseEntity getPermissions(RequestEntity req) throws Exception
	{
		return QueryUtil.queryBySQLAsJqgrid(
				req, 
				"select a.moduleid,b.name from sys_permissions a,sys_module b where roleid=:id and a.moduleid=b.id and a.moduleid like '%:moduleid%' and b.name like '%:name%'", 
				new String [] {"moduleid","name"},
				new String [] {"moduleid","name"},
				null,null);
	}
	
	public ResponseEntity removePermissions(RequestEntity req) throws Exception
	{
		ResponseEntity resp = new ResponseEntity();
		try {
			List<Map> data = req.getData();
			String roleid = data.get(0).get("roleid").toString();
			for(Map map : data)
			{
				SysPermissions sp = new SysPermissions(new SysPermissionsPK(Long.parseLong(roleid), Long.parseLong(map.get("moduleid").toString())));
				PMF.remove(sp);
			}
		} catch(Exception e)
		{
			resp.setType(ResponseEntity.RESPONSE_TYPE_ERROR);
		}
		return resp;
	}
	
	public ResponseEntity getPermissionsNotOwn(RequestEntity req) throws Exception
	{
		return QueryUtil.queryBySQLAsJqgrid(
				req, 
				"select * from sys_module where id not in (select moduleid from sys_permissions where roleid=:roleid)", 
				new Class [] {SysModule.class},
				new String [] {"id","fid","name","address","title"},
				null,null);
	}
	
	public ResponseEntity addPermissions(RequestEntity req)
	{
		ResponseEntity resp = new ResponseEntity();
		try {
			List<Map> data = req.getData();
			String roleid = data.get(0).get("roleid").toString();
			long id = Long.parseLong(roleid),mid;
			for(Map map : data)
			{
				mid = Long.parseLong(map.get("id").toString());
				SysPermissionsPK pk = new SysPermissionsPK(id,mid);
				SysPermissions sp = new SysPermissions(pk);
				PMF.restore(sp);
			}
		} catch(Exception e)
		{
			resp.setType(ResponseEntity.RESPONSE_TYPE_ERROR);
		}
		return resp;
	}
	
}
