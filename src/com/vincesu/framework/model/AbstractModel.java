package com.vincesu.framework.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;

import com.vincesu.persistence.PMF;
import com.vincesu.persistence.SessionFactoryHelper;

public abstract class AbstractModel <T> {
	
	protected Class<T> entityClass = null;
	
	public AbstractModel()
	{
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void save(T entity)
	{
		PMF.save(entity);
	}
	
	public void restore(T entity)
	{
		PMF.restore(entity);
	}
	
	public void modify(T entity)
	{
		PMF.update(entity);
	}
	
	public void remove(T entity)
	{
		PMF.remove(entity);
	}
	
	public Long count() 
	{
		String hql = "select count(*) from "+entityClass.getName();
		Query query=SessionFactoryHelper.getSession().createQuery(hql);
		List d = query.list();
		return (Long)(d.get(0));
	}
	
	public List<T> list()
	{
		String hql = "from "+entityClass.getName();
		Query query=SessionFactoryHelper.getSession().createQuery(hql);
		return query.list();
	}
	
//	public int getCount() throws Exception
//	{
//		Integer i = 0;
//		try {
//			String querystr = "select count(1) from "+entityClass.getName();
//			i = (Integer);
//		} catch(Exception e)
//		{
//			throw new Exception("无法计数，错误原因："+e.getMessage());
//		}
//		return i;
//	}
//	
//	public Long getMaxID() throws Exception
//	{	
//		try {
//			
//			StringBuffer querystr = new StringBuffer();
//			querystr.append("select id from ").append(entityClass.getName());
//			querystr.append(" order by id desc range 0,1");
//			
//			List<Object> result = PMF.get(querystr.toString());
//		
//			return Long.parseLong(result.get(0).toString().trim());
//		} catch(Exception e) {
//			throw new Exception("无法获得最大id，错误原因："+e.getMessage());
//		}
//	}

}
