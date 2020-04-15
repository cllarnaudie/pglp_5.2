package main.java;
//package com.developpez.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostDerby {

	private static String dburl = "jdbc:derby:test; create = true"; 

	private static String user = "user";

	private static String password = "password";

	private static Connection connect;

	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(dburl, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}	

}
