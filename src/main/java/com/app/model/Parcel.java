package com.app.model;

public class Parcel {
	private String parcelID;
	private String ownerID;
	private String parcelName;
	private String status;
	private String dateReceived;
	private String dateCollected;
	
	public String getParcelID() {
		return parcelID;
	}
	public void setParcelID(String parcelID) {
		this.parcelID = parcelID;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getParcelName() {
		return parcelName;
	}
	public void setParcelName(String parcelName) {
		this.parcelName = parcelName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateReceived() {
		return dateReceived;
	}
	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}
	public String getDateCollected() {
		return dateCollected;
	}
	public void setDateCollected(String dateCollected) {
		this.dateCollected = dateCollected;
	}
	
	
}
