package com.project.demo.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	private static Connection con;
	
	public static Connection establishConnection() {
		try {			
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6534", "root", "rootroot");
    		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) {
		Connection connection = DbConnection.establishConnection();
		if(connection != null) {
			System.out.println("Connected...");
		}else {
			System.out.println("Not Connected...");
		}
	}

}
