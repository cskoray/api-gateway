package com.solidcode.gateway.controller;

import static reactor.core.publisher.Mono.just;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

  @RequestMapping("/apiUserFallBack")
  public Mono<String> userServiceFallBack() {
    return just(
        "API-USER Service is taking too long to respond or is down. Please try again later");
  }
}