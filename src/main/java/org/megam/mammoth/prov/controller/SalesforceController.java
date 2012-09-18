package org.megam.mammoth.prov.controller;

import java.util.Map;

import javax.validation.Validator;

import org.megam.mammoth.prov.info.SalesforceUser;
import org.megam.mammoth.prov.salesforce.service.UserService;
import org.megam.mammoth.prov.salesforce.service.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



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
	public String auth(Map<String, Object> map) {
		return "salesforce_auth";
	}
	
	@RequestMapping(value= "/salesforce/home", method = RequestMethod.GET)
	public String home() {
		return "salesforce";
	}
	
	@RequestMapping(value= "/salesforce/list", method = RequestMethod.GET)
	public 	ModelAndView  listPeople(Map<String, Object> map) {
		logger.debug("SalesforceController:List");
		ModelAndView listModelAndView = new ModelAndView("salesforcelist");
		//map.put("person", new SalesforceUser());
		//map.put("peopleList", personService.listPeople());

		listModelAndView.addObject("salesforceusers", personService.listPeople());
		return listModelAndView;
	}

	@RequestMapping(value = "/salesforce/create", method = RequestMethod.POST)
	public String addPerson(SalesforceUser dat) {
		logger.debug("SalesforceController:addPerson");
		//personService.addPerson(dat);
		return "redirect:/salesforce/list";
	}

	@RequestMapping("/salesforce/delete/{personId}")
	public String deletePerson(@PathVariable("personId") String personId) {
		personService.removePerson(personId);
		return "OK";
	}

	

}
