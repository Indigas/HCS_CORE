package sk.durovic.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CoreApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);

	}

	@Bean
	public String getProfile(){

		for (String profile : env.getActiveProfiles()
			 ) {
			System.out.println("Currently using profile:: "+profile);

		}
		return null;
	}

}
