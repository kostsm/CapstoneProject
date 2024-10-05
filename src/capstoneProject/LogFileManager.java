package capstoneProject;

import java.io.File;
import java.time.LocalDate;

public class LogFileManager {
	private String dir = "logs/"; // Log files are stored here
	
	// Functions to manage log files: create, move, delete...
	
	public void createLog(String equipmentName,LocalDate date) {
		// Code to create log
	}
	
	public void moveLog(String logPath, String destinationPath) {
		// Code to move log
	}
	
	public void deleteLog(String logPath) {
		// Code to delete log
	}
	
	public void archiveLog(String logPath) {
		// Code to archive log
	}
	
	public File openLog(String equipmentName,LocalDate date) {
		// Code to open log
		return null;
	}
}
