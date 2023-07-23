package com.solidcode.gateway.filter;

import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static java.util.List.of;

import com.solidcode.gateway.jwt.JwtUtil;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends
    AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

  private JwtUtil jwtUtil;

  @Autowired
  public JwtAuthenticationFilter(JwtUtil jwtUtil) {
    super(Config.class);
    this.jwtUtil = jwtUtil;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return ((exchange, chain) -> {

      ServerHttpRequest request = exchange.getRequest();
      final List<String> apiEndpoints = of("/register", "/login", "list");
      Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
          .noneMatch(uri -> r.getURI().getPath().contains(uri));

//      if (isApiSecured.test(request)) {
//        if (!request.getHeaders().containsKey(AUTHORIZATION)) {
//          ServerHttpResponse response = exchange.getResponse();
//          response.setStatusCode(HttpStatus.UNAUTHORIZED);
//          return response.setComplete();
//        }
//        final String authHeader = request.getHeaders().getOrEmpty(AUTHORIZATION).get(0);
//        try {
//          jwtUtil.verifyToken(authHeader);
//        } catch (Exception e) {
//          ServerHttpResponse response = exchange.getResponse();
//          response.setStatusCode(HttpStatus.BAD_REQUEST);
//          return response.setComplete();
//        }
//      }
      return chain.filter(exchange);
    });
  }

  public static class Config {

  }
}
