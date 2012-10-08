package org.megam.mammoth.provider.crm.info;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;



public class salesforcecrm {
	
	private String Username;
	private String FirstName;
	private String Email;
	private String Alias;
	private String ProfileId;
	private String LastName;
	private String TimeZoneSidKey;
	private String LocaleSidKey;
	private String EmailEncodingKey;
	private String LanguageLocaleKey;
	private String access_token;
	private String instance_url;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
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
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getInstance_url() {
		return instance_url;
	}
	public void setInstance_url(String instance_url) {
		this.instance_url = instance_url;
	}
	
	
	public String toString(){
		
		return(getAccess_token()+""+getAlias()+""+getEmail()+""+getEmailEncodingKey()+""+getFirstName()+""+getInstance_url()+""+getLanguageLocaleKey()+""+getLastName()+""+getLocaleSidKey()+""+getProfileId()+""+getLocaleSidKey()+""+getUsername());
	}
	
	
}
