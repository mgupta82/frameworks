package com.basic.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ThreadContext {
	
	static Logger logger = LoggerFactory.createLogger();
	
	static DataSource dataSource;
	
	static{
		logger.log(Level.INFO, "Getting Data Source");
		try{
			//InitialContext ic = new InitialContext();
			//dataSource = (DataSource)ic.lookup("java:comp/env/jdbc/train");
			InputStream fis = ThreadContext.class.getClassLoader().getResourceAsStream("db.properties");
			Properties props = new Properties();
			props.load(fis);
			MysqlDataSource ds =  new MysqlDataSource();
			ds.setURL(props.getProperty("MYSQL_DB_URL"));
			ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));	
			dataSource = ds;
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
	
	private static ThreadLocal<Connection> connection = new ThreadLocal<Connection>(){
		@Override protected Connection initialValue(){
			try{
				return dataSource.getConnection();
			}catch (Exception ex){
				ex.printStackTrace();
			}
			return null;
		}
	};
	
	public static Connection getConnection(){
		try{
		if(connection.get().isClosed())
			connection.set(dataSource.getConnection());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return connection.get();
	}
	
	public static void beginTransaction(){
		try{
			getConnection().setAutoCommit(false);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	public static void endTransaction(){
		try{
			getConnection().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void rollbackTransaction(){
		try{
			getConnection().rollback();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
