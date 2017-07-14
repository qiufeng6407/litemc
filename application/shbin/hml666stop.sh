#!/bin/sh
id=`ps -ef | grep 'litemc-app-1.11.2.jar HML666'| grep -v grep | awk '{print $2}'` 
echo 
echo "Stop hml666...... kill" $id 
kill -9  $id