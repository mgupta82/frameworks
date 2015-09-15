package com.basic.dao;

import org.junit.Assert;
import org.junit.Test;



public class TestDaoTest {
	
	@Test
	public void testGetData(){
		DaoFactory daoFactory = new DaoFactory();
		TestDao testDao = daoFactory.getDaoImpl(TestDao.class);
		Assert.assertEquals(testDao.getData(), "select id,name from txn");
	}	
	
	@Test
	public void testSetData(){
		DaoFactory daoFactory = new DaoFactory();
		TestDao testDao = daoFactory.getDaoImpl(TestDao.class);
		testDao.setData();
	}		

}
