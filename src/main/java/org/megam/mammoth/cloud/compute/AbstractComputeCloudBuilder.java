package org.megam.mammoth.cloud.compute;

import org.megam.mammoth.cloud.compute.info.ComputeCloudOutput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public abstract class AbstractComputeCloudBuilder<T> implements
		ComputeCloudBuilder {

	protected ComputeCloudOutput<T> output;

	public AbstractComputeCloudBuilder(ComputeCloudOutput<T> tempOutput) {
		this.output = (ComputeCloudOutput<T>) tempOutput;
	}

	@Override
	public <T extends Object> String asJson(T somestuffn) {
		return asJson(somestuffn, String.class);
	}

	public <T extends Object> String asJson(T somestuff, Class<?> clz) {

		java.lang.reflect.Type type = null;
		if (clz != null && clz.isInstance(String.class)) {
			type = new TypeToken<String>() {
			}.getType();
		} else {
			new TypeToken<T>() {
			}.getType();
		}
		return asJson(somestuff, type);
	}

	private <T extends Object> String asJson(T somestuff,
			java.lang.reflect.Type typeToken) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonout = "";
		if (typeToken != null) {
			jsonout = gson.toJson(somestuff, typeToken);
		} else {
			jsonout = gson.toJson(somestuff);
		}
		return jsonout;
	}

}
