package kh.springboot.service1;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import kh.springboot.service1.domain.Example2Response;
import kh.springboot.service1.domain.Example3Response;
import kh.springboot.service1.domain.ExampleResponse;

@RestController
public class Service1RestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AsyncRestTemplate asyncRestTemplate;
	
	
	/**
	 * Calls each of the 2 other services synchronously. Each service sleeps for 2 seconds, so total time
	 * before this method completes is approx 4 seconds.
	 * 
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	@GetMapping("/examplesynccalls")
	public ResponseEntity<ExampleResponse> exampleSyncRestTemplateCalls() throws RestClientException, URISyntaxException{
	
		//call service2
		ResponseEntity<Example2Response> result2 =
				this.restTemplate.getForEntity("http://localhost:8081/service2/example2", Example2Response.class);

		//call service3
		ResponseEntity<Example3Response> result3 =
				this.restTemplate.getForEntity("http://localhost:8082/service3/example3", Example3Response.class);

		
		ExampleResponse result = new ExampleResponse();
		result.setResult1(result2.getBody().getResult());
		result.setResult2(result3.getBody().getResult());
		return new ResponseEntity<ExampleResponse>(result, HttpStatus.OK);
	}
	
	/**
	 * Calls the 2 other services using AsyncRestTemplate which returns a ListenableFuture. Calling .get() on each
	 * Future blocks until the Future result is available.
	 * 
	 * Note that calling the 2 services using this approach takes approx 2 seconds with each service sleeping for
	 * 2 seconds, because both of the AsyncRestTemplate calls occur in parallel.
	 * 
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	@GetMapping("/exampleasynccalls")
	public ResponseEntity<ExampleResponse> exampleAsycRestTemplateCalls() throws RestClientException, URISyntaxException{
	
		//call service2
		ListenableFuture<ResponseEntity<Example2Response>> result2 =
				this.asyncRestTemplate.getForEntity("http://localhost:8081/service2/example2", Example2Response.class);

		//call service3
		ListenableFuture<ResponseEntity<Example3Response>> result3 =
				this.asyncRestTemplate.getForEntity("http://localhost:8082/service3/example3", Example3Response.class);

		
		ExampleResponse result = new ExampleResponse();
		//calling .get() on the ListenableFuture blocks until the result is available
		try {
			result.setResult1(((HttpEntity<Example2Response>) result2.get()).getBody().getResult());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		try {
			result.setResult2(result3.get().getBody().getResult());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ExampleResponse>(result, HttpStatus.OK);
	}
	
}
