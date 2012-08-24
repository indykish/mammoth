package org.megam.mammoth.cloud.compute.info;

import org.hibernate.cfg.ExtendsQueueEntry;
import org.megam.mammoth.cloud.ui.controller.CloudIdentityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputeCloudInput<C extends Object> {
	final Logger logger = LoggerFactory.getLogger(ComputeCloudInput.class);

	private C input;

	public ComputeCloudInput(C tempInput) {
		this.input = tempInput;
	}

	public C get() {
		return input;
	}

}
