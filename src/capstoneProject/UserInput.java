package capstoneProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import capstoneProject.EnergySource;
import capstoneProject.ChargingStation;
import capstoneProject.EnergySystem;
import capstoneProject.LogFileManager;



public class UserInput {	
	public static void main(String args[])throws IOException
    {	
		System.out.println("Current working directory: " + new File(".").getAbsolutePath());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// Create first Energy source
		System.out.println("Welcome to your new smart Home. Please create a new energy Source by naming it:");
        // Using Scanner for Getting Input from User
		// Enter data using BufferReader
		String sourceName="";
		String sourceType = "";
		int sourceMaxPower = 0;
		String consumerName = "";
		String consumerLocation = "";
		int consumerMaxPower = 0;
		Battery mainBattery = new Battery("mainBattery", 1000);
        try{

	        // Reading data using readLine
	        sourceName = reader.readLine();
	        System.out.println("You entered the name " + sourceName);
	        System.out.println("Please specify the type of "+ sourceName);
	        
	        sourceType = reader.readLine();
	        
	        System.out.println("Please specify the sources' maximum power production in kWh:");
	        
	       
	        sourceMaxPower = Integer.parseInt(reader.readLine());
	        
	        System.out.println("New power source " + sourceName + " of type " + sourceType + " with a max. production of " + sourceMaxPower + "kWh created");
	        //Create first consumer
	        System.out.println("Please specify the name of a consumer:");
	        consumerName = reader.readLine();
	        System.out.println("Please specify the location of " + consumerName + ":");
	        consumerLocation = reader.readLine();
	        System.out.println("Please specify the maxPowerConsumption of " + consumerName + " in kWh:");
	        consumerMaxPower = Integer.parseInt(reader.readLine());
	        
	        
	        System.out.println("New charging station " + consumerName + " at " + consumerLocation + " with a max. consumtion of " + consumerMaxPower + "kWh created");
	        
	        
        }catch(Exception e) {
        	System.out.println("Failed to create Buffered Reader. Creating emtpy entitys.");
        }
        EnergySource engSource = new EnergySource(sourceName, sourceType, sourceMaxPower);
    	ChargingStation chrgStation = new ChargingStation(consumerName, consumerLocation, consumerMaxPower, mainBattery);
     // establish energy system and add src and consumer
        EnergySystem engSys = new EnergySystem();
        engSys.addChargingStation(chrgStation);
        engSys.addEnergySource(engSource);
        
        List<ChargingStation> chargingStations;
        List<EnergySource> energySources;
        
        LogFileManager logMng = new LogFileManager();
        
        File dir = new File("logs/");
        
        // Start automatic data exchange
        new Thread(() -> {
        	while (true) {
        		try {
        			Thread.sleep(10000);
        			
        			for (EnergySource src : engSys.getEnergySources()) {
        				src.dataExchange();
        			}
        			
        			for (ChargingStation chrg : engSys.getChargingStations()) {
        				chrg.dataExchange();
        			}

        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		} catch (ChainException e) {
                    throw new RuntimeException(e);
                } catch (MultipleExceptions me) {
					me.printStackTrace();
				}
            }
        }).start();
        
        // run user interface
        try {
	        while(true) {
	        	System.out.println("\n Welcome to the smart home user interface. Your options:");
	        	System.out.println("<add src> to add another power source");
	        	System.out.println("<add cons> to add another consumer");
	        	System.out.println("<adj src> to adjust a power sources power output in %");
	        	System.out.println("<adj cons> to adjust a consumers power consumption in %");
	        	System.out.println("<save log> to save a logfile of specified equipment");
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
	        			// Start automatic data exchange
	        			new Thread(newEngSource).start();
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
	        			ChargingStation newChrgStation = new ChargingStation(useName, useLocation, usePower, mainBattery);
	        			engSys.addChargingStation(newChrgStation);
	        			System.out.println("New consumer added.");
	        			// Start automatic data exchange
	        	        new Thread(newChrgStation).start();
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
	                			if (newPower<=100&&newPower>=0) {
	                				src.setPower(newPower);
	                				System.out.println(src.getName() +" now produces "+ src.getCurrentPower() + "kWh.");
	                				/*try {
	                					src.dataExchange();
	                				}
	                				catch (MultipleExceptions me) {
	                					System.err.println("Error exchanging data for energy source " + src.getName());
	                					for (Exception e: me.getExceptions()) {
	                						e.printStackTrace();	                					}
	                				}*/
	                			}else {
	                				System.out.println("Please specify a percentage between 0 and 100");
	                			}
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
	                			if (newPower<=100&&newPower>=0) {
		                			chrg.setPower(newPower);
		                			System.out.println(chrg.getName() +" now consumes "+ chrg.getCurrentPower() + "kWh.");
		                			/*try {
		                				chrg.dataExchange();
		                			}
		                			catch (MultipleExceptions me) {
	                					System.err.println("Error exchanging data for charging station " + chrg.getName());
	                					for (Exception e: me.getExceptions()) {
	                						e.printStackTrace();	                					}
	                				}*/
	                			}else {
	                				System.out.println("Please specify a percentage between 0 and 100");
	                			}
	                			break;
	                		}
	                		// consumer not found
	                		System.out.println("Consumer by name " + userChngName + " not found.");
	                    }
	        		}
	        	}else if(input.equals("save log")) {
	        		System.out.println("Please specify the name of the equipment you want to log: ");
	        		String equipmentName = reader.readLine();
	        		if(equipmentName.equals("back")) {
	        			// back to interface
	        			continue;
	        		}else {
	        			try {
		        			for (ChargingStation chrg : chargingStations) {
		        				if(chrg.getName().equals(equipmentName)) {
		        					LocalDate lt = LocalDate.now(); 
		        					logMng.createLog(equipmentName, lt);
		        					break;
		        				}
		        			}for (EnergySource src : energySources) {
		        				if(src.getName().equals(equipmentName)) {
		        					LocalDate lt = LocalDate.now(); 
		        					logMng.createLog(equipmentName, lt);
		        					break;
		        				}
		        				System.out.println("No equipment by name " + equipmentName + "found.");
		        			}
	        			}catch(IOException e1) {
	        				e1.printStackTrace();
	        				System.out.println("Please try again with a different equipment name");
	        				continue;
	        			}
	        		}
	        	}else if (input.equals("move log")) {
	        		System.out.println("Please specify the name of the equipment you want to load a logfile of: ");
	        		String equipmentName = reader.readLine();
	        		if(equipmentName.equals("back")) {
	        			// back to interface
	        			continue;
	        		}else {
	        			try {
		        			boolean foundEquipment=false;
		        			for (ChargingStation chrg : chargingStations) {
		        				if(chrg.getName().equals(equipmentName)) {
		        					foundEquipment=true;
		        					break;
		        				}
		        			}for (EnergySource src : energySources) {
		        				if(src.getName().equals(equipmentName)) {
		        					foundEquipment=true;
		        					break;
		        				}	
		        			}
		        			if(foundEquipment) {
		        				Pattern pattern = Pattern.compile(equipmentName, Pattern.CASE_INSENSITIVE);
		        				File[] directoryListing = dir.listFiles();
		        				
		        				List<File> listMatches = new ArrayList<File>();
		        				if (directoryListing != null) {
		        				    for (File logfile : directoryListing) {
		        				    	Matcher matcher = pattern.matcher(logfile.getName());
		        				    	boolean matchFound = matcher.find();
		        				    	if(matchFound)  {
		        				    		listMatches.add(logfile);
		        				    	}
		        				    }
		        				    if(listMatches.size()>0) {
		        				    	System.out.println("Please choose the logfile by number:");
		        				    	for (int i = 0; i < listMatches.size(); i++) {
		        				    		System.out.println(i+" - " + listMatches.get(i).getName());
		        				    	}
		        				    	int logfileNumber = Integer.parseInt(reader.readLine());
		        				    	if (logfileNumber<=listMatches.size()) {
		        				    		
		        				    		logMng.openLog(equipmentName,LocalDate.now());
		        				    	}
		        				    }
		        				  } else {
		        					  System.out.println("Invalid Path:" + dir.getName());
		        				  }
		        			}else {
		        			System.out.println("No equipment by name " + equipmentName + "found.");
		        			}
	        			}catch(IOException e1) {
	        				e1.printStackTrace();
	        				System.out.println("Please try again with a different equipment name");
	        				continue;
	        			}
	        		}
	        	}
	        }
        }catch(Exception e) {
        	System.out.println("Failed to create Buffered Reader. Ending Program.");
        }
    }
}
