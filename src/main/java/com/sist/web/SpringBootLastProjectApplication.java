package com.sist.web;

//import org.springframework.ai.google.genai.GoogleGenAiChatModel;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootLastProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLastProjectApplication.class, args);
	}
	
	/*@Bean
	CommandLineRunner runner(GoogleGenAiChatModel model) {
		return args -> {
			String response = model.call("gpt vs gemini 결과");
			System.out.println(response);
		};
	}*/

}
