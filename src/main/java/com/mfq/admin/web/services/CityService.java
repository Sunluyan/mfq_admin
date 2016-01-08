package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;


import com.mfq.admin.web.bean.AreaCity;
import com.mfq.admin.web.bean.example.AreaCityExample;
import com.mfq.admin.web.dao.AreaCityMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class CityService {

	@Resource
	AreaCityMapper mapper;

	
	public List<AreaCity> findAllExistAreaCity(){
		List<AreaCity> list = mapper.findAllExistAreaCity();
		return list;
	}
	
	public AreaCity findByName(String AreaCityname){
		AreaCityname = AreaCityname.split("市")[0];
		AreaCityExample example = new AreaCityExample();
		example.or().andNameEqualTo(AreaCityname);
		return mapper.selectByExample(example).get(0);
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		CityService service = ac.getBean(CityService.class);
		service.findAllExistAreaCity();
	}
}


