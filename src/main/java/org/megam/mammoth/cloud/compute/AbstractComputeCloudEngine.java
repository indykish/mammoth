package org.megam.mammoth.cloud.compute;

import java.util.Formatter;

import org.megam.mammoth.cloud.compute.info.ComputeCloudSource;

public abstract class AbstractComputeCloudEngine implements ComputeCloudEngine {

	protected ComputeCloudSource source;

	public void setComputeCloudSource(ComputeCloudSource tempSource) {
		this.source = tempSource;

	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n", source.toString());
		return strbd.toString();
	}

}
