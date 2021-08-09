package com.trc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trc.entities.AssigResource;
import com.trc.entities.Provider;
import com.trc.entities.Resource;
import com.trc.repositories.AssigResourceRepository;


@Service
public class AssigResourceServices 
{
	@Autowired
    AssigResourceRepository repository;
	
	public List<AssigResource> getAllResources()
    {
        List<AssigResource> result=(List<AssigResource>) repository.findAll();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<AssigResource>();
        }
    }
     
		
    public AssigResource getResourceById(Long id)
    {
        Optional<AssigResource> resource=repository.findById(id);
         
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
     
    public AssigResource createOrUpdateResource(AssigResource entity) 
    {
    		
    	
        if(entity.getRecordid()==null) 
        {
            entity=repository.save(entity);
            
                                              
            return entity;
        } 
        else
        {
            Optional<AssigResource> resource=repository.findById(entity.getRecordid());
             
            if(resource.isPresent()) 
            {
            	AssigResource newEntity=resource.get();
                
                newEntity.setProvider(entity.getProvider());
                newEntity.setProviderNumber(entity.getProviderNumber());
                newEntity.setCountyNumber(entity.getCountyNumber());
                newEntity.setResourceNumber(entity.getResourceNumber());
                newEntity.setNotes(entity.getNotes());
                newEntity.setPriznakUpdate(entity.getPriznakUpdate());
               
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
        Optional<AssigResource> resource=repository.findById(id);
         
        if(resource.isPresent()) 
        {
            repository.deleteById(id);
        } 
        else 
        {
            System.out.println("No Assigned Resource record exist for given id");
        }
    }
    
    public List<AssigResource> getProvidersByRes(String resourceNumber)
    {
        List<AssigResource> result=(List<AssigResource>) repository.findUniqProvidByRes(resourceNumber);
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<AssigResource>();
        }
    }
    
    public List<AssigResource> findResByProvider(String providerNumber)
    {
        List<AssigResource> result=(List<AssigResource>) repository.findResByProvider(providerNumber);
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<AssigResource>();
        }
    }
    
    public boolean checkRepeatNess(Long providerId, Long resourceId)
    {
    	int priznakRepeated=0;
    	
    	String providerIdString=Long.toString(providerId);
    	String resourceIdString=Long.toString(resourceId);
    	
    	    	
    	priznakRepeated=repository.findAssignResRepeated(providerIdString,resourceIdString);
    	
    	
    	if(priznakRepeated==0)
    		return false;
    	else
    		return true;
    	
   
    }
    
    public void addAssignResource(Provider provider, Resource resource)
    {
    	
    	String providerNumberString=null;
    	String resourceIdString=null;

    	//Converting long to strings
    	providerNumberString=Long.toString(provider.getProviderid());
    	resourceIdString=Long.toString(resource.getResourceid());
    	
    	//Adding the new record to table assigned resources
    	AssigResource newAssignResource=new AssigResource();
		
		newAssignResource.setProvider(provider.getProviderName());
		newAssignResource.setProviderNumber(providerNumberString);
		newAssignResource.setCountyNumber(provider.getCountyNumber());
		newAssignResource.setResource(resource.getResourceName());
		newAssignResource.setResourceNumber(resourceIdString);
   
		newAssignResource=repository.save(newAssignResource);
    
    }

    public void updateNotesAssignRes(Long recordId, String notes)
    {
    	
    	//Updating record in table assign resources
		repository.updateNotes(recordId,notes);
    
    }
    
}
