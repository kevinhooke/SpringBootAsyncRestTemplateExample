package kh.springboot.service3.domain;

public class ExampleResponse {

	private String result;

	public ExampleResponse(){
	}

	public ExampleResponse(String msg){
		this.result = msg;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
