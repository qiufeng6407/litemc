#!/bin/sh
sh ./potato666stop.sh
LOGFILE=$HOME/app/stdout.log
cd $HOME/app
nohup java -jar -Xms10M -Xmx10M -XX:MaxPermSize=8M -jar litemc-app-1.11.2.jar POTATO666 MC.52KO.COM 25565 210 >$LOGFILE 2>&1 &
echo "potato666 start..."