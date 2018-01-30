# SpringBootAsyncRestTemplateExample
Example use of AsyncRestTemplate to call other services asynchronously

Service1 calls Service2 and Service3

GET /service1/examplesynccalls
- uses RestTemplate to call Service2 followed by Service3 synchronously

GET /service1/exampleasynccalls
- uses AsyncRestTemplate to call Service2 and Service3 asynchronously. Each call 
returns a ListenableFuture. Calling .get() on the Future blocks until the result is available
