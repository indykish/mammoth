package org.megam.mammoth.prov.info;

import org.springframework.beans.factory.annotation.Autowired;

public class SalesforceUser {
	@Autowired
	String UserName;
	String FirstName;
	String Email;
	String Alias;
	String ProfileId;
	String LastName;
	String TimeZoneSidKey;
	String LocaleSidKey;
	String EmailEncodingKey;
	String LanguageLocaleKey;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public String getProfileId() {
		return ProfileId;
	}

	public void setProfileId(String profileId) {
		ProfileId = profileId;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getTimeZoneSidKey() {
		return TimeZoneSidKey;
	}

	public void setTimeZoneSidKey(String timeZoneSidKey) {
		TimeZoneSidKey = timeZoneSidKey;
	}

	public String getLocaleSidKey() {
		return LocaleSidKey;
	}

	public void setLocaleSidKey(String localeSidKey) {
		LocaleSidKey = localeSidKey;
	}

	public String getEmailEncodingKey() {
		return EmailEncodingKey;
	}

	public void setEmailEncodingKey(String emailEncodingKey) {
		EmailEncodingKey = emailEncodingKey;
	}

	public String getLanguageLocaleKey() {
		return LanguageLocaleKey;
	}

	public void setLanguageLocaleKey(String languageLocaleKey) {
		LanguageLocaleKey = languageLocaleKey;
	}

}
