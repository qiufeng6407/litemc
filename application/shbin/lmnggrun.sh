#!/bin/sh
sh ./lmnggstop.sh
LOGFILE=$HOME/app/stdout.log
cd $HOME/app
nohup java -jar -Xms10M -Xmx10M -XX:MaxPermSize=8M -jar litemc-app-1.11.2.jar LMNGG MC.52KO.COM 25565 210 >$LOGFILE 2>&1 &
echo "lmngg start..."