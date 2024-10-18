package capstoneProject;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;

class UserInputTest {

	@Test
	void testMainAddSrc() throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		final EnergySystem engsys = new EnergySystem();
		final Field field = engsys.getClass().getDeclaredField("energySources");
        field.setAccessible(true);
		List<EnergySource> list  = (List<EnergySource>) field.get(engsys);
		int length1 = list.size();
		// add consumer
		System.out.println("Please specify the new consumers name: ");
		System.out.println("Use <back> to get back to the main interface");
		String srcName = "test";
		if(srcName.equals("back")) {
			// back to interface
		}else {
			System.out.println("Please specify the location of" + srcName + ":");
			String srcType = "test";
			System.out.println("Please specify the consumers' maximum power sonsumption in kWh:");
			int srcPower = 100;
			EnergySource newEngSrc = new EnergySource(srcName, srcType, srcPower);
			engsys.addEnergySource(newEngSrc);
			System.out.println("New consumer added.");
		}
		list  = (List<EnergySource>) field.get(engsys);
		int length2 = list.size();
		assertEquals(true,length2>length1);
	}
	
	@Test
	void testMainAddChrg() throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		final EnergySystem engsys = new EnergySystem();
		final Field field = engsys.getClass().getDeclaredField("chargingStations");
        field.setAccessible(true);
		List<ChargingStation> list  = (List<ChargingStation>) field.get(engsys);
		int length1 = list.size();
		// add consumer
		System.out.println("Please specify the new consumers name: ");
		System.out.println("Use <back> to get back to the main interface");
		String useName = "test";
		if(useName.equals("back")) {
			// back to interface
		}else {
			System.out.println("Please specify the location of" + useName + ":");
			String useLocation = "test";
			System.out.println("Please specify the consumers' maximum power sonsumption in kWh:");
			int usePower = 100;
			ChargingStation newChrgStation = new ChargingStation(useName, useLocation, usePower);
			engsys.addChargingStation(newChrgStation);
			System.out.println("New consumer added.");
		}
		list  = (List<ChargingStation>) field.get(engsys);
		int length2 = list.size();
		assertEquals(true,length2>length1);
	}
	
	@Test
	void testMainAdjSrc() throws IOException {
		final EnergySystem engsys = new EnergySystem();
		EnergySource newEngSrc = new EnergySource("test", "test", (float)100);
		engsys.addEnergySource(newEngSrc);
		List<EnergySource> energySources = engsys.getEnergySources();
		String prodChngName = "test";
		float pwrBefore = 0;
		float pwrAfter = 0;
		for (EnergySource src : energySources) {
    		if(src.getName().equals(prodChngName)) {
    			pwrBefore = src.getCurrentPower() ;
    			System.out.println("Please specify the percentage of power production for " + src.getName() +":");
    			int newPower = 80;
    			if (newPower<=100&&newPower>=0) {
    				src.setPower(newPower);
    				System.out.println(src.getName() +" now produces "+ src.getCurrentPower() + "kWh.");
    				src.dataExchange();
    				pwrAfter = src.getCurrentPower();
    			}else {
    				System.out.println("Please specify a percentage between 0 and 100");
    			}
    			break;
    				
    		}
    		// source not found
    		System.out.println("Source by name " + prodChngName + " not found.");
        }
		
		assertEquals(true,pwrBefore>pwrAfter);
	}
	
	@Test
	void testMainAdjChrg() throws IOException {
			final EnergySystem engsys = new EnergySystem();
			ChargingStation chrgStation = new ChargingStation("test", "test", (float)100);
			engsys.addChargingStation(chrgStation);
			List<ChargingStation> chargingStations = engsys.getChargingStations();
			String userChngName = "test";
			float pwrBefore = 0;
			float pwrAfter = 0;
			for (ChargingStation chrg : chargingStations) {
	    		if(chrg.getName().equals(userChngName)) {
	    			pwrBefore = chrg.getCurrentPower() ;
	    			System.out.println("Please specify the percentage of power production for " + chrg.getName() +":");
	    			int newPower = 80;
	    			if (newPower<=100&&newPower>=0) {
	    				chrg.setPower(newPower);
	    				System.out.println(chrg.getName() +" now consumes "+ chrg.getCurrentPower() + "kWh.");
	    				chrg.dataExchange();
	    				pwrAfter = chrg.getCurrentPower();
	    			}else {
	    				System.out.println("Please specify a percentage between 0 and 100");
	    			}
	    			break;
	    				
	    		}
	    		// source not found
	    		System.out.println("ChargingStation by name " + userChngName + " not found.");
	        }
			
			assertEquals(true,pwrBefore>pwrAfter);
	}
	
	@Test
	void testMainCheckPower() throws IOException {
		EnergySystem engSys = new EnergySystem();
		EnergySource engSource = new EnergySource("test", "test", (float) 100);
		ChargingStation chrgStation = new ChargingStation("test", "test", (float) 110);
		engSys.addChargingStation(chrgStation);
        engSys.addEnergySource(engSource);
		List<ChargingStation> chargingStations;
        List<EnergySource> energySources;
    	float totalPowerConsumption = 0;
    	float totalPowerProduction = 0;
    	chargingStations = engSys.getChargingStations();
    	energySources = engSys.getEnergySources();
    	
    	for (ChargingStation chrg : chargingStations) {
    		totalPowerConsumption +=chrg.getCurrentPower();
        }
    	
    	// check total power production
    	for (EnergySource src : energySources) {
    		totalPowerProduction +=src.getCurrentPower();            
    	}
    	
    	assertEquals(true,totalPowerConsumption>totalPowerProduction);
	}
}
