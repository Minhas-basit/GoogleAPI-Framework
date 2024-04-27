package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.JsonBody;
import resources.ApiMethods;
import resources.JBData;
import resources.Utils;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.junit.Assert;

public class StepDefinition extends Utils {

	RequestSpecification request;
	Response methodrequest;
	JBData jd = new JBData();
	static String placeid; 
	
	@Given("add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		
		JsonBody json = jd.bodyData(name, language, address);

		request = given().spec(requestSpecification()).body(json);

	}

	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String EPresource, String method) {
		ApiMethods ep_url = ApiMethods.valueOf(EPresource);
		  
		if(method.equalsIgnoreCase("POST")) {
			methodrequest = request.when().post(ep_url.getEPresource());
		}
		else if(method.equalsIgnoreCase("GET")) {
			methodrequest = request.when().post(ep_url.getEPresource());
		}
	}

	@Then("api call get success with status code {int}")
	public void api_call_get_success_with_status_code(Integer int1) {
		Assert.assertEquals(methodrequest.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		Assert.assertEquals(getJsonPath(methodrequest, key), expectedValue);
	
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeid =getJsonPath(methodrequest, "place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", placeid);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(methodrequest,"name");
		Assert.assertEquals(actualName, expectedName);
	}
	
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
		request=given().spec(requestSpecification()).body(jd.deletePlaceload(placeid));
	}

}
