package com.basic.dao;

import java.lang.reflect.Proxy;

public class DaoFactory {
	
	public <T> T getDaoImpl(Class<T> daoClass){
		return (T) (Proxy.newProxyInstance(daoClass.getClassLoader()
				, new Class[]{daoClass}
				, new DaoInvocationHandler()));
	}

}
