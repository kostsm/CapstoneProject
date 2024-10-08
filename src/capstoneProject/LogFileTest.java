package capstoneProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class LogFileTest {

    public static void main(String[] args) {
        try {
            // Initialize the LogFileManager
            LogFileManager logFileManager = new LogFileManager();
            String equipmentName = "TestEquipment";
            LocalDate currentDate = LocalDate.now();

            // Step 1: Create a new log file
            logFileManager.createLog(equipmentName, currentDate);

            // Step 2: Open the log file and write entries with different log levels
            LogFile logFile = logFileManager.openLog(equipmentName, currentDate);
            logFile.writeData("This is an INFO log entry", LogFile.LogLevel.INFO);
            logFile.writeData("This is a WARN log entry", LogFile.LogLevel.WARN);
            logFile.writeData("This is an ERROR log entry", LogFile.LogLevel.ERROR);
            logFile.writeData("This is a DEBUG log entry", LogFile.LogLevel.DEBUG);

            // Step 3: Read the data from the log file to verify entries
            String logContent = logFile.readData();
            System.out.println("Log file content:\n" + logContent);

//            // Step 4: Archive (move) the log file to a different directory
//            String logFilePath = logFile.getFileName();
//            logFileManager.archiveLog(logFilePath);
//
//            // Step 5: Verify that the log file has been moved to the archive directory
//            Path archivedPath = Path.of("archive", logFile.getFileName());
//            if (Files.exists(archivedPath)) {
//                System.out.println("Log file successfully archived to: " + archivedPath);
//            } else {
//                System.out.println("Log file archiving failed.");
//            }
//
//            // Step 6: Delete the log file from the archive directory
//            logFileManager.deleteLog(archivedPath.toString());
//            if (!Files.exists(archivedPath)) {
//                System.out.println("Log file successfully deleted from archive.");
//            } else {
//                System.out.println("Failed to delete the log file from archive.");
//            }

        } catch (IOException e) {
            System.err.println("An error occurred during testing: " + e.getMessage());
        }
    }
}
