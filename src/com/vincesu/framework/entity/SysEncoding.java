package com.vincesu.framework.entity;

import com.vincesu.framework.entity.base.BaseSysEncoding;



public class SysEncoding extends BaseSysEncoding {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysEncoding () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysEncoding (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysEncoding (
		java.lang.Long id,
		java.lang.String fieldName,
		java.lang.String fieldValue,
		java.lang.String codingValue) {

		super (
			id,
			fieldName,
			fieldValue,
			codingValue);
	}

/*[CONSTRUCTOR MARKER END]*/


}