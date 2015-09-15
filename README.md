# Basic j2ee app
Create Maven Project

mvn archetype:generate -DgroupId=com.basic -DartifactId=web -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

Create database named train in mysql

Create table in my sql

CREATE TABLE `train`.`txn` ( `id` INT NOT NULL , `name` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB; 
