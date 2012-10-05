package org.megam.mammoth.prov.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SalesforceRestController {

	

	String issuedAt;
	String accessToken = null;
	String instanceurl;
	String id;
	String signature;
	
     
	private Validator validator;

	 final Logger logger = Logger.getLogger(SalesforceController.class);
	
	@Autowired
	public void SalesforceRESTController(Validator validator) {
		this.validator = validator;
	}

	@Test
	@RequestMapping(value = "/SalesforceRest", method = RequestMethod.POST)
	public @ResponseBody
	String getOAuth() {

		logger.info("IN REST METHOD...........");
		HttpServletResponse response;
		String clientId = "3MVG9Y6d_Btp4xp51yG_eZBS13SJirRv1uk0ITwiM5eRcfsC1qg4UinY_FbU3G0niSsUyI0zkEFkhzO89.TmV";
		String clientSecret = "6832915771039819665";
		String redirectUri = "http://localhost:8080/SForce/_auth";

		String environment = "https://login.salesforce.com/services/oauth2/token";
		String grant_type = "password";

		String username = "pontiyaraja@gmail.com";
		String password = "team4megamE1mlHRMlL7deN0zCN4oDAZlJq";

		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(environment);

		post.addParameter("grant_type", grant_type);
		/** For session ID instead of OAuth 2.0, use "grant_type", "password" **/
		post.addParameter("client_id", clientId);
		post.addParameter("client_secret", clientSecret);
		post.addParameter("username", username);
		post.addParameter("password", password);

		String responseBody = null;
		try {
			httpclient.executeMethod(post);
			responseBody = post.getResponseBodyAsString();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		logger.info("RESPONSE BODY.............." + responseBody);

		JSONObject json = null;
		try {
			json = new JSONObject(responseBody);
			accessToken = json.getString("access_token");
			logger.info("ACCESS TOKEN.............." + accessToken);
			issuedAt = json.getString("issued_at");
			instanceurl = json.getString("instance_url");
			logger.info("Instance url::::::::::" + instanceurl);
			id = json.getString("id");
			signature = json.getString("signature");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		/*
		 * HttpServletResponse httpResponse = (HttpServletResponse)response;
		 * Cookie session = new Cookie(ACCESS_TOKEN, accessToken);
		 * session.setMaxAge(-1); //cookie not persistent, destroyed on browser
		 * exit httpResponse.addCookie(session);
		 */

		return responseBody;
	}

	/* CREATE METHOD 1 */

	@RequestMapping(value = "/SalesforceRest/create", method = RequestMethod.POST)
	public int createUser() {
		logger.info("CREATE METHOD.......");

		String url = instanceurl + "/services/data/v25.0/sobjects/User/";

		PostMethod m = new PostMethod(url);
		m.setRequestHeader("Authorization", "OAuth " + accessToken);
		Map<String, Object> accUpdate = new HashMap<String, Object>();
		accUpdate.put("Username", " ponti@gmail.com");
		accUpdate.put("FirstName", "pan");
		accUpdate.put("Email", "raja.pandiya@megam.co.in");
		accUpdate.put("Alias", "PRam");
		accUpdate.put("ProfileId", "00e90000000Gmi2");
		accUpdate.put("LastName", "Diya");
		accUpdate.put("TimeZoneSidKey", "America/Los_Angeles");
		accUpdate.put("LocaleSidKey", "en_US");
		accUpdate.put("EmailEncodingKey", "UTF-8");
		accUpdate.put("LanguageLocaleKey", "en_US");
		ObjectMapper mapper = new ObjectMapper();

		try {
			m.setRequestEntity(new StringRequestEntity(mapper
					.writeValueAsString(accUpdate), "application/json", "UTF-8"));
		} catch (JsonGenerationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpClient c = new HttpClient();
		int str = 0;
		try {
			str = c.executeMethod(m);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
logger.info("RETURN ID"+str);
		return str;
	}

}
