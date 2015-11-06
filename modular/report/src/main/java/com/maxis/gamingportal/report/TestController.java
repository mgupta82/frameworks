package com.maxis.gamingportal.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.maxis.gamingportal.json.DataTableBean;
import com.maxis.gamingportal.json.DataTableListResponse;
import com.maxis.gamingportal.pojo.TestAdBean;
import com.maxis.gamingportal.pojo.TestBean;
import com.maxis.gamingportal.service.TestAdService;
import com.maxis.gamingportal.service.TestService;


@Controller
public class TestController {
	
	@Autowired
	TestService testService;
	
	@Autowired
	TestAdService testAdService;
	
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
			TestBean gatewayMasterBean = testService.getDetails("bubanc");
			logger.info("gatewayMasterBean:"+gatewayMasterBean);
			model.addAttribute("message", "DB connection succeeded");
		}catch(Exception ex){
			logger.error("Failed to connect to DB");
			ex.printStackTrace();
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
			logger.error("Failed to connect to AD");
			ex.printStackTrace();
			model.addAttribute("message", "Failed to connect to AD");
		}
		return new ModelAndView("test");
	}	

	@RequestMapping(value="/testpwd", method = RequestMethod.POST)
	public ModelAndView testPassword(ModelMap model){
		try{
		//Get Node Name
		InitialContext ic = new javax.naming.InitialContext();
		String nodeName = ic.lookup("thisNode/nodename").toString();			
		//Create alias name
		Map<String, String> map = new HashMap<String,String>();
		map.put(Constants.MAPPING_ALIAS, nodeName+"/bubanc");
		//Login with default context
		CallbackHandler callbackHandler = WSMappingCallbackHandlerFactory.getInstance().getCallbackHandler(map, null);
		LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
		loginContext.login();
		//Get credentials
		Subject subject = loginContext.getSubject();
		Set credentials = subject.getPrivateCredentials();
		PasswordCredential passwordCredential = (PasswordCredential) credentials.iterator().next();
		String user = passwordCredential.getUserName();
		String password = new String(passwordCredential.getPassword());
		logger.info("user:"+user);
		//logger.info("password:"+password);
		model.addAttribute("message", "J2C Auth alias retrieved successfully");
		}catch(Exception ex){
			logger.error("Failed to get bubanc password");
			model.addAttribute("message", "Failed to retreive J2C Auth alias");
			ex.printStackTrace();
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

	@RequestMapping(value = "/testtab", method=RequestMethod.POST)
	public ModelAndView testtab(ModelMap model){
		try {
			
			logger.info("Test Table succeeded");
			model.addAttribute("message", "Test Table succeeded");		      
		} catch (Exception e) {
			logger.info("Test Table failed");
			model.addAttribute("message", "Test Table failed");		      
	        e.printStackTrace();
		}
		return new ModelAndView("datatable");		
	}	
	
	@RequestMapping(value = "/getData",headers = "Accept=*/*",produces = "application/json", method=RequestMethod.POST)
    @ResponseBody
    public DataTableListResponse getData(@RequestParam int jtStartIndex, @RequestParam int jtPageSize) {
		DataTableListResponse jstr;
        List<DataTableBean> dataList;
        try {
        	
            int dataCount = 95;
            dataList = getDataList(jtStartIndex,jtPageSize);
            jstr = new DataTableListResponse("OK",dataList,dataCount);
        } catch (Exception e) {
            jstr = new DataTableListResponse("ERROR",e.getMessage());
        }
        return jstr;
    }	
	
	public List<DataTableBean> getDataList(int jtStartIndex,int jtPageSize){
		List<DataTableBean> dataList = new ArrayList<DataTableBean>();
		for(int i=0;i<jtPageSize;i++){
			DataTableBean dataTableBean = new DataTableBean();
			dataTableBean.setEmpId(Integer.toString(i+jtStartIndex));
			dataTableBean.setEmpName("Emp-"+Integer.toString(i+jtStartIndex));
			dataTableBean.setEmpDesignation("JL-"+Integer.toString(i+jtStartIndex));
			dataList.add(dataTableBean);
		}
		return dataList;
	}
	
}
