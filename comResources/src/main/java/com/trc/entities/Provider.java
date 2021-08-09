package com.trc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="providers")
public class Provider 
{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long providerid;
     
    @Column(name="providername")
    private String providerName;
    
    @Column(name="address")
    private String address;
     
    @Column(name="county")
    private String county;
    
    @Column(name="countynumber")
    private String countyNumber;
    
    @Column(name="active")
    private String active;
    
    @Column(name="phone")
    private String phone;
    
    @Column(name="email")
    private String email;
    
    @Column(name="notes")
    private String notes;
    
    @Column(name="contact")
    private String contact;
    
    @Column(name="otherphone")
    private String otherPhone;
    
    @Column(name="otheremail")
    private String otherEmail;
    
    @Column(name="website")
    private String website;
    
    @Column(name="lon")
    private String lon;
    
    @Column(name="lat")
    private String lat;
    
    @Column(name="latlon")
    private String latLon;
    
    @Column(name="typeprovider")
    private String typeProvider;

    @Column(name="fax")
    private String fax;
    
    @Column(name="nearestmetro")
    private String nearestMetro;
    
    @Column(name="nearestmetronumber")
    private String nearestMetroNumber;
    
    @Column(name="notesmetro")
    private String notesMetro;
    
    @Column(name="line")
    private String line;
    
    @Column(name="datelastupdate")
    private String dateLastUpdate;
    
    @Column(name="buffer1")
    private String buffer1;
    
    
    //The constructor
    
    public Provider(Long providerid, String providerName, String address, String county, String countyNumber, String active,
			String phone, String email, String notes, String contact, String otherPhone, String otherEmail, String website, String lon,
			String lat, String latLon, String typeProvider, String fax, String nearestMetro, String nearestMetroNumber,
			String notesMetro, String line, String dateLastUpdate,String buffer1) 
    {
		super();
		this.providerid=providerid;
		this.providerName=providerName;
		this.address=address;
		this.county=county;
		this.countyNumber=countyNumber;
		this.active=active;
		this.phone=phone;
		this.email=email;
		this.notes=notes;
		this.contact=contact;
		this.otherPhone=otherPhone;
		this.otherEmail=otherEmail;
		this.website=website;
		this.lon=lon;
		this.lat=lat;
		this.latLon=latLon;
		this.typeProvider=typeProvider;
		this.fax=fax;
		this.nearestMetro=nearestMetro;
		this.nearestMetroNumber=nearestMetroNumber;
		this.notesMetro=notesMetro;
		this.line=line;
		this.dateLastUpdate=dateLastUpdate;
		this.buffer1=buffer1;
	}
    
    public Provider()
    {
    	
    }
    
    //Getters and setters

	public Long getProviderid() 
	{
		return providerid;
	}

	public void setProviderid(Long providerid) 
	{
		this.providerid=providerid;
	}

	public String getProviderName() 
	{
		return providerName;
	}

	public void setProviderName(String providerName) 
	{
		this.providerName=providerName;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address=address;
	}

	public String getCounty() 
	{
		return county;
	}

	public void setCounty(String county) 
	{
		this.county=county;
	}

	public String getCountyNumber() 
	{
		return countyNumber;
	}

	public void setCountyNumber(String countyNumber) 
	{
		this.countyNumber=countyNumber;
	}

	public String getActive() 
	{
		return active;
	}

	public void setActive(String active) 
	{
		this.active=active;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phone) 
	{
		this.phone=phone;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email=email;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes=notes;
	}

	public String getContact() 
	{
		return contact;
	}

	public void setContact(String contact) 
	{
		this.contact=contact;
	}

	public String getOtherEmail() 
	{
		return otherEmail;
	}

	public void setOtherEmail(String otherEmail) 
	{
		this.otherEmail=otherEmail;
	}

	public String getWebsite() 
	{
		return website;
	}

	public void setWebsite(String website) 
	{
		this.website=website;
	}

	public String getLon() 
	{
		return lon;
	}

	public void setLon(String lon) 
	{
		this.lon=lon;
	}

	public String getLat() 
	{
		return lat;
	}

	public void setLat(String lat) 
	{
		this.lat=lat;
	}

	public String getLatLon() 
	{
		return latLon;
	}

	public void setLatLon(String latLon) 
	{
		this.latLon=latLon;
	}

	public String getTypeProvider() 
	{
		return typeProvider;
	}

	public void setTypeProvider(String typeProvider) 
	{
		this.typeProvider=typeProvider;
	}

	public String getFax() 
	{
		return fax;
	}

	public void setFax(String fax) 
	{
		this.fax=fax;
	}

	public String getNearestMetro() 
	{
		return nearestMetro;
	}

	public void setNearestMetro(String nearestMetro) 
	{
		this.nearestMetro=nearestMetro;
	}

	public String getNearestMetroNumber() 
	{
		return nearestMetroNumber;
	}

	public void setNearestMetroNumber(String nearestMetroNumber) 
	{
		this.nearestMetroNumber=nearestMetroNumber;
	}

	public String getNotesMetro() 
	{
		return notesMetro;
	}

	public void setNotesMetro(String notesMetro) 
	{
		this.notesMetro=notesMetro;
	}

	public String getLine() 
	{
		return line;
	}

	public void setLine(String line) 
	{
		this.line=line;
	}

	public String getDateLastUpdate() 
	{
		return dateLastUpdate;
	}

	public void setDateLastUpdate(String dateLastUpdate) 
	{
		this.dateLastUpdate=dateLastUpdate;
	}

	public String getOtherPhone() 
	{
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) 
	{
		this.otherPhone=otherPhone;
	}

	public String getBuffer1() 
	{
		return buffer1;
	}

	public void setBuffer1(String buffer1) 
	{
		this.buffer1=buffer1;
	}
    
	
}
