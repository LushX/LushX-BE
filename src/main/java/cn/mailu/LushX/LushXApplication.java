package cn.mailu.LushX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"cn.mailu.LushX"})
@EnableScheduling
public class LushXApplication {

	public static void main(String[] args) {
		SpringApplication.run(LushXApplication.class, args);
	}
}
