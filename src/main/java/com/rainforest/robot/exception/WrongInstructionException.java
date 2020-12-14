package com.rainforest.robot.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WrongInstructionException extends RuntimeException {
  String message;

}
