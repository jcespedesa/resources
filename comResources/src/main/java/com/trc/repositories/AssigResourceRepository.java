package com.trc.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trc.entities.AssigResource;

@Repository
public interface AssigResourceRepository  extends CrudRepository<AssigResource,Long>  
{
	@Query("Select a From AssigResource a Where (countyNumber=?1 and resourceNumber=?2) Order by provider")
	List<AssigResource> findProvidersByCounty(String countyNumber,String resourceNumber);
	
	@Query("Select a From AssigResource a Where resourceNumber=?1 Order by provider")
	List<AssigResource> findUniqProvidByRes(String resourceNumber);
	
	@Query("Select a From AssigResource a Where providerNumber=?1 Order by resource")
	List<AssigResource> findResByProvider(String providerNumber);
	
	@Query("Select Count(1) From AssigResource a Where (providerNumber=?1 and resourceNumber=?2)")
	int findAssignResRepeated(String providerId, String resourceId);
	
	@Modifying
	@Transactional
	@Query("update AssigResource u set u.notes=?2 where u.recordid=?1")
	void updateNotes(Long id,String notes);
	
}

