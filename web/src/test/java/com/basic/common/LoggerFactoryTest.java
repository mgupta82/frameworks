package com.basic.common;

import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

public class LoggerFactoryTest {
	
	@Test
	public void testCreateLogger(){
		Logger logger = LoggerFactory.createLogger();
		Assert.assertEquals(logger.getName(), LoggerFactoryTest.class.getName());
	}

}
