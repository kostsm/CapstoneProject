package capstoneProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.time.LocalDate;

public class LogFile {
	private String fileName;
	private String equipmentName;
	private LocalDate date;
	private File file;
	
	public LogFile(String equipmentName, LocalDate date) {
		this.equipmentName = equipmentName;
		this.date = date;
		this.fileName = "logs/" + equipmentName + "_" + date.toString() + ".log";
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
		StringBuilder data = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				data.append(line).append("\n");
			}
		}
		return data.toString();
	}
	
	public void writeData(String data) throws IOException {
		// Code to write data
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
			bw.write(data);
			bw.newLine();
		}
	}
}
