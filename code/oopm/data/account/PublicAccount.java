// (C) 2009 Ralf Laemmel

package data.account;

/**
 * Arguably, a too public, unchecked account.
 */
public class PublicAccount {

	/**
	 * The public balance of this account.
	 * Everyone is free to mess with it.
	 */
	public float balance = 0;

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
