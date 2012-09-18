package org.megam.mammoth.prov.googleapps.service;

import java.io.IOException;
import java.util.List;

import org.megam.mammoth.prov.info.GoogleUser;
import org.springframework.stereotype.Service;

import com.amazonaws.util.json.JSONObject;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.util.ServiceException;

@Service
public interface GoogleAppsService {

	public abstract void addUser(GoogleUser person);

	//public abstract Object listPeople();

	public abstract void removePerson(String personId);

	public abstract List<GoogleUser> GlistPeople(GoogleUser data);

	

}
