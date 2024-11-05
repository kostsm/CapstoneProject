package capstoneProject;

public class Battery {
	private final String name;
	private final int maxPower;
	private int currentPower;

	public Battery(String name, int capacity) {
		this.name = name;
		this.maxPower = capacity;
		this.currentPower = 0;
	}
	
	public String getName() {
		return name;
	}

	public synchronized void charge(int num) {
		if (num < 0) {
			throw new IllegalArgumentException("Charge amount should be >0");
		}

		int charge = currentPower + num;
		if (charge > maxPower) {
			currentPower = maxPower;
		}
		currentPower = charge;

		System.out.println(Thread.currentThread().getName() + " charging " + num + " to " + name);
		System.out.println("Battery " + name + " charged: " + currentPower + "/" + maxPower);
	}
	
	public synchronized void drain(int num) {
		if (num < 0) {
			throw new IllegalArgumentException("Drain amount should be >0");
		}

		int charge = currentPower - num;
		
		if (charge < 0) {
			charge = 0;
		}
		currentPower = charge;

		System.out.println(Thread.currentThread().getName() + " draining " + num + " from  " + name);
		System.out.println("Battery " + name + " charged: " + currentPower + "/" + maxPower);
	}

	public synchronized double getCurrentPower() {
		return currentPower;
	}
}
