package mongodb.tutorial;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class TutorialApplication {

	public static void main(String[] args) {

		SpringApplication.run(TutorialApplication.class, args);

		System.out.println("Tutorial Application Started");
	}

}
