package org.megam.mammoth.demo.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleUser {

	final Logger logger = LoggerFactory.getLogger(GoogleUser.class);
	
	private String userName;
	private String givenName;
	private String familyName;
	private String password;
	private String adminEmail;
	private String adminPassword;
	private String domainName;
	private boolean isAdmin;
	
		
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
		
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean admin) {
		this.isAdmin = admin;
	}
	
		
	public String toString(){		
		return "("+getUserName()+","+getFamilyName()+","+getGivenName()+","+getPassword()+
				getAdminEmail()+","+getAdminPassword()+","+getDomainName()+")";
		
	}

}
