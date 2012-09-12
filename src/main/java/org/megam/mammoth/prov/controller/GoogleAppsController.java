package org.megam.mammoth.prov.controller;

import java.util.Map;

import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;
import javax.validation.Validator;

import org.megam.mammoth.prov.googleapps.service.GoogleAppsService;
import org.megam.mammoth.prov.info.GoogleUser;
import org.megam.mammoth.prov.info.SalesforceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.util.json.JSONObject;

@Controller
public class GoogleAppsController {

	@Autowired
	private GoogleAppsService googleserv;
	Validator validator;

	final Logger logger = LoggerFactory.getLogger(GoogleAppsController.class);

	@Autowired
	public GoogleAppsController(Validator validator) {
		this.validator = validator;
	}

	@RequestMapping(value = "/googleapps/create", method = RequestMethod.POST)
	public String create(String dat) {
         logger.info("DATA"+dat);
		googleserv.addUser(dat);
		return "OK";

	}

	@RequestMapping(value = "/googleapps/list")
	public String listPeople(Map<String, Object> map) {

		map.put("person", new SalesforceUser());
		map.put("peopleList", googleserv.listPeople());

		return "salesforce";
	}

	@RequestMapping("/googleapps/delete/{personId}")
	public String deleteUser(@PathVariable("personId") String personId) {

		googleserv.removePerson(personId);

		return "redirect:/people/";
	}

	@RequestMapping("/googleapps")
	public @ResponseBody
	ModelAndView sfindex() {

		return new ModelAndView("googleapps");
	}
}
