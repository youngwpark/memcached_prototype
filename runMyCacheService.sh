#!/bin/bash
JAVA_HOME=/usr/lib/jvm/java
CLASSPATH=./build/jars/spymemcached-2.11.4.jar:./build/jars/prototype.jar
echo There are $# arguments to $0: $*
echo first argument: $1
echo here they are again: $@
$JAVA_HOME/bin/java -cp $CLASSPATH MyCacheService "$@" &
exit 0
