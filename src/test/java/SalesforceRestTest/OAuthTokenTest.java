package SalesforceRestTest;

import static org.junit.Assert.*;

import org.apache.wink.client.RestClient;
import org.junit.Test;

import org.apache.wink.client.Resource;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class OAuthTokenTest {

	String accessToken=null;
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void oauthtoken(){
		RestClient rc=new RestClient();
		Resource resource= rc.resource("http://localhost:8080/mammoth/SalesforceRest");
		 String response = resource.contentType("text/plain").accept("text/plain").post(String.class, "foo");
		 System.out.println(response);
		 
		 JSONObject json = null;
			try {
				json = new JSONObject(response);
				accessToken = json.getString("access_token");				
				/**
				 * Use this to validate session instead of expiring on browser
				 * close.
				 */
			} catch (JSONException e) {
				e.printStackTrace();
			}
			System.out.println(accessToken);
	}
	
	@Test
public void Createuser(){
		
		RestClient rc=new RestClient();
		Resource resource= rc.resource("http://localhost:8080/mammoth/SalesforceRest/create");
		 String response = resource.contentType("text/plain").accept("text/plain").post(String.class, "foo");
		 System.out.println(response);
	
}

}
