package com.trc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trc.services.StorageServices;

@Controller
@RequestMapping("/comResources/cms/aws")
public class AwsController 
{

	@Autowired
	private StorageServices service;
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@RequestMapping("/upload")
	public String adminLogin()
	{
		
		return "fileUpload";
	}
	
	/*
	@RequestMapping("/list")
    public ResponseEntity<List<String>> getListOfFiles() 
	{
        return new ResponseEntity<>(service.listFiles(), HttpStatus.OK);
    }
    */
	
	
	@RequestMapping("/list")
    public String getListOfFiles(Model model) 
	{
		List<String> keys=new ArrayList<>();
		
		keys=service.listFiles();
		
		model.addAttribute("list",keys);
		
        return "filesList";
    }
	
	@RequestMapping("/delete/{fileName}")
    public String deleteFile(Model model, @PathVariable String fileName) 
	{
        
		String message=null;
                
		try
		{
			service.deleteFile(fileName);
			message="File was successful deleted...";
			
		}catch (Exception e) 
		{
		      System.out.println("AWS File Handle Exception: Something went wrong during the deletion file process...");
		      message="File handling failed with exception: "+ e;
	    }
		
		model.addAttribute("fileName",fileName);
		model.addAttribute("message",message);
		
		return "fileRedirect";
    }
	
	@GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) 
	{
        byte[] data=service.downloadFile(fileName);
        ByteArrayResource resource=new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    } 
	
	@RequestMapping("/createFolder")
	public String createFolder(Model model)
	{
		
		String bucketName=null;
		
		bucketName=this.bucketName;
		
		model.addAttribute("bucketName",bucketName);
		
		return "fileFolderCrea";
	}
	
	@PostMapping("/folderCreation")
    public String folderCreation(Model model,@RequestParam String folderName) 
	{
	
		//Trying to create the folder
		service.createFolder(bucketName, folderName);
		
		
		return "fileRedirect";
	}
	
}
