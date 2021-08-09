package com.trc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trc.entities.County;


@Repository
public interface CountyRepository extends CrudRepository<County,Long>  
{
	@Query("Select a From County a Order by countyname")
	List<County> findAllDesc();
	
	@Query("Select a.countyName From County a where countyid=?1")
	String findCountyById(Long id);
	
}
