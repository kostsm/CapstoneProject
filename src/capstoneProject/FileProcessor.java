import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    public static void main(String[] args) {
        String fileName = "data.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLine(String line) {
        String[] attributes = line.split(";");
        String objectType = null;
        String name = null, location = null, typeOfSource = null, battery_name = null;
        float maxPowerConsumption = 0, maxPowerProduction = 0;
        int capacity = 0;

        for (String attribute : attributes) {
            String[] keyValue = attribute.split("=");
            String key = keyValue[0].replaceAll("\"", "").trim();
            String value = keyValue[1].replaceAll("\"", "").trim();
            

            switch (key) {
                case "object_type":
                    objectType = value;
                    break;
                case "name":
                    name = value;
                    break;
                case "location":
                    location = value;
                    break;
                case "maxPowerConsumption":
                    maxPowerConsumption = Float.parseFloat(value);
                    break;
                case "typeOfSource":
                    typeOfSource = value;
                    break;
                case "maxPowerProduction":
                    maxPowerProduction = Float.parseFloat(value);
                    break;
                case "capacity":
                    capacity = Integer.parseInt(value);
                    break;
                case "battery_name":
                	battery_name = value;
            }
        }

        switch (objectType) {
            case "chargingStation":
                ChargingStation chargingStation = new ChargingStation(name, location, maxPowerConsumption,);
                System.out.println(chargingStation);
                break;
            case "powerSource":
                EnergySource powerSource = new EnergySource(name, typeOfSource, maxPowerProduction);
                System.out.println(powerSource);
                break;
            case "battery":
                Battery battery = new Battery(name, capacity);
                System.out.println(battery);
                break;
        }
    }
}