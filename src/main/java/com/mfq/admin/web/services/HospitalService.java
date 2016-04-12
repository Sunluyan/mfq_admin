package com.mfq.admin.web.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.Hospital;
import com.mfq.admin.web.bean.HospitalExample;
import com.mfq.admin.web.dao.HospitalMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    public List<Hospital> findByNameAndCity(String name, Integer cityId) {
        HospitalExample example = new HospitalExample();
        HospitalExample.Criteria  criteria= example.or();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (cityId != null) {
            criteria.andCityIdEqualTo((long) cityId);
        }

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

    @Transactional
    public Hospital saveHospital(long hospitalId, String name, String img, String address, String desc, String[] details) throws Exception {
        Hospital hospital;
        if (hospitalId > 0) {
            hospital = mapper.selectByPrimaryKey(hospitalId);
            saveOrUpdateHospital(hospital, name, img, address, desc);

            if (StringUtils.isNotEmpty(hospital.getDetails())) {
                String[] oldDetails = hospital.getDetails().split(",");
                if (oldDetails.length != 0) {
                    for (int i = 0; i < oldDetails.length; i++) {
                        if (StringUtils.isEmpty(details[i])) {
                            details[i] = oldDetails[i];
                        }
                    }
                }
            }

            String detail = "";
            for (String s : details) {
                if (StringUtils.isNotEmpty(s)) {
                    detail += s + ",";
                }
            }

            hospital.setDetails(detail);


            mapper.updateByPrimaryKeySelective(hospital);
        } else {
            hospital = new Hospital();
            saveOrUpdateHospital(hospital, name, img, address, desc);

            String detail = "";
            for (String s : details) {
                detail += s + ",";
            }

            hospital.setDetails(detail);
            mapper.insertSelective(hospital);
        }
        return hospital;
    }

    private Hospital saveOrUpdateHospital(Hospital hospital, String name, String img, String address,
                                          String desc) {

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
