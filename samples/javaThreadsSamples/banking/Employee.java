package banking;

public class Employee implements Runnable {
	
	private Account companyCreditCard;

	// Associate employee with a credit card (of the company)
	public Employee(Account cc) {
		companyCreditCard = cc;
	}

	//
	// Try to get as much beer as possible.
	// Use varying amount of money at times.
	//
	public void run() {
		int total = 0;
		int beer;
		while (!Thread.interrupted()) {
			beer = 1 + (int) (100 * Math.random());
			if (companyCreditCard.withdraw(beer)) {
				System.out.println("Got $" + beer);
				total += beer;
			} else {
				System.out.println("Didn't get $" + beer);
				System.out.println("Out of money after spending $" + total);
				return;
			}
		}
	}
}
