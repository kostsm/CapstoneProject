package capstoneProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class LogFileManager {
	private String dir = "logs/"; // Log files are stored here
	
	// Functions to manage log files: create, move, delete...

	public void createLog(String equipmentName, LocalDate date) throws IOException {
		LogFile logFile = new LogFile(equipmentName, date);
		if (!logFile.getFile().exists()) {
			logFile.getFile().createNewFile();
		}
	}

	public void moveLog(String logPath, String destinationPath) throws IOException {
		Path sourcePath = new File(logPath).toPath();
		Path targetPath = new File(destinationPath).toPath();
		Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
	}

	public void deleteLog(String logPath) throws IOException {
		Path path = new File(logPath).toPath();
		Files.deleteIfExists(path);
	}

	public void archiveLog(String logPath) throws IOException {
		// Example of archiving by moving to a different directory
		String archivedDir = "archive/";
		new File(archivedDir).mkdirs(); // Create archive directory if it doesn't exist
		moveLog(logPath, archivedDir + new File(logPath).getName());
	}
	
	public File openLog(String equipmentName,LocalDate date) {
		// Code to open log
		return new LogFile(equipmentName, date);
	}
}
