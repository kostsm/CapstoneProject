package capstoneProject;

import java.io.IOException;

public class ChargingStation {
	private String name;
	private String location;
	private float currentPowerConsumption;
	private float maxPowerConsumption;
	private int powerLevel;
	private LogFileManager logs;
	
	public ChargingStation(String name, String location, float maxPowerConsumption) {
		this.name = name;
		this.maxPowerConsumption = maxPowerConsumption; // Here we need to specify the exact number, not % (max consumption)
		this.currentPowerConsumption = maxPowerConsumption;
	}
	
	// Getters and setters
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public float getCurrentPower() {
		return currentPowerConsumption;
	}
	
	public float getMaxPower() {
		return maxPowerConsumption;
	}
	
	public void setPower(float power) {
		this.currentPowerConsumption = (power/100) * this.maxPowerConsumption; // Here it will convert specified power to %
	}
	
	// Maybe here we can create data exchanges
	public void dataExchange() throws IOException{
		// Implementation
	}
}
