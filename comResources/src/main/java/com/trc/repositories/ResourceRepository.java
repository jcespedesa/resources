package com.trc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.trc.entities.Resource;


@Repository
public interface ResourceRepository extends CrudRepository<Resource,Long>  
{
	@Query("Select a From Resource a Order by resourceName")
	List<Resource> findAllDesc();
	
	@Query("Select a From Resource a Where providerNumber=?1 Order by resourceName")
	List<Resource> findResByProviderDesc(String providerNumber);
	
	@Query("Select a.resourceName From Resource a Where resourceid=?1")
	String getById(Long id);
	
	@Query("Select a.icon From Resource a Where resourceid=?1")
	String getIconById(Long id);
	
	@Query("Select a From Resource a Where a.active='Yes' Order by resourceName")
	List<Resource> findAllDescActive();
}
