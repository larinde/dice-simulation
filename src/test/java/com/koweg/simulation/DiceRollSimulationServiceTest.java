package com.koweg.simulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class DiceRollSimulationServiceTest {

  private DiceRollSimulationService diceRollSimulationService;
  private int minOutcomePerDie = 1;

  @BeforeEach
  void setUp(){
    diceRollSimulationService = new DiceRollSimulationService();
  }

  @Test
  public void should_compute_distribution_for_3_dice_with_6_sides_and_100_rolls() {
    int numDice = 3;
    int numDiceRolls = 100;
    int numSidesPerDie = 6;
    int maxOutcomePerDie = numSidesPerDie;

    SimulationResult result = diceRollSimulationService.simulate(numDice, numSidesPerDie, numDiceRolls);
    assertThat(result).isNotNull();
    assertThat(result.getOutcomes().size()).isGreaterThan(1);

    verifyDiceRollCountMatch(numDiceRolls, result);

    assertThat(minDiceRollSum(result)).isGreaterThanOrEqualTo(minOutcomePerDie * numDice);
    assertThat(maxDiceRollSum(result)).isLessThanOrEqualTo(maxOutcomePerDie * numDice);
  }

  @Test
  public void should_compute_distribution_for_2_dice_with_4_sides_and_1000_rolls() {
    int numDice = 2;
    int numDiceRolls = 1000;
    int numSidesPerDie = 4;
    int maxOutcomePerDie = numSidesPerDie;

    SimulationResult result = diceRollSimulationService.simulate(numDice, numSidesPerDie, numDiceRolls);
    assertThat(result).isNotNull();
    assertThat(result.getOutcomes().size()).isGreaterThan(1);

    verifyDiceRollCountMatch(numDiceRolls, result);

    assertThat(minDiceRollSum(result)).isGreaterThanOrEqualTo(minOutcomePerDie * numDice);
    assertThat(maxDiceRollSum(result)).isLessThanOrEqualTo(maxOutcomePerDie * numDice);
  }

  @Test
  public void should_throw_exception_when_number_of_dice_input_is_less_than_1() {
    Assertions.assertThrows(InvalidParameterException.class, () ->{
      int numDice = 0;
      int numDiceRolls = 100;
      int numSidesPerDie = 6;
      int maxOutcomePerDie = numSidesPerDie;
      diceRollSimulationService.simulate(numDice, numSidesPerDie, numDiceRolls);
    });
  }

  @Test
  public void should_throw_exception_when_number_of_dice_roll_input_is_less_than_1() {
    Assertions.assertThrows(InvalidParameterException.class, () ->{
      int numDice = 1;
      int numDiceRolls = 0;
      int numSidesPerDie = 6;
      int maxOutcomePerDie = numSidesPerDie;
      diceRollSimulationService.simulate(numDice, numSidesPerDie, numDiceRolls);
    });
  }

  @Test
  public void should_throw_exception_when_dice_number_of_dice_sides_is_less_than_4() {
    Assertions.assertThrows(InvalidParameterException.class, () ->{
      int numDice = 1;
      int numDiceRolls = 10;
      int numSidesPerDie = 3;
      int maxOutcomePerDie = numSidesPerDie;
      diceRollSimulationService.simulate(numDice, numSidesPerDie, numDiceRolls);
    });
  }


  private Integer minDiceRollSum(SimulationResult result) {
    return result.getOutcomes().stream()
            .min(Comparator.comparing(Outcome::getTotal)).get().getTotal();
  }

  private Integer maxDiceRollSum(SimulationResult result) {
    return result.getOutcomes().stream()
            .max(Comparator.comparing(Outcome::getTotal)).get().getTotal();
  }

  private void verifyDiceRollCountMatch(int numDiceRolls, SimulationResult result) {
    assertThat(
            result.getOutcomes()
                    .stream()
                    .map(Outcome::getFrequency)
                    .reduce(0, (f1,f2) -> f1 + f2)
    ).isEqualTo(numDiceRolls);
  }


}
