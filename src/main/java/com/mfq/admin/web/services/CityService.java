package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.constants.BannerType;
import com.mfq.admin.web.context.SpringWrapper;
import com.mfq.admin.web.dao.CityMapper;
import com.mfq.admin.web.dao.HomeBannerMapper;
import com.mfq.admin.web.models.City;
import com.mfq.admin.web.models.HomeBanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.mfq.admin.web.dao.UserMapper;
import com.mfq.admin.web.models.user.User;
import com.mfq.admin.web.utils.JSONUtil;

@Service
public class CityService {

	@Resource
	CityMapper mapper;
	
	public List<City> findAllExistCity(){
		List<City> list = mapper.findAllExistCity();
		return list;
	}
	
	public City findByName(String cityname){
		cityname = cityname.split("市")[0];
		return mapper.findByName(cityname);
	}
	
	public static void main(String[] args) {
		
	}
}


