import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import java.util.concurrent.*;
import java.io.IOException;

public class MyCacheApp
{

public MyCacheApp(){}

    public static void main(String[] args) throws Exception
    {
        if(args.length < 2)
		{
	            System.out.println("Please specify command line options");
	            return;
        }

        System.out.println("Starting to establish MemcachedClient Connection");
        String commandArgument = null;
        String keyArgument = null;
        String valueArgument = null;
        int numberOfArguments = args.length;
        if (numberOfArguments > 0)
        {
            commandArgument = args[numberOfArguments - numberOfArguments];
            System.out.println("commandArgument[" + commandArgument + "]");
            if (numberOfArguments > 1)
            {
    	        keyArgument = args[numberOfArguments - (numberOfArguments - 1)];
    	        System.out.println("keyArgument[" + keyArgument + "]");
       	        if (numberOfArguments > 2)
		        {
		    	    valueArgument = args[numberOfArguments - (numberOfArguments -2)];
                    System.out.println("valueArgument[" + valueArgument + "]");
		        }
	  	    }
        }
	   
	 	// Create Model 
        MyCache cache = new MyCache();
	
		// Create Controller
		MyCacheController cacheController = new MyCacheController(cache);
	    cacheController.handleCommand(commandArgument, keyArgument, valueArgument);
	
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
