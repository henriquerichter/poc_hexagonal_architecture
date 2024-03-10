package com.test;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

  private static Logger LOG = Logger.getLogger(Application.class.getName());

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

    LOG.info("main()");
  }
}
