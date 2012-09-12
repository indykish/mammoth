package org.megam.mammoth.prov.googleapps.service;

import org.megam.mammoth.prov.info.GoogleUser;
import org.springframework.stereotype.Service;

import com.amazonaws.util.json.JSONObject;

@Service
public interface GoogleAppsService {

	public abstract String addUser(String dat);

	public abstract Object listPeople();

	public abstract void removePerson(String personId);

}
