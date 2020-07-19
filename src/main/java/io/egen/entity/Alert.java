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
	
	public Alert() {
		alertID = UUID.randomUUID().toString();
	}

	/**
	 * @param vin
	 * @param message
	 * @param priority
	 * @param alertTime
	 */
	public Alert(String vin, String message, String priority, Date alertTime) {
		this.vin = vin;
		this.message = message;
		this.priority = priority;
		this.alertTime = alertTime;
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

	@Override
	public String toString() {
		return "Alert [alertID=" + alertID + ", vin=" + vin + ", message=" + message + ", priority=" + priority
				+ ", alertTime=" + alertTime + "]";
	}
	
	
}
