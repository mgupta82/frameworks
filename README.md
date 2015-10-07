# Requirements
We need to create Basic J2EE project having layered architecture without using third party frameworks like Spring, Hibernate or struts.

# Solution Overview
##DAO Layer
We will use annotations in DAO layer for specifying the queries. We will use Proxy pattern for calling the DAO methods

##Business Layer
We will wrap the transaction methods beginTransaction, commit() and rollback() in proxy call. We will use ThreadLocal class for maintaing transaction contect and datasource.

##Web Layer
We will use plain servlets

# Technical Setup
##Create Maven Project
mvn archetype:generate -DgroupId=com.basic -DartifactId=lean -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

##Database Setup
1. Create database named train in mysql
2. *Create table in my sql

CREATE TABLE `train`.`txn` ( `id` INT NOT NULL , `name` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB; 
