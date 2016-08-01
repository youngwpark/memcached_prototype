import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
 
public class MyCache 
{
    private static final String NAMESPACE= "MyPrototypeCache_123";
    private static MyCache instance = null;
    private static MemcachedClient memCachedClient = null;

    public MyCache()
    {
 
    }
    
    protected void setMemCachedClient(final MemcachedClient memCachedClient)
    {
	this.memCachedClient = memCachedClient;
    }
 
    protected MemcachedClient getMemCachedClient()
    {
        return memCachedClient;
    }

    protected String getNamespaceKey(final String key)
    {
    	return NAMESPACE + key;
    }

}
