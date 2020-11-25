package com.koweg.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SimulationResult {

  private List<Outcome> outcomes;

}
