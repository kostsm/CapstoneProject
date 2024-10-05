package capstoneProject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class LogFile {
	private String fileName;
	private String equipmentName;
	private LocalDate date;
	private File file;
	
	public LogFile(String equipmentName, LocalDate date) {
		this.equipmentName = equipmentName;
		this.date = date;
		this.fileName = equipmentName + "_" + date.toString() + ".log";
		this.file = new File(fileName);
	}
	
	// Getters
	
	public String getFileName() {
		return fileName;
	}
	
	public String getEquipmentName() {
		return equipmentName;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	// Functions to arrange dataflow
	public String readData() throws IOException {
		return "data"; // Create a code to read data
	}
	
	public void writeData(String data) throws IOException {
		// Code to write data
	}
}
