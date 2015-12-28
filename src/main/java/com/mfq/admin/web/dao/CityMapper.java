package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.City;

@MQMDao
public interface CityMapper {
	
	public City findById();
	
	public List<City> findAllExistCity();
	
	public City findByName(@Param("cityname") String cityname);
}
