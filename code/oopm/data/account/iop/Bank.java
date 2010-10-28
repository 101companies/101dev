package data.account.iop;

public class Bank {

	private Account[] accounts = new Account[42];
	private Property[] properties = new Property[77];
	
	/**
	 * Total the balance of all accounts.
	 * We use interface polymorphism here.
	 */
	public float totalBalance() {
		float result = 0;
		for (Account a : accounts)
			result += a.getBalance();
		return result;
	}
	
	/**
	 * Total the overdraft allowances for all applicable accounts.
	 * We use down-cast here.
	 */
	public float totalOverdraft() {
		float result = 0;
		for (Account a : accounts)
			if (a instanceof CreditAccount)
				result += ((CreditAccount)a).getOverdraft();
		return result;
	}

	/**
	 * Total the worth for all applicable objects.
	 */
	public float totalWorth() {
		float result = 0;
		for (Account a : accounts) {
			if (a instanceof Worth)
				result += ((Worth)a).getWorth();
		}
		for (Property p : properties)
			result += p.getWorth();
		return result;
	}
		
	public static void main(String[] args) {
	}

}
