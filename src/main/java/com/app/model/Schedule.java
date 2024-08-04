package com.app.model;

public class Schedule {
	private int scheduleID;
	private String ownerID;
	private String scheduledDate;
	private String scheduledTime;
	private String parcels;
	
	public String getParcels() {
		return parcels;
	}
	public void setParcels(String parcels) {
		this.parcels = parcels;
	}
	public int getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public String getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	

}
