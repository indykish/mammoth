package org.megam.mammoth.prov.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import org.megam.mammoth.prov.info.SalesforceUser;
import org.megam.mammoth.prov.salesforce.service.UserService;
import org.megam.mammoth.prov.salesforce.service.UserServiceImpl;

import java.util.Map;
import javax.validation.Validator;

@Controller
public class SalesforceController {

	final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserService personService;

	private Validator validator;


	@Autowired
	public SalesforceController(Validator validator) {
		this.validator = validator;		
	}

	@RequestMapping(value= "/salesforce")
	public String listPeople(Map<String, Object> map) {
		return "salesforce";
	}
	
	@RequestMapping(value= "/salesforce/list", method = RequestMethod.GET)
	public 	ModelAndView  listPeople() {
		ModelAndView listModelAndView = new ModelAndView("salesforce");
		listModelAndView.addObject("salesforceusers", personService.listPeople());
		return listModelAndView;
	}

	@RequestMapping(value = "/salesforce/create", method = RequestMethod.POST)
	public String addPerson(SalesforceUser dat) {
		logger.debug("SalesforceController:addPerson");
		personService.addPerson(dat);
		return "salesforce";
	}

	@RequestMapping("/salesforce/delete/{personId}")
	public String deletePerson(@PathVariable("personId") String personId) {
		personService.removePerson(personId);
		return "OK";
	}

	

}
