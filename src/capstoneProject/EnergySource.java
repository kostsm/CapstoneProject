package capstoneProject;

import java.io.IOException;

public class EnergySource {
	private String name;
	private String type; // Solar, wind...
	private float powerProduction; // The max amount of power it can produce
	private LogFileManager logs;
	
	public EnergySource(String name, String type, float powerProduction) {
		this.name = name;
		this.type = type;
		this.powerProduction = powerProduction;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public float getPower() {
		return powerProduction;
	}
	
	// Same for data exchanges
	public void dataExchange() throws IOException{
		// Implementation
	}
}
