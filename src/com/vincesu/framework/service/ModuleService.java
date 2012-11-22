package com.vincesu.framework.service;

import java.util.Map;

import com.vincesu.framework.entity.SysModule;
import com.vincesu.framework.model.ModuleModel;
import com.vincesu.framework.remote.RequestEntity;
import com.vincesu.framework.remote.ResponseEntity;
import com.vincesu.framework.util.BeanUtil;
import com.vincesu.framework.util.QueryUtil;

public class ModuleService {

	public ResponseEntity getModuleList(RequestEntity req) throws Exception
	{
		return QueryUtil.queryBySQLAsJqgrid(req,
				"select * from sys_module where id like '%:id%' and fid like '%:fid%' and name like '%:name%'",
				new Class[] { SysModule.class }, 
				new String[] { "id", "fid",	"name", "address", "title" }, null, null);
	}
	
	public ResponseEntity getModule(RequestEntity req) throws Exception
	{
		ResponseEntity resp = QueryUtil.queryBySQL(req,
				"select * from sys_module where id=':id'", 
				new Class[] { SysModule.class }, 
				null,null);
		return resp;
	}
	
	public ResponseEntity restoreModule(RequestEntity req) throws Exception
	{
		SysModule m = new SysModule();
		BeanUtil.copyProperty((Map)req.getData().get(0), m);
		ModuleModel mm = new ModuleModel();
		mm.restore(m);
		return new ResponseEntity();
	}
	
	public ResponseEntity removeModule(RequestEntity req)
	{
		SysModule m = new SysModule();
		BeanUtil.copyProperty((Map)req.getData().get(0), m);
		ModuleModel mm = new ModuleModel();
		mm.remove(m);
		return new ResponseEntity();
	}
	
}
