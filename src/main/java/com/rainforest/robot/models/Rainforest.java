package com.rainforest.robot.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Rainforest {
  private Feeder feeder;
  private Robot robot;
  private List<Crate> crates;
  private String instructions;
}
