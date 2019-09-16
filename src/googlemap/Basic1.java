package googlemap;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Basic1 {
	@Test
	public void test1() {
		// TODO Auto-generated method stub
		
		//Base URL or HOST
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
				param("location","-33.8670522,151.1957362").
				param("radius","1500").
				param("key","AIzaSyD5JjEcu3q3D-alPe8FPtjGOV9g-mGJwus").
		when().
				get("maps/api/place/nearbysearch/json").
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).
				and().body("results[0].name",equalTo("Sydney")).and().
				header("Server", "scaffolding on HTTPServer2");
				
				
				

	}

}
