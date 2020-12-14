package com.rainforest.robot.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Crate {
  private Position position;
  private Integer bags;
}
