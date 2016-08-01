# memcache_prototype
=============================================================================
Running the existing MemCached on the "MemCached (using key-2)" EC2 Instance:
=============================================================================
Logging into the AWS EC2 instance running the MemCache daemon:
	$ ssh -i my-ec2-key-pair-2.pem ec2-user@52.42.142.52
	**NOTE: Let me know if you need the "my-ec2-key-pair-2.pem" file.

1) Verify that the "MemCached (using key-2)" instance is running. If not, start it.
	- Logging into the AWS Console as a dev user:
		 i) Navigate to the following url: https://console.aws.amazon.com/console/home
		 ii) Account: 862044447122
		 iii) Username: dev1
		 iv) Password: devnrt1
	- Go to "EC2 Instances" and check if the "MemCached (using key-2)" instance is running.
	- Right-click and start it.
	- *** MAKE SURE YOU STOP IT ONCE YOU'RE DONE!!!
2) Logging into the AWS EC2 instance running the MemCache daemon:
	$ ssh -i my-ec2-key-pair-2.pem ec2-user@52.42.142.52
3) To run a process to add 10 "Opportunity" objects to MemCached:
	$ ./runMyCacheService.sh 10
	  NOTE: 
	  - Description: This script will run 10 threads to create Opportunity objects for the following jobs:
	  		- Entry Level Software Engineer
	  		- Mid Level Dev Ops Engineer
	  		- Senior Level Software Engineer
	  - This will result in 30 Opportunity objects to be stored in MemCached
4) Once data is written to MemCache, you can dump all the keys stored in the cache:
	$ memdump --servers=localhost
		- This command lists all the keys stored in MemCached. Pick one to input into the next command.
5) Query a single record in MemCached:
	$ ./runMyCache.sh "get" "MyPrototypeCache_123Thread2_TypeMID_LEVEL"
		- Description: This script executes read/write operations on the local MemCached instance.
			
			
===============================================
Run your own MemCached on your own environment:
===============================================
0) Create your own EC2 Instance or just run on your local machine.
1) Install MemCached:
	$ sudo yum install memcached
	$ sudo service memcached start
	$ sudo chkconfig --level 345 memcached on
	$ ps aux | grep memcached
	$ netstat -na | grep 11211			// Lists the connections to MemCached
	$ memdump --servers=localhost		// Lists all the keys for items stored in MemCached
	$ telnet localhost 11211			// Connects to MemCached
2) Get a checkout of "memcached_prototype" project:
	$ git clone https://github.com/youngwpark/memcached_prototype.git
3) To get the latest changes:
	$ git pull
4) To build the project:
	$ cd <location_of_checkout>
	$ ant fresh
5) To dump all the keys in MemCached:
	$ memdump --servers=localhost		// This shouldn't return anything yet.
6) To add/set/get/replace/delete records in MemCached:
	$ ./runMyCache.sh "add" "apple" "The apple is red."
	$ memdump --servers=localhost		// This should return a record for "apple"
7) To run a process to add 10 "Opportunity" objects to MemCached:
	$ ./runMyCacheService.sh 10
	NOTE: 
	  - Description: This script will run 10 threads to create Opportunity objects for the following jobs:
	  		- Entry Level Software Engineer
	  		- Mid Level Dev Ops Engineer
	  		- Senior Level Software Engineer
	  - This will result in 30 Opportunity objects to be stored in MemCached
	  - The thread is configured to sleep 1 second between each iteration.
8) Dump all the keys stored in MemCached:
	$ memdump --servers=localhost		// This should return about 30 records 
9) How to query for a single record:
	$ memdump --servers=localhost
		- This command lists all the keys stored in MemCached. Pick one to input into the next command.
	$ ./runMyCache.sh "get" "MyPrototypeCache_123Thread2_TypeMID_LEVEL"
		- This should do a "toString()" on the object correpsonding to the key "MyPrototypeCache_123Thread2_TypeMID_LEVEL"
		- Usage: runMyCache.sh "<command>" "<key_value>"
10) To bounce the MemCached daemon:
	$ sudo service memcached restart

	
	
