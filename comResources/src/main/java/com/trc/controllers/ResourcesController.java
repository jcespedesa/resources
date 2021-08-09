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

import com.trc.entities.Resource;
import com.trc.services.ResourceServices;


@Controller
@RequestMapping("/comResources/cms/resources")
public class ResourcesController 
{
	@Autowired
	ResourceServices service;
	
	
	//CRUD Operations
	
	@GetMapping("/list")
	public String getAllResources(Model model)
	{
		List<Resource> list=service.getAllResources();
				
		model.addAttribute("resources",list);
				
		return "adminResourcesList";
	}
			
	@RequestMapping(path={"/edit","/edit/{id}"})
	public String editResourceById(Model model, @PathVariable("id") Optional<Long> id)
	{
				
	   if(id.isPresent()) 
	   {
		   Resource entity=service.getResourceById(id.get());
	       model.addAttribute("resource",entity);
	   } 
	   else 
	   {
	       model.addAttribute("resource",new Resource());
		                        
		            	            
	   }
	   return "adminResourcesAddEdit";
	}
		    
	@RequestMapping(path="/delete/{id}")
	public String deleteResourceById(Model model, @PathVariable("id") Long id) 
	{
	    service.deleteResourceById(id);
	    return "redirect:/comResources/cms/resources/list";
	}
		 
	@RequestMapping(path="/createResource", method=RequestMethod.POST)
	public String createOrUpdateResource(Resource resource) 
	{
	    service.createOrUpdateResource(resource);
	    return "redirect:/comResources/cms/resources/list";
	}
	
	
}
