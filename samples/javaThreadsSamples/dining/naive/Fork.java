package dining.naive;

public final class Fork
{
	private boolean taken = false;

	public boolean isTaken()
	{
		return taken;
	}	
	
	public void take() throws InterruptedException
	{
		Thread.sleep(100); // slow fork
		while(taken) { };
		taken = true;
	}
	
	public void drop() 
	{
		taken = false;
	}
}
