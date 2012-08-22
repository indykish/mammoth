package org.megam.mammoth.core.api;

import java.util.Formatter;

public class APICall {

	private String apistr;
	private String desc;

	public APICall(String tempApi, String tempDesc) {
		this.apistr = tempApi;
		this.desc = tempDesc;
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("-10s%3s%s%n", apistr, " - ",desc);
		return strbd.toString();
	}

}
