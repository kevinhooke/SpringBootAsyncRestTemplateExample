package kh.springboot.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kh.springboot.service2.domain.ExampleResponse;

@RestController
public class Service2RestController {

	@GetMapping(value = "/example2", produces = "application/json")
	public ExampleResponse example2(){
		
		//sleep for 2 secs
		try {
			Thread.sleep(2000);
		}
		catch(Exception e){
			//ignore
		}
		
		ExampleResponse response = new ExampleResponse("response from service2");
		
		return response;
	}
}
