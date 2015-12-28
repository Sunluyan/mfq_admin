package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.Hospital;

@MQMDao
public interface HospitalMapper {

    public Hospital findById(@Param("id") long id);
    
    public List<Hospital> findByName(@Param("name") String name,@Param("cityid") Integer cityid);
    
    public long insertDetail(Hospital hospital);

	public List<Hospital> queryAll();

	public long updateHospital(Hospital hospital);
    
}