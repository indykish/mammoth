package org.megam.mammoth.prov.googleapps.service;

import org.megam.mammoth.prov.info.GoogleUser;
import org.springframework.stereotype.Service;
import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.google.api.client.json.JsonEncoding;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonParser;
import com.google.gdata.client.appsforyourdomain.AppsForYourDomainQuery;
import com.google.gdata.client.appsforyourdomain.AppsGroupsService;
import com.google.gdata.client.appsforyourdomain.EmailListRecipientService;
import com.google.gdata.client.appsforyourdomain.EmailListService;
import com.google.gdata.client.appsforyourdomain.NicknameService;
import com.google.gdata.client.appsforyourdomain.UserService;
import com.google.gdata.data.Link;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainErrorCode;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.data.appsforyourdomain.EmailList;
import com.google.gdata.data.appsforyourdomain.Login;
import com.google.gdata.data.appsforyourdomain.Name;
import com.google.gdata.data.appsforyourdomain.Nickname;
import com.google.gdata.data.appsforyourdomain.Quota;
import com.google.gdata.data.appsforyourdomain.generic.GenericEntry;
import com.google.gdata.data.appsforyourdomain.generic.GenericFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListRecipientEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListRecipientFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.NicknameEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.NicknameFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.UserEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.UserFeed;

import com.google.gdata.data.extensions.Who;
import com.google.gdata.util.ServiceException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javassist.bytecode.ByteArray;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.JsonParseException;
import org.junit.internal.matchers.Each;
import org.megam.mammoth.prov.info.GoogleUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.style.ToStringCreator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.test.web.server.setup.MockMvcBuilders.*;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.*;

@Service
public class GoogleAppsServiceImpl implements GoogleAppsService {

	final Logger logger = LoggerFactory.getLogger(GoogleAppsServiceImpl.class);

	private static final String APPS_FEEDS_URL_BASE = "https://apps-apis.google.com/a/feeds/";

	private static final String SERVICE_VERSION = "2.0";

	protected String domainUrlBase;
	protected EmailListRecipientService emailListRecipientService;
	protected EmailListService emailListService;
	protected NicknameService nicknameService;
	protected UserService userService;
	protected AppsGroupsService groupService;
	private Validator validator;
	protected final String domain = "megam.co.in";

	@Override
	public String addUser(String data) {
        GoogleUser dat=new GoogleUser();
		String username =dat.getUserName();
		String givenName = dat.getGivenName();
		String familyName = dat.getFamilyName();
		String password = dat.getPassword();

		// try {

		// JsonParser parser=

		/*
		 * Object obj=parser.parse(jlist); JSONArray array=(JSONArray)obj;
		 * System.out.println("======the 2nd element of array======");
		 * System.out.println(array.get(1)); System.out.println();
		 * 
		 * JSONObject obj2=(JSONObject)array.get(1);
		 * System.out.println("======field \"1\"==========");
		 * System.out.println(obj2.get("1"));
		 */

		// org.codehaus.jackson.JsonFactory jf=new
		// org.codehaus.jackson.JsonFactory();
		// org.codehaus.jackson.JsonParser parser= jf.createJsonParser(jlist);
		/*
		 * List<String> list=new ArrayList<String>(); list.add(element);
		 * Iterator<String> itr=list.iterator(); while(itr.hasNext()){
		 * logger.info("LIST:"+itr.next()); }
		 */
		// while(parser.nextToken() != null){
		// logger.info("CURRENT NAME    " +parser.getCurrentName());
		// }
		// username=parser.getText();
		logger.info("CURRENT NAME" + username);
		/*
		 * } catch (IOException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */
		logger.info("USERNAME:");
		domainUrlBase = APPS_FEEDS_URL_BASE + domain + "/";

		String adminEmail = "raja.pandiya@yahoo.com";
		String adminPassword = "9787812535";
		UserEntry createdUserEntry = null;
		logger.info("In GOOGLE CREATE USER...........");

		/*
		 * nicknameService = new NicknameService(
		 * "gdata-sample-AppsForYourDomain-NicknameService");
		 * nicknameService.setUserCredentials(adminEmail, adminPassword);
		 * 
		 * emailListService = new EmailListService(
		 * "gdata-sample-AppsForYourDomain-EmailListService");
		 * emailListService.setUserCredentials(adminEmail, adminPassword);
		 * 
		 * emailListRecipientService = new EmailListRecipientService(
		 * "gdata-sample-AppsForYourDomain-EmailListRecipientService");
		 * emailListRecipientService.setUserCredentials(adminEmail,
		 * adminPassword);
		 * 
		 * groupService = new AppsGroupsService(adminEmail, adminPassword,
		 * domain, "gdata-sample-AppsForYourDomain-AppsGroupService");
		 */

		try {

			userService = new UserService(
					"gdata-sample-AppsForYourDomain-UserService");
			userService.setUserCredentials(adminEmail, adminPassword);

			groupService = new AppsGroupsService(adminEmail, adminPassword,
					domain, "gdata-sample-AppsForYourDomain-AppsGroupService");

			createdUserEntry = createUser(username, givenName, familyName,
					password);
		} catch (ServiceException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Not yet implemented";
		}
		ToStringCreator ts = new ToStringCreator(this).append("User Created",
				createdUserEntry);
		return ts.toString();
	}

	public UserEntry createUser(String username, String givenName,
			String familyName, String password)
			throws AppsForYourDomainException, ServiceException, IOException {

		return createUser(username, givenName, familyName, password, null, null);
	}

	public UserEntry createUser(String username, String givenName,
			String familyName, String password, String passwordHashFunction,
			Integer quotaLimitInMb) throws AppsForYourDomainException,
			ServiceException, IOException {

		logger.info("Creating user '"
				+ username
				+ "'. Given Name: '"
				+ givenName
				+ "' Family Name: '"
				+ familyName
				+ "'domain:'"
				+ domain
				+ (passwordHashFunction != null ? "' Hash Function: '"
						+ passwordHashFunction : "")
				+ (quotaLimitInMb != null ? "' Quota Limit: '" + quotaLimitInMb
						+ "'." : "'."));

		UserEntry entry = new UserEntry();
		Login login = new Login();
		login.setUserName(username);
		login.setPassword(password);
		if (passwordHashFunction != null) {
			login.setHashFunctionName(passwordHashFunction);
		}
		entry.addExtension(login);

		Name name = new Name();
		name.setGivenName(givenName);
		name.setFamilyName(familyName);
		entry.addExtension(name);

		if (quotaLimitInMb != null) {
			Quota quota = new Quota();
			quota.setLimit(quotaLimitInMb);
			entry.addExtension(quota);
		}

		URL insertUrl = new URL(domainUrlBase + "user/" + SERVICE_VERSION);
		return userService.insert(insertUrl, entry);

	}

	@Override
	public Object listPeople() {

		return null;
	}

	@Override
	public void removePerson(String element) {

		logger.info("Deleting user '" + element + "'.");

		URL deleteUrl;
		try {
			deleteUrl = new URL(domainUrlBase + "user/" + SERVICE_VERSION + "/"
					+ element);
			userService.delete(deleteUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AppsForYourDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
