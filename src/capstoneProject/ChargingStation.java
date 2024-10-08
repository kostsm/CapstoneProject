package capstoneProject;

import java.io.IOException;

public class ChargingStation {
	private String name;
	private String location;
	private float powerConsumption;
	private LogFileManager logs;
	
	public ChargingStation(String name, String location, float powerConsumption) {
		this.name = name;
		this.location = location;
		this.powerConsumption = powerConsumption; // Here we need to specify the exact number, not % (max consumption)
	}
	
	// Getters and setters
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public float getPower() {
		return powerConsumption;
	}
	
	public void setPower(float power) {
		this.powerConsumption = (power/100) * this.powerConsumption; // Here it will convert specified power to %
	}
	
	// Maybe here we can create data exchanges
	public void dataExchange() throws IOException{
		// Implementation
	}
}
