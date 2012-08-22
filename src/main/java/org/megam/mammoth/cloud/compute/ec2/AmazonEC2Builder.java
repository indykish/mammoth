package org.megam.mammoth.cloud.compute.ec2;

import java.util.ArrayList;
import java.util.List;

import org.megam.mammoth.cloud.compute.AbstractComputeCloudBuilder;
import org.megam.mammoth.cloud.compute.info.CloudInstance;
import org.megam.mammoth.cloud.compute.info.CloudRegion;
import org.megam.mammoth.cloud.compute.info.ComputeCloudOutput;

public class AmazonEC2Builder<T> extends AbstractComputeCloudBuilder {



	public AmazonEC2Builder(ComputeCloudOutput<T> tempOutput) {
		super(tempOutput);
	}

	protected <T extends Object> List<CloudRegion> alive() {
		List<CloudRegion> region = new ArrayList<CloudRegion>();
		if (output.get() != null) {

		}
		return region;
	}

	protected List<CloudInstance> list() {
		List<CloudInstance> instanceList = new ArrayList<CloudInstance>();
		if (output.get() != null) {

		}
		return instanceList;
	}

	protected CloudInstance stick() {
		CloudInstance instance = null;
		if (output.get() != null) {

		}
		return instance;
	}

	protected List<CloudInstance> run() {
		List<CloudInstance> instanceList = new ArrayList<CloudInstance>();
		if (output.get() != null) {

		}
		return instanceList;
	}

	protected CloudInstance start() {
		CloudInstance instance = null;
		if (output.get() != null) {

		}
		return instance;
	}

	protected CloudInstance stop() {
		CloudInstance instance = null;
		if (output.get() != null) {

		}
		return instance;
	}

	protected String log() {
		String logOutput = "";
		if (output.get() != null) {

		}
		return logOutput;
	}

	
}
