package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.model.Collection;
import com.app.model.Schedule;

public class ScheduleDao {
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

	//get user email, return user schedules
		public static List<Schedule>viewScheduleCustomer(String email){
			
			List<Schedule>list = new ArrayList<Schedule>();
		
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(""
						+ "select distinct scheduleID, scheduledDate, parcels, scheduledTime from schedule \r\n"
						+ "right join parcel on schedule.ownerID = parcel.ownerID \r\n"
						+ "right join owner on schedule.ownerID = owner.ownerID\r\n"
						+ "where owner.email=? and status!='Collected'");
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					Schedule schedule = new Schedule();
					schedule.setScheduleID(rs.getInt("scheduleID"));
					schedule.setParcels(rs.getString("schedule.parcels"));
					schedule.setScheduledDate(rs.getString("schedule.scheduledDate"));
					schedule.setScheduledTime(rs.getString("schedule.scheduledTime"));
					list.add(schedule);
				}
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return list;
		}
		
	//get data from schedule form, pass status if successful 
		public static int addSchedule (Schedule u) {
			
			int status = 0;
			
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(""
						+ "insert into schedule (ownerID, scheduledDate, scheduledTime, parcels)\r\n"
						+ "VALUES (?,?,?,?);");
				pst.setString(1, u.getOwnerID());
				pst.setString(2, u.getScheduledDate());
				pst.setString(3, u.getScheduledTime());
				pst.setString(4, u.getParcels());
				
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				
				System.out.println(ex);
				
			}
			return status;
		}
		
	//get form information, and return status if update successful
		public static int updateSchedule (Schedule u) {
			
			int status=0;
			
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("update schedule set scheduledDate=?, scheduledTime=? where scheduleID=?;");
				pst.setString(1, u.getScheduledDate());
				pst.setString(2, u.getScheduledTime());
				pst.setInt(3, u.getScheduleID());

				
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return status;
		}

	//get scheduleID and return status if successful 
		public static int deleteSchedule (String scheduleID) {
			
			int status=0;
			
			try {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("delete from schedule where scheduleID=?");
				pst.setString(1, scheduleID);
			
				status = pst.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return status;
		}

	//get scheduleID and return the schedule data  
		public static Schedule searchScheduleOwner(String scheduleID) {

			Schedule schedule = null;

			try {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("select * from schedule where scheduleID=?");
				pst.setString(1, scheduleID);
				ResultSet rs = pst.executeQuery();
				schedule = new Schedule();
				while(rs.next()) {
					schedule.setScheduleID(rs.getInt("scheduledID"));
					schedule.setOwnerID(rs.getString("ownerID"));
					schedule.setScheduledDate(rs.getString("scheduledDate"));
					schedule.setScheduledTime(rs.getString("scheduledTime"));
				}

			}catch(Exception ex) {
				System.out.println(ex);
			}
			return schedule;
		}
		
		//get scheduleID and return the schedule data  
		public static Schedule viewScheduleCustomerEdit(int scheduleID) {

			Schedule schedule = null;

			try {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("select * from schedule where scheduleID=?");
				pst.setInt(1, scheduleID);
				
				ResultSet rs = pst.executeQuery();
				schedule = new Schedule();
				while(rs.next()) {
					schedule.setScheduleID(rs.getInt("scheduleID"));
					schedule.setOwnerID(rs.getString("ownerID"));
					schedule.setParcels(rs.getString("parcels"));
					schedule.setScheduledDate(rs.getString("scheduledDate"));
					schedule.setScheduledTime(rs.getString("scheduledTime"));
				}

			}catch(Exception ex) {
				System.out.println(ex);
			}
			return schedule;
		}
		
		//return list of schedule created by the users
		public static List<Collection>viewSchedule(){
			
			List<Collection>list = new ArrayList<Collection>();
		
			try {
				
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT p.parcelID, p.parcelName, p.ownerID, MAX(s.scheduledDate) AS latestScheduledDate, MAX(s.scheduledTime) AS latestScheduledTime, p.status\r\n"
						+ "FROM parcel AS p\r\n"
						+ "LEFT JOIN schedule AS s ON p.ownerID = s.ownerID\r\n"
						+ "WHERE p.status = 'Scheduled'\r\n"
						+ "GROUP BY p.parcelID, p.parcelName, p.status;\r\n"
						+ "");
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					
					Collection u = new Collection();
					u.setScheduledDate(rs.getString("latestScheduledDate"));
					u.setScheduledTime(rs.getString("latestScheduledTime"));
					u.setOwnerID(rs.getString("ownerID"));
					u.setParcelName(rs.getString("parcelName"));
					u.setParcelID(rs.getString("parcelID"));
					u.setStatus(rs.getString("status"));
					list.add(u);
				}
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return list;
		}

		public static List<String> getAvailableSlots(String scheduledDate) {
	        List<String> bookedSlots = new ArrayList<>();
	        String sql = "SELECT scheduledTime FROM schedule WHERE scheduledDate = ?";

	        try (Connection con = getConnection();
	             PreparedStatement statement = con.prepareStatement(sql)) {

	            statement.setString(1, scheduledDate);
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {
	                bookedSlots.add(resultSet.getString("scheduledTime"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return bookedSlots;
	    }
}
