package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static String globalBaseUrl(String key) throws IOException {
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(
				"/Users/basitzameer/Tools/Git-Stuff/GoogleApiFramework/src/test/java/resources/baseurl.properties");
		pro.load(fis);
		return pro.getProperty(key);
	}

	public RequestSpecification requestSpecification() throws IOException {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

		RequestSpecification req = new RequestSpecBuilder().setBaseUri(globalBaseUrl("baseUrl"))
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		return req;
	}

	public ResponseSpecification responseSpecification() {
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return res;
	}
	
	public String getJsonPath(Response methodrequest,String key) {
		String stringResponse=methodrequest.asString();
		JsonPath js=new JsonPath(stringResponse);
		
		return js.get(key).toString();
	}

}
