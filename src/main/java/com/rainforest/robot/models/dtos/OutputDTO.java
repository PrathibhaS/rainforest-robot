package com.rainforest.robot.models.dtos;

import com.rainforest.robot.models.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class OutputDTO {
  private Position finalPos;
  private int bagsDropped;
  private String status;
}
