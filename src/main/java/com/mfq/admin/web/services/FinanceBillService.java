package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.FinanceBill;
import com.mfq.admin.web.bean.example.FinanceBillExample;
import com.mfq.admin.web.dao.FinanceBillMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class FinanceBillService {
	@Resource
	FinanceBillMapper mapper ;
	@Resource
	FinanceBillExample example ;

	
	public FinanceBill selectByPrimaryKey(long id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<FinanceBill> selectByIdLessThree(){
		example.or().andIdLessThan(10l);
		return mapper.selectByExample(example);
	}
	
	public List<FinanceBill> selectByStatusLessThree(){
		example.or().andStatusLessThan(1);
		return mapper.selectByExample(example);
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		FinanceBillService service = ac.getBean(FinanceBillService.class);
		service.selectByIdLessThree();
		service.selectByStatusLessThree();
	}
}
