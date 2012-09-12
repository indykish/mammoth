package org.megam.mammoth.prov.salesforce.service;

import java.util.List;

import org.megam.mammoth.prov.info.SalesforceUser;
import org.springframework.stereotype.Service;

@Service
public abstract interface PersonService {

	public abstract List<SalesforceUser> listPeople();

	public abstract void addPerson(SalesforceUser person);

	public abstract void removePerson(String personId);

}
