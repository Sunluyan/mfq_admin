package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.PayRecordMapper;
import com.mfq.admin.web.models.PayRecord;
import com.mfq.admin.web.models.PayRecordExample;

@Service
public class PayRecordService {

	@Resource
	private PayRecordMapper mapper;
	
	public List<PayRecord> selectByOrderNo(String orderNo){
		PayRecordExample example = new PayRecordExample();
		example.or().andOrderNoEqualTo(orderNo);
		example.setOrderByClause("pay_period desc");
		return mapper.selectByExample(example);
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		PayRecordMapper mapper = ac.getBean(PayRecordMapper.class);
		System.out.println(mapper);
	}
	
}

