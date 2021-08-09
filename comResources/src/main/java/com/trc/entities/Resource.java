package com.trc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Jan
 *
 */
@Entity
@Table(name="resources")
public class Resource 
{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long resourceid;
     
    @Column(name="resourcename")
    private String resourceName;
    
    @Column(name="icon")
    private String icon;
    
    @Column(name="active")
    private String active;
     
                
    @Override
    public String toString() 
    {
        return "Resource[resourceid="+ resourceid +",resourceName="+ resourceName +",icon="+ icon +",active="+ active +"]";
    }

    //Getters and Setters

	public Long getResourceid() 
	{
		return resourceid;
	}

	public void setResourceid(Long resourceid) 
	{
		this.resourceid=resourceid;
	}

	public String getResourceName() 
	{
		return resourceName;
	}

	public void setResourceName(String resourceName) 
	{
		this.resourceName=resourceName;
	}

	public String getIcon() 
	{
		return icon;
	}

	public void setIcon(String icon) 
	{
		this.icon=icon;
	}

	public String getActive() 
	{
		return active;
	}

	public void setActive(String active) 
	{
		this.active=active;
	}
	
	
    	
}
