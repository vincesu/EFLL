package com.vincesu.framework.entity;

import com.vincesu.framework.entity.base.BaseSysRole;



public class SysRole extends BaseSysRole {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysRole () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysRole (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysRole (
		java.lang.Long id,
		java.lang.String rolename) {

		super (
			id,
			rolename);
	}

/*[CONSTRUCTOR MARKER END]*/


}