package com.rainforest.robot.services.impl;

import com.rainforest.robot.exception.WrongInstructionException;
import com.rainforest.robot.models.Crate;
import com.rainforest.robot.models.Feeder;
import com.rainforest.robot.models.Position;
import com.rainforest.robot.models.Rainforest;
import com.rainforest.robot.models.Robot;
import com.rainforest.robot.models.dtos.InputDTO;
import com.rainforest.robot.models.dtos.OutputDTO;
import com.rainforest.robot.services.IPerformInstructions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformInstructionsImpl implements IPerformInstructions {

  @Override
  public OutputDTO dropBagAndReturnStatus(InputDTO input) {
    System.out.println("INPUT");
    Rainforest rf = prepareInput(input);
    System.out.println(rf);

    processInstructions(rf);

    System.out.println("OUTPUT");
    OutputDTO output = new OutputDTO(rf.getRobot().getPosition(), rf.getFeeder().getBagsDropped(),
            rf.getRobot().getStatus().toString());
    System.out.println(output.toString());
    return output;
  }

  //Method to process instructions, pick bags and drop them
  private void processInstructions(Rainforest rf) {
    for(Character c : rf.getInstructions().toCharArray()) {
      Robot robot = rf.getRobot();
      switch (c) {
        case 'N':
          robot.getPosition().setY(robot.getPosition().getY() + 1);
          break;
        case 'S':
          robot.getPosition().setY(robot.getPosition().getY() - 1);
          break;
        case 'E':
          robot.getPosition().setX(robot.getPosition().getX() + 1);
          break;
        case 'W':
          robot.getPosition().setX(robot.getPosition().getX() - 1);
          break;
        case 'D':
          boolean canDrop = robot.getPosition().equals(rf.getFeeder().getPosition());
          if(canDrop) {
            int bagsDropped = rf.getFeeder().getBagsDropped();
            rf.getFeeder().setBagsDropped(bagsDropped + robot.getBearCount());
            robot.setBearCount(0);
          } else {
            robot.setStatus(Robot.STATUS.SHORT_CIRCUITED);
            return;
          }
          break;
        case 'P':
          int crateNumber = getCrateIndex(rf);
          if(crateNumber != -1) {
            Crate currentCrate = rf.getCrates().get(crateNumber);
            if(currentCrate.getBags() > 0) {
              currentCrate.setBags(currentCrate.getBags() - 1);
              robot.setBearCount(rf.getRobot().getBearCount() + 1);
            }
          } else {
            robot.setStatus(Robot.STATUS.SHORT_CIRCUITED);
            return;
          }
          break;
        default: throw new WrongInstructionException("Instruction not found");
      }
    }
  }

  //Returns index of crate based on Robot's position. If crate does not exist returns -1
  private int getCrateIndex(Rainforest rf) {
    Position robotPos = rf.getRobot().getPosition();
    for(int i=0; i<rf.getCrates().size(); i++) {
      Crate c = rf.getCrates().get(i);
      if(c.getPosition().equals(robotPos))
        return i;
    }
    return -1;
  }

  //Method to convert DTO to Rainforest object.
  private Rainforest prepareInput(InputDTO input) {
    Position pos = getPosition(input.getFeeder());
    Feeder feeder = new Feeder(pos);

    pos = getPosition(input.getRobot());
    Robot robot = new Robot(pos, Robot.STATUS.FUNCTIONING);

    List<Crate> crates = new ArrayList<>();
    String[] cratesArray = input.getCrates().split(",");
    for(String c : cratesArray) {
      pos = getPosition(c);
      String[] values = c.split(" ");
      crates.add(new Crate(pos, Integer.parseInt(values[values.length - 1])));
    }

    Rainforest rainforest = Rainforest.builder()
            .feeder(feeder)
            .robot(robot)
            .crates(crates)
            .instructions(input.getInstructions().replaceAll(" ", ""))
    .build();
    return rainforest;
  }

  //Method to parse feeder, robot, crate inputs and extract position of them
  private Position getPosition(String value) {
    String[] values = value.split(" ");
    return new Position(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
  }
}
