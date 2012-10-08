package org.megam.mammoth.demo.service;

import java.util.List;

import org.megam.mammoth.demo.info.SalesforceUser;


public abstract interface SalesforceUserService {

	public abstract List<SalesforceUser> listPeople();

	public abstract void addPerson(SalesforceUser person);

	public abstract void removePerson(String personId);

}
