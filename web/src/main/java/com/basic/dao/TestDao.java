package com.basic.dao;

public interface TestDao {
	
	@DbQuery(query = "select id,name from txn where id=?" )
	public String getData(Integer id);
	
	@DbQuery(query = "insert into txn(id,name) values(?,?);" )
	public void setData(Integer id, String name);

}
