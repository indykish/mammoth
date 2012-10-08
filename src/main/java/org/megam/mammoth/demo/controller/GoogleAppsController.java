package org.megam.mammoth.demo.controller;

import java.util.Map;

import javax.validation.Validator;

import org.megam.mammoth.demo.info.GoogleUser;
import org.megam.mammoth.demo.info.SalesforceUser;
import org.megam.mammoth.demo.service.GoogleAppsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping("/googleapps")
	public String home(Map<String, Object> map) {
		map.put("user", new GoogleUser());
		return "googleapps";
	}


	@RequestMapping(value = "/googleapps/create", method = RequestMethod.POST)
	public String createPerson(@ModelAttribute("user") GoogleUser user,
			BindingResult result) {
		logger.debug("GoogleAppsController:create -" + user);

		/**
		 * if (result.hasErrors()) { return "petForm"; }
		 **/
		googleserv.addUser(user);
		/** TO-DO if successful, only then **/
		return "redirect:/googleapps/list";
	}

	@RequestMapping(value = "/googleapps/list", method = RequestMethod.GET)
	public ModelAndView listPeople(Map<String, Object> map) {

		ModelAndView listModelAndView = new ModelAndView("googleappslist");
		map.put("person", new GoogleUser());
		GoogleUser temp = new GoogleUser();
		temp.setAdminEmail("admin@megam.co.in");
		temp.setAdminPassword("don#1ald");
		temp.setDomainName("megam.co.in");
		map.put("peopleList", googleserv.listUser(temp));
		logger.debug("GoogleAppsController:List -");
		return listModelAndView;
	}

	@RequestMapping("/googleapps/delete/{userName}")
	public String deleteUser(@PathVariable("userName") String userName) {
		GoogleUser temp = new GoogleUser();
		temp.setUserName(userName);
		googleserv.removeUser(temp);

		return "redirect:/googleapps/list";
	}

}
