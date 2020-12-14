package com.rainforest.robot.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputDTO {
  private String feeder;
  private String robot;
  private String crates;
  private String instructions;
}
