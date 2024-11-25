# Java Compact Programming Course: Group 6

## Every lab files are located in the corresponding branch: lab1, lab2, lab3

## Used IDE - Eclipse IDE 2024-06

## Project Overview

This project is developed as part of the Java Compact Programming Course. It simulates a smart energy system, incorporating various Java programming concepts such as OOP, exception handling, unit testing, resource management, concurrency and more.

### Team Members and Tasks

- **Konstantin Smirnov - 7221877**:
  - Creating classes: `EnergySource`, `ChargingStation`, `EnergySystem`, `Battery`, `LogFile` (with placeholders methods), `LogFileManagement` (with placeholders methods)
  - Handling Multiple Exceptions
  - Creating Unit Tests for `ChargingStation` and `EnergySource` classes
  - Simulation of multi-thread charging of the battery from energy sources

- **Sebastian Rudolph - 7215190**:
  - Creating classes: `UserInput`
  - Implemented Re-Throwing of exceptions in `LogFile` and `LogFileManager`
  - Added resource manager for all Buffered Readers in `UserInput` and `LogFile`
  - Created Unit Tests for `UserInput` and `EnergySystem` classes
  - Simulation of multi-thread draining of the battery by consumers
    
- **Allen Mwandunga - 7219535**:
  - Enhanced the `LogFileManager` class with the following implementations:
    - `createLog()`: Handles the creation of new log files.
    - `moveLog()`: Manages the transfer of log files to specific directories.
    - `openLog()`: Facilitates opening log files.
  - Extended the LogFile class with:
    - `readData()`: Reads data from log files.
    - `writeData()`: Writes data to log files.
    - `Getters and setters` for log file properties.
  - Contributed to the Concurrency_Task.pdf, addressing:
    - Comparison of concurrency models: Highlighting pros and cons.
    - Differences between Concurrency vs Parallelism.
    - Usage of Blocking vs Non-blocking Concurrency Algorithms.

---

## Project Structure - CapstoneProject

The project is organized as follows:

```
CapstoneProject/
├── .idea/                  # Project configuration files
├── .settings/              # Settings for classes
├── bin/                    # Compiled binary files
├── logs/                   # Log files generated during execution
├── src/                    # Source code for the project
│   └── capstoneProject/    # Main package with project classes and tests
│       ├── Battery.java
│       ├── BatteryTest.java
│       ├── ChainException.java
│       ├── ChargingStation.java
│       ├── ChargingStationTest.java
│       ├── EnergySource.java
│       ├── EnergySourceTest.java
│       ├── EnergySystem.java
│       ├── EnergySystemTest.java
│       ├── LogFile.java
│       ├── LogFileManager.java
│       ├── LogFileManagerTest.java
│       ├── MultipleExceptions.java
│       ├── MultipleExceptionsTest.java
│       ├── TestSuite.java
│       ├── UserInput.java
│       └── UserInputTest.java
├── .classpath              # Classpath configurations for the project
├── .project                # Project configurations
└── README.md               # Project overview and setup instructions
```

