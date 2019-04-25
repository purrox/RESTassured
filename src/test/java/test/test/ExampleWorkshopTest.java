package test.test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ExampleWorkshopTest {
	

	@Test
	public void validateCountryForZipCode() {
		given()
		.when()
			.get("http://api.zippopotam.us/us/90210")
		.then()
			.assertThat()
				.statusCode(200)
				.body("country",
						equalTo("United States"));

	}
	
	@Test
	public void usingPathParameters() {
		given()
			.pathParam("zipCode", "90210")
			.pathParam("countryCode", "us")
		.when()
			.get("http://api.zippopotam.us/{countryCode}/{zipCode}")
		.then()
			.assertThat()
			.statusCode(200)
			.body("country",
				equalTo("United States"));
		
	}
	
	@Test
	public void usingQueryParams() {
		given()
			.queryParam("all", true)
		.when()
			.get("http://demo6968968.mockable.io/cars")
		.then()
			.assertThat()
			.statusCode(200)
			.body("size()", is(6));
	}
	
	
	@DataProvider
	public static Object[][] zipCodeData() {
		return new Object[][] {
			{"us","90210", "United States"},
			{"ca","Y1A", "Canada"}
		};
	}
	

	@Test(dataProvider = "zipCodeData")
	public void validateCountryForZipCodeUsingDataProvider( String countryCode, String zipCode, String expectedCountry) {
		given()
			.pathParam("zipCode", zipCode)
			.pathParam("countryCode", countryCode)
		.when()
			.get("http://api.zippopotam.us/{countryCode}/{zipCode}")
		.then()
			.assertThat()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("country",
				equalTo(expectedCountry));

	}
}
