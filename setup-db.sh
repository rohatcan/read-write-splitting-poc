#!/bin/bash

docker-compose -f ./db-setup/docker-compose.yml down -v
rm -rf ./db-setup/primary/data/*
rm -rf ./db-setup/secondary/data/*
docker-compose -f ./db-setup/docker-compose.yml build
docker-compose -f ./db-setup/docker-compose.yml up -d

until docker exec mysql_primary sh -c 'export MYSQL_PWD=123456; mysql -u root -e ";"'
do
    echo "Waiting for mysql_primary database connection..."
    sleep 4
done

priv_stmt='CREATE USER "mydb_secondary_user"@"%" IDENTIFIED BY "mydb_secondary_pwd"; GRANT REPLICATION SLAVE ON *.* TO "mydb_secondary_user"@"%"; FLUSH PRIVILEGES;'
docker exec mysql_primary sh -c "export MYSQL_PWD=123456; mysql -u root -e '$priv_stmt'"

until docker exec mysql_secondary sh -c 'export MYSQL_PWD=123456; mysql -u root -e ";"'
do
    echo "Waiting for mysql_secondary database connection..."
    sleep 4
done

MS_STATUS=`docker exec mysql_primary sh -c 'export MYSQL_PWD=123456; mysql -u root -e "SHOW MASTER STATUS"'`
CURRENT_LOG=`echo $MS_STATUS | awk '{print $6}'`
CURRENT_POS=`echo $MS_STATUS | awk '{print $7}'`

start_secondary_stmt="CHANGE MASTER TO MASTER_HOST='mysql_primary',MASTER_USER='mydb_secondary_user',MASTER_PASSWORD='mydb_secondary_pwd',MASTER_LOG_FILE='$CURRENT_LOG',MASTER_LOG_POS=$CURRENT_POS; START SLAVE;"
start_secondary_cmd='export MYSQL_PWD=123456; mysql -u root -e "'
start_secondary_cmd+="$start_secondary_stmt"
start_secondary_cmd+='"'
docker exec mysql_secondary sh -c "$start_secondary_cmd"

docker exec mysql_secondary sh -c "export MYSQL_PWD=123456; mysql -u root -e 'SHOW SLAVE STATUS \G'"
