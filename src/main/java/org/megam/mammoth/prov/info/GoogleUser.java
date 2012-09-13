package org.megam.mammoth.prov.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleUser {
	
	String userName;
	String givenName;
	String familyName;
	String password;
	String adminEmail;
	String adminPassword;
	String Domain;
	
	public String getDomain() {
		return Domain;
	}
	public void setDomain(String domain) {
		Domain = domain;
	}
	final Logger logger = LoggerFactory.getLogger(GoogleUser.class);
	
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
	public void setValue(String nextToken) {
		
		String[] st=nextToken.split("=");
		logger.info("STRING!;;;;;;;;"+st[0]+"::::::::"+st[1]);
		
		if(st[0].equalsIgnoreCase("username")){
			setUserName(st[1].trim());
		}
		if(st[0].equalsIgnoreCase("adminemail")){
			setAdminEmail("admin@megam.co.in");
			//setAdminEmail(st[1].trim());
		}
		if(st[0].equalsIgnoreCase("givenname")){
			setGivenName(st[1].trim());
		}
		if(st[0].equalsIgnoreCase("domain")){
			setDomain(st[1].trim());
		}
		if(st[0].equalsIgnoreCase("adminpassword")){
			setAdminPassword("don#1ald");
			//setAdminPassword(st[1].trim());
		}
		if(st[0].equalsIgnoreCase("familyname")){
			setFamilyName(st[1].trim());
		}
		if(st[0].equalsIgnoreCase("password")){
			setPassword(st[1].trim());
		}
	}
	public String toString(){
		
		return "("+getUserName()+","+getAdminEmail()+","+getAdminPassword()+","+getDomain()+","+getFamilyName()+","+getGivenName()+","+getPassword()+")";
		
	}

}
