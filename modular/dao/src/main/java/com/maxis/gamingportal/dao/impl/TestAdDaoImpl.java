package com.maxis.gamingportal.dao.impl;


import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Repository;

import com.maxis.gamingportal.dao.TestAdDao;
import com.maxis.gamingportal.pojo.TestAdBean;

@Repository("testAdDao")
public class TestAdDaoImpl implements TestAdDao {
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	private class UserAttributeMapper implements AttributesMapper {

		@Override
		public Object mapFromAttributes(Attributes attrs) throws NamingException {
			TestAdBean bean = new TestAdBean();
			bean.setUid((String)attrs.get("uid").get());
			bean.setCommonName((String)attrs.get("cn").get());
			bean.setSurName((String)attrs.get("sn").get());
			return bean;
		}
		
	}

	public TestAdBean getUser() throws Exception {
		AndFilter filter = new AndFilter();
	    filter.and(new EqualsFilter("objectClass", "person"));
	    filter.and(new EqualsFilter("uid", "TESTUSER3"));
	    List<TestAdBean> users = ldapTemplate.search("",filter.encode(), new UserAttributeMapper());
	    return users.get(0);
	}
	
	public void setUser(){
		//We are doing mistake here to rollback the  transaction
		AndFilter filter = new AndFilter();
	    filter.and(new EqualsFilter("objectclass#5", "person"));
	    filter.and(new EqualsFilter("uid", "TESTUSER3"));
	    List<TestAdBean> users = ldapTemplate.search("",filter.encode(), new UserAttributeMapper());
	}	

}
