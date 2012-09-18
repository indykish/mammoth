package org.megam.mammoth.prov.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;
import javax.validation.Validator;

import org.json.simple.JSONArray;
import org.megam.mammoth.prov.googleapps.service.GoogleAppsService;
import org.megam.mammoth.prov.googleapps.service.GoogleAppsServiceImpl;
import org.megam.mammoth.prov.info.GoogleUser;
import org.megam.mammoth.prov.info.SalesforceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.util.json.JSONObject;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.util.ServiceException;
import com.google.gson.Gson;

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
	public @ResponseBody ModelAndView index() {
		return new ModelAndView("googleapps");
	}

	@RequestMapping(value = "/googleapps/create", method = RequestMethod.POST)
	public String create(@RequestBody String dat) {
		GoogleUser people = new GoogleUser();

		StringTokenizer stk = new StringTokenizer(dat, "&");

		while (stk.hasMoreTokens()) {
			String strp = stk.nextToken();
			people.setValue(strp);
		}

		googleserv.addUser(people);
		return "redirect:/googleapps/list";
	}

	@RequestMapping(value = "/googleapps/list")
	public ModelAndView listPeople(Map<String, Object> map) {
		
		ModelAndView listModelAndView = new ModelAndView("googleappslist");
		map.put("person", new GoogleUser());
		GoogleUser temp = new GoogleUser();
		temp.setAdminEmail("admin@megam.co.in");
		temp.setAdminPassword("don#1ald");
		temp.setDomain("megam.co.in");
		map.put("peopleList", googleserv.listUser(temp));

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
