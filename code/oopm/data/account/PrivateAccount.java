// (C) 2009 Ralf Laemmel

package data.account;

/**
 * An account that keeps the balance private.
 */
public class PrivateAccount {

	/**
	 * The public balance of this account.
	 * Everyone is free to mess with it.
	 */
	private float balance = 0;

	/**
	 * Read access to the balance
	 */
	public float getBalance() {
		return balance;
	}
	
	/**
	 * Deposit some money into account.
	 * In fact, don't dare checking for amount to be positive.
	 */
	public void deposit(float amount) {
		balance += amount;
	}
	
	/**
	 * Withdraw some money from account.
	 * In fact, don't dare checking for amount to be positive.
	 * Also, don't check balance to remain positive.
	 */
	public void withdraw(float amount) {
		balance -= amount;
	}	
}
