package capstoneProject;

import java.io.IOException;

public class EnergySource {
	private String name;
	private String type; // Solar, wind...
	private float maxPowerProduction; // The max amount of power it can produce
	private float currentPowerProduction;
	private LogFileManager logs;
	
	public EnergySource(String name, String type, float maxPowerProduction) {
		this.name = name;
		this.type = type;
		this.maxPowerProduction = maxPowerProduction;
		this.currentPowerProduction = maxPowerProduction;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public float getCurrentPower() {
		return currentPowerProduction;
	}
	
	public float getMaxPower() {
		return maxPowerProduction;
	}
	
	// Same for data exchanges
	public void dataExchange() throws IOException{
		// Implementation
	}
	
	public void setPower(int power) {
		this.currentPowerProduction = (power/100)*this.maxPowerProduction;
	}
}
