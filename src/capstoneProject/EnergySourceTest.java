package capstoneProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.File;
import java.time.LocalDate;

public class EnergySourceTest {

	private EnergySource energySource;

	@BeforeEach
	public void setUp() throws IOException {
		Battery mainBattery = new Battery("mainBattery", 1000);
		energySource = new EnergySource("Name", "Type", 500,mainBattery);
	}

	@Test
	public void testInitialization() {
		assertEquals("Name", energySource.getName());
		assertEquals("Type", energySource.getType());
		assertEquals(500, energySource.getMaxPower(), 0.001);
		assertEquals(500, energySource.getCurrentPower(), 0.001);
	}

	@Test
	public void testValidPower() {
		energySource.setPower(50); 
		assertEquals(250, energySource.getCurrentPower(), 0.001);
	}

	@Test
	public void testZeroPower() {
		energySource.setPower(0); 
		assertEquals(0, energySource.getCurrentPower(), 0.001);
	}

	@Test
	public void testInvalidPower() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			energySource.setPower(150);
		});
		assertEquals("Power must be between 0 and 100", e.getMessage());
	}

	@Test
	public void testDataExchange() {
		assertDoesNotThrow(() -> {
			energySource.dataExchange();
		});
	}
	
	@AfterEach
	public void clear() {
		File log = new File("logs" + File.separator + energySource.getName() + "_" + LocalDate.now().toString() + ".log");
		if (log.exists()) {
			log.delete();
		}
	}
}
