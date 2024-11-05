package capstoneProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.File;
import java.time.LocalDate;

public class ChargingStationTest {

	private ChargingStation chargingStation;

	@BeforeEach
	public void setUp() throws IOException {
		Battery mainBattery = new Battery("mainBattery", 1000);
		chargingStation = new ChargingStation("Name", "Location", 500,mainBattery);
	}

	@Test
	public void testInitialization() {
		assertEquals("Name", chargingStation.getName());
		assertEquals("Location", chargingStation.getLocation());
		assertEquals(500, chargingStation.getMaxPower(), 0.001);
		assertEquals(500, chargingStation.getCurrentPower(), 0.001);
	}

	@Test
	public void testValidPower() {
		chargingStation.setPower(50); 
		assertEquals(250, chargingStation.getCurrentPower(), 0.001);
	}

	@Test
	public void testZeroPower() {
		chargingStation.setPower(0); 
		assertEquals(0, chargingStation.getCurrentPower(), 0.001);
	}

	@Test
	public void testInvalidPower() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			chargingStation.setPower(150);
		});
		assertEquals("Power must be between 0 and 100", e.getMessage());
	}

	@Test
	public void testDataExchange() {
		assertDoesNotThrow(() -> {
			chargingStation.dataExchange();
		});
	}
	
	@AfterEach
	public void clear() {
		File log = new File("logs" + File.separator + chargingStation.getName() + "_" + LocalDate.now().toString() + ".log");
		if (log.exists()) {
			log.delete();
		}
	}
}
