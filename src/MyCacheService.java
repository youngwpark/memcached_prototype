import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import java.util.concurrent.*;
import java.io.IOException;

public class MyCacheService
{

public MyCacheService(){}

    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting to establish MemcachedClient Connection");
        String threadCountArgument = null;
        int numberOfArguments = args.length;
        if (numberOfArguments > 0)
        {
        	threadCountArgument = args[numberOfArguments - numberOfArguments];
            System.out.println("ThreadCount[" + threadCountArgument + "]");
        }
        
	 	// Create Model 
        MyCache cache = new MyCache();
	
		// Create Controller
		MyCacheController cacheController = new MyCacheController(cache);
	
	    OppGenerator oppGenerator = new OppGenerator(cacheController, Integer.parseInt(threadCountArgument));
	    oppGenerator.generateOppDataSet();
	    //cache.shutdown();
    }

	/*
	public List<Product> getAllProducts() {
	        List<Product> products = (List<Product>) MyCache.getInstance().get("AllProducts");
	        if(products != null) {
	              return products;
	        }
	        products = getAllProductsFromDB()
	        if(products) {
	              MyCache.getInstance().put("AllProducts", 3600, customer);
	        }
	        return products;
	}
	 
	public void updateProduct(String id) {
	        updateProductIntoDB(id)
	        MyCache.getInstance().delete("AllProducts");
	}
	public void deleteProduct(String id) {
	        deleteProductFromDB(id)
	        MyCache.getInstance().delete("AllProducts");
	}
	*/
}
