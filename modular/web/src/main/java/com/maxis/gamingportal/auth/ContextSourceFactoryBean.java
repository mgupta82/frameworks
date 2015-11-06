package com.maxis.gamingportal.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.ldap.core.support.LdapContextSource;

import com.ibm.wsspi.security.auth.callback.Constants;
import com.ibm.wsspi.security.auth.callback.WSMappingCallbackHandlerFactory;
import com.maxis.gamingportal.util.MgapConstants;
import com.maxis.gamingportal.util.MgapUtil;

public class ContextSourceFactoryBean extends AbstractFactoryBean<LdapContextSource> {

	@Value("${ldap.url}")
	private String ldapUrl;
	@Value("${ldap.base}")
	private String ldapBase;
	@Value("${j2c.alias.prefix}")
	private String aliasPrefix;	
	
	@Override
	protected LdapContextSource createInstance() throws Exception {
	      LdapContextSource contextSource = new LdapContextSource();
	      contextSource.setUrl(ldapUrl);
	      contextSource.setBase(ldapBase);
	      PasswordCredential passwordCredential = MgapUtil.getCredentials(aliasPrefix+"/"+MgapConstants.J2C_AD_ALIAS);
	      contextSource.setUserDn(passwordCredential.getUserName());
	      contextSource.setPassword(new String(passwordCredential.getPassword()));
	      contextSource.afterPropertiesSet();
	      return contextSource;
	}

	@Override
	public Class<?> getObjectType() {
		return LdapContextSource.class;
	}

}
