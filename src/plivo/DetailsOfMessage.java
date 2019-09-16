package plivo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class DetailsOfMessage {
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
			
		Response res =
				given().
					headers("Authorization","Basic TUFPRFVaWVRRMFkyRk1ZSkJMT1c6TXprME16VTFNemMzTVRjMU1URXlNR1UyTTJSbFlUSXdOMlV5TXprMQ==").
				when().
					get("v1/Account/MAODUZYTQ0Y2FMYJBLOW/Message/3c326c34-6d21-11e8-9746-060740e7e786").
				then().
					assertThat().statusCode(200).extract().response();
		String s = res.asString();
		System.out.println(s);
		
	}
	
}
