package org.megam.mammoth.cloud.compute.info;

import java.util.Formatter;

import com.amazonaws.services.ec2.model.InstanceType;

public class BasicCloudComputeSource extends ComputeCloudSource {

	private final String defaultImageId = "notset";
	private final String defaultInstanceType = "t1.micro";

	private String instanceType = "";
	private String imageId = "";

	/**
	 * @return the defaultImageId
	 */
	public String getDefaultImageId() {
		return defaultImageId;
	}

	/**
	 * @return the defaultInstanceType
	 */
	public String getDefaultInstanceType() {
		return defaultInstanceType;
	}

	public String getInstanceType() {
		if (instanceType == null) {
			instanceType = getDefaultInstanceType();
		}
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public String getImageId() {
		if (imageId == null) {
			imageId = getDefaultImageId();
		}
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("-10s%3s%s%n", "region", " - ", getRegion());
		formatter.format("-10s%3s%s%n", "access key", " - ", getAccesskey());
		formatter.format("-10s%3s%s%n", "secret key", " - ", getSecretkey());
		formatter.format("-10s%3s%s%n", "image  id", " - ", getImageId());
		formatter.format("-10s%3s%s%n", "instance type", " - ",
				getInstanceType());
		return strbd.toString();
	}
}
