package io.bush.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import io.bush.api.config.SwaggerConfig;
import io.bush.api.config.WebConfig;


@SpringBootApplication
@Import({ WebConfig.class, SwaggerConfig.class })
public class Application {

	public static void main(String[] args) {
		//System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,
		 //"prod");
		SpringApplication.run(Application.class, args);
	}
}