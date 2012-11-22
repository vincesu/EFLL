package com.vincesu.framework.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sys_role table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="sys_role"
 */

public abstract class BaseSysRole  implements Serializable {

	public static String REF = "SysRole";
	public static String PROP_ROLENAME = "Rolename";
	public static String PROP_RESERVED = "Reserved";
	public static String PROP_ID = "Id";


	// constructors
	public BaseSysRole () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysRole (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysRole (
		java.lang.Long id,
		java.lang.String rolename) {

		this.setId(id);
		this.setRolename(rolename);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String rolename;
	private java.lang.String reserved;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="increment"
     *  column="roleid"
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
	 * Return the value associated with the column: rolename
	 */
	public java.lang.String getRolename () {
		return rolename;
	}

	/**
	 * Set the value related to the column: rolename
	 * @param rolename the rolename value
	 */
	public void setRolename (java.lang.String rolename) {
		this.rolename = rolename;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.vincesu.framework.entity.SysRole)) return false;
		else {
			com.vincesu.framework.entity.SysRole sysRole = (com.vincesu.framework.entity.SysRole) obj;
			if (null == this.getId() || null == sysRole.getId()) return false;
			else return (this.getId().equals(sysRole.getId()));
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