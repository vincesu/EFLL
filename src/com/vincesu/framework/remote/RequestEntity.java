package com.vincesu.framework.remote;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.vincesu.framework.entity.SysEncoding;
import com.vincesu.framework.entity.SysPermissions;
import com.vincesu.framework.entity.SysRole;
import com.vincesu.framework.entity.SysUser;
import com.vincesu.framework.model.EncodingModel;

public class RequestEntity {
	
	protected HttpSession session = null;
	
	private List<Map<Object,Object>> data = null;
	
	public RequestEntity(HttpSession s)
	{
		session = s;
	}
	
	public void setData(List data) {
		this.data = data;
	}

	public List getData() {
		return data;
	}

	public SysRole getRole() {
		if(session!=null)
		{
			return (SysRole) session.getAttribute("role");
		}
		return null;
	}

	public void setRole(SysRole role) {
		if(session!=null)
			setSessionAttribute("role", role);
	}

	public SysUser getUser() {
		if(session!=null)
		{
			return (SysUser) session.getAttribute("user");
		}
		return null;
	}

	public void setUser(SysUser user) {
		if(session!=null)
			setSessionAttribute("user", user);
	}

	public List<SysPermissions> getPermissions() {
		if(session!=null)
		{
			return (List<SysPermissions>) session.getAttribute("permissions");
		}
		return null;
	}

	public void setPermissions(List<SysPermissions> permissions) {
		if(session!=null)
			setSessionAttribute("permissions", permissions);
	}
	
	public void setSessionAttribute(String key,Object value)
	{
		this.session.setAttribute(key, value);
	}
	
	public Object getSessionAttribute(String key)
	{
		return this.session.getAttribute(key);
	}
	
	public void removeSessionAttribute(String key)
	{
		this.session.removeAttribute(key);
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setEncodingTable(EncodingModel value)
	{
		this.setSessionAttribute("encoding", value);
	}

	public EncodingModel getEncodingTable() {
		EncodingModel em = (EncodingModel)this.getSessionAttribute("encoding");
		if(em == null)
			this.setEncodingTable(new EncodingModel());
		return (EncodingModel)this.getSessionAttribute("encoding");
	}
	
}
