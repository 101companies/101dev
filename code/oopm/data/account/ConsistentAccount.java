// (C) 2009 Ralf Laemmel

package data.account;

/**
 * An account that keeps the balance private.
 * Some checks are performed to maintain consistency of the account.
 */
public class ConsistentAccount {

	/**
	 * The private balance of this account.
	 * Only the methods can change the balance.
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
		if (amount <= 0 || balance < 0) // test for balance not needed
			return result;
		result = amount > balance ? balance : amount;
		balance -= result;
		return result;
	}	
}
