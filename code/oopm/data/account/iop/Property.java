package data.account.iop;

public class Property implements Worth {

	private float worth = 0;
	
	public Property(float worth) {
		this.worth = worth;
	}
	
	/**
	 * Determine worth of account
	 */
	public float getWorth() {
		return worth;
	}
	
}
