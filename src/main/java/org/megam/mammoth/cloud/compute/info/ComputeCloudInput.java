package org.megam.mammoth.cloud.compute.info;

public class ComputeCloudInput<C extends Object> {
	
	private C input;
	
	public ComputeCloudInput(C tempInput) {
		this.input = tempInput;
	}

	public C get() {
		return input;
	}

}
