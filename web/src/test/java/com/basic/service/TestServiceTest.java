package com.basic.service;

import org.junit.Assert;
import org.junit.Test;

public class TestServiceTest {
	
	@Test
	public void testGetData(){
		ServiceFactory serviceFactory = new ServiceFactory();
		TestService testService = (TestService) serviceFactory.getService(TestService.class);
		Assert.assertNotNull(testService);
		Assert.assertEquals(testService.getData(), "select id,name from txn");
		
	}

}
