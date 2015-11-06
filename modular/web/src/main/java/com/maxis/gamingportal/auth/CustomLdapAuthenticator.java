package com.maxis.gamingportal.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.maxis.gamingportal.pojo.TestAdBean;
import com.maxis.gamingportal.service.TestAdService;

@Component
public class CustomLdapAuthenticator implements AuthenticationProvider  {
	
	@Autowired
	TestAdService testAdService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		//code for remember me option here. simply return new UsernamePasswordAuthenticationToken(name, "XXXXXXXX", grantedAuths); 
		try{
			TestAdBean bean =  testAdService.getUser();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        //Do actual bind of username and password here with LDAP or Database based on your requirement
        if (name.equals("admin") && password.equals("system")) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            //Extract role from LDAP or DB based on your requirement
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
