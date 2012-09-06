package org.megam.mammoth.cloud.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;

import org.megam.mammoth.cloud.compute.ComputeCloudEngine;
import org.megam.mammoth.cloud.compute.exception.ComputeEngineException;
import org.megam.mammoth.cloud.compute.info.ComputeCloudInput;
import org.megam.mammoth.cloud.compute.info.ComputeCloudOutput;
import org.megam.mammoth.core.api.APICall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CloudIdentityController<C> {

	final Logger logger = LoggerFactory
			.getLogger(CloudIdentityController.class);

	@Autowired
	private ComputeCloudEngine engine;

	private Validator validator;

	@Autowired
	public CloudIdentityController(Validator validator) {
		this.validator = validator;
	}

	public @RequestMapping(value = "/cloudidentity/version/{id}", method = RequestMethod.GET)
	@ResponseBody
	String version(@Valid @PathVariable String id) {
		String output = engine.version(id);
		return output;
	}

	public @RequestMapping(value = "/cloudidentity/describe", method = RequestMethod.GET)
	@ResponseBody
	String describe() {
		List<APICall> output = engine.describe(null);
		return engine.builder(new ComputeCloudOutput(output)).asJson(output);

	}

	public @RequestMapping(value = "/cloudidentity/describe/{apicall}", method = RequestMethod.GET)
	@ResponseBody
	String describe(@Valid @PathVariable String apicall) {
		List<APICall> output = engine.describe(apicall);
		return engine.builder(new ComputeCloudOutput(output)).asJson(output);

	}

	public @RequestMapping(value = "/cloudidentity/list/{instid}", method = RequestMethod.GET)
	@ResponseBody
	String list(@Valid @RequestParam(value="instid", required=false) String instid) {
		ComputeCloudOutput<C> output = null;
		
		try {
			
			output = engine.list(new ComputeCloudInput<String>(instid));
			
		} catch (ComputeEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.builder(output).asJson(output);
	}
	
	public @RequestMapping(value = "/cloudidentity/log/{instid}", method = RequestMethod.GET)
	@ResponseBody
	String log(@Valid @RequestParam(value="instid", required=false) String instid) {
		logger.info("IN LIST METHOD...................." + instid);
		ComputeCloudOutput<C> output = null;
		
		try {
			
			output = engine.log(new ComputeCloudInput<String>(instid));
			
		} catch (ComputeEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.builder(output).asJson(output);
	}

	@RequestMapping(value = "/cloudidentity/up", method = RequestMethod.GET)
	public @ResponseBody
	String up(@Valid @RequestBody String instid) {
		ComputeCloudOutput<C> output = null;
		try {
			output = engine.up(new ComputeCloudInput<String>(instid));
		} catch (ComputeEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.builder(output).asJson(output);
	}

	@RequestMapping(value = "/cloudidentity/start", method = RequestMethod.POST)
	public @ResponseBody
	String start(@Valid @RequestBody String instid) {
		ComputeCloudOutput<C> output = null;
		try {
			output = engine.start(new ComputeCloudInput<String>(instid));
		} catch (ComputeEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.builder(output).asJson(output);
	}

	@RequestMapping(value = "/cloudidentity/stick", method = RequestMethod.POST)
	public @ResponseBody
	String stick(@Valid @RequestBody String ami) {
		ComputeCloudOutput<C> output = null;
		try {
			output = engine.stick(new ComputeCloudInput<String>(ami));
		} catch (ComputeEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.builder(output).asJson(output);
	}

	@RequestMapping(value = "/cloudidentity/stop", method = RequestMethod.POST)
	public @ResponseBody
	String stop(@Valid @RequestBody String ami) {
		ComputeCloudOutput<C> output = null;
		try {
			output = engine.stick(new ComputeCloudInput<String>(ami));
		} catch (ComputeEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.builder(output).asJson(output);
	}

	@RequestMapping(value = "/cloudidentity/run", method = RequestMethod.POST)
	public @ResponseBody
	String run(@Valid @RequestBody String ami) {
		ComputeCloudOutput<C> output = null;
		try {
			output = engine.run(new ComputeCloudInput<String>(ami));
		} catch (ComputeEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.builder(output).asJson(output);
	}

}