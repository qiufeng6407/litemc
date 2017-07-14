#!/bin/sh
id=`ps -ef | grep 'litemc-app-1.11.2.jar POTATO666'| grep -v grep | awk '{print $2}'` 
echo 
echo "Stop potato666...... kill" $id 
kill -9  $id