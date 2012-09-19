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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(value = "/salesforce")
	public String auth() {
		return "salesforce_auth";
	}
	@RequestMapping(value = "/sforce")
	public String create() {
		return "redirect:/salesforce/home";
	}
	@RequestMapping(value = "/sforcelist")
	public String list() {
		return "redirect:/salesforce/list";
	}

	@RequestMapping(value = "/salesforce/home", method = RequestMethod.GET)
	public String home(Map<String, Object> map) {
		map.put("user", new SalesforceUser());
		map.put("userList", personService.listPeople());
		return "salesforce";
	}

	@RequestMapping(value = "/salesforce/list", method = RequestMethod.GET)
	public ModelAndView listPeople(Map<String, Object> map) {
		logger.debug("SalesforceController:List");
		ModelAndView listModelAndView = new ModelAndView("salesforce_list");
		map.put("user", new SalesforceUser());
		map.put("userList", personService.listPeople());
		return listModelAndView;
	}

	@RequestMapping(value = "/salesforce/create", method = RequestMethod.POST)
	public String createPerson(@ModelAttribute("user") SalesforceUser user,
			BindingResult result) {
		logger.debug("SalesforceController:create -" + user);

		/**
		 * if (result.hasErrors()) { return "petForm"; }
		 **/
		personService.addPerson(user);
		/** TO-DO if successful, only then **/
		return "redirect:/salesforce/list";
	}

	@RequestMapping(value= "/salesforce/delete/{personid}", method = RequestMethod.POST)
	public String deletePerson(@PathVariable String personid) {
		personService.removePerson(personid);
		return "redirect:/salesforce/list";
	}

}
