package capstoneProject;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogFileManagerTest {

    private static LogFileManager logFileManager;
    private static String equipmentName;
    private static LocalDate currentDate;
    private static Path archivedPath;

    @BeforeAll
    static void setup() {
        logFileManager = new LogFileManager();
        equipmentName = "TestEquipment";
        currentDate = LocalDate.now();
    }

    @Test
    @Order(1)
    void testCreateLogFile() {
        assertDoesNotThrow(() -> logFileManager.createLog(equipmentName, currentDate));
        Path logFilePath = Path.of("logs", equipmentName + "_" + currentDate + ".log");
        assertTrue(Files.exists(logFilePath), "Log file should be created successfully.");
    }

    @Test
    @Order(2)
    void testWriteLogEntries() throws IOException,ChainException{
        LogFile logFile = logFileManager.openLog(equipmentName, currentDate);
        logFile.writeData("This is an INFO log entry", LogFile.LogLevel.INFO);
        logFile.writeData("This is a WARN log entry", LogFile.LogLevel.WARN);
        logFile.writeData("This is an ERROR log entry", LogFile.LogLevel.ERROR);
        logFile.writeData("This is a DEBUG log entry", LogFile.LogLevel.DEBUG);

        String logContent = logFile.readData();
        assertTrue(logContent.contains("INFO"), "Log file should contain INFO level entry.");
        assertTrue(logContent.contains("WARN"), "Log file should contain WARN level entry.");
        assertTrue(logContent.contains("ERROR"), "Log file should contain ERROR level entry.");
        assertTrue(logContent.contains("DEBUG"), "Log file should contain DEBUG level entry.");
    }

    @Test
    @Order(3)
    void testReadLogData() throws IOException, ChainException {
        LogFile logFile = logFileManager.openLog(equipmentName, currentDate);
        String logContent = logFile.readData();
        assertNotNull(logContent, "Log file content should not be null.");
        assertTrue(logContent.contains("This is an INFO log entry"), "Log file should have data that was written earlier.");
    }

    @Test
    @Order(4)
    void testArchiveLogFile() throws IOException, ChainException {
        LogFile logFile = logFileManager.openLog(equipmentName, currentDate);
        String logFilePath = logFile.getFileName();
        logFileManager.archiveLog(logFilePath);

        archivedPath = Path.of("archive", equipmentName + "_" + currentDate + ".log");
        assertTrue(Files.exists(archivedPath), "Archived log file should exist in the archive directory.");
    }

    @Test
    @Order(5)
    void testDeleteLogFile() throws IOException, ChainException {
        logFileManager.deleteLog(archivedPath.toString());
        assertFalse(Files.exists(archivedPath), "Log file should be deleted from the archive directory.");
    }
}
