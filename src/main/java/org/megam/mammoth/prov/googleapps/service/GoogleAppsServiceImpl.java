package org.megam.mammoth.prov.googleapps.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.validation.Validator;

import org.megam.mammoth.prov.info.GoogleUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.style.ToStringCreator;
import org.springframework.stereotype.Service;

import com.google.gdata.client.appsforyourdomain.AppsGroupsService;
import com.google.gdata.client.appsforyourdomain.EmailListRecipientService;
import com.google.gdata.client.appsforyourdomain.EmailListService;
import com.google.gdata.client.appsforyourdomain.NicknameService;
import com.google.gdata.client.appsforyourdomain.UserService;
import com.google.gdata.data.Link;
import com.google.gdata.data.Person;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.data.appsforyourdomain.Login;
import com.google.gdata.data.appsforyourdomain.Name;
import com.google.gdata.data.appsforyourdomain.Quota;
import com.google.gdata.data.appsforyourdomain.provisioning.UserEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.UserFeed;
import com.google.gdata.util.ServiceException;

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

	private Object List;

	private java.util.List<UserEntry> UserEntry;

	@Override
	public void addUser(GoogleUser data) {

		/*
		 * domainUrlBase = APPS_FEEDS_URL_BASE + data.getDomain() + "/";
		 * 
		 * UserEntry createdUserEntry = null;
		 * 
		 * 
		 * try {
		 * 
		 * userService = new UserService( "Megam-Mammoth-UserService");
		 * userService.setUserCredentials(data.getAdminEmail(),
		 * data.getAdminPassword());
		 * 
		 * groupService = new AppsGroupsService(data.getAdminEmail(),
		 * data.getAdminPassword(), data.getDomain(),
		 * "Megam-mammoth-AppsGroupService");
		 * 
		 * createdUserEntry = createUser(data.getUserName(),
		 * data.getGivenName(), data.getFamilyName(), data.getPassword(),
		 * data.getDomain()); } catch (ServiceException | IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); return
		 * "Not yet implemented"; } ToStringCreator ts = new
		 * ToStringCreator(this).append("User Created", createdUserEntry);
		 * return ts.toString(); }
		 * 
		 * public UserEntry createUser(String username, String givenName, String
		 * familyName, String password,String domain) throws
		 * AppsForYourDomainException, ServiceException, IOException {
		 * 
		 * return createUser(username, givenName, familyName, password, null,
		 * null, domain); }
		 * 
		 * public UserEntry createUser(String username, String givenName, String
		 * familyName, String password, String passwordHashFunction, Integer
		 * quotaLimitInMb,String domain) throws AppsForYourDomainException,
		 * ServiceException, IOException {
		 * 
		 * logger.info("Creating user '" + username + "'. Given Name: '" +
		 * givenName + "' Family Name: '" + familyName + "'domain:'" + domain +
		 * (passwordHashFunction != null ? "' Hash Function: '" +
		 * passwordHashFunction : "") + (quotaLimitInMb != null ?
		 * "' Quota Limit: '" + quotaLimitInMb + "'." : "'."));
		 * 
		 * UserEntry entry = new UserEntry(); Login login = new Login();
		 * login.setUserName(username); login.setPassword(password); if
		 * (passwordHashFunction != null) {
		 * login.setHashFunctionName(passwordHashFunction); }
		 * entry.addExtension(login);
		 * 
		 * Name name = new Name(); name.setGivenName(givenName);
		 * name.setFamilyName(familyName); entry.addExtension(name);
		 * 
		 * if (quotaLimitInMb != null) { Quota quota = new Quota();
		 * quota.setLimit(quotaLimitInMb); entry.addExtension(quota); }
		 * 
		 * URL insertUrl = new URL(domainUrlBase + "user/" + SERVICE_VERSION);
		 * return userService.insert(insertUrl, entry);
		 */
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GoogleUser> listPeople(GoogleUser data) throws AppsForYourDomainException, ServiceException, IOException {
		
		//GoogleUser data=new GoogleUser();
		UserFeed usrfd = null;
		String adminEmail=data.getAdminEmail();
		String adminPassword=data.getAdminPassword();
		String domain=data.getDomain();
		logger.info("IN LIST METHOD::::::::::::::::::"+adminEmail+""+adminPassword+""+domain);
				
		try {
			AppsForYourDomainClient apclient = new AppsForYourDomainClient(adminEmail,
					adminPassword, domain);
			   usrfd=apclient.retrieveAllUsers();
			
			String id=usrfd.getId();
			String logo=usrfd.getLogo();
			String localname=usrfd.getExtensionLocalName();
						
			logger.info("RETRIVED VALUE.................''''''''''''"+id+""+logo+""+localname);
			//apclient.retrieveAllUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<UserEntry> usetry=usrfd.getEntries();
		
		List<GoogleUser> gulist = new ArrayList<GoogleUser>();
		gulist.add((GoogleUser) usetry);
		logger.info("RETRIVED VALUE.................''''''''''''"+usrfd);
		//return null;
		return (java.util.List<GoogleUser>) usrfd;
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
