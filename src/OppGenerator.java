import java.lang.InterruptedException;
import java.lang.Thread;
import java.util.concurrent.ExecutionException;

import model.Job;
import model.Opportunity;
import model.SoftwareEngineer;
import model.enums.CompanyEnum;
import model.enums.ExperienceLevelEnum;
import model.enums.JobTypeEnum;
import model.enums.LocationEnum;

import java.lang.Runnable;

public class OppGenerator 
{
	private int threadCountLimit = 0;
	private int threadCounter = 0;
	private int idCounter = 0;
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
		
		final Job softwareEngineerJob_entry = new SoftwareEngineer("Junior Programmer", 
				JobTypeEnum.PART_TIME, 
				LocationEnum.DENVER, 
				CompanyEnum.BITS, 
				ExperienceLevelEnum.ENTRY_LEVEL);
		
		final Job softwareEngineerJob_mid = new SoftwareEngineer("MidLevel Programmer", 
				JobTypeEnum.FULL_TIME, 
				LocationEnum.SEATTLE, 
				CompanyEnum.AMAZON, 
				ExperienceLevelEnum.MID_LEVEL);
		
		final Job softwareEngineerJob_senior = new SoftwareEngineer("Senior Programmer", 
				JobTypeEnum.FULL_TIME, 
				LocationEnum.VIRGINIA, 
				CompanyEnum.CACI, 
				ExperienceLevelEnum.SENIOR_LEVEL);
		
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
						for (int i=1; i<=3; i++)
						{
							Opportunity opp = null;
							Job job = null;
							switch (i)
							{
								case 1:
									job = softwareEngineerJob_entry;
									break;
								case 2:
									job = softwareEngineerJob_mid;
									break;
								case 3:
									job = softwareEngineerJob_senior;
									break;
								default:
									break;
							}
							long id = idCounter++;
							double rank = 0.123 + threadCounter;
							
							final String key = "Thread" + threadCounter + "_Type" + job.getExperienceLevel().toString();
							opp = new Opportunity(id, key, rank, job);
							MyCacheController.logMessage("Adding Opportunity with key[" + key + "], id[" + id + "], rank[" + rank + "], opp[" + opp.toString() + "] to MemCached.");
							try 
							{
								cacheController.add(key, opp);
							} 
							catch (ExecutionException e) 
							{
								MyCacheController.logMessage("ExecutionException occurred while trying to save Opportunity with id[" + id + "], rank[" + rank + "]");
								e.printStackTrace();
								return;
							}
							MyCacheController.logMessage("Thread sleeping for " + timerInterval + " seconds");
						}
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
