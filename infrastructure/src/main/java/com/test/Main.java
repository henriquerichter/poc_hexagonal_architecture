package com.test;

import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

  private static Logger LOG = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);

    LOG.info("main()");
    LOG.info("Arguments: " + ManagementFactory.getRuntimeMXBean().getInputArguments());
  }
}
