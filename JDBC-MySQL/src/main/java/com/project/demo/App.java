package com.project.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.project.demo.utility.DbConnection;

public class App {
	public static void main(String[] args) {
		// createTable();
		// insertRecord();
		// updateRecord();
		// deleteRecord();
		 fetchAllRecord();
		// searchRecord();
	}

	private static void searchRecord() {
		try {

			// step2: Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step3: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6534", "root", "rootroot");
			if (con != null) {
				System.out.println("Connected...");
			} else {
				System.out.println("Not connected...");
			}

			// Step4: Prepare statement to execute queries
			PreparedStatement ps = con.prepareStatement("select * from person where id=?");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter person's Id");
			int id = sc.nextInt();
			ps.setInt(1, id);

			// Step5: Execute the query
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_2 = rs.getInt("id");
				String name = rs.getString("name");
				String city = rs.getString("city");
				System.out.println("Id = " + id_2 + " Name = " + name + " City = " + city);
			}

			// Step6: close the connection
			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static void fetchAllRecord() {
		try {

			// step2: Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded..");

			// Step3: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6534", "root", "rootroot");
			if (con != null) {
				System.out.println("Connected...");
			} else {
				System.out.println("Not connected...");
			}

			// Step4: Prepare statement to execute queries
			PreparedStatement ps = con.prepareStatement("select * from person", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			// Step5: Execute the query
			ResultSet rs = ps.executeQuery();
			rs.absolute(6);
			while (rs.previous()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String city = rs.getString("city");
				System.out.println("Id = " + id + " Name = " + name + " City = " + city);
			}

			// Step6: close the connection
			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void deleteRecord() {

		try {
			// step2: Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded..");

			// Step3: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6534", "root", "rootroot");
			if (con != null) {
				System.out.println("Connected...");
			} else {
				System.out.println("Not connected...");
			}

			// Step4: Prepare statement to execute sql queries
			PreparedStatement ps = con.prepareStatement("delete from person where id=?");
			Scanner s = new Scanner(System.in);
			System.out.println("Enter person Id");
			int id = s.nextInt();
			ps.setInt(1, id);

			// Step5: Execute the query
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println(rows + " Record has been deleted successfully");
			} else {
				System.out.println("Something went wrong to delete record..");
			}

			// Step6: close the connection
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void updateRecord() {
		try {

			// step2: Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded..");

			// Step3: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6534", "root", "rootroot");
			if (con != null) {
				System.out.println("Connected...");
			} else {
				System.out.println("Not connected...");
			}

			// Step4: Prepare statement to execute sql queries
			PreparedStatement ps = con.prepareStatement("update person set city=? where id=?");
			Scanner s = new Scanner(System.in);

			System.out.println("Enter person Id");
			int id = s.nextInt();
			ps.setInt(2, id);

			System.out.println("Enter city name");
			String city = s.next();
			ps.setString(1, city);

			// Step5: Execute the query
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println(rows + " Record has been updated successfully");
			} else {
				System.out.println("Something went wrong to update record..");
			}

			// Step6: close the connection
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void insertRecord() {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = DbConnection.establishConnection();

			// Step4: Prepare statement to execute sql queries
			ps = con.prepareStatement("insert into person(id,name,city) values(?,?,?)");

			Scanner s = new Scanner(System.in);

			System.out.println("Enter person Id");
			int id = s.nextInt();
			ps.setInt(1, id);

			System.out.println("Enter person name");
			String name = s.next();
			ps.setString(2, name);

			System.out.println("Enter city name");
			String city = s.next();
			ps.setString(3, city);

			// Step5: Execute the query
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println(rows + " Record has been inserted successfully");
			} else {
				System.out.println("Something went wrong to insert record..");
			}

			// Step6: close the connection
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null && con != null) {
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static void createTable() {
		try {

			// step2: Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded..");

			// Step3: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6534", "root", "rootroot");
			if (con != null) {
				System.out.println("Connected...");
			} else {
				System.out.println("Not connected...");
			}

			// Step4: Prepare statement to execute queries
			PreparedStatement ps = con.prepareStatement(
					"create table person(id int primary key, name varchar(30) not null, city varchar(40))");

			// Step5: Execute the query
			int rows = ps.executeUpdate();

			// Step6: close connection
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}





