package com.basic.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Statement;

import com.basic.common.ThreadContext;

public class DaoInvocationHandler implements InvocationHandler {
	
	public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
		DbQuery dbQuery = method.getAnnotation(DbQuery.class);
		Connection connection = ThreadContext.getConnection();
		Statement stmt = connection.createStatement();
		//statement.executeQuery(dbQuery.query());
		if(method.getReturnType().equals(Void.TYPE)){
			stmt.executeUpdate(dbQuery.query());
		}else{
			stmt.executeQuery(dbQuery.query());
		}
		stmt.close();
		if(connection.getAutoCommit()){
			connection.close();
		}
		return dbQuery.query();
		
	}

}
