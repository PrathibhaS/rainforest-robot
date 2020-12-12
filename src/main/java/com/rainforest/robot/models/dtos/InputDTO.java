package com.rainforest.robot.models.dtos;

import com.rainforest.robot.models.Crate;
import com.rainforest.robot.models.Feeder;
import com.rainforest.robot.models.Robot;

import java.util.List;

public class InputDTO {//todo needs change
  private Feeder feeder;
  private Robot robot;
  private List<Crate> crates;
  private String instructions;
}
