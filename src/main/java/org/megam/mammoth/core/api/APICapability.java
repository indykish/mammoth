package org.megam.mammoth.core.api;

import java.util.List;

public interface APICapability {

	public String title();

	public String description();

	public String version(String versionid);

	public List<APICall> describe(String apicall);

}
