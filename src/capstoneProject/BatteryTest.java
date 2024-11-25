package capstoneProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BatteryTest {

    private Battery battery;

    @BeforeEach
    public void setUp() {
	battery = new Battery("TestBattery", 1000);
    }

    @Test
    public void testConstructor() {
	assertEquals("TestBattery", battery.getName());
	assertEquals(1000, battery.maxPower);
	assertEquals(0.0, battery.getCurrentPower(), 0.001);
    }

    @Test
    public void testChargeValid() {
	battery.charge(500.0);
	assertEquals(500.0, battery.getCurrentPower(), 0.001);
    }

    @Test
    public void testChargeOverMaxPower() {
	battery.charge(1200.0);
	assertEquals(1000.0, battery.getCurrentPower(), 0.001);
    }

    @Test
    public void testChargeNegative() {
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
		battery.charge(-100.0);
	});
	assertEquals("Charge amount should be >0", exception.getMessage());
    }

    @Test
    public void testDrainValid() {
    	battery.charge(500.0);
    	battery.drain(200);
    	assertEquals(300.0, battery.getCurrentPower(), 0.001);
    }

    @Test
    public void testDrainOverPower() {
    	battery.charge(300.0);
    	battery.drain(500);
    	assertEquals(0.0, battery.getCurrentPower(), 0.001);
    }

    @Test
    public void testDrainNegative() {
    	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    		battery.drain(-50);
    	});
    	assertEquals("Drain amount should be >0", exception.getMessage());
    }
}
