package com.trc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="metrostations")
public class MetroStation 
{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long stationid;
     
    @Column(name="stationnumber")
    private String stationNumber;
    
    @Column(name="station")
    private String station;
    
    @Column(name="line")
    private String line;
    
    @Column(name="notes")
    private String notes;
    
    @Column(name="active")
    private String active;
    
    //Constructor

    @Override
    public String toString() 
    {
        return "MetroStation[stationid="+ stationid +",stationNumber="+ stationNumber +",station="+ station +",line="+ line +",notes="+ notes +",active="+ active +"]";
    }
	
	//Getters and setters

	public Long getStationid() 
	{
		return stationid;
	}

	public void setStationid(Long stationid) 
	{
		this.stationid=stationid;
	}

	public String getStationNumber() 
	{
		return stationNumber;
	}

	public void setStationNumber(String stationNumber) 
	{
		this.stationNumber=stationNumber;
	}

	public String getStation() 
	{
		return station;
	}

	public void setStation(String station) 
	{
		this.station=station;
	}

	public String getLine() 
	{
		return line;
	}

	public void setLine(String line) 
	{
		this.line=line;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes=notes;
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
