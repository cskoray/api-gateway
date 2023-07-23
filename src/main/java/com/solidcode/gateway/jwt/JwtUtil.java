package com.solidcode.gateway.jwt;

import static com.solidcode.gateway.exception.ErrorType.EXPIRED_TOKEN;
import static com.solidcode.gateway.exception.ErrorType.INVALID_TOKEN;
import static com.solidcode.gateway.exception.ErrorType.UNAUTHORIZED_TOKEN;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;
import static java.util.Base64.getEncoder;

import com.solidcode.gateway.exception.GatewayException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtil {

  @Value("${security.jwt.secret}")
  private String jwtKey;

  public static final String SCOPE = "scope";
  public static final String STATUS = "status";
  public static final String USER_KEY = "key";

  public DecodedToken verifyToken(String token) {

    Claims claims;
    try {
      String signingKey = jwtKey;
      String encodedKey = getEncoder().encodeToString(signingKey.getBytes());
      claims = Jwts.parser()
          .setSigningKey(encodedKey)
          .parseClaimsJws(token)
          .getBody();
    } catch (ExpiredJwtException ex) {
      log.error("JWT token is expired");
      throw new GatewayException(EXPIRED_TOKEN);
    } catch (Exception ex) {
      log.error("JWT token exception", ex);
      throw new GatewayException(INVALID_TOKEN);
    }
    checkTokenTypes(claims);
    return DecodedToken.builder()
        .token(token)
        .subject(claims.getSubject())
        .type(TokenType.valueOf(claims.get(SCOPE).toString()))
        .userStatus(UserStatus.valueOf(claims.get(STATUS).toString()))
        .userKey(claims.get(USER_KEY).toString())
        .expiryDateTime(getLocalDateTime(claims))
        .build();
  }

  private void checkTokenTypes(Claims claims) {

    try {
      TokenType.valueOf(claims.get("scope").toString());
    } catch (Exception ex) {
      log.error("JWT token type is wrong");
      throw new GatewayException(UNAUTHORIZED_TOKEN);
    }
  }

  private LocalDateTime getLocalDateTime(Claims claims) {

    Instant instant = claims.getExpiration().toInstant();
    return ofInstant(instant, systemDefault());
  }
}
