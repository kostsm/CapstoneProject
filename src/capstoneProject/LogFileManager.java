package capstoneProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class LogFileManager {

    // Functions to manage log files: create, move, delete...
	public void createLog(String equipmentName, LocalDate date) throws IOException {
		String dir = "logs/";
		File logDir = new File(dir);
		if (!logDir.exists()) {
			logDir.mkdirs(); // Ensure the directory exists
		}
		try {
			Path path = new File(dir + equipmentName + "_" + date.toString() + ".log").toPath();
			if (Files.notExists(path)) {
				Files.createFile(path);
				System.out.println("File created: " + path.getFileName());
			} else {
				System.out.println("File already exists.");
			}
		}catch(Exception e) {
			System.out.println("FAILED to create Logfile " +dir + equipmentName + "_" + date.toString() + ".log");
			throw e;
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
	
	public LogFile openLog(String equipmentName, LocalDate date) {
		// Code to open log
		return new LogFile(equipmentName, date);
	}
}

