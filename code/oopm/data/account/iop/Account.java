// (C) 2009 Ralf Laemmel

package data.account.iop;

/**
 * An interface for simple bank accounts
 */
public interface Account {
	float getBalance();
	void deposit(float amount);
	float withdraw(float amount);
}
