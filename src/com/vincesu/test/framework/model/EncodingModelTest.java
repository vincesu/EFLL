package com.vincesu.test.framework.model;

import com.vincesu.framework.entity.SysEncoding;
import com.vincesu.framework.model.EncodingModel;
import com.vincesu.persistence.PMF;

import junit.framework.TestCase;

public class EncodingModelTest extends TestCase {

	public void setUp() throws Exception { }

	public void tearDown() throws Exception { }
	
	public void testCount()
	{
		EncodingModel em = new EncodingModel();
		System.out.println(em.count());
		assertTrue(true);
	}
	
	public void testGetCodingValue()
	{
		SysEncoding se = new SysEncoding(null,"testcode","code","value");
		EncodingModel em = new EncodingModel();
		PMF.save(se);
		em.getEncodingTable();
		assertEquals("value", em.getCodingValue("testcode", "code"));
		PMF.remove(se);
	}
	
}
