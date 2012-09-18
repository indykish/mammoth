package org.megam.mammoth.prov.info;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesforceUser implements Serializable {
	@JsonProperty(value = "UserName")
	String UserName;
	@JsonProperty(value = "FirstName")
	String FirstName;
	@JsonProperty(value = "Email")
	String Email;
	@JsonProperty(value = "Alias")
	String Alias;
	@JsonProperty(value = "ProfileId")
	String ProfileId;
	@JsonProperty(value = "LastName")
	String LastName;
	@JsonProperty(value = "TimeZoneKey")
	String TimeZoneSidKey;
	@JsonProperty(value = "LocaleSideKey")
	String LocaleSidKey;
	@JsonProperty(value = "EmailEncodingKey")
	String EmailEncodingKey;
	@JsonProperty(value = "LanguageLocaleKey")
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
