package cn.mailu.LushX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"cn.mailu.LushX"})
@EnableScheduling
@EnableCaching
public class LushXApplication {

	public static void main(String[] args) {
		SpringApplication.run(LushXApplication.class, args);
	}
}
