package com.vincesu.framework.entity;

import com.vincesu.framework.entity.base.BaseSysUser;



public class SysUser extends BaseSysUser {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysUser () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysUser (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysUser (
		java.lang.Long id,
		java.lang.String username,
		java.lang.String pwd,
		java.lang.Long roleId,
		java.util.Date joinedTime) {

		super (
			id,
			username,
			pwd,
			roleId,
			joinedTime);
	}

/*[CONSTRUCTOR MARKER END]*/


}