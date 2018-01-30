package kh.springboot.service3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kh.springboot.service3.domain.ExampleResponse;

@RestController
public class Service3RestController {

	@GetMapping(value = "/example3", produces = "application/json")
	public ExampleResponse example2(){
		
		//sleep for 2 secs
		try {
			Thread.sleep(2000);
		}
		catch(Exception e){
			//ignore
		}
		
		ExampleResponse response = new ExampleResponse("response from service3");
		
		return response;
	}
}
