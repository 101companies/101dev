// (C) 2009 Ralf Laemmel

package data.account.iop;

/**
 * An account with adjustable credit.
 * Some checks are performed to maintain consistency of the account.
 */
public class SimpleCreditAccount implements CreditAccount {

	// The private balance of this account.
	private float balance = 0;

	// The arranged overdraft of this account.
	private float overdraft = 0;
		
	/**
	 * Read access to the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * Read access to the overdraft
	 */
	public float getOverdraft() {
		return overdraft;
	}

	/**
	 * Adjust overdraft.
	 * Ignore any attempts to install negative overdraft.
	 * Do not allow overdraft to be less than current credit.
	 */
	public boolean adjustOverdraft(float amount) {
		if (amount < 0)
			return false;
		if (balance < -amount)
			return false;
		overdraft = amount;
		return true;
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
	 * Observe overdraft.
	 */
	public float withdraw(float amount) {
		float result = 0;
		if (amount <= 0)
			return result;
		result = balance - amount < -overdraft ? balance + overdraft : amount;
		balance -= result;
		return result;
	}
}
