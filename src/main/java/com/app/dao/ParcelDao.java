package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.model.Admin;
import com.app.model.Collection;
import com.app.model.Parcel;

public class ParcelDao {

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
	
	public static List<Parcel>trackParcelOwner(String email) {
		
		List<Parcel>list = new ArrayList<Parcel>();
		
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(
					"select * from parcel right join owner\r\n"
					+ "on parcel.ownerID = owner.ownerID \r\n"
					+ "where owner.email=? and parcel.status='Not Collected'");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Parcel parcel = new Parcel();
				
				parcel.setParcelID(rs.getString("parcelID"));
				parcel.setOwnerID(rs.getString("ownerID"));
				parcel.setParcelName(rs.getString("parcelName"));
				parcel.setDateReceived(rs.getString("dateReceived"));
				parcel.setStatus(rs.getString("status"));
				parcel.setDateCollected(rs.getString("dateCollected"));
				list.add(parcel);//setup the list 
			}
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return list;
	}
	
	//get user email and return data for view collection history
	public static List<Parcel>viewCollectionHistory(String email){
			
			List<Parcel>list = new ArrayList<Parcel>();
		
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("select * from parcel right join owner on parcel.ownerID = owner.ownerID\r\n"
						+ "WHERE status = 'Collected' and email = ?");
				pst.setString(1, email);
				
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					Parcel parcel = new Parcel();
					parcel.setOwnerID(rs.getString("ownerID"));
					parcel.setParcelID(rs.getString("parcel.parcelID"));
					parcel.setParcelName(rs.getString("parcel.parcelName"));
					parcel.setStatus(rs.getString("parcel.status"));
					parcel.setDateReceived(rs.getString("dateReceived"));
					parcel.setDateCollected(rs.getString("dateCollected"));
					list.add(parcel);
				}
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return list;
		}

	public static List<Parcel>trackParcelCustomer(String email) {
		
		//hold lists of parcels 
		List<Parcel>list = new ArrayList<Parcel>();
		
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(
					"select * from parcel right join owner\r\n"
					+ "on parcel.ownerID = owner.ownerID \r\n"
					+ "where owner.email=? and parcel.status='Not Collected'");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Parcel u = new Parcel();
				//set user variable from the value we get from the query 
				//user.pID = getString named pID
				u.setParcelID(rs.getString("parcelID"));
				u.setOwnerID(rs.getString("ownerID"));
				u.setDateReceived(rs.getString("dateReceived"));
				u.setStatus(rs.getString("status"));
				u.setDateCollected(rs.getString("dateCollected"));
				list.add(u);//setup the list 
			}
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return list;
	}

	//return list of all parcels 
	public static List<Parcel>adminParcelSummary(){
			
			List<Parcel>list = new ArrayList<Parcel>();
		
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT * FROM parcel");
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					
					Parcel u = new Parcel();
					u.setParcelID(rs.getString("parcelID"));
					u.setParcelName(rs.getString("parcelName"));
					u.setOwnerID(rs.getString("ownerID"));
					u.setStatus(rs.getString("status"));
					u.setDateReceived(rs.getString("dateReceived"));
					u.setDateCollected(rs.getString("dateCollected"));
					list.add(u);
				}
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return list;
		}

	//get pID and return parcel data 
	public static Parcel trackParcel(String parcelID) {
	    Parcel u = null;
	    try (Connection con = getConnection();
	         PreparedStatement pst = con.prepareStatement("SELECT * FROM parcel WHERE parcelID=?")) {
	        pst.setString(1, parcelID);
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                u = new Parcel();
	                u.setParcelID(rs.getString("parcelID"));
	                u.setParcelName(rs.getString("parcelName"));
	                u.setOwnerID(rs.getString("ownerID"));
	                u.setStatus(rs.getString("status"));
	                u.setDateReceived(rs.getString("dateReceived"));
	                u.setDateCollected(rs.getString("dateCollected"));
	            }
	        }
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    return u;
	}	
	
		
	//get user form data and return status if update successful
	public static int updateParcel (Parcel u) {
			
			int status=0;
			
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("UPDATE parcel SET parcelID=?, ownerID=?, dateReceived=?, status=?, dateCollected=? WHERE parcelID=?");
				pst.setString(1, u.getParcelID());
				pst.setString(2, u.getOwnerID());
				pst.setString(3, u.getDateReceived());
				pst.setString(4, u.getStatus());
				pst.setString(5, u.getDateCollected());
				pst.setString(6, u.getParcelID());
				
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return status;
		}
		
	//get user form and return status if update successful
	public static int deleteParcel (String parcelID) {
			
			int status=0;
			
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("DELETE FROM parcel WHERE parcelID=?");
				pst.setString(1, parcelID);
			
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return status;
		}
		
	//get user form and return if add successful
	public static int addParcel (Parcel u) {
			
			int status = 0;
			
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("INSERT INTO parcel (parcelID, ownerID, parcelName, dateReceived, status) VALUES (?,?,?,?,?)");
				pst.setString(1, u.getParcelID());
				pst.setString(2, u.getOwnerID());
				pst.setString(3, u.getParcelName());
				pst.setString(4, u.getDateReceived());
				pst.setString(5, u.getStatus());
				
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				
				System.out.println(ex);
				
			}
			return status;
		}

	//get input pID and return list of parcels data
	public static List<Parcel>searchParcel (String parcelID) {
			
			List<Parcel>list = new ArrayList<Parcel>();
			
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("select * from parcel where parcelID=?");
				pst.setString(1, parcelID);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					Parcel u = new Parcel();
					u.setParcelID(rs.getString("parcelID"));
					u.setOwnerID(rs.getString("ownerID"));
					u.setParcelName(rs.getString("parcelName"));
					u.setDateReceived(rs.getString("dateReceived"));
					u.setStatus(rs.getString("status"));
					u.setDateCollected(rs.getString("dateCollected"));
					list.add(u);
				}
					
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return list;
		}
		
	
	public static Collection viewParcelSummary() { 
		Collection u = new Collection();
	    
	    try {
	        Connection con = getConnection();
	        PreparedStatement pst = con.prepareStatement("SELECT DISTINCT " +
	                "(SELECT COUNT(status) FROM parcel WHERE status='Collected') AS status, " +
	                "(SELECT COUNT(status) FROM parcel WHERE status='Not Collected') AS statusNo, " +
	                "COUNT(status) AS statusAdd " +
	                "FROM parcel");
	        ResultSet rs = pst.executeQuery();
	        
	        while (rs.next()) {
	            u.setStatus(rs.getString("status"));
	            u.setStatusNo(rs.getString("statusNo"));
	            u.setStatusAdd(rs.getString("statusAdd"));
	        }
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    
	    return u;
	}
	
	public static void updateParcelStatusToScheduled(String parcelIdsString) {
        String[] parcelIds = parcelIdsString.split(",");
        String sql = "UPDATE parcel SET status = 'Scheduled' WHERE parcelID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            for (String parcelId : parcelIds) {
                preparedStatement.setString(1, parcelId.trim());
                preparedStatement.addBatch();
            }
            
            preparedStatement.executeBatch();
        } catch (SQLException e) {
        	 System.out.println(e);
        }
    }
	
	public static void updateParcelStatusToNotCollected(String parcelIdsString) {
        String[] parcelIds = parcelIdsString.split(",");
        String sql = "UPDATE parcel SET status = 'Not Collected' WHERE parcelID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            for (String parcelId : parcelIds) {
                preparedStatement.setString(1, parcelId.trim());
                preparedStatement.addBatch();
            }
            
            preparedStatement.executeBatch();
        } catch (SQLException e) {
        	 System.out.println(e);
        }
    }

}
