package com.basic.dao;

import org.junit.Assert;
import org.junit.Test;



public class TestDaoTest {
	
	@Test
	public void testGetData(){
		DaoFactory daoFactory = new DaoFactory();
		TestDao testDao = daoFactory.getDaoImpl(TestDao.class);
		Assert.assertEquals(testDao.getData(new Integer(1)), "select id,name from txn where id=?");
	}	
	
	@Test
	public void testSetData(){
		DaoFactory daoFactory = new DaoFactory();
		TestDao testDao = daoFactory.getDaoImpl(TestDao.class);
		testDao.setData(new Integer(2),"Two");
	}		

}
