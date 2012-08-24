package org.megam.mammoth.cloud.compute.info;

public class ComputeCloudOutput<T extends Object> {

	private T out;

	public ComputeCloudOutput(T tempOut) {
		out = tempOut;
	}


	public T get() {
		return out;
	}

}
