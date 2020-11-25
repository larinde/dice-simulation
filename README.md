# Dice Roll Distribution Simulation

| Action                     | HTTP Method   | URI                                                                                                                          |
|:---------------------------|:-------------:|:-----------------------------------------------------------------------------------------------------------------------------|
| Place an order             |  POST         | /api/simulations/numberOfDice/{numberOfDice}/numberOfSidesPerDie/{numberOfSidesPerDie}/numberOfDiceRolls/{numberOfDiceRolls} |              |


## Building and deploying the app

This implementation is based on Spring Boot and embedded Tomcat container frameworks.
The running application is accessible through the default container port - 8080.

```bash
 mvn clean package
 java -jar target/dice-simulation.jar
```

## Running a simulation from the command line:

__REQUEST__

```bash
 http://localhost:8080/api/simulations/numberOfDice/3/numberOfSidesPerDie/6/numberOfDiceRolls/5
```
The response is a JSON payload containing the created order resource id

__RESPONSE__

```json
{
  "outcomes": [{
    "total": 17,
    "frequency": 1
  }, {
    "total": 7,
    "frequency": 1
  }, {
    "total": 10,
    "frequency": 1
  }, {
    "total": 13,
    "frequency": 1
  }, {
    "total": 14,
    "frequency": 1
  }]
}
```
