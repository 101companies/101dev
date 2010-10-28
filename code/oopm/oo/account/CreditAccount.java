// (C) 2009 Ralf Laemmel

package oo.account;

/**
 * An account with adjustable credit.
 * Some checks are performed to maintain consistency of the account.
 */
public class CreditAccount extends Account {

	// The arranged overdraft of this account.
	private float overdraft = 0;
		
	/**
	 * Print a statement about the account
	 */
	public void statement() {
		super.statement();
		System.out.println("Account's overdraft is " + getOverdraft() + ".");
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
