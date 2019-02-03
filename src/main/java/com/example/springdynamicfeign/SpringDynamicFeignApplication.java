package com.example.springdynamicfeign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootApplication
public class SpringDynamicFeignApplication implements CommandLineRunner {

  @Autowired private ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication.run(SpringDynamicFeignApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    request("APPNAME1");
    request("APPNAME2");
    request("APPNAME3");
  }

  private void request(final String appServiceName) {
    log.info("#######    Requesting for {}   #######", appServiceName);

    DynamicFeignClient dynamicFeignClient =
        (DynamicFeignClient)
            new FeignClientBuilder(applicationContext)
                .forType(DynamicFeignClient.class, appServiceName)
                .path("/actuator/info")
                .build();

    ResponseEntity<String> info = dynamicFeignClient.info();
    log.info("Http Status Code {}", info.getStatusCodeValue());
    log.info("Body {}", info.getBody());
  }
}
