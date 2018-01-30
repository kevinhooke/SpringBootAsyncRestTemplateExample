package kh.springboot.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootService1App {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootService1App.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public AsyncRestTemplate getAsyncRestTemplate(){
		return new AsyncRestTemplate();
	}
}
