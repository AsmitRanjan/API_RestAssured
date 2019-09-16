package plivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SendMessage {
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		FileInputStream fis = new FileInputStream("F:\\javafiles1\\LetAPI\\src\\plivofiles\\env.properties");
		prop.load(fis);
	}
	@Test
	public void test1(){
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = (Response)
				given().
					header("Content-Type","application/json").header("Authorization","Basic TUFPRFVaWVRRMFkyRk1ZSkJMT1c6TXprME16VTFNemMzTVRjMU1URXlNR1UyTTJSbFlUSXdOMlV5TXprMQ==").
					body("{"+
						"\"src\" : \"14153014785\","+
						"\"dst\" : \"14153014770\","+
						"\"text\": \"Hiiiii\""+
						"}").
				when().
					post("v1/Account/MAODUZYTQ0Y2FMYJBLOW/Message/").
				then().
					assertThat().statusCode(202).and().extract().response();
		System.out.println(res.statusCode());
	}
	
}
