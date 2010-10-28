// (C) 2009 Ralf Laemmel

package data.account.iop;

/**
 * An account with zero credit.
 * Some checks are performed to maintain consistency of the account.
 */
public class ZeroCreditAccount implements Account {

	// The private balance of this account.
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
		if (amount <= 0)
			return result;
		result = amount > balance ? balance : amount;
		balance -= result;
		return result;
	}	
}
