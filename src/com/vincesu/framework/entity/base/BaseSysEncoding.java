package com.vincesu.framework.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sys_encoding table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="sys_encoding"
 */

public abstract class BaseSysEncoding  implements Serializable {

	public static String REF = "SysEncoding";
	public static String PROP_CODING_VALUE = "CodingValue";
	public static String PROP_FIELD_VALUE = "FieldValue";
	public static String PROP_FIELD_NAME = "FieldName";
	public static String PROP_RESERVED = "Reserved";
	public static String PROP_ID = "Id";


	// constructors
	public BaseSysEncoding () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysEncoding (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysEncoding (
		java.lang.Long id,
		java.lang.String fieldName,
		java.lang.String fieldValue,
		java.lang.String codingValue) {

		this.setId(id);
		this.setFieldName(fieldName);
		this.setFieldValue(fieldValue);
		this.setCodingValue(codingValue);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String fieldName;
	private java.lang.String fieldValue;
	private java.lang.String codingValue;
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
	 * Return the value associated with the column: field_name
	 */
	public java.lang.String getFieldName () {
		return fieldName;
	}

	/**
	 * Set the value related to the column: field_name
	 * @param fieldName the field_name value
	 */
	public void setFieldName (java.lang.String fieldName) {
		this.fieldName = fieldName;
	}



	/**
	 * Return the value associated with the column: field_value
	 */
	public java.lang.String getFieldValue () {
		return fieldValue;
	}

	/**
	 * Set the value related to the column: field_value
	 * @param fieldValue the field_value value
	 */
	public void setFieldValue (java.lang.String fieldValue) {
		this.fieldValue = fieldValue;
	}



	/**
	 * Return the value associated with the column: coding_value
	 */
	public java.lang.String getCodingValue () {
		return codingValue;
	}

	/**
	 * Set the value related to the column: coding_value
	 * @param codingValue the coding_value value
	 */
	public void setCodingValue (java.lang.String codingValue) {
		this.codingValue = codingValue;
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
		if (!(obj instanceof com.vincesu.framework.entity.SysEncoding)) return false;
		else {
			com.vincesu.framework.entity.SysEncoding sysEncoding = (com.vincesu.framework.entity.SysEncoding) obj;
			if (null == this.getId() || null == sysEncoding.getId()) return false;
			else return (this.getId().equals(sysEncoding.getId()));
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