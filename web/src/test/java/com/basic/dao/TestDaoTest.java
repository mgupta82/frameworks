package com.basic.dao;

import org.junit.Test;

import junit.framework.Assert;

public class TestDaoTest {
	
	@Test
	public void testGetData(){
		DaoFactory daoFactory = new DaoFactory();
		TestDao testDao = daoFactory.getDaoImpl(TestDao.class);
		Assert.assertEquals(testDao.getData(), "select id,name from txn");
	}	

}
