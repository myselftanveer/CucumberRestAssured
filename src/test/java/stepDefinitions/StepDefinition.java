package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	RequestSpecification res;
	Response response;

	TestDataBuild data = new TestDataBuild();
	static String placeID;

	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlaceApi(name, language, address));
	}

	@When("Use calls {string} with {string} http request")
	public void use_calls_with_http_request(String resource, String method) {

		ApiResources addPlaceApi = ApiResources.valueOf(resource);
		System.out.println(addPlaceApi.getApiResource());

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(addPlaceApi.getApiResource()).then().spec(responceSpecification()).extract()
					.response();
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(addPlaceApi.getApiResource()).then().spec(responceSpecification()).extract()
					.response();
	}

	@Then("the API call get success with success code {int}")
	public void the_api_call_get_success_with_success_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String valueExpected) {
//		String responseString = response.asString();
//		System.out.println(responseString);
//		JsonPath js = new JsonPath(responseString);
//		assertEquals(js.get(keyValue).toString(), valueExpected);
		assertEquals(convertRawToJson(response, keyValue), valueExpected);

	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		placeID = convertRawToJson(response, "place_id");
		System.out.println(placeID);
		res = given().spec(requestSpecification()).queryParam("place_id", placeID);
		use_calls_with_http_request(resource, "GET");
		String actualName = convertRawToJson(response, "name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		
		res =given().spec(requestSpecification()).body(data.deletePlacePayload(placeID));
	}
}
