package org.megam.mammoth.provider.crm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.megam.mammoth.demo.controller.SalesforceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/provider/crm")
public class CRMController {

	
	private Validator validator;
	private static final String clz ="CRMController:";
	private final Logger logger = Logger.getLogger(CRMController.class);

	@Autowired
	public CRMController(Validator validator) {
		this.validator = validator;
	}

	@RequestMapping(method = RequestMethod.GET,produces="application/json")
	public @ResponseBody
	String getOAuth() {
		logger.info(clz+"getOAuth GET start.");
		HttpServletResponse response;
		String clientId = "3MVG9Y6d_Btp4xp51yG_eZBS13SJirRv1uk0ITwiM5eRcfsC1qg4UinY_FbU3G0niSsUyI0zkEFkhzO89.TmV";
		String clientSecret = "6832915771039819665";
		String redirectUri = "http://localhost:8080/SForce/_auth";

		String salesforce_oauth_url = "https://login.salesforce.com/services/oauth2/token";
		/** For session ID instead of OAuth 2.0, use "grant_type", "password" **/
		String grant_type = "password";
		String username = "pontiyaraja@gmail.com";
		String password = "team4megamE1mlHRMlL7deN0zCN4oDAZlJq";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_oauth_url);
		
		/** Add the name value pairs as needed. Refer the links below.
		 * http://hc.apache.org/httpcomponents-client-ga/quickstart.html
		 * http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/index.html
		 *  List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		HttpResponse response2 = httpclient.execute(httpPost);

		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity2);
		} finally {
		    httpPost.releaseConnection();
		} **/

		post.addParameter("grant_type", grant_type);
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

		logger.info(clz+"getOAuth GET end.");

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.POST,consumes="application/json")
	public @ResponseBody
	String createUser(@RequestBody String access_stuff) {
		logger.info(clz+"createUser : POST start.");
		
		/* Convert the access_stuff using Gson to an object, grab the strings as necessary */
		 String issuedAt;
		 String accessToken = null;
		 String instanceurl ="";
		 String id;
		 String signature;

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
		String output = "";
		try {
			int return_code = c.executeMethod(m);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.info(clz+"createUser : POST end.");
		return output;
	}

}
