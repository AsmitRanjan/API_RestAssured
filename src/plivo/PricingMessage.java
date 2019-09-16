package plivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PricingMessage {
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		FileInputStream fis = new FileInputStream("F:\\javafiles1\\LetAPI\\src\\plivofiles\\env.properties");
		prop.load(fis);
	}
	@Test
	public void test(){
		//Base URI
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = 
				given().
				headers("Authorization","Basic TUFPRFVaWVRRMFkyRk1ZSkJMT1c6TXprME16VTFNemMzTVRjMU1URXlNR1UyTTJSbFlUSXdOMlV5TXprMQ==").
				when().
					get("v1/Account/MAODUZYTQ0Y2FMYJBLOW/Message/3c326c34-6d21-11e8-9746-060740e7e786").
				then().
					assertThat().statusCode(200).extract().response();
		System.out.println(res.statusCode());
		String s = res.asString();
		System.out.println(s);
		
	}
}
