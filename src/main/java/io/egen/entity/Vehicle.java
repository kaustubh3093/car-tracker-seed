package io.egen.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

	@Id
	private String vin;
	private String make;
	private String model;
	private int year;
	private int redlineRpm;
	private int maxFuelVolume;
	private Date lastServiceDate;
	
	/**
	 * @return the lastServiceDate
	 */
	public Date getLastServiceDate() {
		return lastServiceDate;
	}

	/**
	 * @param lastServiceDate the lastServiceDate to set
	 */
	public void setLastServiceDate(Date lastServiceDate) {
		System.out.println(lastServiceDate);
		this.lastServiceDate = lastServiceDate;
	}

	/**
	 * Empty Constructor
	 */
	public Vehicle() {
		
	}

	/**
	 * @param vin
	 * @param make
	 * @param model
	 * @param year
	 * @param redlineRpm
	 * @param maxFuelVolume
	 * @param date
	 */
	public Vehicle(String vin, String make, String model, int year, int redlineRpm, int maxFuelVolume, Date lastServiceDate) {
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.year = year;
		this.redlineRpm = redlineRpm;
		this.maxFuelVolume = maxFuelVolume;
		this.lastServiceDate = lastServiceDate;
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

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the redlineRpm
	 */
	public int getRedlineRpm() {
		return redlineRpm;
	}

	/**
	 * @param redlineRpm the redlineRpm to set
	 */
	public void setRedlineRpm(int redlineRpm) {
		this.redlineRpm = redlineRpm;
	}

	/**
	 * @return the maxFuelVolume
	 */
	public int getMaxFuelVolume() {
		return maxFuelVolume;
	}

	/**
	 * @param maxFuelVolume the maxFuelVolume to set
	 */
	public void setMaxFuelVolume(int maxFuelVolume) {
		this.maxFuelVolume = maxFuelVolume;
	}

	@Override
	public String toString() {
		return "Vehicle [vin=" + vin + ", make=" + make + ", model=" + model + ", year=" + year + ", redlineRpm="
				+ redlineRpm + ", maxFuelVolume=" + maxFuelVolume + ", lastServiceDate=" + lastServiceDate + "]";
	}
	
}
