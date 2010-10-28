// (C) 2009 Ralf Laemmel

package data.account.iop;

/**
 * An account that helps with Ponzi schemes.
 * http://en.wikipedia.org/wiki/Ponzi_scheme
 * This sort of account does not need its own balance.
 * Of course, there is a getter that mimics a balance.
 */
public class PonziAccount implements Account, Worth {
	
	private float balance = 0; // The apparent balance
	private Account ponzi = null; // The account of Ponzi

	// Ponzi typically creates this sort of account.
	public PonziAccount(Account ponzi) {
		this.ponzi = ponzi;
	}
	
	/**
	 * Read access to the balance
	 */
	public float getBalance() {
		return balance;
	}
	
	/**
	 * Deposit some money into account.
	 */
	public void deposit(float amount) {
		ponzi.deposit(amount);
		if (amount <= 0)
			return;
		balance += amount;
	}
	
	/**
	 * Withdraw some money from account.
	 */
	public float withdraw(float amount) {
		float result = 0;
		if (amount <= 0)
			return result;
		result = amount > balance ? balance : amount;
		result = ponzi.withdraw(result);
		balance -= result;		
		return result;
	}	
	
	/**
	 * Determine worth of account
	 */
	public float getWorth() {
		return 0;
	}
}
