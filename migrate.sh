#!/bin/bash

SQL_COMMANDS=$(cat <<-END
CREATE DATABASE IF NOT EXISTS orders;
USE orders;
CREATE TABLE IF NOT EXISTS orders  (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    basketSize INT
);

CREATE DATABASE IF NOT EXISTS products;
USE products;
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price INT
);

CREATE DATABASE IF NOT EXISTS users;
USE users;
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    loginCount INT
);
END
)

docker exec -it mysql_primary mysql -u root -p'123456' -e "$SQL_COMMANDS"
