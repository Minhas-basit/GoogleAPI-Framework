package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinition sd = new StepDefinition();

		if (StepDefinition.placeid == null) {

			sd.add_place_payload("Ammar Haider", "Persian", "Afghanistan");
			sd.user_calls_with_http_request("addPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Ammar Haider", "getPlaceAPI");
		}
	}

}
