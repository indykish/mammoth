package org.megam.mammoth.cloud.compute;

import org.megam.mammoth.cloud.compute.info.ComputeCloudSource;
import org.megam.mammoth.cloud.ui.controller.CloudIdentityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

@Service("computeCloud")
public class DefaultComputeCloud implements CloudSourceable {

	final Logger logger = LoggerFactory.getLogger(DefaultComputeCloud.class);

	private ComputeCloudSource computeCloudSource;
	private ComputeCloudEngine engine;

	// this is (unsurprisingly) the initialization callback method
	public void init() {
		if (this.computeCloudSource == null) {
			throw new IllegalStateException(
					"The [computeCloudSource] property must be set.");
		}
	}

	public void setComputeCloudSource(ComputeCloudSource tempComputeCloud) {
		this.computeCloudSource = tempComputeCloud;
	}

	public @Bean
	ComputeCloudEngine engine() {
		if (engine == null) {
			engine = instantiateClass(computeCloudSource
					.getComputeEngineClassName());
			((CloudSourceable) engine)
					.setComputeCloudSource(computeCloudSource);
		}
		if (this.engine == null)
			throw new IllegalStateException(
					"The [computeEngineClassName] must be a valid class name.");

		return engine;
	}

	@SuppressWarnings("unchecked")
	private <T> T instantiateClass(String className) {
		Class<?> clazz;
		try {
			clazz = ClassUtils.forName(className,
					DefaultComputeCloud.class.getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new BeanInitializationException("Could not instantiate "
					+ className, e);
		} catch (LinkageError e) {
			throw new BeanInitializationException("Could not instantiate "
					+ className, e);
		}
		return (T) BeanUtils.instantiate(clazz);
	}

}
