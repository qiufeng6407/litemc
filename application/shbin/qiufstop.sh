#!/bin/sh
id=`ps -ef | grep 'litemc-app-1.11.2.jar qiuF'| grep -v grep | awk '{print $2}'` 
echo 
echo "Stop qiuf...... kill" $id 
kill -9  $id