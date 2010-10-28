// (C) 2009-10 Ralf Laemmel

package oo.account.exceptional;


public class Program {

	public static void transfer(Account from, Account to, float amount) {
		try {
			from.withdraw(amount);
			to.deposit(amount);
		}
		catch (OverdraftException o) {
			// ... but what to do?
		}
	}
	
	public static void main(String[] args) {
		
		// Construct account
		Account a = new Account();
		
		// Deposit some money for bad times
		a.deposit(100);
		
		// Withdraw some money
		try {
			a.withdraw(77);
		} 
		catch (OverdraftException e) {
			// I need those 77 bucks; otherwise I am dead.
			System.exit(1);
		}
		
		// Withdraw all money that remains
		do {
			float wanted = a.getBalance();
			try {
				a.withdraw(wanted);
			}
			catch (OverdraftException e) {
				// It looks like my wife was faster; let's see what's left.
				continue;
			}
			break;
		} while (true);
		
		// Print balance
		System.out.println(a.getBalance());	
	}
}
