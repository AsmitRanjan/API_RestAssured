package plivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import plivofiles.resources;;

public class SearchNumber {
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		FileInputStream fis = new FileInputStream("F:\\javafiles1\\LetAPI\\src\\plivofiles\\env.properties");
		prop.load(fis);
		prop.getProperty("HOST");
	}
	
	
	@Test
	public void test1(){
		//Base URL
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = given().
				param("country_iso", "US").and().headers("Authorization","Basic TUFPRFVaWVRRMFkyRk1ZSkJMT1c6TXprME16VTFNemMzTVRjMU1URXlNR1UyTTJSbFlUSXdOMlV5TXprMQ==").
		when().
				get(resources.getResource()).
		then().
				assertThat().statusCode(200).and().extract().response();
		
		String s = res.asString();
		System.out.println(s);
		JsonPath js = new JsonPath(s);
		String number1 = js.get("objects[0].number");
		System.out.println(number1);
		String number2 = js.get("objects[1].number");
		System.out.println(number2);
				
	}
}
