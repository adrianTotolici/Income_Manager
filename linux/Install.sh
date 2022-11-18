#!/bin/bash

#install posgresql
apt-get update
apt-get install postgresql postgresql-contrib

#create user and database


#install java 8 and set path
add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install oracle-java8-installer
java -version
apt-get install oracle-java8-set-default

#install Income Manager and set path
cp /home/adrian/IncomeManager2/IncomeManager2.sh /home/adrian/desktop/
chmod 777 /home/adrian/desktop/IncomeManager2.sh