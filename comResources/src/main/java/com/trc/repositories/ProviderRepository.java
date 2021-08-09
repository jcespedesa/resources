package com.trc.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trc.entities.Provider;


@Repository
public interface ProviderRepository extends CrudRepository<Provider,Long>  
{
	@Query("Select a From Provider a Order by providerName")
	List<Provider> findAllDesc();
	
	@Query("Select a From Provider a Where countyNumber=?1 Order by providerName")
	List<Provider> findProvidersByCounty(String countyNumber);
	
	@Query("Select a.providerNumber,a.provider From AssigResource a Where resourceNumber=?1 Order by provider")
	List<Provider> findProvidersByRes(String resourceNumber);
	
	@Query("Select a.providerName From Provider a where providerid=?1")
	String findProviderById(Long id);
	
	@Modifying
	@Transactional
	@Query("update Provider u set u.address=?2,u.latLon=?3,u.lat=?4,u.lon=?5 where u.providerid=?1")
	void updateDecoder(Long id,String address,String latLon,String lat,String lon);
	
	@Query("Select a From Provider a where (a.providerName Like '%' || ?1 || '%') Order by providerName")
	List<Provider> getProvidersByName(String stringSearch);
	
	@Query("Select a From Provider a where a.providerid=?1")
	List<Provider> getProvidersByID(Long stringSearchLong);
	
	@Query(value="Select dateLastUpdate From providers Where providerId=?1" ,nativeQuery=true)
    List<String> findLastSubscriptionDate(String providerId);
	
}
