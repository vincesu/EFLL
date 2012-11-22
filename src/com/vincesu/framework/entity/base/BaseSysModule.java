package com.vincesu.framework.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sys_module table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="sys_module"
 */

public abstract class BaseSysModule  implements Serializable {

	public static String REF = "SysModule";
	public static String PROP_NAME = "Name";
	public static String PROP_TARGET = "Target";
	public static String PROP_ICON_OPEN = "IconOpen";
	public static String PROP_ADDRESS = "Address";
	public static String PROP_FID = "Fid";
	public static String PROP_RESERVED = "Reserved";
	public static String PROP_ID = "Id";
	public static String PROP_ICON = "Icon";
	public static String PROP_TITLE = "Title";


	// constructors
	public BaseSysModule () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysModule (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysModule (
		java.lang.Long id,
		java.lang.Long fid,
		java.lang.String name) {

		this.setId(id);
		this.setFid(fid);
		this.setName(name);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Long fid;
	private java.lang.String name;
	private java.lang.String address;
	private java.lang.String title;
	private java.lang.String target;
	private java.lang.String icon;
	private java.lang.String iconOpen;
	private java.lang.String reserved;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="increment"
     *  column="id"
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
	 * Return the value associated with the column: fid
	 */
	public java.lang.Long getFid () {
		return fid;
	}

	/**
	 * Set the value related to the column: fid
	 * @param fid the fid value
	 */
	public void setFid (java.lang.Long fid) {
		this.fid = fid;
	}



	/**
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: address
	 */
	public java.lang.String getAddress () {
		return address;
	}

	/**
	 * Set the value related to the column: address
	 * @param address the address value
	 */
	public void setAddress (java.lang.String address) {
		this.address = address;
	}



	/**
	 * Return the value associated with the column: title
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: title
	 * @param title the title value
	 */
	public void setTitle (java.lang.String title) {
		this.title = title;
	}



	/**
	 * Return the value associated with the column: target
	 */
	public java.lang.String getTarget () {
		return target;
	}

	/**
	 * Set the value related to the column: target
	 * @param target the target value
	 */
	public void setTarget (java.lang.String target) {
		this.target = target;
	}



	/**
	 * Return the value associated with the column: icon
	 */
	public java.lang.String getIcon () {
		return icon;
	}

	/**
	 * Set the value related to the column: icon
	 * @param icon the icon value
	 */
	public void setIcon (java.lang.String icon) {
		this.icon = icon;
	}



	/**
	 * Return the value associated with the column: iconOpen
	 */
	public java.lang.String getIconOpen () {
		return iconOpen;
	}

	/**
	 * Set the value related to the column: iconOpen
	 * @param iconOpen the iconOpen value
	 */
	public void setIconOpen (java.lang.String iconOpen) {
		this.iconOpen = iconOpen;
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
		if (!(obj instanceof com.vincesu.framework.entity.SysModule)) return false;
		else {
			com.vincesu.framework.entity.SysModule sysModule = (com.vincesu.framework.entity.SysModule) obj;
			if (null == this.getId() || null == sysModule.getId()) return false;
			else return (this.getId().equals(sysModule.getId()));
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