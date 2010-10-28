// (C) 2009 Ralf Laemmel

package data.account.iop;

/**
 * An extended interface for credit accounts
 */
public interface CreditAccount extends Account {
	float getOverdraft();
	boolean adjustOverdraft(float amount);
}
