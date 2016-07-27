import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.ArrayList;

public class MyCacheController
{
    private static String MEM_CACHED_ADDRESS = "127.0.0.1:11211";
    private static int DEFAULT_TIME_TO_LIVE = 0;
    private static boolean isLoggerEnabled = false;
    
    //Model
    private MyCache cache = null;

    public MyCacheController(final MyCache cache)
    {
        MemcachedClient memCachedClient = null;
        try 
        {   
            memCachedClient =  new MemcachedClient(AddrUtil.getAddresses(MEM_CACHED_ADDRESS));
        }
        catch (IOException e)
        {   
            e.printStackTrace();
        }
        this.cache = cache;
        this.cache.setMemCachedClient(memCachedClient);
    }

    public MemcachedClient getMemCachedClient()
    {
    	return cache.getMemCachedClient();
    }

    public String getNamespaceKey(final String key)
    {
        return cache.getNamespaceKey(key);
    }

    protected static void logMessage(final String message)
    {
        if (MyCacheController.isLoggerEnabled)
        {
            // TODO Need to implement a logger
        }
        else
        {
  	    System.out.println(message);
        }
    }
           
    public void handleCommand(final String command, final String key, final String value)
    {
        try
        {
            CommandEnum commandEnum = CommandEnum.toEnum(command);
            if (commandEnum == null)
            {
                MyCacheController.logMessage("Failed to find CommandEnum enum for string[" + command + "]");
            }
            MyCacheController.logMessage("Starting to establish MemcachedClient Connection");
            switch (commandEnum)
            {
                case GET:
                    get(key);
                    break;
                case SET:
                    set(key, value);
                    break;
                case ADD:
                    add(key, value);
                    break;
                case REPLACE:
                    replace(key, value);
                    break;
                case DELETE:
                    delete(key);
                    break;
                default:
		    int count = getOpportunityCount();
		    MyCacheController.logMessage("Total Opportunity Count[" + count + "]");
                    break;
            }
        }
        catch (InterruptedException e1)
        {
            e1.printStackTrace();
        }
        catch (ExecutionException e2)
        {
            e2.printStackTrace();
        }
    }
    
    public Object get(String key)
    {
        MyCacheController.logMessage("Command = GET, Key Argument = " + key);
        Object o = getMemCachedClient().get(getNamespaceKey(key));
        if(o == null)
        {
            MyCacheController.logMessage("Cache MISS for KEY[" + key + "]");
        }
        else
        {
            MyCacheController.logMessage("Cache HIT for KEY[" + key + "], VALUE[" + o + "]");
        }
        return o;
    }

    public Boolean set(String key, int timeToLive, final Object o) throws InterruptedException, ExecutionException
    {
        MyCacheController.logMessage("Command = SET, Key Argument = " + key + ", TimeToLive = " + timeToLive + ", Value Argument = " + o);
        //Future<Boolean> result = getMemCachedClient().set(getNamespaceKey(key), timeToLive, o);
        Opportunity opp1 = new Opportunity("MyOpp1", 1.234);
	Future<Boolean> result = getMemCachedClient().set(getNamespaceKey(key), timeToLive, opp1);
        Boolean success = result.get();
        MyCacheController.logMessage("Result of SET Command[" + success + "]");
        return success;
    }

    public Boolean set(String key, final Object o) throws InterruptedException, ExecutionException
    {
        return this.set(key, DEFAULT_TIME_TO_LIVE, o);
    }

    public Boolean add(final String key, final int timeToLive, final Object o) throws InterruptedException, ExecutionException
    {
        MyCacheController.logMessage("Command = ADD, Key Argument = " + key + ", TimeToLive = " + timeToLive + ", Value Argument = " + o);
        Future<Boolean> result = getMemCachedClient().add(getNamespaceKey(key), timeToLive, o);
        Boolean success = result.get();
        MyCacheController.logMessage("Result of ADD Command[" + success + "]");
        return success;
    }

    public Boolean add(final String key, final Object o) throws InterruptedException, ExecutionException
    {
        return this.add(key, DEFAULT_TIME_TO_LIVE, o);
    }


    public Boolean replace(final String key, final int timeToLive, final Object o) throws InterruptedException, ExecutionException
    {
        MyCacheController.logMessage("Command = REPLACE, Key Argument = " + key + ", TimeToLive = " + timeToLive + ", Value Argument = " + o);
        Future<Boolean> result = getMemCachedClient().replace(getNamespaceKey(key), timeToLive, o);
        Boolean success = result.get();
        MyCacheController.logMessage("Result of REPLACE Command[" + success + "]");
        return success;
    }

    public Boolean replace(final String key, final Object o) throws InterruptedException, ExecutionException
    {
        return this.replace(key, DEFAULT_TIME_TO_LIVE, o);
    }

    public Boolean delete(String key) throws InterruptedException, ExecutionException
    {
        MyCacheController.logMessage("Command = DELETE, Key Argument = " + key);
        Future<Boolean> result = getMemCachedClient().delete(getNamespaceKey(key));
        Boolean success = result.get();
        MyCacheController.logMessage("Result of DELETE Command[" + success + "]");
        return success;
    }

    public void shutdown()
    {
        getMemCachedClient().shutdown();
    }

    public List<Opportunity> getAllOpportunities()
    {
        List<Opportunity> opportunities = new ArrayList<Opportunity>();
	List<Opportunity> retrievedOpps = (List<Opportunity>) getMemCachedClient().get("Opportunity");
	if (retrievedOpps != null && !retrievedOpps.isEmpty())
	{
	    opportunities.addAll(retrievedOpps);
	}
	return opportunities; 
    }

    public int getOpportunityCount()
    {
	List<Opportunity> opps = getAllOpportunities();
	return (opps != null) ? opps.size() : 0;
    }
 
}
