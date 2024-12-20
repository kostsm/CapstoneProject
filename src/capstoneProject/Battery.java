package capstoneProject;

public class Battery {
	private final String name;
	final int maxPower;
	private double currentPower;

	public Battery(String name, int capacity) {
		this.name = name;
		this.maxPower = capacity;
		this.currentPower = 0;
	}
	
	public String getName() {
		return name;
	}

	public synchronized void charge(double chargeAmount) {
		if (chargeAmount < 0) {
			throw new IllegalArgumentException("Charge amount should be >0");
		}

		double charge = currentPower + chargeAmount;
		if (charge > maxPower) {
			charge = maxPower;
		}
		currentPower = charge;

		System.out.println(Thread.currentThread().getName() + " charging " + chargeAmount + " kWh to " + name);
		System.out.println("Battery " + name + " charged: " + currentPower/maxPower*100 + "%");
	}
	
	public synchronized void drain(double num) {
		if (num < 0) {
			throw new IllegalArgumentException("Drain amount should be >0");
		}

		double charge = currentPower - num;
		
		if (charge < 0) {
			charge = 0;
		}
		currentPower = charge;

		System.out.println(Thread.currentThread().getName() + " draining " + num + " kWh from  " + name);
		System.out.println("Battery " + name + " charged: " + currentPower/maxPower*100 + "%");
	}

	public synchronized double getCurrentPower() {
		return currentPower;
	}
}
