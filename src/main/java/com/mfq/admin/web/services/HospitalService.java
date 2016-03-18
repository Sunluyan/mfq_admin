package com.mfq.admin.web.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.Hospital;
import com.mfq.admin.web.bean.HospitalExample;
import com.mfq.admin.web.dao.HospitalMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class HospitalService {

    @Resource
	HospitalMapper mapper;

    public Hospital findById(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Hospital> findAll() {
		HospitalExample example = new HospitalExample();
        return mapper.selectByExample(example);
    }
    
    public List<Hospital> findByNameAndCity(String name,Integer id) {
		HospitalExample example = new HospitalExample();
		if(StringUtils.isNotBlank(name)){
			example.or().andNameLike("%"+name+"%");
		}
		if(id != null){
			example.or().andIdEqualTo((long)id);
		}
		System.out.println(example);
		List<Hospital> list = mapper.selectByExample(example);
        return list;
    }

    public long insertDetail(Hospital h) {
        return mapper.insert(h);
    }

	public List<Hospital> queryAll() {
		HospitalExample example = new HospitalExample();
		return mapper.selectByExample(example);
	}

	public Hospital saveHospital(long hospitalId, String name, String img, String address,String desc) {
		Hospital hospital ;
		if(hospitalId > 0){
			hospital = mapper.selectByPrimaryKey(hospitalId);
			saveOrUpdateHospital(hospital, name, img, address,desc);
			mapper.updateByPrimaryKeySelective(hospital);
		}else{
			hospital = new Hospital();
			saveOrUpdateHospital(hospital, name, img, address,desc);
			mapper.insertSelective(hospital);
		}
		return hospital;
	}
		private Hospital saveOrUpdateHospital(Hospital hospital, String name, String img, String address,
										  String desc){
		
		hospital.setName(name);
		hospital.setAddress(address);
		if (StringUtils.isNotBlank(img)) {
			hospital.setImg(img);
        }
		hospital.setUpdatedAt(new Date());
		hospital.setDescription(desc);
		return hospital;
	}

}
