package com.koweg.simulation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import static  org.hamcrest.Matchers.*;

@WebMvcTest(DiceRollSimulationController.class)
public class DiceRollSimulationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DiceRollSimulationService diceRollSimulationService;

  @Test
  public void should_successfully_receive_a_mocked_simulation_response() throws Exception {
    Mockito.when(diceRollSimulationService.simulate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockResponse());

    mockMvc.perform(post("/api/simulations/numberOfDice/3/numberOfSidesPerDie/6/numberOfDiceRolls/5"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("outcomes")));
  }

  private SimulationResult mockResponse() {
    return new SimulationResult(Arrays.asList(new Outcome(10, 1)));
  }


}
