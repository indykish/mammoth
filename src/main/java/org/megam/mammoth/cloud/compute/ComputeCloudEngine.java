package org.megam.mammoth.cloud.compute;

import org.megam.mammoth.cloud.compute.exception.ComputeEngineException;
import org.megam.mammoth.cloud.compute.info.ComputeCloudInput;
import org.megam.mammoth.cloud.compute.info.ComputeCloudOutput;
import org.megam.mammoth.core.api.APICapability;

public interface ComputeCloudEngine extends APICapability, CloudSourceable {

	<D> ComputeCloudBuilder builder(ComputeCloudOutput<D> tempOutput);

	<C extends Object, D> ComputeCloudOutput<D> list(ComputeCloudInput<C> input)
			throws ComputeEngineException;

	<C extends Object, D> ComputeCloudOutput<D> up(ComputeCloudInput<C> input)
			throws ComputeEngineException;

	<C extends Object, D> ComputeCloudOutput<D> stick(ComputeCloudInput<C> input)
			throws ComputeEngineException;

	<C extends Object, D> ComputeCloudOutput<D> start(ComputeCloudInput<C> input)
			throws ComputeEngineException;

	<C extends Object, D> ComputeCloudOutput<D> stop(ComputeCloudInput<C> input)
			throws ComputeEngineException;

	<C extends Object, D> ComputeCloudOutput<D> run(ComputeCloudInput<C> input)
			throws ComputeEngineException;

}
