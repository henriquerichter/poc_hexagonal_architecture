package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

@SpringBootApplication
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        LOG.info("main()");
        LOG.info("Arguments: " + ManagementFactory.getRuntimeMXBean().getInputArguments());
    }
}
