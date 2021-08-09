package com.trc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trc.entities.Resource;
import com.trc.repositories.ResourceRepository;


@Service
public class ResourceServices 
{
	
	@Autowired
    ResourceRepository repository;
	
	public List<Resource> getAllResources()
    {
        List<Resource> result=(List<Resource>) repository.findAll();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<Resource>();
        }
    }
     
	public List<Resource> getAllResourcesDesc()
    {
        List<Resource> result=(List<Resource>) repository.findAllDesc();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<Resource>();
        }
    }
	
	public List<Resource> getAllResourcesDescActive()
    {
        List<Resource> result=(List<Resource>) repository.findAllDescActive();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<Resource>();
        }
    }
	
    public Resource getResourceById(Long id)
    {
        Optional<Resource> resource=repository.findById(id);
         
        if(resource.isPresent())
        {
            return resource.get();
        } 
        else 
        {
            System.out.println("No record exist for given id..");
            
            return null;
        }
		
		
    }
     
    public Resource createOrUpdateResource(Resource entity) 
    {
    		
    	
        if(entity.getResourceid()==null) 
        {
            entity=repository.save(entity);
            
                                              
            return entity;
        } 
        else
        {
            Optional<Resource> resource=repository.findById(entity.getResourceid());
             
            if(resource.isPresent()) 
            {
            	Resource newEntity=resource.get();
                
                newEntity.setResourceName(entity.getResourceName());
                newEntity.setIcon(entity.getIcon());
                newEntity.setActive(entity.getActive());
               
                newEntity=repository.save(newEntity);
                 
                return newEntity;
            } 
            else 
            {
                entity=repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteResourceById(Long id)
    {
        Optional<Resource> resource=repository.findById(id);
         
        if(resource.isPresent()) 
        {
            repository.deleteById(id);
        } 
        else 
        {
            System.out.println("No Resource record exist for given id");
        }
    }
    
    public void updatePreview(Resource entity, Long id) 
    {
    	    	
    	Resource newEntity=repository.findById(id).get();
        
        newEntity.setResourceName(entity.getResourceName());
        newEntity.setIcon(entity.getIcon());
        newEntity.setActive(entity.getActive());
                
        repository.save(newEntity);
         
        return;
    	
    	
    }
    
    
    public List<Resource> findResByProvider(String providerNumber)
    {
        List<Resource> result=(List<Resource>) repository.findResByProviderDesc(providerNumber);
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<Resource>();
        }
    }

    public String formatDate(String inDate) 
    {
    	
    	SimpleDateFormat inSDF=new SimpleDateFormat("mm/dd/yyyy");
    	SimpleDateFormat outSDF=new SimpleDateFormat("yyyy-mm-dd");
        String outDate="";
        
        if (inDate != null) 
        {
            try 
            {
                Date date=inSDF.parse(inDate);
                outDate=outSDF.format(date);
            } 
            catch(ParseException ex)
            { 
            }
        }
        return outDate;
    }
    
    public String findResourceById(Long id)
    {
        String resourceName=repository.getById(id);
         
        return resourceName;
		
    }
    
    public String findIconById(Long id)
    {
        String icon=repository.getIconById(id);
         
        return icon;
		
    }
    
}
