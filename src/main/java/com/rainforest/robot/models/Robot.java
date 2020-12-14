package com.rainforest.robot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Robot {
  @NonNull private Position position;
  @NonNull private STATUS status;
  private Integer bearCount = 0;

  public enum STATUS {
    FUNCTIONING, SHORT_CIRCUITED
  }
}
