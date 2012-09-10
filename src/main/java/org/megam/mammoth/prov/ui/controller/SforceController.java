package org.megam.mammoth.prov.ui.controller;

import org.megam.mammoth.cloud.compute.info.ComputeCloudOutput;
import org.megam.mammoth.prov.info.USER;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SforceController {
	

	@RequestMapping(value="/sforcecreate", method=RequestMethod.POST)
	public @ResponseBody String sflist(USER usr,BindingResult result){
		
		
		   
		return "ok";		
	}
	
	@RequestMapping(value="sforce")
	public@ResponseBody ModelAndView sfindex(){
		
		return new ModelAndView("salesforce");
	}
	@RequestMapping(value="gapp")
	public@ResponseBody ModelAndView gindex(){
		
		return new ModelAndView("googleapp");
	}
	@RequestMapping(value="life")
	public@ResponseBody ModelAndView lindex(){
		
		return new ModelAndView("liferay");
	}
	@RequestMapping(value="scrm")
	public@ResponseBody ModelAndView scindex(){
		
		return new ModelAndView("sugarcrm");
	}

}
