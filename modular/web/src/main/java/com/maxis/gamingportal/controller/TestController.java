package com.maxis.gamingportal.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ibm.websphere.cache.DistributedMap;
import com.ibm.wsspi.security.auth.callback.Constants;
import com.ibm.wsspi.security.auth.callback.WSMappingCallbackHandlerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.maxis.gamingportal.pojo.TestAdBean;
import com.maxis.gamingportal.pojo.TestBean;
import com.maxis.gamingportal.service.TestAdService;
import com.maxis.gamingportal.service.TestService;
import com.maxis.gamingportal.util.MgapConstants;
import com.maxis.gamingportal.util.MgapUtil;


@Controller
public class TestController {
	
	public static int cnt=0;
	
	@Autowired
	TestService testService;
	
	@Autowired
	TestAdService testAdService;
	
	@Autowired
	PropertiesConfiguration propertiesConfiguration;
	
	@Value("${callback.url}")
	private String callbackUrl;		
	
	@Value("${j2c.alias.prefix}")
	private String aliasPrefix;	
	
	static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};		
	
	private static final Logger logger = LogManager.getLogger(TestController.class.getName());
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ModelAndView test(ModelMap model){
		logger.info("AppServer Connection Succeeded");
		model.addAttribute("message", "Ready to start testing");
		return new ModelAndView("test");
	}
	
	@RequestMapping(value = "/testdb", method=RequestMethod.POST)
	public ModelAndView testdb(ModelMap model){
		try{
			TestBean gatewayMasterBean = testService.getDetails("Garena");
			logger.info("gatewayMasterBean:"+gatewayMasterBean);
			model.addAttribute("message", "DB connection succeeded");
		}catch(Exception ex){
			logger.error("Failed to connect to DB",ex);
			model.addAttribute("message", "Failed to connect to DB");
		}
		
		return new ModelAndView("test");
	}	

	@RequestMapping(value = "/testad", method=RequestMethod.POST)
	public ModelAndView testad(ModelMap model){
		try{
			TestAdBean adUserBean = testAdService.getUser();
			logger.info("adUserBean:"+adUserBean);
			model.addAttribute("message", "AD connection succeeded");
		}catch(Exception ex){
			logger.error("Failed to connect to AD",ex);
			model.addAttribute("message", "Failed to connect to AD");
		}
		return new ModelAndView("test");
	}	

	@RequestMapping(value="/testpwd", method = RequestMethod.POST)
	public ModelAndView testPassword(ModelMap model){
		try{
			PasswordCredential passwordCredential = MgapUtil.getCredentials(aliasPrefix+"/"+MgapConstants.J2C_AD_ALIAS);
			String user = passwordCredential.getUserName();
			String password = new String(passwordCredential.getPassword());
			logger.info("user:"+user);
			logger.info("password:"+password);
		model.addAttribute("message", "J2C Auth alias retrieved successfully");
		}catch(Exception ex){
			logger.error("Failed to get AD password",ex);
			model.addAttribute("message", "Failed to retreive J2C Auth alias");
		}
		
		return new ModelAndView("test");
	}
	
	@RequestMapping(value = "/testex", method=RequestMethod.POST)
	public ModelAndView testex(ModelMap model){
		testService.setGatewayDetails();
		logger.info("Test Exception Failed");
		model.addAttribute("message", "Test Transaction Failed");
		return new ModelAndView("test");		
	}
	
	@RequestMapping(value = "/testtx", method=RequestMethod.POST)
	public ModelAndView testtx(ModelMap model){
		testAdService.setUser();
		logger.info("Test Transaction Failed");
		model.addAttribute("message", "Test Transaction Failed");
		return new ModelAndView("test");		
	}	
	
	@RequestMapping(value = "/testcache", method=RequestMethod.POST)
	public ModelAndView getcache(ModelMap model){
		DistributedMap cacheObject = null;
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			cacheObject = (DistributedMap) ctx.lookup("cache/TestCache");
			String test = (String) cacheObject.get("test");
			if(test==null){
				logger.info("Reloading Cache...");
				cnt++; //in actual you will load from database
				cacheObject.setTimeToLive(60);
				cacheObject.put("test", "Cache"+cnt);
				test = (String) cacheObject.get("test");
			}
			logger.info("Test Caching succeeded : "+test);
			model.addAttribute("message", "Test Caching succeeded");		      
		} catch (NamingException e) {
			logger.error("Test Caching failed",e);
			model.addAttribute("message", "Test Caching failed");		      
		}
		return new ModelAndView("test");		
	}	
	
	@RequestMapping(value = "/invalidcache", method=RequestMethod.POST)
	public ModelAndView invalidateCache(ModelMap model){
		DistributedMap cacheObject = null;
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			cacheObject = (DistributedMap) ctx.lookup("cache/TestCache");
			String test = (String) cacheObject.get("test");
			if(test!=null){
				cacheObject.invalidate("test");
			}
			logger.info("Invalidate Caching succeeded...");
			model.addAttribute("message", "Invalidate Caching succeeded");		      
		} catch (NamingException e) {
			logger.error("Invalidate Caching failed",e);
			model.addAttribute("message", "Invalidate Caching failed");		      
		}
		return new ModelAndView("test");		
	}		
	
	@RequestMapping(value = "/testprop", method=RequestMethod.POST)
	public ModelAndView testprop(ModelMap model){
		try {
			logger.info("test.key:"+propertiesConfiguration.getString("test.key"));
			logger.info("Test Properties succeeded");
			model.addAttribute("message", "Test Properties succeeded");		      
		} catch (Exception e) {
			logger.error("Test Properties failed",e);
			model.addAttribute("message", "Test Properties failed");		      
		}
		return new ModelAndView("test");		
	}	
	
	@RequestMapping(value = "/testget", method=RequestMethod.POST)
	public ModelAndView testget(ModelMap model){
		try{
            URL url = new URL(callbackUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept", "application/xml");
 
            logger.info("Respone Code:"+conn.getResponseCode());
 
            /*BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String apiOutput = br.readLine();
            System.out.println(apiOutput);
            conn.disconnect();
 
            JAXBContext jaxbContext = JAXBContext.newInstance(Token.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Token token = (Token) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
 
            System.out.println(token.token);*/
			logger.info("Test GET succeeded");
			model.addAttribute("message", "Test GET succeeded");
		}catch(Exception ex){
			logger.error("Test GET failed",ex);
			model.addAttribute("message", "Test GET failed");
		}
		return new ModelAndView("test");
	}	
	
	@RequestMapping(value = "/testput", method=RequestMethod.POST)
	public ModelAndView testput(ModelMap model){
		try{

			logger.info("Test PUT succeeded");
			model.addAttribute("message", "Test PUT succeeded");
		}catch(Exception ex){
			logger.error("Test PUT failed",ex);
			model.addAttribute("message", "Test PUT failed");
		}
		return new ModelAndView("test");
	}	
	
	@RequestMapping(value = "/testhash", method=RequestMethod.POST)
	public ModelAndView testhash(@RequestParam("hash") String hash,@RequestParam("sensitive") String sensitive, ModelMap model){
		try{
			logger.info("hash:"+hash);
			logger.info("sensitive:"+sensitive);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] ba = sha.digest(sensitive.getBytes("UTF-8"));
			StringBuffer generatedHash = new StringBuffer();
			for(int i=0;i<ba.length;i++){
				generatedHash.append(HEX_TABLE[(ba[i]  >> 4)& 0xf]);
				generatedHash.append(HEX_TABLE[ba[i]& 0xf]);
			}
			logger.info("generatedHash:"+generatedHash);
			if(generatedHash.toString().equals(hash)){
				logger.info("Test HASH succeeded");
				model.addAttribute("message", "Test HASH succeeded");
			}
			else{
				logger.info("Test HASH Invalid");
				model.addAttribute("message", "Test HASH invalid");
			}
			
		}catch(Exception ex){
			logger.error("Test HASH failed",ex);
			model.addAttribute("message", "Test HASH failed");
		}
		return new ModelAndView("test");
	}	
	
	@RequestMapping(value = "/testnode", method=RequestMethod.POST)
	public ModelAndView testnode(ModelMap model){
		try{
			logger.info("Current Node Name:"+MgapUtil.getNodeName());
			logger.info("Test NODE succeeded");
			model.addAttribute("message", "Test NODE succeeded");
		}catch(Exception ex){
			logger.error("Test NODE failed",ex);
			model.addAttribute("message", "Test NODE failed");
		}
		return new ModelAndView("test");
	}	
	
	@RequestMapping(value = "/testajax", method=RequestMethod.GET)
	public @ResponseBody String testajax(){
		try{
			logger.info("Test AJAX succeeded");
			return "Test AJAX succeeded";
			//model.addAttribute("message", "Test AJAX succeeded");
		}catch(Exception ex){
			logger.error("Test AJAX failed",ex);
			return "failed";
			//model.addAttribute("message", "Test AJAX failed");
		}
		//return new ModelAndView("test");
	}	
	
}
