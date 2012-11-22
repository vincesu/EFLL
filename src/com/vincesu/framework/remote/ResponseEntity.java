package com.vincesu.framework.remote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ResponseEntity {
	
	public static final int RESPONSE_TYPE_ERROR = 0;
	public static final int RESPONSE_TYPE_DATA = 1;
	
	private int type = -1;
	private String message = null;
	private int total = 0 ;
	private int page = 0;
	private int records = 0;
	private List rows = null;
	private List<Map<Object,Object>> data = null;

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setData(List<Map<Object,Object>> data) {
		this.data = data;
	}

	public List<Map<Object,Object>> getData() {
		return data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getTypeName() {

		String result = null;
		
		switch(this.type)
		{
			case -1:result="normal";break;
			case 0:result="error";break;
			case 1:result="data";break;
				
		}
		return result;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getRecords() {
		return records;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getRows() {
		return rows;
	}
	
}
