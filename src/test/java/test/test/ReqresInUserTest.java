package test.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.testng.annotations.Test;

/**
 *  Use 
 *  	https://reqres.in/
 *  
 *  In the page you will found a lot of of endpoints 
 *  that you can use to do create some tests with restassured.
 * */
public class ReqresInUserTest {
	

	private static String payload = "{\n" +
	        "  \"name\": \"morpheus\",\n" +
	        "  \"job\": \"leader\"\n" +
	        "}";

	@Test
	public void postExampleForCreateAndUSer() {
		given()
			.body(payload)
		.when()
		 	.post("https://reqres.in/api/users")
	 	.then()
		 .assertThat()
		 	.statusCode(201)
			.body(containsString("id"));
	}
	
	
	
}
