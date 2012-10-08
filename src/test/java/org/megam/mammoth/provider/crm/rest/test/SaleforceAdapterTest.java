package org.megam.mammoth.provider.crm.rest.test;

import static org.junit.Assert.*;

import org.apache.wink.client.RestClient;
import org.junit.Test;

import org.apache.wink.client.Resource;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class SaleforceAdapterTest {

	private String accessToken = null;

	@Test
	public void testGetOAuthToken() {
		RestClient rc = new RestClient();
		Resource resource = rc
				.resource("http://localhost:8080/mammoth/salesforce_connector");
		String response = resource.contentType("text/plain")
				.accept("text/plain").post(String.class, "foo");
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

		RestClient rc = new RestClient();
		Resource resource = rc
				.resource("http://localhost:8080/mammoth/salesforce_connector/create");
		String response = resource.contentType("text/plain")
				.accept("text/plain").post(String.class, "foo");
		System.out.println(response);

	}

}
