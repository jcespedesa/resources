package com.trc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trc.entities.AssigResource;
import com.trc.entities.County;
import com.trc.entities.Provider;
import com.trc.entities.Resource;
import com.trc.services.AssigResourceServices;
import com.trc.services.CountyServices;
import com.trc.services.ProviderServices;
import com.trc.services.ResourceServices;


@Controller
@RequestMapping("/comResources")
public class MainController 
{
	@Autowired
	CountyServices countyService;
	
	@Autowired
	ResourceServices resourceService;
	
	@Autowired
	ProviderServices providerService;
	
	@Autowired
	AssigResourceServices assigResourceService;
	
	@RequestMapping("")
	public String index(Model model,String priznak)
	{
				
		return "index";
	}
	
	@RequestMapping("/selectLoc")
	public String getLocation(Model model)
	{
		List<County> list=countyService.getAllCountiesDesc();
		List<Resource> listResources=resourceService.getAllResourcesDescActive();
		
		model.addAttribute("counties",list);
		model.addAttribute("resources",listResources);
				
		return "locationSelect";
	}
	
	 @RequestMapping(path="/location/{countyNumber}")
	 public String getResourcesByLocation(Model model, @PathVariable("countyNumber") String countyNumber) 
	 {
		 int counter=0;
		 
		 List<Resource> resultList=new ArrayList<>();
		 
		 List<Resource> list=resourceService.getAllResourcesDescActive();
		 
		 List<AssigResource> listProviders=assigResourceService.getAllResources();
		 
		 		 
		 for(Resource resource:list)
		 {	 
			 for(AssigResource provider:listProviders)
			 { 
				 				 
				 if((String.valueOf(resource.getResourceid()).equals(provider.getResourceNumber()))&&(provider.getCountyNumber().equals(countyNumber)))
				 {
					 counter++;
					 
					 //System.out.println("Resource found...");
				 }
				 
			 }
			 if(counter>0)
				 resultList.add(resource);
			 
			 counter=0;
			 
		 }
		 
		 
		 County county=null;
		 
		 Long countyNumberLong=null;
		 
		 //Converting string id to long id
		 
		 countyNumberLong=Long.parseLong(countyNumber);
		 
		 //Retrieving county name
		 county=countyService.getCountyById(countyNumberLong);
	        
		 //System.out.println("The variable captured was "+ countyNumber);
	     
		 model.addAttribute("countyNumber",countyNumber);
		 model.addAttribute("county",county);
		 model.addAttribute("resources",resultList);
		 		 
	     return "resourcesList";
	 }    
	   
    
	 
	 @RequestMapping(path="/providers/{countyNumber}/{resourceNumber}")
	 public String getProvidersByLoc(Model model, @PathVariable("countyNumber") String countyNumber,@PathVariable("resourceNumber") String resourceNumber) 
	 {
		 List<AssigResource> list=providerService.getProvidersDesc(resourceNumber,countyNumber);
		 
		 County county=null;
		 Resource resource=null;
		 
		 Long countyNumberLong=null;
		 Long resourceNumberLong=null;
		 
		 		 
		 //Converting string id to long id
		 countyNumberLong=Long.parseLong(countyNumber);
		 resourceNumberLong=Long.parseLong(resourceNumber);
		 
		 //Retrieving county name
		 county=countyService.getCountyById(countyNumberLong);
		 
		 //Retrieving resource name
		 resource=resourceService.getResourceById(resourceNumberLong);
	        
		 //System.out.println("The variable captured was "+ countyNumber);
	     
		 model.addAttribute("countyNumber",countyNumber);
		 model.addAttribute("county",county);
		 model.addAttribute("resource",resource);
		 model.addAttribute("providers",list);
		 model.addAttribute("resourceNumber",resourceNumber);
		 
		 
	     return "providersList";
	   
    } 
	 
	 @RequestMapping(path="/providers/{resourceNumber}")
	 public String getProvidersByRes(Model model, @PathVariable("resourceNumber") String resourceNumber) 
	 {
		 		 
		 List<AssigResource> listProvidersRaw=assigResourceService.getProvidersByRes(resourceNumber);
		 
		 Resource resource=null;
		 
		 Long resourceNumberLong=null;
		 
		 		 		 
		//Converting string id to long id
		 resourceNumberLong=Long.parseLong(resourceNumber);
		 		 
		 //Retrieving resource name
		 resource=resourceService.getResourceById(resourceNumberLong);
		 
		 //Retrieving resource name
		 resource=resourceService.getResourceById(resourceNumberLong);
	        
		 //System.out.println("The variable captured was "+ countyNumber);
		 
		 //Trying to get the county name
		 for(AssigResource resources : listProvidersRaw)
		 {
			 Long countyNumberLong=Long.parseLong(resources.getCountyNumber());
			 
			 String countyName=countyService.findCountyById(countyNumberLong);
			 
			 resources.setPriznakUpdate(countyName);
		 }
		 	     
		 model.addAttribute("resource",resource);
		 model.addAttribute("providers",listProvidersRaw);
		 
		 return "providersListRes";
	   
    } 
	 
	 @RequestMapping("/providersView/{providerNumber}/{countyNumber}/{resourceNumber}")
		public String providersView(Model model, @PathVariable("providerNumber") String providerNumber,@PathVariable("countyNumber") String countyNumber,@PathVariable("resourceNumber") String resourceNumber)
		{
			
			List<AssigResource> listResources=assigResourceService.findResByProvider(providerNumber);
			
			Provider entity;
			
			String lon=null;
			String lat=null;
									
			//String provider=null;
			
			Long providerNumberLong=null;
			
			//Converting string id to long id
			providerNumberLong=Long.parseLong(providerNumber);
									
			//Retrieving provider entity
			entity=providerService.getProviderById(providerNumberLong);
			
			//Adding resource names
			//Trying to get the resource names
			 for(AssigResource resources : listResources)
			 {
				 Long resourceNumberLong=Long.parseLong(resources.getResourceNumber());
				 
				 String resourceName=resourceService.findResourceById(resourceNumberLong);
				 String icon=resourceService.findIconById(resourceNumberLong);
				 
				 resources.setBuffer(resourceName);
				 resources.setPriznakUpdate(icon);
			 }
			 						 
			 lat=entity.getLat();
			 lon=entity.getLon();
			 			
			 model.addAttribute("provider",entity);
			 model.addAttribute("resources",listResources);
			
			 model.addAttribute("lat",lat);
			 model.addAttribute("lon",lon);
			 
			 model.addAttribute("countyNumber",countyNumber);
			 model.addAttribute("resourceNumber",resourceNumber);
					
			return "providersView";
		} 
	 
	 	
	
}
