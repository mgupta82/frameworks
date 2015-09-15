package com.basic.dao;

public interface TestDao {
	
	@DbQuery(query = "select id,name from txn" )
	public String getData();
	
	@DbQuery(query = "insert into txn(id,name) values(2,'Two');" )
	public void setData();

}
