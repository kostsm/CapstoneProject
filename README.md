# Java Compact Programming Course: Group 6
## Used IDE - Eclipse IDE 2024-06

## Lab1 Overview

This lab is developed as part of the Java Compact Programming Course. It simulates a smart energy system with data exchange, implementing classes for Energy sources, energy consumers, log files and the ability to control the system using user input.

### Team Members and Tasks

- **Konstantin Smirnov - 7221877**:
  - Creating classes: `EnergySource`, `ChargingStation`, `EnergySystem`, `LogFile` (with placeholders methods), `LogFileManagement` (with placeholders methods)

- **Sebastian Rudolph - 7215190**:
  - Creating classes: `UserInput`
  - Added resource manager for all Buffered Readers in `UserInput` and `LogFile`
    
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

## Project Structure - Lab1

The lab is organized as follows:

```
CapstoneProject/
├── .idea/                  # Project configuration files
├── .settings/              # Settings for classes
├── bin/                    # Compiled binary files
├── logs/                   # Log files generated during execution
├── src/                    # Source code for the project
│   └── capstoneProject/    # Main package with project classes and tests
│       ├── ChargingStation.java
│       ├── EnergySource.java
│       ├── EnergySystem.java
│       ├── LogFile.java
│       ├── LogFileManager.java
│       ├── LogFileTest.java
│       └── UserInput.java
├── .classpath              # Classpath configurations for the project
├── .project                # Project configurations
└── README.md               # Project overview and setup instructions
```


