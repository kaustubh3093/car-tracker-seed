package io.egen.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alert {

	@Id
	private String alertID;
	
	private String vin;
	private String message;
	private String priority;
	private Date alertTime;
	private String make;
	private String model;
	
	public Alert() {
		alertID = UUID.randomUUID().toString();
	}

	
	/**
	 * @param alertID
	 * @param message
	 * @param priority
	 * @param alertTime
	 * @param make
	 * @param model
	 */
	public Alert(String alertID, String message, String priority, Date alertTime, String make, String model) {
		this.alertID = alertID;
		this.message = message;
		this.priority = priority;
		this.alertTime = alertTime;
		this.make = make;
		this.model = model;
	}


	/**
	 * @return the alertID
	 */
	public String getAlertID() {
		return alertID;
	}

	/**
	 * @param alertID the alertID to set
	 */
	public void setAlertID(String alertID) {
		this.alertID = alertID;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the alertTime
	 */
	public Date getAlertTime() {
		return alertTime;
	}

	/**
	 * @param alertTime the alertTime to set
	 */
	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}

	
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Alert [alertID=" + alertID + ", vin=" + vin + ", message=" + message + ", priority=" + priority
				+ ", alertTime=" + alertTime + ", make=" + make + ", model=" + model + "]";
	}

	
	
	
}
