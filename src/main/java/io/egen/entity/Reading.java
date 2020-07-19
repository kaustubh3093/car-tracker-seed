package io.egen.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reading {

	@Id
	private String vehicleId;
	private String vin;
	private double latitude;
	private double longitude;
	private Date timestamp;
	private float fuelVolume;
	private int speed;
	private int engineHp;
	private boolean checkEngineLightOn;
	private boolean engineCoolantLow;
	private boolean cruiseControlOn;
	private int engineRpm;
	
	@OneToOne(targetEntity = io.egen.entity.Tire.class, cascade = {CascadeType.ALL})
	private Tire tires;
	
	public Reading() {
		this.vehicleId = UUID.randomUUID().toString();
	}

	/**
	 * @param vehicleId
	 * @param vin
	 * @param latitude
	 * @param longitude
	 * @param timestamp
	 * @param fuelVolume
	 * @param speed
	 * @param engineHp
	 * @param checkEngineLightOn
	 * @param engineCoolantLow
	 * @param cruiseControlOn
	 * @param engineRpm
	 * @param tire
	 */
	public Reading(String vehicleId, String vin, double latitude, double longitude, Date timestamp, float fuelVolume,
			int speed, int engineHp, boolean checkEngineLightOn, boolean engineCoolantLow, boolean cruiseControlOn,
			int engineRpm) {
		this.vehicleId = vehicleId;
		this.vin = vin;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
		this.fuelVolume = fuelVolume;
		this.speed = speed;
		this.engineHp = engineHp;
		this.checkEngineLightOn = checkEngineLightOn;
		this.engineCoolantLow = engineCoolantLow;
		this.cruiseControlOn = cruiseControlOn;
		this.engineRpm = engineRpm;
	}

	/**
	 * @return the vehicleId
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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

	/**
	 * @return the fuelVolume
	 */
	public float getFuelVolume() {
		return fuelVolume;
	}

	/**
	 * @param fuelVolume the fuelVolume to set
	 */
	public void setFuelVolume(float fuelVolume) {
		this.fuelVolume = fuelVolume;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the engineHp
	 */
	public int getEngineHp() {
		return engineHp;
	}

	/**
	 * @param engineHp the engineHp to set
	 */
	public void setEngineHp(int engineHp) {
		this.engineHp = engineHp;
	}

	/**
	 * @return the checkEngineLightOn
	 */
	public boolean isCheckEngineLightOn() {
		return checkEngineLightOn;
	}

	/**
	 * @param checkEngineLightOn the checkEngineLightOn to set
	 */
	public void setCheckEngineLightOn(boolean checkEngineLightOn) {
		this.checkEngineLightOn = checkEngineLightOn;
	}

	/**
	 * @return the engineCoolantLow
	 */
	public boolean isEngineCoolantLow() {
		return engineCoolantLow;
	}

	/**
	 * @param engineCoolantLow the engineCoolantLow to set
	 */
	public void setEngineCoolantLow(boolean engineCoolantLow) {
		this.engineCoolantLow = engineCoolantLow;
	}

	/**
	 * @return the cruiseControlOn
	 */
	public boolean isCruiseControlOn() {
		return cruiseControlOn;
	}

	/**
	 * @param cruiseControlOn the cruiseControlOn to set
	 */
	public void setCruiseControlOn(boolean cruiseControlOn) {
		this.cruiseControlOn = cruiseControlOn;
	}

	/**
	 * @return the engineRpm
	 */
	public int getEngineRpm() {
		return engineRpm;
	}

	/**
	 * @param engineRpm the engineRpm to set
	 */
	public void setEngineRpm(int engineRpm) {
		this.engineRpm = engineRpm;
	}

	/**
	 * @return the tire
	 */
	public Tire getTires() {
		return tires;
	}

	/**
	 * @param tire the tire to set
	 */
	public void setTires(Tire tires) {
		this.tires = tires;
	}

	@Override
	public String toString() {
		return "Reading [vehicleId=" + vehicleId + ", vin=" + vin + ", latitude=" + latitude + ", longitude="
				+ longitude + ", timestamp=" + timestamp + ", fuelVolume=" + fuelVolume + ", speed=" + speed
				+ ", engineHp=" + engineHp + ", checkEngineLightOn=" + checkEngineLightOn + ", engineCoolantLow="
				+ engineCoolantLow + ", cruiseControlOn=" + cruiseControlOn + ", engineRpm=" + engineRpm + ", tire="
				+ tires + "]";
	}
	
}
