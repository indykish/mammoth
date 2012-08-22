package org.megam.mammoth.cloud.compute.ec2;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.mammoth.cloud.compute.AbstractComputeCloudEngine;
import org.megam.mammoth.cloud.compute.ComputeCloudBuilder;
import org.megam.mammoth.cloud.compute.exception.ComputeEngineException;
import org.megam.mammoth.cloud.compute.info.CloudInstance;
import org.megam.mammoth.cloud.compute.info.ComputeCloudInput;
import org.megam.mammoth.cloud.compute.info.ComputeCloudOutput;
import org.megam.mammoth.cloud.compute.info.ComputeCloudSource;
import org.megam.mammoth.core.api.APICall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeRegionsRequest;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.GetConsoleOutputRequest;
import com.amazonaws.services.ec2.model.GetConsoleOutputResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;


public class AmazonEC2Engine<C extends Object> extends
		AbstractComputeCloudEngine {

	private AmazonEC2Client client;

	private static Map<String, APICall> apiHelp = new HashMap<String, APICall>();

	static {
		apiHelp.put("up", new APICall("up", "Checks if the compute cloud engine is connectable."));
		apiHelp.put("list", new APICall("list",
				"Lists all the raw identity."));
		apiHelp.put(
				"stick",
				new APICall(
						"stick",
						"Builds a baked identity in EC2 using OpenAM. Or in otherwords the cloud identity is setup for an account."));
	}

	public AmazonEC2Engine() {
	}

	public void setComputeCloudSource(ComputeCloudSource tempSource) {
		super.setComputeCloudSource(tempSource);
		AWSCredentials credentials = new BasicAWSCredentials(
				source.getAccesskey(), source.getSecretkey());
		client = new AmazonEC2Client(credentials);
		client.setEndpoint(source.getRegion());
	}

	public String title() {
		return "Mammoth - RESTful Identity";
	}

	public String description() {
		return "A RESTful Identity provisioner used to standup a cloud identity using OpenAM in Amazon EC2.";
	}

	public String version(String versionid) {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		String out = "";
		if (versionid != null && versionid.trim().length() > 0) {
			out = "mammoth v1.0" + "\nMegam Systems";
		} else {
			out = "mammoth v0.9"+ "\nMegam Systems";
		}
		return out;
	}

	public List<APICall> describe(String apicall) {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		List<APICall> list = new ArrayList<APICall>();

		if (apicall != null && apicall.trim().length() > 0) {
			if (apiHelp.containsKey(apicall)) {
				list.add(apiHelp.get(apicall));
			} else {
				list.add(new APICall(apicall,
						"Argh.Can't locate it. Did you spell it correctly ? "));
			}
		} else {
			list.addAll(apiHelp.values());
		}
		return list;
	}

	public <C, D> ComputeCloudOutput<D> up(ComputeCloudInput<C> tempInput)
			throws ComputeEngineException {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		ComputeCloudOutput<DescribeRegionsResult> output;
		try {
			DescribeRegionsResult result = client
					.describeRegions(new RequestCreator<C>().alive(tempInput));
			output = new ComputeCloudOutput<DescribeRegionsResult>(result);
		} catch (AmazonServiceException ase) {
			throw new ComputeEngineException(ase);

		} catch (AmazonClientException ase) {
			throw new ComputeEngineException(ase);
		}
		return (ComputeCloudOutput<D>) output;
	}

	public <C, D> ComputeCloudOutput<D> list(ComputeCloudInput<C> tempInput)
			throws ComputeEngineException {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		ComputeCloudOutput<DescribeInstancesResult> output = null;

		try {
			DescribeInstancesResult result = client
					.describeInstances(new RequestCreator<C>().list(tempInput));
			output = new ComputeCloudOutput<DescribeInstancesResult>(result);
		} catch (AmazonServiceException ase) {
			throw new ComputeEngineException(ase);

		} catch (AmazonClientException ase) {
			throw new ComputeEngineException(ase);
		}
		return (ComputeCloudOutput<D>) output;
	}

	public <C, D> ComputeCloudOutput<D> start(ComputeCloudInput<C> tempInput)
			throws ComputeEngineException {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		ComputeCloudOutput<StartInstancesResult> output;
		try {
			StartInstancesResult result = client
					.startInstances(new RequestCreator<C>().start(tempInput));
			output = new ComputeCloudOutput<StartInstancesResult>(result);
		} catch (AmazonServiceException ase) {
			throw new ComputeEngineException(ase);

		} catch (AmazonClientException ase) {
			throw new ComputeEngineException(ase);
		}
		return (ComputeCloudOutput<D>) output;
	}

	public <C, D> ComputeCloudOutput<D> stop(ComputeCloudInput<C> tempInput)
			throws ComputeEngineException {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		ComputeCloudOutput<StopInstancesResult> output;
		try {
			StopInstancesResult result = client
					.stopInstances(new RequestCreator<C>().stop(tempInput));
			output = new ComputeCloudOutput<StopInstancesResult>(result);
		} catch (AmazonServiceException ase) {
			throw new ComputeEngineException(ase);

		} catch (AmazonClientException ase) {
			throw new ComputeEngineException(ase);
		}
		return (ComputeCloudOutput<D>) output;
	}

	public <C, D> ComputeCloudOutput<D> stick(ComputeCloudInput<C> tempInput)
			throws ComputeEngineException {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		ComputeCloudOutput<StartInstancesResult> output;
		try {
			StartInstancesResult result = client
					.startInstances(new RequestCreator<C>().start(tempInput));
			output = new ComputeCloudOutput<StartInstancesResult>(result);
		} catch (AmazonServiceException ase) {
			throw new ComputeEngineException(ase);

		} catch (AmazonClientException ase) {
			throw new ComputeEngineException(ase);
		}
		return (ComputeCloudOutput<D>) output;

	}

	public <C, D> ComputeCloudOutput<D> run(ComputeCloudInput<C> tempInput)
			throws ComputeEngineException {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		ComputeCloudOutput<RunInstancesResult> output;
		try {
			RunInstancesResult result = client
					.runInstances(new RequestCreator<C>().run(tempInput));
			output = new ComputeCloudOutput<RunInstancesResult>(result);

		} catch (AmazonServiceException ase) {
			throw new ComputeEngineException(ase);

		} catch (AmazonClientException ase) {
			throw new ComputeEngineException(ase);
		}
		return (ComputeCloudOutput<D>) output;
	}

	public <C, D> ComputeCloudOutput<D> log(ComputeCloudInput<C> tempInput)
			throws ComputeEngineException {
		Assert.notNull(client,
				"'ComputeCloudClient['" + source.getComputeEngineClassName()
						+ "'] must not be null");

		ComputeCloudOutput<GetConsoleOutputResult> output;

		try {
			GetConsoleOutputResult result = client
					.getConsoleOutput(new RequestCreator<C>().log(tempInput));
			output = new ComputeCloudOutput<GetConsoleOutputResult>(result);
		} catch (AmazonServiceException ase) {
			throw new ComputeEngineException(ase);

		} catch (AmazonClientException ase) {
			throw new ComputeEngineException(ase);
		}
		return (ComputeCloudOutput<D>) output;

	}

	public <D> ComputeCloudBuilder builder(ComputeCloudOutput<D> tempOutput) {
		return new AmazonEC2Builder(tempOutput);

	}

	private class RequestCreator<T extends Object> {

		DescribeRegionsRequest alive(ComputeCloudInput<T> input) {
			return new DescribeRegionsRequest();
		}

		DescribeInstancesRequest list(ComputeCloudInput<T> tempInput) {
			/*
			 * T input = tempInput.get(); CloudInstance tempInst =
			 * (CloudInstance)input; DescribeInstancesRequest request = ....;
			 * request.set (tempInst.getId()); return request;
			 */return null;
		}

		StickIntoInstanceRequest stick(ComputeCloudInput<T> input) {
			return null;
		}

		RunInstancesRequest run(ComputeCloudInput<T> input) {
			/*
			 * CloudInstance... RunInstancesResult runInstanceReq = null;
			 * 
			 * runInstanceReq = new RunInstancesRequest();
			 * runInstanceReq.withImageId(param1).withInstanceType("t1.micro")
			 * .withMinCount(1).withMaxCount(1);
			 */
			return null;
		}

		StartInstancesRequest start(ComputeCloudInput<T> input) {
			CloudInstance instance = (CloudInstance) input.get();
			StartInstancesRequest inststart = new StartInstancesRequest();
			inststart.withInstanceIds(new String[] { instance.getId() });
			return inststart;
		}

		StopInstancesRequest stop(ComputeCloudInput<T> input) {
			CloudInstance instance = (CloudInstance) input.get();
			StopInstancesRequest inststart = new StopInstancesRequest();
			inststart.withInstanceIds(new String[] { instance.getId() });
			return inststart;
		}

		GetConsoleOutputRequest log(ComputeCloudInput<T> input) {
			return null;
		}

	}

}
