package com.rainforest.robot.models;

import lombok.Data;

@Data
public class Robot {
  private Position position;
  private STATUS status;

  enum STATUS {
    FUNCTIONING, SHORT_CIRCUITED
  }
}
