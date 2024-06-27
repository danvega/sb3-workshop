package dev.danvega.sb3w;

import dev.danvega.sb3w.post.PostClient;
import dev.danvega.sb3w.post.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository repository,
										PostClient client) {
	    return args -> {
			log.info("Hello KCDC 👋🏻");
		};
	}

}
