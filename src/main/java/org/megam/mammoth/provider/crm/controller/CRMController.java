package org.megam.mammoth.provider.crm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.megam.mammoth.demo.controller.SalesforceController;
import org.megam.mammoth.provider.crm.info.salesforcecrm;
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
import com.google.gson.reflect.TypeToken;

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
		
		 /*Add the name value pairs as needed. Refer the links below.
		 http://hc.apache.org/httpcomponents-client-ga/quickstart.html
		 http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/index.html*/
		 List <NameValuePair> nvps = new ArrayList <NameValuePair>();
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
		logger.info("Get OAuth"+httpPost);
		try {
			response2 = httpclient.execute(httpPost);
			
			System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    //EntityUtils.consume(entity2);
		    output=EntityUtils.toString(entity2);
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
		
		


		logger.info(clz+"getOAuth GET end."+output);

		return output;
	}
    
	@RequestMapping(method = RequestMethod.POST,consumes="application/json")
	public @ResponseBody
	String createUser(@RequestBody String access_stuff) {
		logger.info(clz+"createUser : POST start."+access_stuff);
		
		/* Convert the access_stuff using Gson to an object, grab the strings as necessary */
		
		
		 String issuedAt;
		 String accessToken; 
		 String instanceurl = null;
		 String id;
		 String signature;
		 logger.info(clz+"createUser : POST start2222222.");
		 Gson gson = new GsonBuilder().create();
		    salesforcecrm scrm = gson.fromJson(access_stuff, salesforcecrm.class);
		    
		    accessToken=scrm.getAccess_token();
		    instanceurl=scrm.getInstance_url();
		    logger.info("ACCESTOKEN & INSTANCE URL"+accessToken+""+instanceurl);
		 
		String salesforce_create_user_url = instanceurl + "/services/data/v25.0/sobjects/User/";
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_create_user_url);
		
		httpPost.addHeader("Authorization", "OAuth " + accessToken);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("Username", scrm.getUsername()));
		nvps.add(new BasicNameValuePair("FirstName", scrm.getFirstName()));
		nvps.add(new BasicNameValuePair("Email", scrm.getEmail()));
		nvps.add(new BasicNameValuePair("Alias", scrm.getAlias()));
		nvps.add(new BasicNameValuePair("ProfileId", scrm.getProfileId()));
		nvps.add(new BasicNameValuePair("LastName", scrm.getLastName()));
		nvps.add(new BasicNameValuePair("TimeZoneSidKey", scrm.getTimeZoneSidKey()));
		nvps.add(new BasicNameValuePair("LocaleSidKey", scrm.getLocaleSidKey()));
		nvps.add(new BasicNameValuePair("EmailEncodingKey", scrm.getEmailEncodingKey()));
		nvps.add(new BasicNameValuePair("LanguageLocaleKey", scrm.getLanguageLocaleKey()));
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			//httpPost.
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpResponse response2;
		String output = null;
		try {
			response2 = httpclient.execute(httpPost);
			
			System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    //EntityUtils.consume(entity2);
		    output=EntityUtils.toString(entity2);
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
		
		


		logger.info(clz+"CreateUser GET end."+output);

		return output;
	}

}
