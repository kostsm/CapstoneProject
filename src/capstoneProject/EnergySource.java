package capstoneProject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class EnergySource {
	private String name;
	private String type; // Solar, wind...
	private float maxPowerProduction; // The max amount of power it can produce
	private float currentPowerProduction;
	private LogFile logs;
	
	public EnergySource(String name, String type, float maxPowerProduction) throws IOException {
		this.name = name;
		this.type = type;
		this.maxPowerProduction = maxPowerProduction;
		this.currentPowerProduction = maxPowerProduction;
		this.logs = new LogFile(name, LocalDate.now());
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
	
	public void setPower(int power) {
		this.currentPowerProduction = (power/100)*this.maxPowerProduction;
	}
	
	// Simulation of byte data exchange
	public void dataExchange() throws IOException{
		String data = "EnergySource:" + name + ", power:" + currentPowerProduction;
		byte[] dataBytes = data.getBytes();

		ByteArrayInputStream byteInput = new ByteArrayInputStream(dataBytes);
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

		int bytes;
		while ((bytes = byteInput.read()) != -1) {
			byteOutput.write(bytes);
		}

		String receivedData = byteOutput.toString();

		logs.writeData("Data Exchange (Byte Stream): " + receivedData, LogFile.LogLevel.INFO);
	}
}
