#!/bin/bash
# Usage: runMyCache.sh "command" "key_value"
# Possible commands: get, set, add, replace, delete
# Example: runMyCache.sh "get" "MyPrototypeCache_123Thread2_TypeMID_LEVEL"
# Description: This script executes read/write operations on the local MemCached instance.

JAVA_HOME=/usr/lib/jvm/java
CLASSPATH=./build/jars/spymemcached-2.11.4.jar:./build/jars/prototype.jar
echo There are $# arguments to $0: $*
echo first argument: $1
echo second argument: $2
echo third argument: $3
echo here they are again: $@
$JAVA_HOME/bin/java -cp $CLASSPATH MyCacheApp "$@" &
exit 0
