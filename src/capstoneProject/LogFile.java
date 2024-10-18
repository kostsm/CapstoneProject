package capstoneProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFile {
	private String fileName;
	private String equipmentName;
	private LocalDate date;
	private File file;

	public enum LogLevel {
		INFO, WARN, ERROR, DEBUG
	}
	public LogFile(String equipmentName, LocalDate date) {
		this.equipmentName = equipmentName;
		this.date = date;
		this.fileName = "logs"+ File.separator + equipmentName + "_" + date.toString() + ".log";
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
	public String readData() throws LogFileException {
		StringBuilder data = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				data.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new LogFileException("Error while reading from log file: " + fileName, e);
		}
		return data.toString();
	}
	
	public void writeData(String message, LogLevel logLevel) throws LogFileException {
		// Code to write data
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String formattedMessage = String.format("[%s] [%s] %s", timestamp, logLevel, message);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
			bw.write(formattedMessage);
			bw.newLine();
		} catch (IOException e) {
			throw new LogFileException("Error while writing to log file: " + fileName, e);
		}
	}
}
