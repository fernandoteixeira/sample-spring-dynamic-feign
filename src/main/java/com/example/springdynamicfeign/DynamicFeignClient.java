package com.example.springdynamicfeign;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface DynamicFeignClient {

  @RequestMapping(method = RequestMethod.GET)
  ResponseEntity<String> info();
}
