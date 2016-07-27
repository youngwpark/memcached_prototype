import java.lang.InterruptedException;
import java.lang.Thread;
import java.util.concurrent.ExecutionException;
import java.lang.Runnable;

public class OppGenerator 
{
	private int counter = 0;
	private int timerInterval = 1;	// 1 second sleep
	private MyCacheController cacheController;
	
	public OppGenerator()
	{
		
	}
	
	public OppGenerator(final MyCacheController cacheController)
	{
		this.cacheController = cacheController;
	}
	
	public void generateOppDataSet()
	{
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				while (counter < 100)
				{
					try
					{
						// Generate a new opportunity
						String key = "" + "_Counter" + counter;
						String name = "" + "_Counter" + counter;
						double rank = 0.123 + counter;
						final Opportunity opp = new Opportunity(name, rank);
						try 
						{
							cacheController.set(key, opp);
						} 
						catch (ExecutionException e) 
						{
							System.out.println("ExecurtionException occurred while trying to save Opportunity with name[" + name + "], rank[" + rank + "]");
							e.printStackTrace();
						}
						Thread.sleep(timerInterval * 1000);
					}
					catch (InterruptedException ex)
					{
						ex.printStackTrace();
					}
					counter++;
				}
			}
		});
		
		//t.setDaemon(true);
		t.start();
	}
	
}
