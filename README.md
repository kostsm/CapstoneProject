# Java Compact Programming Course: Group 6

## Every lab files are located in the corresponding branch: lab1, lab2, lab3

## Used IDE - Eclipse IDE 2024-06

## Project Overview

This project is developed as part of the Java Compact Programming Course. It simulates a smart energy system, incorporating various Java programming concepts such as OOP, exception handling, unit testing, resource management, concurrency and more.

### Team Members and Tasks

- **Konstantin Smirnov - 7221877**:
  - Creating classes: `EnergySource`, `ChargingStation`, `EnergySystem`, `Battery`, `LogFile` (with placeholders methods), `LogFileManagement` (with placeholders methods)
  - Handling Multiple Exceptions
  - Creating Unit Tests for `ChargingStation` and `EnergySource`and `Battery` classes
  - Simulation of multi-thread charging of the battery from energy sources
  - Creating [UML Diagram](//www.plantuml.com/plantuml/png/pLVDRkCs4BxxAOYzr6biFy0YDJRDPe4KoMwGRD53qSD47Cji9L8bHqcCjkzUIgHCZsYbIT53SuZbRpuEXz_yKFxW22orTJNxHlpeq5xffYLHLE2S01AqHK6ccGKW4VwO2VztMeFISx2b4-_5FLcbovz_EV7FZqR9sLypx_EyQNvIMfgdu3CwIcXqj0Vc9-Bih33QaMqBCdQz3WIB82-Ctim7zm5k6mc4SIatWPIN6csvkpUjBJ2tUdUkhT5cbb_lRP637PsZIwKL7TjxsLXJe7FNIkEyyl_syd3QCdmu0bA5-1w8qEv49sfyPlPucfqZh0NgqZy5ZIEPAHongEzIGvs2250DpxVc2Qs7bQQ05Qsrg6d0fMaVAZoCzJJwdJE72r500uMYNN0saSCIwJFKFXCyajDY2xR4UVWuhqshQRyvJuEqNS7fjixjmmuNwGWXiu4Nb3ei22ONCqnhHhjcIiPRQwG_hJBQ4pQLWKpIQKvb-WwwDkKdDUZz41FuVaWbImEhx54kM90BCHtWGcIRl35p7l-wQp8ypqCwRpJVmFDHayEufEd-3xPH5G31vRCl5rsEszgsUiu7q4LNLSft2q5tTfRriMqwyPKf0eN1BBbXd3HvTcC2fWdzKQ_n4QlOY_-oA4R76TS5EqE0yj3-s-AuZbl-Zmk4ZLLM7SmGIwFW4lS9KexEHjAl0niFkdHFFMpTzMIgMQF78ARkHf1od7f7Gwf7bfVlIDT_UCe1ce_OGwE_4YSE5icZ_HC_qooa8uh0CZ5WCGaR__mvxr7ylLLDZPgEs2HG1tNrM7tqpycHHpFHutxpunuNOknjkZ4wDrFDSJbudo9urm5dOOTt8VyDiZLFLX7sbXgTWv9tVpcCcEluE2IM2Ny3sY-qN7zkYkziXKKWzBPNwyGhgJQF_MeVsItGzd0TEbAwhmfcuUibLaYvXum7jjYgnrSGJOFwhSU9qWG9RzgAL5EXdptOzDNEPCpDGqLZGePc6f0rgypSHPRgwI6nNpJlsFjlUNydvoTvFFjhLUazo8x9JKErSFGLKK-luY-UhUk-VzrQymGFLTQBCTukIRVXhTpmukQMr_omUVihVZwon7VYdGI25JtJkn3YbIQq6oXGtBLQTsUPgGmQ0ipliJkimeJTgiRDsCyvoqm9NwtMwQUOT-Qbx5ScZAzCk5yvl1N4TTp8By-ZZD7uFqfYzz1n1fzDBtAE6tc9USuMjAnw8PBggcugh577Isp6ubgj_bodd8tFnQrTwvYwCKw5TWSjnNbPMYoFUJRBvO7CMt2Pzvp1bF_MctEXwrwidChbZhnO4bBs4KF5QuB7cukTov5JU0eP8yMGa_BCKJS4VBX71krowREm_pxZIyVM6Pi94zKQhZ-NSySgJXDZubxcj9iDMfHt23BLTyxha7_YN7sPgFChlKMh2BGFU6DDBV2Pq6gemgJ4OLR-yCb2ZQc1Wd0HzNu-ekTPd5YU50xVGpmCuQUDO3fQjyUNMX5r4xGmMcCHqaj6E08jmSguPuzvJss17caxeVtY-p6fKh_XS9dqDzqYtUyBWLIi5W8iTehAlhxQScjQcdr0BTkw-Xi0) of the classes in the project
  - Making a list of requirements

- **Sebastian Rudolph - 7215190**:
  - Creating classes: `UserInput`, `AppWindow`
  - Implemented Re-Throwing of exceptions in `LogFile` and `LogFileManager`
  - Added resource manager for all Buffered Readers in `UserInput` and `LogFile`
  - Created Unit Tests for `UserInput` and `EnergySystem` classes
  - Simulation of multi-thread draining of the battery by consumers
  - Integrated Graphic User Interface to work with all previously created functionalities
  - Initial configuration loading
    
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
├── config/                 # Starting configurations for the simulation
├── src/                    # Source code for the project
│   ├── layout/             # Grid-style layouts with SpringLayout
│   │   └── SpringUtilities.java
│   └── capstoneProject/    # Main package with project classes and tests
│       ├── AppWindow.java
│       ├── Battery.java
│       ├── BatteryTest.java
│       ├── ChainException.java
│       ├── ChargingStation.java
│       ├── ChargingStationTest.java
│       ├── EnergySource.java
│       ├── EnergySourceTest.java
│       ├── EnergySystem.java
│       ├── EnergySystemTest.java
│       ├── FileProcessor.java
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

