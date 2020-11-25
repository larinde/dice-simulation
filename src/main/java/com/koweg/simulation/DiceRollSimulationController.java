package com.koweg.simulation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DiceRollSimulationController {

  private final DiceRollSimulationService diceRollSimulationService;

  public DiceRollSimulationController(DiceRollSimulationService diceRollSimulationService) {
    this.diceRollSimulationService = diceRollSimulationService;
  }

  @PostMapping( value = "/simulations/numberOfDice/{numberOfDice}/numberOfSidesPerDie/{numberOfSidesPerDie}/numberOfDiceRolls/{numberOfDiceRolls}", produces = { MediaType.APPLICATION_JSON_VALUE } )
  public SimulationResult rollDice(@PathVariable int numberOfDice, @PathVariable int numberOfSidesPerDie, @PathVariable int numberOfDiceRolls){
    return diceRollSimulationService.simulate(numberOfDice, numberOfSidesPerDie, numberOfDiceRolls);
  }

  @ExceptionHandler(InvalidParameterException.class)
  public ErrorResponse handleException(Exception ex) {
    return new ErrorResponse(ex.getMessage());
  }
}
