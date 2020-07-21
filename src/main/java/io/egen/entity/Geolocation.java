package io.egen.entity;

import java.util.Date;

public class Geolocation {

	private double latitude;
	private double longitude;
	private Date timestamp;
	
	public Geolocation() {
		
	}
	/**
	 * @param latitude
	 * @param longitude
	 * @param timestamp
	 */
	public Geolocation(double latitude, double longitude, Date timestamp) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Geolocation [latitude=" + latitude + ", longitude=" + longitude + ", timestamp=" + timestamp + "]";
	}
	
	
}
