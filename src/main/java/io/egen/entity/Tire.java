package io.egen.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tire {

	@Id
	private String tireId;
	
	private int frontLeft;
	private int frontRight;
	private int rearLeft;
	private int rearRight;
	
	public Tire() {
		tireId = UUID.randomUUID().toString();
	}
	/**
	 * @param vehicleId
	 * @param frontLeft
	 * @param frontRight
	 * @param rearLeft
	 * @param rearRight
	 */
	public Tire(int frontLeft, int frontRight, int rearLeft, int rearRight) {
		this.frontLeft = frontLeft;
		this.frontRight = frontRight;
		this.rearLeft = rearLeft;
		this.rearRight = rearRight;
	}
	
	
	/**
	 * @return the tireId
	 */
	public String getTireId() {
		return tireId;
	}
	/**
	 * @param tireId the tireId to set
	 */
	public void setTireId(String tireId) {
		this.tireId = tireId;
	}
	/**
	 * @return the frontLeft
	 */
	public int getFrontLeft() {
		return frontLeft;
	}
	/**
	 * @param frontLeft the frontLeft to set
	 */
	public void setFrontLeft(int frontLeft) {
		this.frontLeft = frontLeft;
	}
	/**
	 * @return the frontRight
	 */
	public int getFrontRight() {
		return frontRight;
	}
	/**
	 * @param frontRight the frontRight to set
	 */
	public void setFrontRight(int frontRight) {
		this.frontRight = frontRight;
	}
	/**
	 * @return the rearLeft
	 */
	public int getRearLeft() {
		return rearLeft;
	}
	/**
	 * @param rearLeft the rearLeft to set
	 */
	public void setRearLeft(int rearLeft) {
		this.rearLeft = rearLeft;
	}
	/**
	 * @return the rearRight
	 */
	public int getRearRight() {
		return rearRight;
	}
	/**
	 * @param rearRight the rearRight to set
	 */
	public void setRearRight(int rearRight) {
		this.rearRight = rearRight;
	}
	
	@Override
	public String toString() {
		return "Tire [tireId=" + tireId + ", frontLeft=" + frontLeft + ", frontRight=" + frontRight
				+ ", rearLeft=" + rearLeft + ", rearRight=" + rearRight + "]";
	}

}
