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
import org.megam.mammoth.prov.salesforce.service.PersonService;
import org.megam.mammoth.prov.salesforce.service.PersonServiceImpl;
import com.force.api.ApiError;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;

@Controller

public class SalesforceController {
	
	
     
	final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	

	@Autowired
	private PersonService personService;
	Validator validator;
    @Autowired 
	public SalesforceController(Validator validator){
		this.validator=validator;
	}
	@RequestMapping("/salesforce/list")	
	public String listPeople(Map<String, Object> map) {

		map.put("person", new SalesforceUser());
		map.put("peopleList", personService.listPeople());

		return "people";
	}

	@RequestMapping(value = "/salesforce/create",method=RequestMethod.POST)
	public String addPerson(SalesforceUser dat) {
		
         
		personService.addPerson(dat);		
		return "OK";
	}

	@RequestMapping("/salesforce/delete/{personId}")
	public String deletePerson(@PathVariable("personId") String personId) {

		personService.removePerson(personId);

		return "redirect:/people/";
	}
	@RequestMapping(value="/salesforce")
public@ResponseBody ModelAndView sfindex(){

return new ModelAndView("salesforce");
}


}
