#!/bin/bash
# Usage: runMyCacheService.sh <number_of_threads>
# Example: runMyCacheService.sh 10
# Description: This script will run 10 threads to create Opportunity objects for the following jobs:
#	1) Entry Level Software Engineer
#	2) Mid Level Dev Ops Engineer
#	3) Senior Level Software Engineer

JAVA_HOME=/usr/lib/jvm/java
CLASSPATH=./build/jars/spymemcached-2.11.4.jar:./build/jars/prototype.jar
echo There are $# arguments to $0: $*
echo first argument: $1
echo here they are again: $@
$JAVA_HOME/bin/java -cp $CLASSPATH MyCacheService "$@" &
exit 0
