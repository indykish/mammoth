package org.megam.mammoth.demo.service;

import java.util.List;

import org.megam.mammoth.demo.info.GoogleUser;
import org.springframework.stereotype.Service;

@Service
public interface GoogleAppsService {

	public abstract void addUser(GoogleUser person);

	public abstract void removeUser(GoogleUser person);

	public abstract List<GoogleUser> listUser(GoogleUser data);

	

}
