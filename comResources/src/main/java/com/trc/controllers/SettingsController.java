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

import com.trc.entities.Setting;
import com.trc.services.SettingServices;

@Controller
@RequestMapping("/comResources/cms/settings")
public class SettingsController 
{
	@Autowired
	SettingServices service;
	
	//CRUD Operations for settings
	
		@GetMapping("/list")
		public String getAllSettings(Model model)
		{
			List<Setting> list=service.getAllSettings();
			
			model.addAttribute("settings",list);
			
			return "settingsList";
		}
		
		@RequestMapping(path={"/edit","/edit/{id}"})
	    public String editSettingsById(Model model, @PathVariable("id") Optional<Long> id) throws Exception
	    {
	        if (id.isPresent()) 
	        {
	            Setting entity=service.getSettingById(id.get());
	            model.addAttribute("setting",entity);
	        } 
	        else 
	        {
	            model.addAttribute("setting",new Setting());
	        }
	        return "settingsAddEdit";
	    }
	    
	    @RequestMapping(path="/delete/{id}")
	    public String deleteSettingById(Model model, @PathVariable("id") Long id) throws Exception
	    {
	        service.deleteSettingById(id);
	        return "redirect:/comResources/cms/settings/list";
	    }
	 
	    @RequestMapping(path="/createSetting", method=RequestMethod.POST)
	    public String createOrUpdateSetting(Setting setting) 
	    {
	        service.createOrUpdateSetting(setting);
	        return "redirect:/comResources/cms/settings/list";
	    }
	    

}
