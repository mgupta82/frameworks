package com.basic.service;

import com.basic.dao.TestDao;

public class TestServiceImpl extends Service implements TestService {

	@Override
	public String getData(Integer id) {
		return getDao(TestDao.class).getData(id);
	}

	@Override
	public void setData(Integer id,String name) {
		getDao(TestDao.class).setData(id,name);
	}

}
