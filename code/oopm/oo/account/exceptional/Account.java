// (C) 2009-10 Ralf Laemmel

package oo.account.exceptional;

/**
 * Another account class.
 * Method arguments are validated.
 * Violations are communicated by checked and unchecked exceptions as appropriate.
 */
public class Account {

	private float balance;
	
	/**
	 * Return balance in account
	 */
	public float getBalance() {
		return balance;
	}
	
	/**
	 * Deposit some money into account
	 */
	public void deposit(float amount) 
	{	
		// Precondition
		if (amount < 0)
			throw new IllegalArgumentException();
				
		balance += amount;
	}
	
	/**
	 * Withdraw some money from account.
	 * Refuse negative amounts as nonsense.
	 * Throw an exception if an attempt is made to overdraw.
	 */
	public void withdraw(float amount) 
		throws OverdraftException
	{
		// Precondition
		if (amount < 0)
			throw new IllegalArgumentException();
		if (balance < amount)
			throw new OverdraftException();
   
		balance -= amount;
	}	
}
