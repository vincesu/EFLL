package com.vincesu.test.bean;

import java.util.Date;

public class testBean 
{
	private String str;
	
	private Date date;
	
	private Long l;
	
	private Integer i;
	
	public testBean() {}
	
	public testBean(String str, Date d,Long l,Integer i)
	{
		this.str = str;
		this.date = d;
		this.l=l;
		this.i=i;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setL(Long l) {
		this.l = l;
	}

	public Long getL() {
		return l;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Integer getI() {
		return i;
	}
	
}
