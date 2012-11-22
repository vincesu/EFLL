package com.vincesu.test.framework.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.vincesu.framework.util.BeanUtil;
import com.vincesu.framework.util.TimeUtil;
import com.vincesu.test.bean.testBean;

import junit.framework.TestCase;

public class BeanUtilTest extends TestCase {
	
	public void setUp() throws Exception { }

	public void tearDown() throws Exception { }
	
	public void testCopyProperty()
	{
		testBean bean = new testBean();
		Map map = new HashMap();
		map.put("str", "str");
		map.put("date", "1989-05-07");
		map.put("l", (long)2);
		map.put("i", 2);
		BeanUtil.copyProperty(map, bean, new String [] {"yyyy-MM-dd"});
		assertEquals("str", bean.getStr());
		assertEquals("1989-05-07", TimeUtil.toString(bean.getDate(),"yyyy-MM-dd"));
		assertEquals((long)2, (long)bean.getL());
		assertEquals((Integer)2, bean.getI());
		
	}
	
}
