package com.thewickettk.betamode5812.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.thewickettk.betamode5812.Main;

public class Connection {
	public static java.sql.Connection connection;
	public static String ip;
	public static String port;
	public static String username;
	public static String password;
	public static String database;
	public static Boolean isConnected;
	
	public static void mysqlConnect(){
		ip = Main.config.getString("mysql.host");	
		port = Main.config.getString("mysql.port");
		database = Main.config.getString("mysql.database");
		username = Main.config.getString("mysql.username");
		password = Main.config.getString("mysql.password");
    	try {
	    if (connection != null && !connection.isClosed()) {
	        return;
	    }
	        if (connection != null && !connection.isClosed()) {
	            return;
	        }
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+database, username, password);
	        isConnected = true;
    	}catch(Exception e){
    	  System.err.println("(BETAMode) Could not connect to database!");
    	  e.printStackTrace();
    	}
	}
	public static void mysqlClose(){
		if (isConnected){
			try {
				connection.close();
				isConnected = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
