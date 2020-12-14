package com.rainforest.robot;

import com.rainforest.robot.exception.WrongInstructionException;
import com.rainforest.robot.models.Position;
import com.rainforest.robot.models.Robot;
import com.rainforest.robot.models.dtos.InputDTO;
import com.rainforest.robot.models.dtos.OutputDTO;
import com.rainforest.robot.services.IPerformInstructions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PerformInstructionsTest {

  @Autowired
  private IPerformInstructions performInstructions;

  @Test
  public void test_functioningScenario() {
    InputDTO input = InputDTO.builder()
            .feeder("1 1")
            .robot("0 1")
            .crates("0 0 5")
            .instructions("S" +
                    "P" +
                    "P" +
                    "N" +
                    "E" +
                    "D")
            .build();
    OutputDTO expectedOutput = OutputDTO.builder().bagsDropped(2).finalPos(new Position(1, 1))
            .status(Robot.STATUS.FUNCTIONING.name()).build();
    OutputDTO output = performInstructions.dropBagAndReturnStatus(input);
    assert (output.equals(expectedOutput));
  }

  @Test
  public void test_shortCircuitedScenario() {
    InputDTO input = InputDTO.builder()
            .feeder("1 1")
            .robot("1 1")
            .crates("0 0 5,1 2 3")
            .instructions("SPPNED")
            .build();
    OutputDTO expectedOutput = OutputDTO.builder().bagsDropped(0).finalPos(new Position(1, 0))
            .status(Robot.STATUS.SHORT_CIRCUITED.name()).build();
    OutputDTO output = performInstructions.dropBagAndReturnStatus(input);
    assert (output.equals(expectedOutput));
  }

  @Test
  public void test_throwsException() {
    InputDTO input = InputDTO.builder()
            .feeder("1 1")
            .robot("1 1")
            .crates("0 0 5,1 2 3")
            .instructions("TTT")
            .build();
    assertThrows(WrongInstructionException.class, () -> performInstructions.dropBagAndReturnStatus(input));
  }
}
