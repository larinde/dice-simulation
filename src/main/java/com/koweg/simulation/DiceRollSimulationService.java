package com.koweg.simulation;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

@Service
public class DiceRollSimulationService {

  private static final int MINIMUM_SIDES_PER_DIE = 4;
  private static final int MINIMUM_DICE_ROLLS = 1;
  private final int minOutcomePerDie = 1;
  private final SecureRandom randomGen = new SecureRandom();

  public SimulationResult simulate(int numDice, int numSidesPerDie, int numDiceRolls) {

    validateSimulationInput(numDice, numSidesPerDie, numDiceRolls);

    final int maxOutcomePerDie = numSidesPerDie;
    final Map<Integer, Integer> stats = new ConcurrentHashMap();
    for (int rollCount = 1; rollCount <= numDiceRolls; rollCount++) {
      IntStream outcomes = randomGen.ints(numDice, minOutcomePerDie, maxOutcomePerDie + 1);
      int total = outcomes.sum();
      stats.computeIfPresent(total,  (_total, _count) -> ++_count);
      stats.putIfAbsent(total, 1);
    }

    List<Outcome> outcomes = new ArrayList();
    stats.entrySet().forEach(o -> outcomes.add(new Outcome(o.getKey(), o.getValue())));
    return new SimulationResult(outcomes);
  }

  private void validateSimulationInput(int numDice, int numSidesPerDie, int numDiceRolls) {
    if (numSidesPerDie < MINIMUM_SIDES_PER_DIE)
      throw new InvalidParameterException("Die must have a minimum of " + MINIMUM_SIDES_PER_DIE + " sides");
    if ((numDice <minOutcomePerDie || numDiceRolls <MINIMUM_DICE_ROLLS))
      throw new InvalidParameterException("The number of dice and the total number of rolls must be at least " + MINIMUM_DICE_ROLLS);
  }

}
