#Iungo project

**Short discalaimer**: I am Dutch so I sometimes accidentally use dutch words for variable names, apologies in advance.

1. I use Intellji Idea.

2. This program only builds if it detects a running mysql server on the system. The username and password it uses to login are found in the application.properties file under resources and a guide on how to set up MySQL on Linux is included (ignore of course if you have no problem setting this up).

3. The command to build and run is: $`./gradlew clean build && java -jar build/libs/iungo-0.0.1-SNAPSHOT.jar`

4. To try the login in Postman, I **POST** to **localhost:8080/oauth/token** with **x-www-form-urlencoded** with the following key-value pairs:
- grant_type = password
- client_id = app
- username = admin
- password = password


#Good luck!
