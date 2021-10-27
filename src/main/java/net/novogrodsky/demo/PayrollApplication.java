package net.novogrodsky.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
		// meta-annotation brings in a lot like component scanning and property
		// support
public class PayrollApplication {

  public static void main(String[] args) {

		SpringApplication.run(PayrollApplication.class, args);
  }

}
