package org.megam.mammoth.prov.salesforce.service;

import java.util.List;

import org.megam.mammoth.prov.info.SalesforceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.force.api.ApiSession;
import com.force.api.ForceApi;
import com.force.api.QueryResult;
import com.force.api.http.HttpRequest;
import com.force.sdk.oauth.context.ForceSecurityContextHolder;
import com.force.sdk.oauth.context.SecurityContext;

@Service
public class UserServiceImpl implements UserService{

	HttpRequest request = new HttpRequest();

	ApiSession s = new ApiSession();

	public String Token;

	final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public void addPerson(SalesforceUser person) {
		getForceApi().createSObject("User", person);
	}

	public List<SalesforceUser> listPeople() {
		logger.debug("SalesforceController:LIST imp");
		QueryResult<SalesforceUser> res = getForceApi().query(
				"SELECT FirstName, LastName, Username FROM User",
				SalesforceUser.class);
		return res.getRecords();
	}

	public void removePerson(String id) {
		getForceApi().deleteSObject("User", id);

	}
	
	private ForceApi getForceApi() {
		SecurityContext sc = ForceSecurityContextHolder.get();
		s.setAccessToken(sc.getSessionId());
		s.setApiEndpoint(sc.getEndPointHost());
		Token = s.getAccessToken();
		String ENDpoint = s.getApiEndpoint();
		logger.info("ENDPOINT" + ENDpoint);
		logger.info("ACCES TOKEN=" + Token);
		logger.debug("TOKEN" + Token);
		return new ForceApi(s);
	}


}
