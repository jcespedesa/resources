package com.trc.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.trc.entities.Setting;


public interface SettingRepository extends CrudRepository<Setting,Long>  
{
	@Query("Select u.path from Setting u where u.sname=?1")
	String getAppPath(@Param("sname") String sname);
}
