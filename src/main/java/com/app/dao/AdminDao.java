package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.model.Admin;

public class AdminDao {
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parcels_db?serverTimezone=UTC", "root", "database");
			return con;

		}catch(Exception ex) {
		
		    ex.printStackTrace();  
		    return null;
		
		}
	}
	
	//get form data and return status
		public static int loginAdmin(Admin u) {
			
			int status = 0;
			
			try {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("select * from admin where email=? and password =? and role='Admin'");
				pst.setString(1, u.getEmail());
				pst.setString(2, u.getPassword());
				//execute the select query 
				ResultSet rs = pst.executeQuery();
				if(rs.next()) 
					status=1;
				con.close();
			}catch(Exception ex) {
				
				System.out.println(ex);
				
			}
			return status;
		}
	
	//get form data to store staff data and return status
		public static int addStaff (Admin u) {
			
			int status = 0;
			
			try {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(""
						+ "INSERT INTO admin (firstName, lastName, email, phoneNumber, password, role) "
						+ "VALUES (?,?,?,?,?,?)");
				pst.setString(1, u.getFirstName());
				pst.setString(2, u.getLastName());
				pst.setString(3, u.getEmail());
				pst.setString(4, u.getPhoneNumber());
				pst.setString(5, u.getPassword());
				pst.setString(6, u.getRole());
				
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				
				System.out.println(ex);
				
			}
			return status;
		}

	//return list of staffs
		public static List<Admin>viewStaffList(){
			
			List<Admin>list = new ArrayList<Admin>();
		
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT * FROM admin");
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					
					Admin u = new Admin();
					u.setAdminID(rs.getInt("adminID"));
					u.setFirstName(rs.getString("firstName"));
					u.setLastName(rs.getString("lastName"));
					u.setEmail(rs.getString("email"));
					u.setPhoneNumber(rs.getString("phoneNumber"));
					u.setRole(rs.getString("role"));
					list.add(u);
				}
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return list;
		}

	//get form data and return status if delete successful
		public static int deleteStaff (String adminID) {
			
			int status=0;
			
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("DELETE FROM admin WHERE adminID=?");
				pst.setString(1, adminID);
			
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return status;
		}

	//get form data and return status if update successful 
		public static int updateStaff (Admin u) {
			
			int status=0;
			
			try {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(""
						+ "update admin set firstName=?, lastName=?, email=?, "
						+ "password=?, phoneNumber=? WHERE adminID=?");
				pst.setString(1, u.getFirstName());
				pst.setString(2, u.getLastName());
				pst.setString(3, u.getEmail());
				pst.setString(4, u.getPassword());
				pst.setString(5, u.getPhoneNumber());
				pst.setInt(6, u.getAdminID());
				
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return status;
		}

	//get email return staff data 
		public static Admin staffListEdit(String adminID){  
			
			Admin u = null;
		
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT * FROM admin WHERE adminID=?");
				pst.setString(1, adminID);
				ResultSet rs = pst.executeQuery();
				u = new Admin();
				while(rs.next()) {
					u.setAdminID(rs.getInt("adminID"));
					u.setFirstName(rs.getString("firstName"));
					u.setLastName(rs.getString("lastName"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setPhoneNumber(rs.getString("phoneNumber"));
					u.setRole(rs.getString("role"));
				}
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return u;
		}

	//get form data and verify if staff exist
		public static int isStaffExist(Admin u) {
			
			int status = 0;
			
			try {
				
				Connection con = getConnection();
				Statement stmt=con.createStatement();
				PreparedStatement ps=con.prepareStatement("SELECT * FROM admin WHERE adminID=?");
				ps.setInt(1, u.getAdminID());
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					status=1;
				con.close();
					
				
			}catch(Exception ex) {
				
				System.out.println(ex);
				
			}
			return status;
		}

		public static int loginStaff(Admin u) {
			
			int status = 0;
			
			try {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("select * from admin where email=? and password =?");
				pst.setString(1, u.getEmail());
				pst.setString(2, u.getPassword());
				//execute the select query 
				ResultSet rs = pst.executeQuery();
				if(rs.next()) 
					status=1;
				con.close();
			}catch(Exception ex) {
				
				System.out.println(ex);
				
			}
			return status;
		}
	
	//get email and return staff data
		public static Admin viewStaff(String email){  
			
			Admin u = null;
		
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT * FROM admin WHERE email=?");
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				u = new Admin();
				while(rs.next()) {
					u.setAdminID(rs.getInt("adminID"));
					u.setFirstName(rs.getString("firstName"));
					u.setLastName(rs.getString("lastName"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setPhoneNumber(rs.getString("phoneNumber"));
					u.setPassword(rs.getString("password"));
					u.setRole(rs.getString("role"));
				}
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return u;
		}

	//get form data and return if update successful
		public static int updateStaffProfile(Admin u) {
			
			int status=0;
			
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("UPDATE admin SET firstName=?, lastName=?, email=?, phoneNumber=?, password=? WHERE adminID=?");
				pst.setString(1, u.getFirstName());
				pst.setString(2, u.getLastName());
				pst.setString(3, u.getEmail());
				pst.setString(4, u.getPhoneNumber());
				pst.setString(5, u.getPassword());
				pst.setInt(6, u.getAdminID());
				
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return status;
		}


}
