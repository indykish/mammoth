package org.megam.mammoth.demo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.megam.mammoth.demo.info.GoogleUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.data.appsforyourdomain.provisioning.UserEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.UserFeed;
import com.google.gdata.util.ServiceException;

@Service
public class GoogleAppsServiceImpl implements GoogleAppsService {

	final Logger logger = LoggerFactory.getLogger(GoogleAppsServiceImpl.class);

	private Validator validator;

	@Override
	public void addUser(GoogleUser data) {
		AppsForYourDomainClient apclient;
		try {
			apclient = new AppsForYourDomainClient(data.getAdminEmail(),
					data.getAdminPassword(), data.getDomainName());
			apclient.createUser(data.getUserName(), data.getGivenName(), data.getFamilyName(), data.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<GoogleUser> listUser(GoogleUser data) {

		List<GoogleUser> users = new ArrayList<GoogleUser>();
		UserFeed userFeed = null;
		logger.info("Input user :"+data);
		AppsForYourDomainClient apclient;
		try {
			apclient = new AppsForYourDomainClient(data.getAdminEmail(),
					data.getAdminPassword(), data.getDomainName());
			userFeed = apclient.retrieveAllUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userFeed != null) {
			for (UserEntry userEntry : userFeed.getEntries()) {
				GoogleUser singleUser = new GoogleUser();
				singleUser.setUserName(userEntry.getLogin().getUserName());
				singleUser.setPassword(userEntry.getLogin().getPassword());
				singleUser.setAdmin(userEntry.getLogin().getAdmin());
				singleUser.setFamilyName(userEntry.getName().getFamilyName());
				singleUser.setGivenName(userEntry.getName().getGivenName());
				users.add(singleUser);
			}

		}

		logger.info("Returned users:\n"+users);
		return users;

	}

	@Override
	public void removeUser(GoogleUser data) {
		AppsForYourDomainClient apclient;
		try {
			apclient = new AppsForYourDomainClient(data.getAdminEmail(),
					data.getAdminPassword(), data.getDomainName());
			apclient.deleteUser(data.getUserName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
