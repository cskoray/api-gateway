package com.solidcode.gateway.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

  INVALID_TOKEN("10003", "Invalid token.", UNAUTHORIZED),
  EXPIRED_TOKEN("10004", "Expired token.", UNAUTHORIZED),
  UNAUTHORIZED_TOKEN("10005", "Unauthorized token.", UNAUTHORIZED);

  private String code;
  private String message;
  private HttpStatus httpStatus;
}
