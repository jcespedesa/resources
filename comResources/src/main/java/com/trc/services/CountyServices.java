package com.trc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trc.entities.County;
import com.trc.repositories.CountyRepository;


@Service
public class CountyServices 
{
	@Autowired
    CountyRepository repository;
	
	public List<County> getAllCounties()
    {
        List<County> result=(List<County>) repository.findAll();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<County>();
        }
    }
     
	public List<County> getAllCountiesDesc()
    {
        List<County> result=(List<County>) repository.findAllDesc();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<County>();
        }
    }
	
    public County getCountyById(Long id)
    {
        Optional<County> county=repository.findById(id);
         
        if(county.isPresent())
        {
            return county.get();
        } 
        else 
        {
            System.out.println("No record exist for given id..");
            
            return null;
        }
		
		
    }
     
    public County createOrUpdateCounty(County entity) 
    {
        	
        if(entity.getCountyid()==null) 
        {
            entity=repository.save(entity);
                                                          
            return entity;
        } 
        else
        {
            Optional<County> county=repository.findById(entity.getCountyid());
             
            if(county.isPresent()) 
            {
            	County newEntity=county.get();
                
                newEntity.setCountyName(entity.getCountyName());
                newEntity.setLatLon(entity.getLatLon());
                newEntity.setLat(entity.getLat());
                newEntity.setLon(entity.getLon());
                newEntity.setState(entity.getState());
                newEntity.setZoomView(entity.getZoomView());
               
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
     
    public void deleteCountyId(Long id)
    {
        Optional<County> county=repository.findById(id);
         
        if(county.isPresent()) 
        {
            repository.deleteById(id);
        } 
        else 
        {
            System.out.println("No County record exist for given id");
        }
    }
    
    public String findCountyById(Long id)
    {
    	String county=null;
    	
    	county=repository.findCountyById(id);
    	
    	return county;
    }
    
   
}
