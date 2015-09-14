package com.basic.service;

import com.basic.dao.TestDao;

public class TestServiceImpl extends Service implements TestService {

	@Override
	public String getData() {
		return getDao(TestDao.class).getData();
	}

	@Override
	public void setData() {
		getDao(TestDao.class).setData();
	}

}
