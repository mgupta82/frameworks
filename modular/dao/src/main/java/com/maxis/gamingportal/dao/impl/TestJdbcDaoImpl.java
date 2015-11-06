package com.maxis.gamingportal.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.maxis.gamingportal.dao.TestDao;
import com.maxis.gamingportal.pojo.TestBean;

@Repository("testDao")
public class TestJdbcDaoImpl implements TestDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;	
	
	private static final String CREATE = "";
	private static final String READ = "Select gateway_provider_name,gateway_rest_url from Gateway_Mstr where gateway_provider_name = ?";
	private static final String UPDATE = "";
	private static final String DELETE = "";

	private class GatewayMasterRowMapper implements  RowMapper<TestBean>{
		public TestBean mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			TestBean gatewayMasterBean = new TestBean();
			gatewayMasterBean.setGatewayName(rs.getString("gateway_provider_name"));
			gatewayMasterBean.setRestUrl(rs.getString("gateway_rest_url"));
			gatewayMasterBean.setBannerImage("img_xxx");
			gatewayMasterBean.setBannerUrl("test");
			return gatewayMasterBean;
		}	
	}
	
	public TestBean getGatewayDetails(String gatewayName) throws Exception {
		TestBean gatewayMasterBean = null;
		try{
			gatewayMasterBean = jdbcTemplate.queryForObject(READ, new Object[]{gatewayName},new GatewayMasterRowMapper());
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return gatewayMasterBean;
	}
	
	public void setGatewayDetails(){
		//We are doing mistake here to rollback the  transaction
		jdbcTemplate.queryForObject(READ, new GatewayMasterRowMapper());
	}	

}
