package com.koweg.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidParameterException extends  RuntimeException{
  private String message;

}
