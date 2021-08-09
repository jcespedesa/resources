package com.trc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.trc.entities.County;
import com.trc.services.CountyServices;


@Controller
@RequestMapping("/comResources/cms/counties")
public class CountiesController 
{
	@Autowired
	CountyServices service;
	
	
	//CRUD Operations
	
	@GetMapping("/list")
	public String getAllPosts(Model model)
	{
		List<County> list=service.getAllCounties();
				
		model.addAttribute("counties",list);
				
		return "countiesList";
	}
			
	@RequestMapping(path={"/edit","/edit/{id}"})
	public String editCountyById(Model model, @PathVariable("id") Optional<Long> id)
	{
				
	   if(id.isPresent()) 
	   {
	       County entity=service.getCountyById(id.get());
	       model.addAttribute("county",entity);
	   } 
	   else 
	   {
	       model.addAttribute("county",new County());
		                        
		            	            
	   }
	   return "countiesAddEdit";
	}
		    
	@RequestMapping(path="/delete/{id}")
	public String deleteCountyById(Model model, @PathVariable("id") Long id) 
	{
	    service.deleteCountyId(id);
	    return "redirect:/comResources/cms/counties/list";
	}
		 
	@RequestMapping(path="/createCounty", method=RequestMethod.POST)
	public String createOrUpdateCounty(County county) 
	{
	    service.createOrUpdateCounty(county);
	    return "redirect:/comResources/cms/counties/list";
	}
		    
}
