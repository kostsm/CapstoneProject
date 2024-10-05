package capstoneProject;

import java.util.List;

// Main class for the whole energy management system
public class EnergySystem {
	
	// We have energy stations, consumers and log files
	private List<ChargingStation> chargingStations;
    private List<EnergySource> energySources;
    private LogFileManager logs;
    
    // Methods to modify our system: add new sources of energy or consumers
    public void addChargingStation(ChargingStation station) {
    	chargingStations.add(station);
    }
    
    public void addEnergySource(EnergySource source) {
    	energySources.add(source);
    }
}
