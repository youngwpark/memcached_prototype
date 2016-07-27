import java.lang.InterruptedException;
import java.lang.Thread;
import java.lang.Runnable;

public class OppGenerator 
{

	private int counter = 0;
	private int timerInterval = 10;
	
	public OppGenerator()
	{
		
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
