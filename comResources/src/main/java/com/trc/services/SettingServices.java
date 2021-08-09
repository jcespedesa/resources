package com.trc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trc.entities.Setting;
import com.trc.repositories.SettingRepository;

@Service
public class SettingServices 
{
	@Autowired
    SettingRepository repository;
	
	public List<Setting> getAllSettings()
    {
        List<Setting> result=(List<Setting>) repository.findAll();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<Setting>();
        }
    }
     
    public Setting getSettingById(Long id) throws Exception
    {
        Optional<Setting> setting=repository.findById(id);
         
        if(setting.isPresent()) {
            return setting.get();
        } 
        else 
        {
            throw new Exception("No record exist for given id");
        }
    }
     
    public Setting createOrUpdateSetting(Setting entity) 
    {
        if(entity.getSettingid()==null) 
        {
            entity=repository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<Setting> setting=repository.findById(entity.getSettingid());
             
            if(setting.isPresent()) 
            {
            	Setting newEntity=setting.get();
                
                newEntity.setSname(entity.getSname());
                newEntity.setPath(entity.getPath());
                                
                newEntity.setParam1(entity.getParam1());
                newEntity.setParam2(entity.getParam2());
                
                newEntity.setStrobe(entity.getStrobe());
 
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
     
    public void deleteSettingById(Long id) throws Exception 
    {
        Optional<Setting> setting=repository.findById(id);
         
        if(setting.isPresent()) 
        {
            repository.deleteById(id);
        } 
        else 
        {
            throw new Exception("No Setting record exist for given id");
        }
    }
}
