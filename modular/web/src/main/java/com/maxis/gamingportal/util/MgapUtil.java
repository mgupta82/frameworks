package com.maxis.gamingportal.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ibm.wsspi.security.auth.callback.Constants;
import com.ibm.wsspi.security.auth.callback.WSMappingCallbackHandlerFactory;


public class MgapUtil {
	
	private static final Logger logger = LogManager.getLogger(MgapUtil.class.getName());	
	
	
	public static final PasswordCredential getCredentials(String aliasName){
		try{
		//Get Node Name
		//InitialContext ic = new javax.naming.InitialContext();
		//String nodeName = ic.lookup("thisNode/nodename").toString();			
		//Create alias name
		Map<String, String> map = new HashMap<String,String>();
		map.put(Constants.MAPPING_ALIAS, aliasName);
		//Login with default context
		CallbackHandler callbackHandler = WSMappingCallbackHandlerFactory.getInstance().getCallbackHandler(map, null);
		LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
		loginContext.login();
		//Get credentials
		Subject subject = loginContext.getSubject();
		Set credentials = subject.getPrivateCredentials();
		PasswordCredential passwordCredential = (PasswordCredential) credentials.iterator().next();
		return passwordCredential;
		}catch(Exception ex){
			logger.error("Failed to get Active Directory password"+ex);
		}
		return null;
	}
	
	public static String getNodeName(){
		String nodeName = "";
		//Get Node Name
		try{
		InitialContext ic = new javax.naming.InitialContext();
		nodeName = ic.lookup("thisNode/nodename").toString();
		}catch (Exception ex){
			logger.error("Failed to get current Node Name",ex);
		}
		return nodeName;		
		
	}

}
