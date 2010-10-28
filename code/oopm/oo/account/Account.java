// (C) 2009 Ralf Laemmel

package oo.account;

/**
 * An account with zero credit.
 * Some checks are performed to maintain consistency of the account.
 */
public class Account {

	// The balance of this account.
	protected float balance = 0;

	/**
	 * Read access to the balance
	 */
	public float getBalance() { return balance; }
	
	/**
	 * Print a statement about the account
	 */
	public void statement() {
		System.out.println("Account's balance is " + getBalance() + ".");
	}
	
	/**
	 * Deposit some money into account.
	 * Ignore any attempts to deposit negative amounts.
	 */
	public void deposit(float amount) {
		if (amount <= 0)
			return;
		balance += amount;
	}
	
	/**
	 * Withdraw some money from account.
	 * Ignore any attempts to withdraw negative amounts.
	 * Limit withdrawals to available positive balance.
	 */
	public float withdraw(float amount) {
		float result = 0;
		if (amount <= 0)
			return result;
		result = amount > balance ? balance : amount;
		balance -= result;
		return result;
	}	
}
