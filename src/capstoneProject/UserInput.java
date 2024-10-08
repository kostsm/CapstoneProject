package capstoneProject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import capstoneProject.EnergySource;
import capstoneProject.ChargingStation;
import capstoneProject.EnergySystem;


public class UserInput {
	public static void main(String args[])throws IOException
    {	
		
		// Create first Energy source
		System.out.println("Welcome to your new smart Home. Please create a new energy Source by naming it:");
        // Using Scanner for Getting Input from User
		// Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

        // Reading data using readLine
        String sourceName = reader.readLine();
        System.out.println("You entered the name " + sourceName);
        System.out.println("Please specify the type of "+ sourceName);
        
        String sourceType = reader.readLine();
        
        System.out.println("Please specify the sources' maximum power production in kWh:");
        
       
        int sourceMaxPower = Integer.parseInt(reader.readLine());
        
        EnergySource engSource = new EnergySource(sourceName, sourceType, sourceMaxPower);
        
        System.out.println("New power source " + sourceName + " of type " + sourceType + " with a max. production of " + sourceMaxPower + "kWh created");
        //Create first consumer
        System.out.println("Please specify the name of a consumer:");
        String consumerName = reader.readLine();
        System.out.println("Please specify the location of " + consumerName + ":");
        String consumerLocation = reader.readLine();
        System.out.println("Please specify the maxPowerConsumption of " + consumerName + " in kWh:");
        int consumerMaxPower = Integer.parseInt(reader.readLine());
        
        ChargingStation chrgStation = new ChargingStation(consumerName, consumerLocation, consumerMaxPower);
        
        System.out.println("New charging station " + consumerName + " at " + consumerLocation + " with a max. consumtion of " + consumerMaxPower + "kWh created");
        
        // establish energy system and add src and consumer
        EnergySystem engSys = new EnergySystem();
        engSys.addChargingStation(chrgStation);
        engSys.addEnergySource(engSource);
        
        List<ChargingStation> chargingStations;
        List<EnergySource> energySources;
        
        
        
        // run user interface
        while(true) {
        	System.out.println("\n Welcome to the smart home user interface. Your options:");
        	System.out.println("<add src> to add another power source");
        	System.out.println("<add cons> to add another consumer");
        	System.out.println("<adj src> to adjust a power sources power output in %");
        	System.out.println("<adj cons> to adjust a consumers power consumption in %");
        	System.out.println("<end> to leave this interface");
        	float totalPowerConsumption = 0;
        	float totalPowerProduction = 0;
        	chargingStations = engSys.getChargingStations();
        	energySources = engSys.getEnergySources();
        	
        	// check total power consumption
        	for (ChargingStation chrg : chargingStations) {
        		totalPowerConsumption +=chrg.getCurrentPower();
            }
        	
        	// check total power production
        	for (EnergySource src : energySources) {
        		totalPowerProduction +=src.getCurrentPower();            
        	}
        	
        	
        	// compare consumption and production
        	if(totalPowerConsumption>totalPowerProduction) {
        		System.out.println("WARNING! System overload! \n Please reduce power consumption!");
        	}else {
        		System.out.println("Power consumption normal. Excess power produced: " + (totalPowerProduction-totalPowerConsumption) + "kWh");
        	}
        	
        	
        	
        	// start user input
        	String input = reader.readLine();
        	if(input.equals("end")) {
        		// end interface & exit program
        		System.out.println("Leaving smart home...");
        		break;
        	}else if(input.equals("add src")) {
        		// add power source
        		System.out.println("Please specify the new sources name: ");
        		System.out.println("Use <back> to get back to the main interface");
        		String srcName = reader.readLine();
        		if(srcName.equals("back")) {
        			// back to interface
        			continue;
        		}else {
        			System.out.println("Please specify type of" + srcName + ":");
        			String srcType = reader.readLine();
        			System.out.println("Please specify the sources' maximum power production in kWh:");
        			int srcPower = Integer.parseInt(reader.readLine());
        			EnergySource newEngSource = new EnergySource(srcName, srcType, srcPower);
        			engSys.addEnergySource(newEngSource);
        			System.out.println("New Energy Source added.");
        			continue;
        		}
        	}else if(input.equals("add cons")) {
        		// add consumer
        		System.out.println("Please specify the new consumers name: ");
        		System.out.println("Use <back> to get back to the main interface");
        		String useName = reader.readLine();
        		if(useName.equals("back")) {
        			// back to interface
        			continue;
        		}else {
        			System.out.println("Please specify the location of" + useName + ":");
        			String useLocation = reader.readLine();
        			System.out.println("Please specify the consumers' maximum power sonsumption in kWh:");
        			int usePower = Integer.parseInt(reader.readLine());
        			ChargingStation newChrgStation = new ChargingStation(useName, useLocation, usePower);
        			engSys.addChargingStation(newChrgStation);
        			System.out.println("New consumer added.");
        			continue;
        		}
        	}else if(input.equals("adj src")) {
        		// adjust power production
        		System.out.println("Please specify the name of the producer you want to adjust: ");
        		System.out.println("Use <back> to get back to the main interface");
        		String prodChngName = reader.readLine();
        		if(prodChngName.equals("back")) {
        			// back to interface
        			continue;
        		}else {
        			for (EnergySource src : energySources) {
                		if(src.getName().equals(prodChngName)) {
                			// source found
                			System.out.println("Please specify the percentage of power production for " + src.getName() +":");
                			int newPower = Integer.parseInt(reader.readLine());
                			src.setPower(newPower);
                			System.out.println(src.getName() +" now produces "+ src.getCurrentPower() + "kWh.");
                			break;
                		}
                		// source not found
                		System.out.println("Source by name " + prodChngName + " not found.");
                    }
        		}
        	}else if(input.equals("adj cons")) {
        		System.out.println("Please specify the name of the consumer you want to adjust: ");
        		System.out.println("Use <back> to get back to the main interface");
        		String userChngName = reader.readLine();
        		if(userChngName.equals("back")) {
        			// back to interface
        			continue;
        		}else {
        			for (ChargingStation chrg : chargingStations) {
                		if(chrg.getName().equals(userChngName)) {
                			// consumer found
                			System.out.println("Please specify the percentage of power consumption for " + chrg.getName() +":");
                			int newPower = Integer.parseInt(reader.readLine());
                			chrg.setPower(newPower);
                			System.out.println(chrg.getName() +" now consumes "+ chrg.getCurrentPower() + "kWh.");
                			break;
                		}
                		// consumer not found
                		System.out.println("Consumer by name " + userChngName + " not found.");
                    }
        		}
        }
        	
        
    }
}
}
