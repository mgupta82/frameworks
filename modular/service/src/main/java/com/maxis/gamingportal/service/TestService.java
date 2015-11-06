package com.maxis.gamingportal.service;

import com.maxis.gamingportal.pojo.TestBean;

public interface TestService {
	
	public TestBean getDetails(String gateway) throws Exception;
	
	public void setGatewayDetails();

}
