package org.megam.mammoth.provider.crm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.megam.mammoth.provider.crm.info.Salesforcecrm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/provider/crm/")
public class CRMController {

	private Validator validator;
	private static final String clz = "CRMController:";
	private final Logger logger = Logger.getLogger(CRMController.class);

	@Autowired
	public CRMController(Validator validator) {
		this.validator = validator;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String getOAuth() {
		logger.info(clz + "getOAuth GET start.");
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

		/*
		 * Add the name value pairs as needed. Refer the links below.
		 * http://hc.apache.org/httpcomponents-client-ga/quickstart.html
		 * http://hc
		 * .apache.org/httpcomponents-client-ga/httpclient/apidocs/index.html
		 */
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("grant_type", grant_type));
		nvps.add(new BasicNameValuePair("client_id", clientId));
		nvps.add(new BasicNameValuePair("client_secret", clientSecret));
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpResponse response2;
		String output = null;
		logger.info("Get OAuth" + httpPost);
		try {
			response2 = httpclient.execute(httpPost);

			System.out.println(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			// EntityUtils.consume(entity2);
			output = EntityUtils.toString(entity2);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			httpPost.releaseConnection();
		}

		logger.info(clz + "getOAuth GET end." + output);

		return output;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	String createUser(@RequestBody String access_stuff)
			throws UnsupportedEncodingException {
		logger.info(clz + "createUser : POST start." + access_stuff);

		/*
		 * Convert the access_stuff using Gson to an object, grab the strings as
		 * necessary
		 */

		String issuedAt;
		String accessToken;
		String instanceurl = null;
		String id;
		String signature;
		String ContentType = "application/json";
		logger.info(clz + "createUser : POST start2222222.");
		Gson gson = new GsonBuilder().create();
		Salesforcecrm scrm = gson.fromJson(access_stuff, Salesforcecrm.class);

		accessToken = scrm.getAccess_token();
		instanceurl = scrm.getInstance_url();
		logger.info("ACCESTOKEN & INSTANCE URL" + accessToken + ""
				+ instanceurl);

		String salesforce_create_user_url = instanceurl
				+ "/services/data/v25.0/sobjects/User/";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_create_user_url);

		httpPost.addHeader("Authorization", "OAuth " + accessToken);

		Map<String, Object> userupd = new HashMap<String, Object>();

		userupd.put("Username", scrm.getUsername());
		userupd.put("FirstName", scrm.getFirstName());
		userupd.put("Email", scrm.getEmail());
		userupd.put("Alias", scrm.getAlias());
		userupd.put("ProfileId", scrm.getProfileId());
		userupd.put("LastName", scrm.getLastName());
		userupd.put("TimeZoneSidKey", scrm.getTimeZoneSidKey());
		userupd.put("LocaleSidKey", scrm.getLocaleSidKey());
		userupd.put("EmailEncodingKey", scrm.getEmailEncodingKey());
		userupd.put("LanguageLocaleKey", scrm.getLanguageLocaleKey());

		ObjectMapper objmap = new ObjectMapper();

		try {
			httpPost.setEntity(new StringEntity(objmap
					.writeValueAsString(userupd), "application/json", "UTF-8"));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// httpPost.setEntity(new UrlEncodedFormEntity(nvps1));

		HttpResponse response2;
		String output = null;
		try {
			response2 = httpclient.execute(httpPost);

			System.out.println("RESPONSE2" + response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			// EntityUtils.consume(entity2);
			output = EntityUtils.toString(entity2);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}

		finally {
			httpPost.releaseConnection();
		}

		logger.info(clz + "CreateUser GET end." + output);

		return output;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody
	String listuser(@RequestBody String access_stuff) {

		String issuedAt;
		String accessToken;
		String instanceurl = null;
		String id;
		String signature;
		String ContentType = "application/json";
		logger.info(clz + "ListUser : POST start33333333333.");
		Gson gson = new GsonBuilder().create();
		Salesforcecrm scrm = gson.fromJson(access_stuff, Salesforcecrm.class);

		accessToken = scrm.getAccess_token();
		instanceurl = scrm.getInstance_url();
		logger.info("ACCESTOKEN & INSTANCE URL" + accessToken + ""
				+ instanceurl);

		String salesforce_create_user_url = instanceurl
				+ "/services/data/v25.0/query/?q=SELECT+Username+from+User";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(salesforce_create_user_url);
		httpGet.addHeader("Authorization", "OAuth " + accessToken);

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity2 = response.getEntity();
			output = EntityUtils.toString(entity2);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;
	}

	@RequestMapping(method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody
	String deleteUser(@RequestBody String access_stuff) {

		String issuedAt;
		String accessToken;
		String instanceurl = null;
		String id;
		String signature;
		String ContentType = "application/json";
		logger.info(clz + "ListUser : POST start33333333333.");
		Gson gson = new GsonBuilder().create();
		Salesforcecrm scrm = gson.fromJson(access_stuff, Salesforcecrm.class);

		accessToken = scrm.getAccess_token();
		instanceurl = scrm.getInstance_url();
		logger.info("ACCESTOKEN & INSTANCE URL" + accessToken + ""
				+ instanceurl);

		String salesforce_create_user_url = instanceurl
				+ "/services/data/v25.0/sobjects/Users/005900000010GuZ";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpDelete = new HttpDelete(salesforce_create_user_url);
		httpDelete.addHeader("Authorization", "OAuth " + accessToken);

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpDelete);
			HttpEntity entity2 = response.getEntity();
			output = EntityUtils.toString(entity2);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;

	}

}
