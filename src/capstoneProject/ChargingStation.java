package capstoneProject;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;

public class ChargingStation {
	private String name;
	private String location;
	private float currentPowerConsumption;
	private float maxPowerConsumption;
	private int powerLevel;
	private LogFile logs;
	
	public ChargingStation(String name, String location, float maxPowerConsumption) throws IOException {
		this.name = name;
		this.location = location;
		this.maxPowerConsumption = maxPowerConsumption; // Here we need to specify the exact number, not % (max consumption)
		this.currentPowerConsumption = maxPowerConsumption;
		this.logs = new LogFile(name, LocalDate.now());
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
	
	// Simulation of data exchange
	public void dataExchange() throws IOException, ChainException {
		String data = "ChargingStation:" + name + ", power:" + currentPowerConsumption;

		StringReader charInput = new StringReader(data);
		StringWriter charOutput = new StringWriter();

		int chars;
		while ((chars = charInput.read()) != -1) {
			charOutput.write(chars);
		}

		String receivedData = charOutput.toString();

		logs.writeData("Data Exchange (Character Stream): " + receivedData, LogFile.LogLevel.INFO);
	}
}
