package com.mfq.admin.web.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.HospitalMapper;
import com.mfq.admin.web.models.Hospital;

@Service
public class HospitalService {

    @Resource
    HospitalMapper mapper;

    public Hospital findById(long id) {
        return mapper.findById(id);
    }

    public List<Hospital> findAll() {
        return mapper.findByName(null,null);
    }
    
    public List<Hospital> findByNameAndCity(String name,Integer id) {
    	List<Hospital> list = mapper.findByName(name,id);
        return list;
    }

    public long insertDetail(Hospital h) {
        return mapper.insertDetail(h);
    }

	public List<Hospital> queryAll() {
		return mapper.queryAll();
	}

	public Hospital saveHospital(long hospitalId, String name, String img, String address,long cityid) {
		Hospital hospital = null;
		if(hospitalId > 0){
			hospital = mapper.findById(hospitalId);
			saveOrUpdateHospital(hospital, name, img, address, cityid);
			mapper.updateHospital(hospital);
		}else{
			hospital = new Hospital();
			saveOrUpdateHospital(hospital, name, img, address, cityid);
			mapper.insertDetail(hospital);
		}
		return hospital;
	}
	
	private Hospital saveOrUpdateHospital(Hospital hospital, String name, String img, String address,long cityid){
		
		hospital.setName(name);
		hospital.setAddress(address);
		if (StringUtils.isNotBlank(img)) {
			hospital.setImg(img);
        }
		hospital.setCityId(cityid);
		hospital.setUpdatedAt(new Date());
		return hospital;
	}

}
