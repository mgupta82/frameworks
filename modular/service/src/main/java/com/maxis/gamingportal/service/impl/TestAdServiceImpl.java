package com.maxis.gamingportal.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maxis.gamingportal.dao.TestAdDao;
import com.maxis.gamingportal.pojo.TestAdBean;
import com.maxis.gamingportal.service.TestAdService;

@Service("testAdService")
public class TestAdServiceImpl implements TestAdService  {
	
	@Autowired
	TestAdDao testAdDao;

	public TestAdBean getUser() throws Exception {
		TestAdBean adUserBean;
		try{
			adUserBean =  testAdDao.getUser();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return adUserBean;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void setUser(){
		testAdDao.setUser();
	}

}
