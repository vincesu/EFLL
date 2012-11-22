package com.vincesu.framework.entity.base;

import java.io.Serializable;


public abstract class BaseSysPermissionsPK implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private java.lang.Long roleid;
	private java.lang.Long moduleid;


	public BaseSysPermissionsPK () {}
	
	public BaseSysPermissionsPK (
		java.lang.Long roleid,
		java.lang.Long moduleid) {

		this.setRoleid(roleid);
		this.setModuleid(moduleid);
	}


	/**
	 * Return the value associated with the column: roleid
	 */
	public java.lang.Long getRoleid () {
		return roleid;
	}

	/**
	 * Set the value related to the column: roleid
	 * @param roleid the roleid value
	 */
	public void setRoleid (java.lang.Long roleid) {
		this.roleid = roleid;
	}



	/**
	 * Return the value associated with the column: moduleid
	 */
	public java.lang.Long getModuleid () {
		return moduleid;
	}

	/**
	 * Set the value related to the column: moduleid
	 * @param moduleid the moduleid value
	 */
	public void setModuleid (java.lang.Long moduleid) {
		this.moduleid = moduleid;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vincesu.framework.entity.SysPermissionsPK)) return false;
		else {
			com.vincesu.framework.entity.SysPermissionsPK mObj = (com.vincesu.framework.entity.SysPermissionsPK) obj;
			if (null != this.getRoleid() && null != mObj.getRoleid()) {
				if (!this.getRoleid().equals(mObj.getRoleid())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getModuleid() && null != mObj.getModuleid()) {
				if (!this.getModuleid().equals(mObj.getModuleid())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			if (null != this.getRoleid()) {
				sb.append(this.getRoleid().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getModuleid()) {
				sb.append(this.getModuleid().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}