package capstoneProject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class EnergySource implements Runnable {
	private String name;
	private String type; // Solar, wind...
	private float maxPowerProduction; // The max amount of power it can produce
	private float currentPowerProduction;
	LogFile logs;
	private Battery battery;
	
	public EnergySource(String name, String type, float maxPowerProduction) throws IOException {
		this.name = name;
		this.type = type;
		this.maxPowerProduction = maxPowerProduction;
		this.currentPowerProduction = maxPowerProduction;
		this.logs = new LogFile(name, LocalDate.now());
		this.battery = battery;
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
	
	public void setPower(float power) {
		if (power < 0 || power > 100) {
			throw new IllegalArgumentException("Power must be between 0 and 100");
		}
		this.currentPowerProduction = (power/100)*this.maxPowerProduction;
	}
	
	// Simulation of byte data exchange
	public void dataExchange() throws MultipleExceptions, ChainException {
		MultipleExceptions exceptions = 
				new MultipleExceptions("Errors in enegry source: " + name);
		
		try {
			String data = "EnergySource:" + name + ", power:" + currentPowerProduction;
			byte[] dataBytes = data.getBytes();
	
			ByteArrayInputStream byteInput = new ByteArrayInputStream(dataBytes);
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			
			try {
				int bytes;
				while ((bytes = byteInput.read()) != -1) {
					byteOutput.write(bytes);
				}
	
			String receivedData = byteOutput.toString();
			logs.writeData("Data Exchange (Byte Stream): " + receivedData, LogFile.LogLevel.INFO);
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
			this.dataExchange();
			int chargeAmount = (int)currentPowerProduction; 
			battery.charge(chargeAmount);

			logs.writeData("Charged battery " + battery.getName() + " with " + chargeAmount + " units.", LogFile.LogLevel.INFO);

			//dataExchange();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}


