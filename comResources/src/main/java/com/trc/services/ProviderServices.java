package com.trc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trc.entities.AssigResource;
import com.trc.entities.MetroStation;
import com.trc.entities.Provider;
import com.trc.repositories.AssigResourceRepository;
import com.trc.repositories.CountyRepository;
import com.trc.repositories.MetroStationRepository;
import com.trc.repositories.ProviderRepository;


@Service
public class ProviderServices 
{
	@Autowired
    ProviderRepository repository;
	
	@Autowired
    AssigResourceRepository repositoryAssign;
	
	@Autowired
    MetroStationRepository repositoryStations;
	
	@Autowired
    CountyRepository repositoryCounties;
	
	
	public List<Provider> getAllProviders()
    {
        List<Provider> result=(List<Provider>) repository.findAll();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<Provider>();
        }
    }
     
	public List<Provider> getAllProvidersDesc()
    {
        List<Provider> result=(List<Provider>) repository.findAllDesc();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<Provider>();
        }
    }
	
    public Provider getProviderById(Long id)
    {
        Optional<Provider> provider=repository.findById(id);
         
        if(provider.isPresent())
        {
            return provider.get();
        } 
        else 
        {
            System.out.println("No record exist for given id..");
            
            return null;
        }
		
		
    }
     
    public Provider createOrUpdateProvider(Provider entity) 
    {
    	
    	String county=null;
    	String countyNumber=null;
    	Long countyNumberLong=null;
    	String dateString=null;
    	String stationNumber=null;
    	String station=null;
    	String line=null;
    	
    	//Trying to get near station derivatives
    	stationNumber=entity.getNearestMetroNumber();
    	station=repositoryStations.getStationByNumber(stationNumber);
    	line=repositoryStations.getLine(stationNumber);
    	
    	
    	LocalDate dateObj=LocalDate.now();
    	DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM/dd/yyyy");
    	
    	dateString=dateObj.format(formatter);
    	
    	//System.out.println(dateObj.format(formatter));
    	
    	//Trying to get the county description
    	countyNumber=entity.getCountyNumber();
    	countyNumberLong=Long.parseLong(countyNumber);
    	county=repositoryCounties.findCountyById(countyNumberLong);
    	
    	//System.out.println("County was found as "+ county);
    	//System.out.println("Today date was found to be "+ dateString);
    	
    	    	
        if(entity.getProviderid()==null) 
        {
            entity=repository.save(entity);
            
                                              
            return entity;
        } 
        else
        {
            Optional<Provider> provider=repository.findById(entity.getProviderid());
             
            if(provider.isPresent()) 
            {
            	Provider newEntity=provider.get();
                
                newEntity.setProviderName(entity.getProviderName());
                newEntity.setAddress(entity.getAddress());
                newEntity.setCounty(county);
                newEntity.setCountyNumber(entity.getCountyNumber());
                
                newEntity.setActive(entity.getActive());
               
                newEntity.setPhone(entity.getPhone());
                newEntity.setEmail(entity.getEmail());
                newEntity.setNotes(entity.getNotes());
                
                newEntity.setContact(entity.getContact());
                newEntity.setOtherPhone(entity.getOtherPhone());
                newEntity.setOtherEmail(entity.getOtherEmail());
                newEntity.setWebsite(entity.getWebsite());
                
                newEntity.setLon(entity.getLon());
                newEntity.setLat(entity.getLat());
                newEntity.setLatLon(entity.getLatLon());
                
                newEntity.setTypeProvider(entity.getTypeProvider());
                newEntity.setFax(entity.getFax());
                
                newEntity.setNearestMetro(station);
                newEntity.setNearestMetroNumber(entity.getNearestMetroNumber());
                newEntity.setNotesMetro(entity.getNotesMetro());
                newEntity.setLine(line);
                
                newEntity.setDateLastUpdate(dateString);
                
                newEntity=repository.save(newEntity);
                 
                return newEntity;
            } 
            else 
            {
            	entity.setCounty(county);
            	entity.setDateLastUpdate(dateString);
            	entity.setLine(line);
            	entity.setNearestMetro(station);
            	            	
                entity=repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteProviderById(Long id)
    {
        Optional<Provider> provider=repository.findById(id);
         
        if(provider.isPresent()) 
        {
            repository.deleteById(id);
        } 
        else 
        {
            System.out.println("No Provider record exist for given id");
        }
    }
    
    public List<AssigResource> getProvidersDesc(String resourceNumber, String countyNumber)
    {
    	
    	List<AssigResource> listProviders=(List<AssigResource>) repositoryAssign.findProvidersByCounty(countyNumber,resourceNumber);
    	
    	   	
		return listProviders;
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
    
    public List<Provider> getProviders(String resourceNumber)
    {
        List<Provider> result=(List<Provider>) repository.findProvidersByRes(resourceNumber);
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<Provider>();
        }
    }
    
    public String findProviderById(Long id)
    {
    	String county=null;
    	
    	county=repository.findProviderById(id);
    	
    	return county;
    }
    
    public List<MetroStation> getAllStationsDesc()
    {
        List<MetroStation> result=(List<MetroStation>) repositoryStations.findAllDesc();
         
        if(result.size() > 0) 
        {
            return result;
        } 
        else 
        {
            return new ArrayList<MetroStation>();
        }
    }
    
    public Provider decoderUpdate(Provider entity) 
    {
    	String lon="";
    	String lat="";
    	String latLon="";
    	String address="";
    	
    	Long providerId=null;

    	int indexRasdelitel=0;
    	int indexEndOfString=0;
    	
    	latLon=entity.getLatLon();
    	providerId=entity.getProviderid();
    	address=entity.getAddress();
    	
    	//Converting the string latLon in two separate values of lat and lon
    	indexRasdelitel=latLon.indexOf(",");
    	indexEndOfString=latLon.indexOf(")");
    	
    	lat=latLon.substring(1,indexRasdelitel);
    	
    	lon=latLon.substring(indexRasdelitel+1,indexEndOfString);
    	
    	repository.updateDecoder(providerId,address,latLon,lat,lon);
    	
    	//System.out.println("Lat value is : "+ lat);
    	//System.out.println("Lon value is : "+ lon);
    	
    	return entity;
    }
    
    public List<Provider> searchProvidersByName(String stringSearch)
    {
    	List<Provider> result=(List<Provider>) repository.getProvidersByName(stringSearch);
    	
    	return result;
    }
    
    public List<Provider> searchProvidersByID(String stringSearch)
    {
    	Long stringSearchLong=null;
    	
    	//Converting string to long
    	stringSearchLong=Long.parseLong(stringSearch);
    	
    	
    	List<Provider> result=(List<Provider>)repository.getProvidersByID(stringSearchLong);
    	
    	return result;
    }
    
    public String getDaysLeftSubscription(Long providerId)
    {
    	List<String> lastSubscriptionList=new ArrayList<String>();
    	
    	String daysLeftSubscription=null;
    	    	
    	String lastSubscription=null;
    	String providerIdString=null;
    	
    	Long daysLeftSubscriptionLong=null;
    	    	
    	Date lastSubscriptionDate=null;
    	Date todayDate=null;
    	
    	//Trying to get today's date
    	todayDate=new Date();
    	
    	
    	//Converting long to string
    	providerIdString=String.valueOf(providerId);
    	
    	//Retrieving last subscription payment date
    	lastSubscriptionList=repository.findLastSubscriptionDate(providerIdString);
    	    	
    	    	
    	if(lastSubscriptionList.size()==0)
    		daysLeftSubscription="N/A";
    	
    	else
    	{	
    	
    		//Assigning the last date from the array of subscription dates
        	lastSubscription=lastSubscriptionList.get(lastSubscriptionList.size()-1);
        	
    		
    		//Converting last date string to date
    		try 
    		{
    			lastSubscriptionDate=new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(lastSubscription);
			//todayDate=new SimpleDateFormat("dd-MM-yyyy").parse(todayDate);
			
    		} catch (ParseException e) 
    		{
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	
    		//System.out.println("Today date is: "+ todayDate);
    		//System.out.println("Date last subscription string is: "+ lastSubscription);
    		//System.out.println("Date last subscription is: "+ lastSubscriptionDate);
    	
    		long diffInMillies=Math.abs(todayDate.getTime() - lastSubscriptionDate.getTime());
    		long diffInDays=TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
                       
    		//System.out.println("Difference in milliseconds is: "+ diffInMillies); 
    		//System.out.println("Difference in days is: "+ diff);  
    		
    		//daysLeftSubscriptionLong=365L - diffInDays;
    		daysLeftSubscriptionLong=diffInDays;
    		
    		if(daysLeftSubscriptionLong<=3)
    			daysLeftSubscription="Updated";
    		
    		else
    			daysLeftSubscription=String.valueOf(daysLeftSubscriptionLong);
        
    	}	
    		
    	return daysLeftSubscription;
    }
    
}
