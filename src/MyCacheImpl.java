import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;
import java.util.concurrent.*;
import java.io.IOException;

public class MyCacheImpl
{

public MyCacheImpl(){}

public static void main(String[] args) throws Exception{
    if(args.length < 2){
        System.out.println("Please specify command line options");
        return;
    }

    System.out.println("Starting to establish MemcachedClient Connection");
    MemcachedClient memcachedClient = null;
    try 
    {
        memcachedClient = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
    }
    catch (IOException e) {
       e.printStackTrace();
       System.err.println("connection problem");
    }
    System.out.println("Finished establishing MemcachedClient Connection");

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

    if(commandArgument.equals("get") && keyArgument != null){
        System.out.println("Key Argument " + keyArgument);
        System.out.println("Value of key[" + memcachedClient.get(keyArgument) + "]");
    }else if(commandArgument.equals("set") && keyArgument != null && valueArgument != null){
        System.out.println("Key Argument = " + keyArgument + ", Value Argument = " + valueArgument);
        Future<Boolean> result= memcachedClient.set(keyArgument, 0, valueArgument);
        System.out.println("Result of set[" + result.get() + "]");
    }else if(commandArgument.equals("add") && keyArgument != null && valueArgument != null){
        System.out.println("Key Argument = " + keyArgument + ", Value Argument = " + valueArgument);
        Future<Boolean> result= memcachedClient.add(keyArgument, 0, valueArgument);
        System.out.println("Result of add[" + result.get() + "]");
    }else if(commandArgument.equals("replace") && keyArgument != null && valueArgument != null){
        String keyName =args[1];
        String value=args[2];
        System.out.println("Key Argument = " +keyArgument + ", Value Argument = " + valueArgument);
        Future<Boolean> result= memcachedClient.replace(keyArgument, 0, valueArgument);
        System.out.println("Result of replace[" + result.get() + "]");
    }else if(commandArgument.equals("delete") && keyArgument != null){
        System.out.println("Key Argument = " + keyArgument );
        Future<Boolean> result= memcachedClient.delete(keyArgument);
        System.out.println("Result of delete[" + result.get() + "]");
    }else{
        System.out.println("Command not found");
    }
    memcachedClient.shutdown();
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
