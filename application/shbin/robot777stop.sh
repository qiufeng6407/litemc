#!/bin/sh
id=`ps -ef | grep 'litemc-app-1.11.2.jar ROBOT777'| grep -v grep | awk '{print $2}'` 
echo 
echo "Stop robot777...... kill" $id 
kill -9  $id