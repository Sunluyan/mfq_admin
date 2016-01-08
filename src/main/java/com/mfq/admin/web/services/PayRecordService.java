package com.mfq.admin.web.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.PayRecord;
import com.mfq.admin.web.bean.example.PayRecordExample;
import com.mfq.admin.web.constants.Constants;
import com.mfq.admin.web.constants.OrderType;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.PayRecordMapper;

@Service
public class PayRecordService {

	@Resource
	private PayRecordMapper mapper;

	PayRecordExample example = new PayRecordExample();
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

	public List<PayRecord> selectByTimeAndUid(Long uid) {
		PayRecordExample.Criteria c =example.or().andStatusEqualTo(2).andUidEqualTo(uid);   //1  gopay  2 支付完成   3 取消
		return mapper.selectByExample(example);
	}

	public List<Long> queryByGroupByUid(Date ob, Date oe, Integer start, Integer size) {

		return mapper.queryByUpDateAndGroupByUid(ob, oe, 2, start, size);    //支付完成
	}

	public List<PayRecord> queryPayRecordsByUser(long uid){
		return mapper.queryPayRecordsByUid(uid, 2);   //支付完成
	}

	public OrderType getOrderType(String billNo) throws Exception {
		if (StringUtils.startsWithIgnoreCase(billNo, Constants.RECHARGE_ORDER_PREFIX)) {
			return OrderType.RECHARGE;
		} else if (StringUtils.startsWithIgnoreCase(billNo, Constants.ONLINE_ORDER_PREFIX)) {
			return OrderType.ONLINE;
		} else if(StringUtils.startsWithIgnoreCase(billNo, Constants.REFUND_ORDER_PREFIX)){
			return OrderType.REFUND;
		} else {
			throw new Exception("不支持的订单类型！billNo=" + billNo);
		}
	}

}

