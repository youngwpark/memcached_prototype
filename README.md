# memcache_prototype
Logging into the AWS EC2 instance running the MemCache daemon:
	$ ssh -i my-ec2-key-pair-2.pem ec2-user@52.42.142.52
	
	
1) Get a checkout of "memcached_prototype":
	$ git clone https://github.com/youngwpark/memcached_prototype.git
2) To get the latest changes:
	$ git pull
3) To build the project:
	$ ant fresh
4) To dump all the keys in MemCached:
	$ memdump --servers=localhost
5) To add/set/get/replace/delete records in MemCached:
	$ ./runMyCache.sh "add" "apple" "The apple is red."
6) To run a process to add 10 "Opportunity" object to MemCached:
	$ ./runMyCacheService.sh 10
	NOTE: 
	  - This will call the OppGenerator to run a thread to add 10 Opportunity objects to the MemCache. 
	  - The thread is configured to sleep 1 second between each iteration.
7) After steps #5 or #6, try dumping all the keys:
	$ memdump --servers=localhost
8) To bounce the MemCached daemon:
	$ sudo service memcached restart
