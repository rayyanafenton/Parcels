package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.model.Owner;

public class OwnerDao {
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
	
	public static int signUpOwner(Owner owner) {
	    int status = 0;
	    Connection con = null;
	    try {
	        con = getConnection();
	        if (con != null) {
				PreparedStatement pst = con.prepareStatement("INSERT INTO parcels_db.owner (firstName, lastName, email, password, phoneNumber) VALUES (?,?,?,?,?)");
	            pst.setString(1, owner.getFirstName());
	            pst.setString(2, owner.getLastName());
	            pst.setString(3, owner.getEmail());
	            pst.setString(4, owner.getPassword());
	            pst.setString(5, owner.getPhoneNumber());
	            System.out.println(pst);

	            status = pst.executeUpdate();
	        } else {
	            System.out.println("Connection to database failed.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();  
	    } finally {
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();  
	            }
	        }
	    }
	    return status;
	}
	
	public static int loginOwner(Owner owner) {
	    int status = 0;
	    Connection con = null;
	    try {
	        con = getConnection();
	        if (con != null) {
				PreparedStatement pst = con.prepareStatement("select * from owner where email=? and password =?");
				pst.setString(1, owner.getEmail());
				pst.setString(2, owner.getPassword());
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					status= 1;
				}
	        } else {
	            System.out.println("Connection to database failed.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();  
	    } finally {
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();  
	            }
	        }
	    }
	    return status;
	}
	
 	public static Owner viewProfile(String email){
			
			Owner owner = null;
		
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("select * from owner where email=?");
				pst.setString(1, email);
				
				ResultSet rs = pst.executeQuery();
				
				 if (rs.next()) {
			            owner = new Owner();
			            owner.setOwnerID(rs.getInt("ownerID"));
			            owner.setFirstName(rs.getString("firstName"));
			            owner.setLastName(rs.getString("lastName"));
			            owner.setEmail(rs.getString("email"));
			            owner.setPassword(rs.getString("password"));
			            owner.setPhoneNumber(rs.getString("phoneNumber"));
			      }
			
				 rs.close();
			     pst.close();
			     con.close();
			        
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return owner;
		}
	
	public static int updateProfile (Owner owner) {
			
			int status=0;
			
			try {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(""
						+ "update owner set firstName=?, lastName=?, email=?, "
						+ "password=?, phoneNumber=? WHERE ownerID=?");
				
				pst.setString(1, owner.getFirstName());
				pst.setString(2, owner.getLastName());
				pst.setString(3, owner.getEmail());
				pst.setString(4, owner.getPassword());
				pst.setString(5, owner.getPhoneNumber());
				pst.setInt(6, owner.getOwnerID());
				
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return status;
		}
}
