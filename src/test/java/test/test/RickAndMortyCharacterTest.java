package test.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.hasKey;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

/**
 * 
 * This is a test class use it for check the functionality of the Character API.
 * 
 * @author csalas-as
 *
 */
public class RickAndMortyCharacterTest {


	@Test
	public void verifyThatGetCharacterReturn200() {
		given()
		.when()
			 .get("https://rickandmortyapi.com/api/character/")
		 .then()
		 .assertThat()
			 .statusCode(200);	 
	}
	
	
	@Test
	public void verifyThatGetCharacterReturnAtLeast20Characters() {
		Response response = given()
		.when()
			 .get("https://rickandmortyapi.com/api/character/")
		 .then()
		 .assertThat()
			 .statusCode(200)
			 .extract().response();
		
		List<String> results = response.jsonPath().get("results");
		
		assertTrue(results.size() <= 20);
		assertTrue(results.size() > 0);
	}
	
	
	@Test
	public void verifyThatThefilterNameIsWorkingAsExpected() {
	
		given()
			.queryParam("name", "rick")
			.queryParam("status", "alive")
		.when()
			.get("https://rickandmortyapi.com/api/character/")
		.then()
			.assertThat()
			.statusCode(200)
			.body("results.name", everyItem(containsStringIgnoringCase("rick")));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
