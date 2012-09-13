package org.megam.mammoth.prov.salesforce.service;

import java.util.List;

import org.megam.mammoth.prov.info.SalesforceUser;


public abstract interface UserService {

	public abstract List<SalesforceUser> listPeople();

	public abstract void addPerson(SalesforceUser person);

	public abstract void removePerson(String personId);

}
