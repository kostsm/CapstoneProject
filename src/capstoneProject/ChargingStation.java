package capstoneProject;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;

public class ChargingStation implements Runnable{
	private String name;
	private String location;
	private float currentPowerConsumption;
	private float maxPowerConsumption;
	private int powerLevel;
	private LogFile logs;
	private Battery battery;
	
	public ChargingStation(String name, String location, float maxPowerConsumption, Battery battery) throws IOException {
		this.name = name;
		this.location = location;
		this.maxPowerConsumption = maxPowerConsumption; // Here we need to specify the exact number, not % (max consumption)
		this.currentPowerConsumption = maxPowerConsumption;
		this.logs = new LogFile(name, LocalDate.now());
		this.battery = battery;
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
		if (power < 0 || power > 100) {
			throw new IllegalArgumentException("Power must be between 0 and 100");
		}
		this.currentPowerConsumption = (power/100) * this.maxPowerConsumption; // Here it will convert specified power to %
	}
	
	// Simulation of data exchange
	public void dataExchange() throws MultipleExceptions, ChainException {
		
		MultipleExceptions exceptions = 
				new MultipleExceptions("Errors in charging station: " + name);

		try {

			String data = "ChargingStation:" + name + ", power:" + currentPowerConsumption;
	
			StringReader charInput = new StringReader(data);
			StringWriter charOutput = new StringWriter();
			
			try {
				
				int chars;
				while ((chars = charInput.read()) != -1) {
					charOutput.write(chars);
				}

			String receivedData = charOutput.toString();
	
			logs.writeData("Data Exchange (Character Stream): " + receivedData, LogFile.LogLevel.INFO);
			}
			catch (IOException e) {
				exceptions.addException(e);
			}
		}
		catch (Exception e) {
			exceptions.addException(e);
		}
		
		if (!exceptions.getExceptions().isEmpty()) {
			throw exceptions;
		}
	}
	
	@Override
	public void run() {
		try {
			for (int i =0; i < 5; i++) {
				double drawAmount = currentPowerConsumption/5; 
				battery.drain(drawAmount);
				logs.writeData("Drained " + drawAmount + " units from battery "+ battery.getName(), LogFile.LogLevel.INFO);
				Thread.sleep(3000);
			}
			dataExchange();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
