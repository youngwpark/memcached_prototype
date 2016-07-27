import java.lang.InterruptedException;
import java.lang.Thread;
import java.util.concurrent.ExecutionException;
import java.lang.Runnable;

public class OppGenerator 
{
	private int threadCountLimit = 0;
	private int threadCounter = 0;
	private int timerInterval = 1;	// 1 second sleep
	private MyCacheController cacheController;
	
	public OppGenerator()
	{
		
	}
	
	public OppGenerator(final MyCacheController cacheController, final int threadCountLimit)
	{
		this.cacheController = cacheController;
		this.threadCountLimit = threadCountLimit;
	}
	
	public void generateOppDataSet()
	{
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() 
			{
		        MyCacheController.logMessage("Starting the OppGenerator thread.");

				// TODO Auto-generated method stub
				while (threadCounter < threadCountLimit)
				{
					try
					{
						// Generate a new opportunity
						String key = "" + "_Counter" + threadCounter;
						String name = "" + "_Counter" + threadCounter;
						double rank = 0.123 + threadCounter;
						final Opportunity opp = new Opportunity(name, rank);
						MyCacheController.logMessage("Adding Opportunity with key[" + key + "], name[" + name + "], rank[" + rank + "] to MemCached.");
						try 
						{
							cacheController.add(key, opp);
						} 
						catch (ExecutionException e) 
						{
							MyCacheController.logMessage("ExecutionException occurred while trying to save Opportunity with name[" + name + "], rank[" + rank + "]");
							e.printStackTrace();
							return;
						}
						MyCacheController.logMessage("Thread sleeping for " + timerInterval + " seconds");
						Thread.sleep(timerInterval * 1000);
					}
					catch (InterruptedException ex)
					{
						ex.printStackTrace();
						
						return;
					}
					threadCounter++;
				}
			}
		});
		
		//t.setDaemon(true);
		t.start();
	}
	
}
