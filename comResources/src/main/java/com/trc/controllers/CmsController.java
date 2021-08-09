package com.trc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/comResources")
public class CmsController 
{

	@RequestMapping("/cms")
	public String adminLogin()
	{
		
		return "adminLogin";
	}
	
 	@RequestMapping("/cmsMenu")
	public String cmsMenu()
	{
		
		return "cmsMenu";
	}
	
	 		
 	
}
