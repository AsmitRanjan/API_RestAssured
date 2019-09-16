package googlemap;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import googlemapfiles.ReusableMethods;
import googlemapfiles.payLoad;
import googlemapfiles.resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basic2 {
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		
		FileInputStream fis = new FileInputStream("F:\\javafiles1\\LetAPI\\src\\googlemapfiles\\env.properties");
		prop.load(fis);
		prop.getProperty("HOST");
	}
	
	
	@Test
	public void postTest(){
		//Base URL
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = 
		given().
				queryParam("key", prop.getProperty("KEY")).and().
				body(payLoad.getPostData()).and().
		when().
				post(resources.placePostData()).
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				extract().response();
		
		
		JsonPath js = ReusableMethods.rawToJson(res);
		String placeId = js.get("place_id");
		System.out.println(placeId);
		
		given().
		queryParam("key", prop.getProperty("KEY")).and().
		body("{"+
			  "\"place_id\": \""+placeId+"\""+
		"}").
		when().
				post("maps/api/place/delete/json").
		then().
				assertThat().statusCode(200);
	}
}
