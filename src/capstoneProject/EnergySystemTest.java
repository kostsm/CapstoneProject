package capstoneProject;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;

class EnergySystemTest {

	@Test
	void testEnergySystem() {
		try {
			new EnergySystem();
		}catch(Exception e) {
		      fail(e.getMessage());
	    }
	}

	@Test
	void testAddChargingStation() throws NoSuchFieldException, IOException,IllegalAccessException {
		final EnergySystem engsys = new EnergySystem();
		final Field field = engsys.getClass().getDeclaredField("chargingStations");
        field.setAccessible(true);
		List<ChargingStation> list  = (List<ChargingStation>) field.get(engsys);
		int length1 = list.size();
		ChargingStation chrgStat = new ChargingStation("test", "test",(float) (0.0));
		engsys.addChargingStation(chrgStat);
		list  = (List<ChargingStation>) field.get(engsys);
		int length2 = list.size();
		assertEquals(true, length2>length1);
	}

	@Test
	void testAddEnergySource() throws NoSuchFieldException, IOException,IllegalAccessException {
		final EnergySystem engsys = new EnergySystem();
		final Field field = engsys.getClass().getDeclaredField("energySources");
        field.setAccessible(true);
		List<EnergySource> list  = (List<EnergySource>) field.get(engsys);
		int length1 = list.size();
		EnergySource engsrc = new EnergySource("test", "test",(float) (0.0));
		engsys.addEnergySource(engsrc);
		list  = (List<EnergySource>) field.get(engsys);
		int length2 = list.size();
		assertEquals(true, length2>length1);
	}

	@Test
	void testGetEnergySources() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final EnergySystem engsys = new EnergySystem();
		final Field field = engsys.getClass().getDeclaredField("energySources");
		field.setAccessible(true);
		
		List<EnergySource> list  = (List<EnergySource>) field.get(engsys);
		
		field.set(engsys,list);
		
		final List<EnergySource> retList = engsys.getEnergySources();
		assertEquals(retList, list);
		
	}

	@Test
	void testGetChargingStations() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final EnergySystem engsys = new EnergySystem();
		final Field field = engsys.getClass().getDeclaredField("chargingStations");
		field.setAccessible(true);
		
		List<ChargingStation> list  = (List<ChargingStation>) field.get(engsys);
		
		field.set(engsys,list);
		
		final List<ChargingStation> retList = engsys.getChargingStations();
		assertEquals(retList, list);
	}

}
