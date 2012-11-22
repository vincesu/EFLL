package com.vincesu.framework.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vincesu.framework.entity.SysEncoding;

public class EncodingModel extends AbstractModel<SysEncoding> {

	protected Map<String,HashMap<String,Object>> encodingTable = null;
	protected List<SysEncoding> _encodingList = null;
	
	public EncodingModel()
	{
		this.encodingTable = new HashMap<String, HashMap<String,Object>>();
		getEncodingTable();
	}
	
	public void getEncodingTable()
	{
		_encodingList = this.list();
		this.encodingTable.clear();
		HashMap<String,Object> m = null;
		for(SysEncoding se : _encodingList)
		{
			m = this.encodingTable.get(se.getFieldName());
			if(m == null)
			{
				m = new HashMap<String,Object>();
				this.encodingTable.put(se.getFieldName(), m);
			}
			m.put(se.getFieldValue(), se.getCodingValue());
		}
	}
	
	public String getCodingValue(String field,String fieldValue)
	{
		Object obj = this.encodingTable.get(field);
		if(obj == null)
			return "";
		obj = this.encodingTable.get(field).get(fieldValue);
		if(obj == null)
			return "";
		else
			return obj.toString();
	}
	
	public List<SysEncoding> getEncodingList()
	{
		return this._encodingList;
	}
	
}
