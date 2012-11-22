package com.vincesu.framework.entity;

import com.vincesu.framework.entity.base.BaseSysModule;



public class SysModule extends BaseSysModule {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysModule () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysModule (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysModule (
		java.lang.Long id,
		java.lang.Long fid,
		java.lang.String name) {

		super (
			id,
			fid,
			name);
	}

/*[CONSTRUCTOR MARKER END]*/

}