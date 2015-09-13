package com.basic.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DaoInvocationHandler implements InvocationHandler {
	
	public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
		DbQuery dbQuery = method.getAnnotation(DbQuery.class);
		return dbQuery.query();
		
	}

}
