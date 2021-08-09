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
import org.springframework.web.bind.annotation.RequestParam;

import com.trc.entities.AssigResource;
import com.trc.entities.County;
import com.trc.entities.MetroStation;
import com.trc.entities.Provider;
import com.trc.entities.Resource;
import com.trc.services.AssigResourceServices;
import com.trc.services.CountyServices;
import com.trc.services.ProviderServices;
import com.trc.services.ResourceServices;

@Controller
@RequestMapping("/comResources/cms/providers")
public class ProvidersController 
{
	@Autowired
	ProviderServices service;
	
	@Autowired
	CountyServices serviceCounties;
	
	@Autowired
	AssigResourceServices assigResourceService;
	
	@Autowired
	ResourceServices serviceListResources;
	
	//CRUD Operations
	
	@GetMapping("/list")
	public String getAllProviders(Model model)
	{
		List<Provider> list=service.getAllProvidersDesc();
		
		String daysLeftSubscription=null;
		
		//Retrieving days after last update for each provider
        for(Provider provider : list)
        {
        	 daysLeftSubscription=service.getDaysLeftSubscription(provider.getProviderid());
        	 provider.setBuffer1(daysLeftSubscription);
  	
        }
		
		model.addAttribute("providers",list);
				
		return "adminProvidersList";
	}
			
	@RequestMapping(path={"/edit","/edit/{id}"})
	public String editCountyById(Model model, @PathVariable("id") Optional<Long> id)
	{
		//Retrieving the list of counties
		List<County> list=serviceCounties.getAllCountiesDesc();	
		
		//Retrieving the list of metro stations
		List<MetroStation> listStations=service.getAllStationsDesc();	
				
	   if(id.isPresent()) 
	   {
		   Provider entity=service.getProviderById(id.get());
	       model.addAttribute("provider",entity);
	   } 
	   else 
	   {
	       model.addAttribute("provider",new Provider());
		            	            
	   }
	   
	   model.addAttribute("counties",list);
	   model.addAttribute("stations",listStations);
	   
	   return "adminProviderAddEdit";
	}
		    
	@RequestMapping(path="/delete/{id}")
	public String deleteProviderById(Model model, @PathVariable("id") Long id) 
	{
	    service.deleteProviderById(id);
	    return "redirect:/comResource/cms/providers/list";
	}
		 
	@RequestMapping(path="/createProvider", method=RequestMethod.POST)
	public String createOrUpdateProvider(Provider provider) 
	{
		service.createOrUpdateProvider(provider);
	    return "redirect:/comResources/cms/providers/list";
	}
	
	@RequestMapping(path="/mapInfo/{id}")
	public String mapInfo(Model model, @PathVariable("id") Long id) 
	{
		Provider entity=service.getProviderById(id);
		
	    model.addAttribute("provider",entity);
	    
	    return "decoderView"; 
	}
	
	@RequestMapping(path="/mapInfoUpdate", method=RequestMethod.POST)
	public String mapInfoUpdate(Provider provider) 
	{
		service.decoderUpdate(provider);
	    return "redirect:/comResources/cms/providers/list";
	}
	
	
	@RequestMapping(path="/search")
	public String search() 
	{
		return "searchFormProviders"; 
	}
	
	@RequestMapping(path="/findProviderName", method=RequestMethod.POST)
	public String findProviderName(Model model,String stringSearch) 
	{
		List<Provider> list=service.searchProvidersByName(stringSearch);
		
		model.addAttribute("providers",list);
		model.addAttribute("stringSearch",stringSearch);
		
	    return "adminProvidersList";
	}
	
	@RequestMapping(path="/findProviderID", method=RequestMethod.POST)
	public String findProviderID(Model model,String stringSearch) 
	{
		List<Provider> list=service.searchProvidersByID(stringSearch);
		
		model.addAttribute("providers",list);
		model.addAttribute("stringSearch",stringSearch);
		
	    return "adminProvidersList";
	}
	
	
	@RequestMapping(path="/assignInfo/{id}")
	public String assignInfo(Model model, @PathVariable("id") Long id) 
	{
		String providerNumberString=null;
		Long providerNumber=null;
		
		//Getting the list of available services
		List<Resource> listResources=serviceListResources.getAllResources();
		
		//Getting the provider information
		Provider entity=service.getProviderById(id);
		
		//Getting the provider ID
		providerNumber=entity.getProviderid();
		
		//Converting the ID to string
		providerNumberString=Long.toString(providerNumber);
		
		//Getting the list of assigned resources to this provider
		List<AssigResource> listAssignResources=assigResourceService.findResByProvider(providerNumberString);
			
		
	    model.addAttribute("provider",entity);
	    model.addAttribute("listAssignResources",listAssignResources);
	    model.addAttribute("listResources",listResources);
	    
	    return "assignResView"; 
	}
	
	
	@RequestMapping(path="/assignInfoUpdate", method=RequestMethod.POST)
    public String updateAssignResource(Model model,@RequestParam Long resourceid,@RequestParam String providerName,@RequestParam Long providerId)
    {
		String providerNumberString=null;
		Long providerNumber=null;
		
		boolean priznakRepeated=true;
		
		Provider provider=new Provider();
		Resource resource=new Resource();
				
		
		//Getting the provider information
		Provider entity=service.getProviderById(providerId);
		
		//Getting the provider ID
		providerNumber=entity.getProviderid();
				
		//Converting the ID to string
		providerNumberString=Long.toString(providerNumber);
		
		//System.out.println("The resource ID is "+ resourceid);
    	//System.out.println("The provider ID is "+ providerId);
    	//System.out.println("The name of the provider is "+ providerName);
    	
    	//Checking if this resource is already assigned to this user
    	priznakRepeated=assigResourceService.checkRepeatNess(providerId,resourceid);
    	
    	//System.out.println("The response for repeatedness is "+ priznakRepeated);
    	
    	
    	if(priznakRepeated)
    		System.out.println("Cannot add new assigned resource as it already exists in the db... ");
    	else
    	{
    		
    		//Saving new entity information
        	provider=service.getProviderById(providerId);
    		resource=serviceListResources.getResourceById(resourceid);
    	    
    		assigResourceService.addAssignResource(provider,resource);
    		
    	}
    	
    	//Getting the list of available services
    	List<Resource> listResources=serviceListResources.getAllResources();
    	
    	//Getting the list of assigned resources to this provider
    	List<AssigResource> listAssignResources=assigResourceService.findResByProvider(providerNumberString);
    	
    	model.addAttribute("provider",entity);
	    model.addAttribute("listAssignResources",listAssignResources);
	    model.addAttribute("listResources",listResources);
    	
    	
        return "assignResView";
    }
	
	@RequestMapping(path="/removeAssign/{providerId}/{assignResourceId}")
	public String removeAssign(Model model, @PathVariable("providerId") Long providerId,@PathVariable("assignResourceId") Long assignResourceId) 
	{
		String providerNumberString=null;
		Long providerNumber=null;
		
		//Deleting selected record
		assigResourceService.deleteResourceById(assignResourceId);
		
		//Getting the provider information
		Provider entity=service.getProviderById(providerId);
		
		//Getting the provider ID
		providerNumber=entity.getProviderid();
						
		//Converting the ID to string
		providerNumberString=Long.toString(providerNumber);
		
		//Getting the list of available services
    	List<Resource> listResources=serviceListResources.getAllResources();
    	
    	//Getting the list of assigned resources to this provider
    	List<AssigResource> listAssignResources=assigResourceService.findResByProvider(providerNumberString);
    	
    	model.addAttribute("provider",entity);
	    model.addAttribute("listAssignResources",listAssignResources);
	    model.addAttribute("listResources",listResources);
				
		//System.out.println("The assigned resource to be deleted is  "+ assignResourceId);
		//System.out.println("The provider ID is "+ providerId);
		
		 return "assignResView";
	}

	@RequestMapping(path="/editAssign/{providerId}/{assignResourceId}")
	public String editAssign(Model model, @PathVariable("providerId") Long providerId,@PathVariable("assignResourceId") Long assignResourceId) 
	{
		String providerName=null;
		
		//Getting the selected assigned resource information
		AssigResource entity=assigResourceService.getResourceById(assignResourceId);
		
		//Getting the current provider name
		providerName=service.findProviderById(providerId);
		
		model.addAttribute("providerName",providerName);
		model.addAttribute("providerId",providerId);
		model.addAttribute("assignResource",entity);
	
		return "assignResEdit";
	}
	
	@RequestMapping(path="/updateAssign", method=RequestMethod.POST)
    public String updateAssign(Model model,@RequestParam Long resourceid,@RequestParam String providerName,@RequestParam Long providerId,@RequestParam String notes)
    {
		String providerNumberString=null;
		Long providerNumber=null;
		
		//Getting the provider information
		Provider entity=service.getProviderById(providerId);
		
		//Getting the provider ID
		providerNumber=entity.getProviderid();
								
		//Converting the ID to string
		providerNumberString=Long.toString(providerNumber);
		
		//Updating notes in selected record
		assigResourceService.updateNotesAssignRes(resourceid,notes);
				
				
		//Getting the list of available services
		List<Resource> listResources=serviceListResources.getAllResources();
		    	
		//Getting the list of assigned resources to this provider
		List<AssigResource> listAssignResources=assigResourceService.findResByProvider(providerNumberString);
		
		
		model.addAttribute("provider",entity);
	    model.addAttribute("listAssignResources",listAssignResources);
	    model.addAttribute("listResources",listResources);
				
		
		return "assignResView";
		
    }
	
	
}
