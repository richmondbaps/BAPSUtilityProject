package com.baps.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
 * @author Mayuresh Trivedi
 * This is the main Application class which will be loaded by Spring Boot.
 */

@SpringBootApplication
public class Application {

	/**
	 * The main method of the Application.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
