package com.rainforest.robot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Feeder {
  @NonNull private Position position;
  private Integer bagsDropped = 0;
}
