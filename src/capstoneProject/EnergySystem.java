package capstoneProject;
import capstoneProject.ChargingStation;
import capstoneProject.EnergySource;
import java.util.ArrayList;
import java.util.List;

// Main class for the whole energy management system
public class EnergySystem {
	
	// We have energy stations, consumers and log files
	private List<ChargingStation> chargingStations;
    private List<EnergySource> energySources;
    private LogFileManager logs;
    
    public EnergySystem() {
		this.chargingStations = new ArrayList <ChargingStation>();
		this.energySources = new ArrayList <EnergySource>();

	}
    
    // Methods to modify our system: add new sources of energy or consumers
    public void addChargingStation(ChargingStation station) {
    	chargingStations.add(station);
    }
    
    public void addEnergySource(EnergySource source) {
    	energySources.add(source);
    }
    public List<EnergySource> getEnergySources() {
    	return energySources;
    }
    
    public List<ChargingStation> getChargingStations() {
    	return chargingStations;
    }
}
