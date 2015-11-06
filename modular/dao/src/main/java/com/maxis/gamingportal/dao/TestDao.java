/**
 * 
 */
package com.maxis.gamingportal.dao;

import com.maxis.gamingportal.pojo.TestBean;

/**
 * @author Mukesh_Gupta04
 *
 */
public interface TestDao {
	
	public TestBean getGatewayDetails(String gatewayName) throws Exception;
	public void setGatewayDetails();

}
