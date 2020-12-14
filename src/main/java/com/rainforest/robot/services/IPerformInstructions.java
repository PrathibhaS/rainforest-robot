package com.rainforest.robot.services;

import com.rainforest.robot.models.dtos.InputDTO;
import com.rainforest.robot.models.dtos.OutputDTO;

public interface IPerformInstructions {

  /**
   * Service to drop bags in feeder by processing instructions
   *
   * @param inputDTO Object containing feeder, robot, crate positions and instructions
   * @return Final position, status of robot and bags dropped
   */
  OutputDTO dropBagAndReturnStatus(InputDTO inputDTO);
}
