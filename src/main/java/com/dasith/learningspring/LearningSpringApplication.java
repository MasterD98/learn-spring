package com.dasith.learningspring;

import com.dasith.learningspring.business.ReservationService;
import com.dasith.learningspring.business.RoomDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LearningSpringApplication {
	private static final Logger LOG= LoggerFactory.getLogger(LearningSpringApplication.class);

	@Bean
	public CommandLineRunner run() throws Exception{
		return args -> {
			for (int i = 1; i <= 100; i++) {
				if(i%3==0 && i%5==0){
					LOG.info("FizzBuzz");
				}else if(i%3==0){
					LOG.info("Fizz");
				} else if (i%5==0) {
					LOG.info("Buzz");
				}else {
					LOG.info(Integer.toString(i));
				}
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

}
