package com.basic.service;

import com.basic.dao.DaoFactory;

public class Service {
	
	static final DaoFactory daoFactory = new DaoFactory();
	
	public <T> T getDao(Class<T> daoClass){
		return daoFactory.getDaoImpl(daoClass);
	}

}
