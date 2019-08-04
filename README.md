# backend-exercise-java

## Run the executable jar
```
java -jar moneyTransfer.jar
```

## Build project
```
mvn clean install tomcat7:exec-war-only
```

## Request Example

POST - http://localhost:8080/accountmanagement/api/transfermoney
```
{
 "sourceAccount":"12121212",
 "destinationAccount": "98765432",
 "amount": 14.34
}
```
