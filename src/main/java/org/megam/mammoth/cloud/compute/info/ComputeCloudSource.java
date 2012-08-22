package org.megam.mammoth.cloud.compute.info;

public abstract class ComputeCloudSource {
	
	private String region =null;
	private String accesskey =null;
	private String secretkey =null;
	private String computeEngineClassName = null;
	
	
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	

	/**
	 * @param accesskey the accesskey to set
	 */
	public void setRegion(String tempRegion) {
		this.region = tempRegion;
	}


	/**
	 * @return the accesskey
	 */
	public String getAccesskey() {
		return accesskey;
	}


	/**
	 * @param accesskey the accesskey to set
	 */
	public void setAccesskey(String tempAccesskey) {
		this.accesskey = tempAccesskey;
	}


	/**
	 * @return the secretkey
	 */
	public String getSecretkey() {
		return secretkey;
	}


	/**
	 * @param secretkey the secretkey to set
	 */
	public void setSecretkey(String tempSecretkey) {
		this.secretkey = tempSecretkey;
	}


	public String getComputeEngineClassName() {
		return computeEngineClassName;
	}


	public void setComputeEngineClassName(String computeEngineClassName) {
		this.computeEngineClassName = computeEngineClassName;
	}
	
	

}
