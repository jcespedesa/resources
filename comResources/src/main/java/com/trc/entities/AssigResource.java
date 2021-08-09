package com.trc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="assignresources")
public class AssigResource 
{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long recordid;
     
    @Column(name="provider")
    private String provider;
    
    @Column(name="providernumber")
    private String providerNumber;
    
    @Column(name="countynumber")
    private String countyNumber;
    
    @Column(name="resourcenumber")
    private String resourceNumber;
    
    @Column(name="resource")
    private String resource;
    
    @Column(name="notes")
    private String notes;
    
    @Column(name="priznakupdate")
    private String priznakUpdate;
    
    @Column(name="buffer")
    private String buffer;
    
    //The constructors
   
    public AssigResource() 
	{
	
	}

    
    @Override
    public String toString() 
    {
        return "AssigResource[recordid="+ recordid +",provider="+ provider +",providerNumber="+ providerNumber +",countyNumber="+ countyNumber +",resourceNumber="+ resourceNumber +",notes="+ notes +",priznakUpdate="+ priznakUpdate +",buffer="+ buffer +",resource="+ resource +"]";
    }


	public AssigResource(Long recordid, String provider, String providerNumber, String countyNumber,
			String resourceNumber, String notes, String priznakUpdate, String buffer, String resource) 
	{
		super();
		this.recordid=recordid;
		this.provider=provider;
		this.providerNumber=providerNumber;
		this.countyNumber=countyNumber;
		this.resourceNumber=resourceNumber;
		this.resource=resource;
		this.notes=notes;
		this.priznakUpdate=priznakUpdate;
		this.buffer=buffer;
	}


	public AssigResource(String provider, String providerNumber) 
	{
		super();
		this.provider=provider;
		this.providerNumber=providerNumber;
	}

	

	//Getters and setters
	
	public Long getRecordid() 
	{
		return recordid;
	}

	public void setRecordid(Long recordid) 
	{
		this.recordid=recordid;
	}

	public String getProvider() 
	{
		return provider;
	}

	public void setProvider(String provider) 
	{
		this.provider=provider;
	}

	public String getProviderNumber() 
	{
		return providerNumber;
	}

	public void setProviderNumber(String providerNumber) 
	{
		this.providerNumber=providerNumber;
	}

	public String getCountyNumber() 
	{
		return countyNumber;
	}

	public void setCountyNumber(String countyNumber) 
	{
		this.countyNumber=countyNumber;
	}

	public String getResourceNumber() 
	{
		return resourceNumber;
	}

	public void setResourceNumber(String resourceNumber) 
	{
		this.resourceNumber=resourceNumber;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes=notes;
	}

	public String getPriznakUpdate() 
	{
		return priznakUpdate;
	}

	public void setPriznakUpdate(String priznakUpdate) 
	{
		this.priznakUpdate=priznakUpdate;
	}


	public String getBuffer() 
	{
		return buffer;
	}


	public void setBuffer(String buffer) 
	{
		this.buffer=buffer;
	}


	public String getResource() 
	{
		return resource;
	}


	public void setResource(String resource) 
	{
		this.resource=resource;
	}
    
    
	
}
