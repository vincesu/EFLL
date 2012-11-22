package com.vincesu.framework.remote;

public class ServiceEntity {
	
	private String serviceid = null;
	private String classname = null;
	private String method = null;
	
	public ServiceEntity(String sid,String cn,String method)
	{
		this.serviceid = sid;
		this.classname = cn;
		this.method = method;
	}
	
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getClassname() {
		return classname;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMethod() {
		return method;
	}

}
