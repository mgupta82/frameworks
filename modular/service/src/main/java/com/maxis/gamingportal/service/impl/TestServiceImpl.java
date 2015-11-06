package com.maxis.gamingportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maxis.gamingportal.dao.TestDao;
import com.maxis.gamingportal.pojo.TestBean;
import com.maxis.gamingportal.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Autowired
	TestDao testDao;
	
	public TestBean getDetails(String gateway) throws Exception{
		TestBean testBean;
		try{
			testBean = testDao.getGatewayDetails(gateway);
		}catch(Exception ex){
			throw ex;
		}
		return testBean;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void setGatewayDetails(){
		testDao.setGatewayDetails();
	}

}
