package com.trc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trc.entities.MetroStation;

@Repository
public interface MetroStationRepository extends CrudRepository<MetroStation,Long> 
{
	@Query("Select a From MetroStation a Order by station")
	List<MetroStation> findAllDesc();
	
	@Query("Select a.station From MetroStation a where stationnumber=?1")
	String getStationByNumber(String stationNumber);
	
	@Query("Select a.line From MetroStation a where stationnumber=?1")
	String getLine(String stationNumber);
	
}
