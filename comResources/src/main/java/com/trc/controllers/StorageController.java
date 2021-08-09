package com.trc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trc.services.StorageServices;

@RestController
@RequestMapping("/comResources/cms/file")
public class StorageController 
{

	@Autowired
	private StorageServices service;
	
	
	/*
	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value="file") MultipartFile file) 
	{
        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
    }
    */
	
	@PostMapping("/upload")
    public String uploadFile(Model model,@RequestParam(value="file") MultipartFile file) 
	{
        String message=null;
        String fileName=null;
        
		try
		{
			fileName=service.uploadFile(file);
			message="Upload was successful...";
			
		}catch (Exception e) 
		{
		      System.out.println("AWS File Upload Exception: Something went wrong during the upload file process...");
		      message="Upload failed with exception: "+ e;
	    }
		
		model.addAttribute("fileName",fileName);
		model.addAttribute("message",message);
		
				
		return "redirect:/comResources/cms/aws/list";
        
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
	
	
	@DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) 
	{
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
	
	
	
}
