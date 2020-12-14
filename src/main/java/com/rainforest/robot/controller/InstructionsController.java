package com.rainforest.robot.controller;

import com.rainforest.robot.models.dtos.InputDTO;
import com.rainforest.robot.models.dtos.OutputDTO;
import com.rainforest.robot.services.IPerformInstructions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructionsController {

  @Autowired
  IPerformInstructions performInstructions;

  @PostMapping
  public OutputDTO get(@RequestBody InputDTO inputDTO) {
    return performInstructions.dropBagAndReturnStatus(inputDTO);
  }
}
