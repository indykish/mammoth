package org.megam.mammoth.provider.crm.rest.test;

import static org.junit.Assert.*;

import org.apache.wink.client.RestClient;
import org.junit.Before;
import org.junit.Test;

import org.apache.wink.client.Resource;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class SaleforceAdapterTest {

	private String accessToken = null;
	String response;

	@Before
	public void testGetOAuthToken() {
	RestClient rc = new RestClient();
		Resource resource = rc
				.resource("http://localhost:8080/mammoth/provider/crm");
		response = resource.accept("application/json").get(String.class);
		System.out.println(response);

		JSONObject json = null;
		try {
			json = new JSONObject(response);
			accessToken = json.getString("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(accessToken);
	}

	@Test
	public void testCreateUser() {
		System.out.println(response);
		RestClient rc = new RestClient();
		Resource resource = rc
				.resource("http://localhost:8080/mammoth/provider/crm");
		String response1 = resource.contentType("application/json").accept("application/json").post(String.class, response);
		System.out.println(response1);

	}

}
