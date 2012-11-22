package com.vincesu.framework.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sys_user table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="sys_user"
 */

public abstract class BaseSysUser  implements Serializable {

	public static String REF = "SysUser";
	public static String PROP_LAST_LOGIN_TIME = "LastLoginTime";
	public static String PROP_JOINED_TIME = "JoinedTime";
	public static String PROP_USERNAME = "Username";
	public static String PROP_PWD = "Pwd";
	public static String PROP_RESERVED = "Reserved";
	public static String PROP_ID = "Id";
	public static String PROP_ROLE_ID = "RoleId";


	// constructors
	public BaseSysUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysUser (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysUser (
		java.lang.Long id,
		java.lang.String username,
		java.lang.String pwd,
		java.lang.Long roleId,
		java.util.Date joinedTime) {

		this.setId(id);
		this.setUsername(username);
		this.setPwd(pwd);
		this.setRoleId(roleId);
		this.setJoinedTime(joinedTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String username;
	private java.lang.String pwd;
	private java.lang.Long roleId;
	private java.lang.String reserved;
	private java.util.Date joinedTime;
	private java.util.Date lastLoginTime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="increment"
     *  column="uid"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: username
	 */
	public java.lang.String getUsername () {
		return username;
	}

	/**
	 * Set the value related to the column: username
	 * @param username the username value
	 */
	public void setUsername (java.lang.String username) {
		this.username = username;
	}



	/**
	 * Return the value associated with the column: pwd
	 */
	public java.lang.String getPwd () {
		return pwd;
	}

	/**
	 * Set the value related to the column: pwd
	 * @param pwd the pwd value
	 */
	public void setPwd (java.lang.String pwd) {
		this.pwd = pwd;
	}



	/**
	 * Return the value associated with the column: role_id
	 */
	public java.lang.Long getRoleId () {
		return roleId;
	}

	/**
	 * Set the value related to the column: role_id
	 * @param roleId the role_id value
	 */
	public void setRoleId (java.lang.Long roleId) {
		this.roleId = roleId;
	}



	/**
	 * Return the value associated with the column: reserved
	 */
	public java.lang.String getReserved () {
		return reserved;
	}

	/**
	 * Set the value related to the column: reserved
	 * @param reserved the reserved value
	 */
	public void setReserved (java.lang.String reserved) {
		this.reserved = reserved;
	}



	/**
	 * Return the value associated with the column: joinedTime
	 */
	public java.util.Date getJoinedTime () {
		return joinedTime;
	}

	/**
	 * Set the value related to the column: joinedTime
	 * @param joinedTime the joinedTime value
	 */
	public void setJoinedTime (java.util.Date joinedTime) {
		this.joinedTime = joinedTime;
	}



	/**
	 * Return the value associated with the column: lastLoginTime
	 */
	public java.util.Date getLastLoginTime () {
		return lastLoginTime;
	}

	/**
	 * Set the value related to the column: lastLoginTime
	 * @param lastLoginTime the lastLoginTime value
	 */
	public void setLastLoginTime (java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vincesu.framework.entity.SysUser)) return false;
		else {
			com.vincesu.framework.entity.SysUser sysUser = (com.vincesu.framework.entity.SysUser) obj;
			if (null == this.getId() || null == sysUser.getId()) return false;
			else return (this.getId().equals(sysUser.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}