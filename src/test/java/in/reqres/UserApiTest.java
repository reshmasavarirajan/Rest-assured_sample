package in.reqres;


import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class UserApiTest {
	
	/**
	 * Test for verifying user API response.
	 */
	@Test
	public void getUserTest() {
	    RestAssured.baseURI = "https://reqres.in/api/users/";
	    RequestSpecification httpRequest = RestAssured.given();
	    Response response = httpRequest.request(Method.GET,"2");
	    JsonPath jsonResponse = response.jsonPath();
	    String name =  jsonResponse.get("data.first_name");
	    Assert.assertEquals(name,"Janet");
	    int statusCode = response.getStatusCode();
	    Assert.assertEquals(statusCode,200);
	}
	
	/**
	 * Test for creating new user.
	 */
	@Test
	public void createUserTest() {
	    RestAssured.baseURI = "https://reqres.in/api/users/";
	    RequestSpecification httpRequest = RestAssured.given();
	    JSONObject updateUser = new JSONObject();
	    updateUser.put("first_name","Kate");
	    updateUser.put("last_name","Spade");
	    updateUser.put("email","katespade@reqres.in");
	    updateUser.put("avatar","https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
	    JSONObject updateData = new JSONObject();
	    updateData.put("data",updateUser);
	    httpRequest.header("Content-Type","application/json");
	    httpRequest.body(updateData.toJSONString());
	    Response response = httpRequest.request(Method.POST);
	    int statusCode = response.getStatusCode();
	    Assert.assertEquals(statusCode,201);
	}

}
