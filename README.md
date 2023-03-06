# taxi-service

My First simple java pet project

it simulates a taxi service where we can manage cars, drivers

## Features
Authentication by driver

Display all drivers | Display all cars | Display my cars | Display all manufactures

create new driver | create new Car | create new Manufacturer

Add driver to car

Logout

<img src="https://i.postimg.cc/WbyB6hgw/Screenshot-3.png" width = "400" >

## :rocket:	Involved Technologies::rocket:	

Java 8

MySql

jdbc

Tomcat

JUnit 4

## :wrench:	 How to setup :wrench:	

### For connection to database

1. create MySql connection in local Desktop <br/><br/>

2. open file with path

 src/main/java/taxi/util/ConnectionUtil.java <br/><br/>

3. change value of private static final String USERNAME = "YOUR_USERNAME";

 where YOUR_USERNAME = username of your connection <br/><br/>

4. change value of  private static final String PASSWORD = "YOUR_PASSWORD";

where YOUR_PASSWORD = password of your connection <br/><br/>

5. change value of variable URL to "jdbc:mysql://YOUR_HOST:YOUR_PORT/taxi?serverTimezone=UTC";

where YOUR_HOST = host of your connection and YOUR:PORT = port of your connection <br/><br/>

6. copy SQL query in file src/main/resources/init_db.sql past and execute in your connection <br/><br/>

### For start project 

0. download Tomcat version 9 and Intelij Idea Ultimate

1. click in IDE to Edit Configuration 

2. click Add New Configuration

3. pick Tomcat Server - Local

4. click Configure 

5. in "Tomcat Home" select our downloaded before tomcat 9 and click Ok

6. step to Deployment menu 

7. click add select taxi-service:war exploaded

8. Apply, Ok

9. select in Configuration Tomcat and run 

Setup completed you can use taxi-service





