package restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAPIFunctions {

   public Response getAllTheData(String Resource_URL, String endpoint) {
      Response resp = RestAssured.given().get(Resource_URL + endpoint);
      return resp;
   }

}
