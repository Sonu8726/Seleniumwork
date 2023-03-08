package apitest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import restAssured.RestAPIFunctions;
import utils.ConfigManager;

public class APITest {

   ConfigManager api = new ConfigManager("API");

   private String baseUrl = api.getProperty("BASE_URI");
   // Users
   private String accountAuthorized = api.getProperty("AUTH_ENDPOINT");
   private String generateToken = api.getProperty("GE_ENDPOINT");
   private String users = api.getProperty("USR_ENDPOINT");

   // Books
   private String booksEndpoint = api.getProperty("BOOKS_ENDPOINT");
   private String bookEndpoint = api.getProperty("BOOK_ENDPOINT");

   @Test
   public void test() {
      RestAPIFunctions rp = new RestAPIFunctions();

      Response resp = rp.getAllTheData(baseUrl, booksEndpoint);

      System.out.println("Status Code:" + resp.statusCode());
      System.out.println("Headers:" + resp.getHeader("Content-Type"));
      System.out.println("Response:" + resp.prettyPrint());
   }
}
