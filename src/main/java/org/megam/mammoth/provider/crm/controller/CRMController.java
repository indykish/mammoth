package org.megam.mammoth.provider.crm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Validator;

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

import org.megam.mammoth.provider.crm.info.SalesforceCRM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private final Logger logger = LoggerFactory.getLogger(CRMController.class);


	@Autowired
	public CRMController(Validator validator) {		
		this.validator = validator;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String getOAuth() {
		logger.info(clz + "getOAuth GET start.");


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



		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("grant_type", grant_type));
		nvps.add(new BasicNameValuePair("client_id", clientId));
		nvps.add(new BasicNameValuePair("client_secret", clientSecret));
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpPost);
			System.out.println(response.getStatusLine());
			output = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}finally {

			httpPost.releaseConnection();
		}

		logger.info(clz + "getOAuth GET end." + output);

		return output;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	String createUser(@RequestBody String access_stuff)
			throws UnsupportedEncodingException {

		logger.info(clz + "createUser : POST start.\n" + access_stuff);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);
		logger.info(clz + "createUser :" + salesforceCRM.toString());

		String salesforce_create_user_url = salesforceCRM.getInstance_url()

				+ "/services/data/v25.0/sobjects/User/";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_create_user_url);


		httpPost.addHeader("Authorization",
				"OAuth " + salesforceCRM.getAccess_token());

		Map<String, Object> userAttrMap = new HashMap<String, Object>();
		userAttrMap.put("Username", salesforceCRM.getUsername());
		userAttrMap.put("FirstName", salesforceCRM.getFirstName());
		userAttrMap.put("Email", salesforceCRM.getEmail());
		userAttrMap.put("Alias", salesforceCRM.getAlias());
		userAttrMap.put("ProfileId", salesforceCRM.getProfileId());
		userAttrMap.put("LastName", salesforceCRM.getLastName());
		userAttrMap.put("TimeZoneSidKey", salesforceCRM.getTimeZoneSidKey());
		userAttrMap.put("LocaleSidKey", salesforceCRM.getLocaleSidKey());
		userAttrMap
				.put("EmailEncodingKey", salesforceCRM.getEmailEncodingKey());
		userAttrMap.put("LanguageLocaleKey",
				salesforceCRM.getLanguageLocaleKey());


		ObjectMapper objmap = new ObjectMapper();

		try {
			httpPost.setEntity(new StringEntity(objmap

					.writeValueAsString(userAttrMap), "application/json",
					"UTF-8"));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return e.toString();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpPost);
			System.out.println(clz + "createUser : POST : RESPONSE\n"
					+ response);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {

			e.printStackTrace();
			return e.toString();
		} finally {
			httpPost.releaseConnection();
		}


		logger.info(clz + "CreateUser POST end." + output);
		return output;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody
	String listuser(@RequestBody String access_stuff) {
		logger.info(clz + "ListUser : GET start.");
		Gson gson = new GsonBuilder().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);

		logger.info(clz + "ListUser :" + salesforceCRM.toString());

		String salesforceListSingeUserURL = salesforceCRM.getInstance_url()
				+ "/services/data/v25.0/query/?q=SELECT+Username+from+User";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(salesforceListSingeUserURL);
		httpGet.addHeader("Authorization",
				"OAuth " + salesforceCRM.getAccess_token());

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpGet);
			System.out.println(clz + "listUser : GET : RESPONSE\n" + response);
			output = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();

		} finally {
			httpGet.releaseConnection();
		}

		return output;
	}

	@RequestMapping(method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody
	String deleteUser(@RequestBody String access_stuff) {

		logger.info(clz + "deleteUser : DELETE.");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);

		logger.info(clz + "deleteUser :" + salesforceCRM.toString());

		String salesforceDeleteSingeUserURL = salesforceCRM.getInstance_url()
				+ "/services/data/v25.0/sobjects/Users/005900000010GuZ";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpDelete = new HttpDelete(salesforceDeleteSingeUserURL);
		httpDelete.addHeader("Authorization",
				"OAuth " + salesforceCRM.getAccess_token());

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpDelete);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return output;

	}


}

