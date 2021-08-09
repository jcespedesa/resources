package com.trc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="counties")
public class County
{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long countyid;
     
    @Column(name="countyname")
    private String countyName;
    
    @Column(name="latlon")
    private String latLon;
     
    @Column(name="lat")
    private String lat;
    
    @Column(name="lon")
    private String lon;
    
    @Column(name="state")
    private String state;
    
    @Column(name="zoomview")
    private String zoomView;
            
    @Override
    public String toString() 
    {
        return "County[countyid="+ countyid +",countyName="+ countyName +",latlon="+ latLon +",lat="+ lat +",lon="+ lon +",state="+ state +",zoomView="+ zoomView +"]";
    }

    //Getters and setters
    
	public Long getCountyid() 
	{
		return countyid;
	}

	public void setCountyid(Long countyid) 
	{
		this.countyid=countyid;
	}

	public String getCountyName() 
	{
		return countyName;
	}

	public void setCountyName(String countyName) 
	{
		this.countyName=countyName;
	}

	public String getLatLon() 
	{
		return latLon;
	}

	public void setLatLon(String latLon) 
	{
		this.latLon=latLon;
	}

	public String getLat() 
	{
		return lat;
	}

	public void setLat(String lat) 
	{
		this.lat=lat;
	}

	public String getLon() 
	{
		return lon;
	}

	public void setLon(String lon) 
	{
		this.lon=lon;
	}

	public String getState() 
	{
		return state;
	}

	public void setState(String state) 
	{
		this.state=state;
	}

	public String getZoomView() 
	{
		return zoomView;
	}

	public void setZoomView(String zoomView) 
	{
		this.zoomView=zoomView;
	}

    	
}
