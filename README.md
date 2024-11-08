# Java Compact Programming Course: Group 6
## Used IDE - Eclipse IDE 2024-06

## Lab2 Overview

This lab is developed as part of the Java Compact Programming Course. It simulates a smart energy system, incorporating exceptions handling and implementing unit test for created classes to ensure the correctness of the developing system.

### Team Members and Tasks

- **Konstantin Smirnov - 7221877**:
  - Creating classes: `EnergySource`, `ChargingStation`, `EnergySystem`, `LogFile` (with placeholders methods), `LogFileManagement` (with placeholders methods)
  - Handling Multiple Exceptions
  - Creating Unit Tests for `ChargingStation` and `EnergySource` classes

- **Sebastian Rudolph - 7215190**:
  - Creating classes: `UserInput`
  - Implemented Re-Throwing of exceptions in `LogFile` and `LogFileManager`
  - Added resource manager for all Buffered Readers in `UserInput` and `LogFile`
  - Created Unit Tests for `UserInput` and `EnergySystem` classes
    
- **Allen Mwandunga - 7219535**:
  - *specify*
---

## Project Structure - Lab2

The Lab2 is organized as follows:

```
CapstoneProject/
├── .idea/                  # Project configuration files
├── .settings/              # Settings for classes
├── bin/                    # Compiled binary files
├── logs/                   # Log files generated during execution
├── src/                    # Source code for the project
│   └── capstoneProject/    # Main package with project classes and tests
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

