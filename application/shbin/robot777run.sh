#!/bin/sh
sh ./robot777stop.sh
LOGFILE=$HOME/app/stdout.log
cd $HOME/app
nohup java -jar -Xms10M -Xmx10M -XX:MaxPermSize=8M -jar litemc-app-1.11.2.jar ROBOT777 MC.52KO.COM 25565 210 >$LOGFILE 2>&1 &
echo "robot777 start..."