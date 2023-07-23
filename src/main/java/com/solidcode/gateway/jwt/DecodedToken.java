package com.solidcode.gateway.jwt;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DecodedToken {

  private String userKey;
  private String token;
  private String subject;
  private TokenType type;
  private UserStatus userStatus;
  private LocalDateTime expiryDateTime;
}
