package com.solidcode.gateway.exception;

import lombok.Getter;

@Getter
public class GatewayException extends RuntimeException {

  private ErrorType errorType;
  private String field;
  private String code;

  public GatewayException(ErrorType errorType) {
    super(errorType.getMessage());
    this.errorType = errorType;
  }

  public GatewayException(ErrorType errorType, String field) {
    super(errorType.getMessage());
    this.code = errorType.getCode();
    this.errorType = errorType;
    this.field = field;
  }

}
