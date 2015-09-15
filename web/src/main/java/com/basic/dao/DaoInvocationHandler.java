package com.basic.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.basic.common.ThreadContext;


public class DaoInvocationHandler implements InvocationHandler {
	
	public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
		DbQuery dbQuery = method.getAnnotation(DbQuery.class);
		Connection connection = ThreadContext.getConnection();
		PreparedStatement ps = connection.prepareStatement(dbQuery.query());
		if(args!=null){
			for(int i=0;i<args.length;i++){
				if(args[i] instanceof Integer)
					ps.setInt(i+1, ((Integer)args[i]).intValue());
				if(args[i] instanceof String){
					ps.setString(i+1, (String)args[i]);
				}
			}
		}
		if(method.getReturnType().equals(Void.TYPE)){
			ps.executeUpdate();
		}else{
			ps.executeQuery();
		}
		ps.close();
		if(connection.getAutoCommit()){
			connection.close();
		}
		return dbQuery.query();
		
	}

}
