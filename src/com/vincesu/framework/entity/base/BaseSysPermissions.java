package com.vincesu.framework.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sys_permissions table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="sys_permissions"
 */

public abstract class BaseSysPermissions  implements Serializable {

	public static String REF = "SysPermissions";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_RESERVED = "Reserved";
	public static String PROP_ID = "Id";


	// constructors
	public BaseSysPermissions () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysPermissions (com.vincesu.framework.entity.SysPermissionsPK id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.vincesu.framework.entity.SysPermissionsPK id;

	// fields
	private java.lang.String description;
	private java.lang.String reserved;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.vincesu.framework.entity.SysPermissionsPK getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.vincesu.framework.entity.SysPermissionsPK id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: description
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
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
		if (!(obj instanceof com.vincesu.framework.entity.SysPermissions)) return false;
		else {
			com.vincesu.framework.entity.SysPermissions sysPermissions = (com.vincesu.framework.entity.SysPermissions) obj;
			if (null == this.getId() || null == sysPermissions.getId()) return false;
			else return (this.getId().equals(sysPermissions.getId()));
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