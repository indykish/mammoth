package org.megam.mammoth.demo.info;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesforceUser {

	@JsonProperty(value = "Id")
	private String id;

	@JsonProperty(value = "Username")
	private String userName;

	@JsonProperty(value = "FirstName")
	private String firstName;

	@JsonProperty(value = "LastName")
	private String lastName;
	
	@JsonProperty(value = "Email")
	private String email;
	
	@JsonProperty(value = "Alias")
	private String alias;
	
	@JsonProperty(value = "ProfileId")
	private String profileId;
	
	@JsonProperty(value = "TimeZoneSidKey")
	private String timeZoneSidKey;
	
	@JsonProperty(value = "LocaleSidKey")
	private String localeSidKey;
	
	@JsonProperty(value = "EmailEncodingKey")
	private String emailEncodingKey;
	
	@JsonProperty(value = "LanguageLocaleKey")
	private String languageLocaleKey;

	public String getId() {
		return id;
	}

	public void setId(String tempId) {
		id = tempId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getTimeZoneSidKey() {
		return timeZoneSidKey;
	}

	public void setTimeZoneSidKey(String timeZone) {
		this.timeZoneSidKey = timeZone;
	}

	public String getLocaleSidKey() {
		return localeSidKey;
	}

	public void setLocaleSidKey(String localeSidKey) {
		this.localeSidKey = localeSidKey;
	}

	public String getEmailEncodingKey() {
		return emailEncodingKey;
	}

	public void setEmailEncodingKey(String emailEncodingKey) {
		this.emailEncodingKey = emailEncodingKey;
	}

	public String getLanguageLocaleKey() {
		return languageLocaleKey;
	}

	public void setLanguageLocaleKey(String languageLocaleKey) {
		this.languageLocaleKey = languageLocaleKey;
	}

	public String toString() {
		return "[" + getUserName() + "," + getFirstName() + "," + getLastName()
				+ "," + getEmail() + "]";
	}

}
