package com.mfq.admin.web.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.FinanceBill;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.mfq.admin.web.bean.OrderInfo;
import com.mfq.admin.web.bean.PayRecord;
import com.mfq.admin.web.bean.example.OrderInfoExample;
import com.mfq.admin.web.bean.example.PayRecordExample;
import com.mfq.admin.web.cache.RedisCache;
import com.mfq.admin.web.constants.Constants;
import com.mfq.admin.web.constants.OrderType;
import com.mfq.admin.web.constants.PayAPIType;
import com.mfq.admin.web.dao.OrderInfoMapper;
import com.mfq.admin.web.dao.PayRecordMapper;
import com.mfq.admin.web.utils.JSONUtil;
import org.springframework.stereotype.Service;;

@Service
public class PayRecordService {

    @Resource
    PayRecordMapper mapper;
    @Resource
    OrderInfoMapper orderInfoMapper;
    @Resource
    OrderService orderService;

    PayRecordExample example = new PayRecordExample();

    public List<PayRecord> selectByOrderNo(String orderNo) {
        PayRecordExample example = new PayRecordExample();
        example.or().andOrderNoEqualTo(orderNo).andStatusEqualTo(2).andAmountNotEqualTo(BigDecimal.valueOf(19));
        return mapper.selectByExample(example);
    }


    public List<PayRecord> selectByTimeAndUid(Long uid) {
        PayRecordExample.Criteria c = example.or().andStatusEqualTo(2).andUidEqualTo(uid);   //1  gopay  2 支付完成   3 取消
        return mapper.selectByExample(example);
    }

    public List<Long> queryByGroupByUid(Date ob, Date oe, Integer start, Integer size) {

        return mapper.queryByUpDateAndGroupByUid(ob, oe, 2, start, size);    //支付完成
    }

    public List<PayRecord> queryPayRecordsByUser(long uid, OrderType type, Integer status) throws Exception {
        PayRecordExample example = new PayRecordExample();
        PayRecordExample.Criteria criteria = example.createCriteria();
        if (status == null) {
            criteria.andUidEqualTo(uid);
        } else {
            criteria.andUidEqualTo(uid).andStatusEqualTo(status);
        }
        if (type != null) {
            String prefix = orderService.getOrderPrefixByOrderType(type);
            criteria.andOrderNoLike(prefix + "%");
        }

        return mapper.selectByExample(example);  //支付完成
    }

    public OrderType getOrderType(String billNo) throws Exception {
        if (StringUtils.startsWithIgnoreCase(billNo, Constants.RECHARGE_ORDER_PREFIX)) {
            return OrderType.RECHARGE;
        } else if (StringUtils.startsWithIgnoreCase(billNo, Constants.ONLINE_ORDER_PREFIX)) {
            return OrderType.ONLINE;
        } else if (StringUtils.startsWithIgnoreCase(billNo, Constants.REFUND_ORDER_PREFIX)) {
            return OrderType.REFUND;
        } else {
            throw new Exception("不支持的订单类型！billNo=" + billNo);
        }
    }

    public long countFinanceByPrames(Date b, Date e, OrderType type, PayAPIType payapi, List<Long> pids) {
        String typeStr = "", tppStr = "%";
        if (type == OrderType.ONLINE) {
            typeStr = Constants.ONLINE_ORDER_PREFIX;
        } else if (type == OrderType.RECHARGE) {
            typeStr = Constants.RECHARGE_ORDER_PREFIX;
        } else if (type == OrderType.REFUND) {
            typeStr = Constants.REFUND_ORDER_PREFIX;
        }
        typeStr = typeStr + "%";

        if (payapi != null) {
            tppStr = payapi.getCode();
        }

        //查询订单
        OrderInfoExample oexample = new OrderInfoExample();
        if (pids.size() > 0) {
            oexample.or().andPidIn(pids);
        } else {
            oexample.or().andPidEqualTo(0l);
        }
        oexample.setStart(0);
        oexample.setSize(100);
        List<OrderInfo> orders = orderInfoMapper.selectByExample(oexample);
        List<String> orderNos = Lists.newArrayList();
        for (OrderInfo no : orders) {
            orderNos.add(no.getOrderNo());
        }
        PayRecordExample example = new PayRecordExample();
        PayRecordExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoLike(typeStr).andTppLike(tppStr);
        if (b != null && e != null) {
            criteria.andUpdatedAtBetween(b, e);
        }
        if (orderNos.size() > 0) {
            criteria.andOrderNoIn(orderNos);
        } else if (pids.size() > 0 && orderNos.size() < 1) {
            criteria.andOrderNoEqualTo("mn");
        }

        return mapper.countByExample(example);
    }

    public List<PayRecord> queryFinanceByPrames(Date b, Date e, OrderType type, PayAPIType payapi, Integer start, Integer size, List<Long> pids) {
        String typeStr = "", tppStr = "%";
        if (type == OrderType.ONLINE) {
            typeStr = Constants.ONLINE_ORDER_PREFIX;
        } else if (type == OrderType.RECHARGE) {
            typeStr = Constants.RECHARGE_ORDER_PREFIX;
        } else if (type == OrderType.REFUND) {
            typeStr = Constants.REFUND_ORDER_PREFIX;
        }
        typeStr = typeStr + "%";

        if (payapi != null) {
            tppStr = payapi.getCode();
        }

        //查询订单
        OrderInfoExample oexample = new OrderInfoExample();
        if (pids.size() > 0) {
            oexample.or().andPidIn(pids);
        } else {
            oexample.or().andPidEqualTo(0l);
        }
        List<OrderInfo> orders = orderInfoMapper.selectByExample(oexample);
        List<String> orderNos = Lists.newArrayList();
        for (OrderInfo no : orders) {
            orderNos.add(no.getOrderNo());
        }

        PayRecordExample example = new PayRecordExample();
        PayRecordExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoLike(typeStr).andTppLike(tppStr);
        if (b != null && e != null) {
            criteria.andUpdatedAtBetween(b, e);
        }
        if (orderNos.size() > 0) {
            criteria.andOrderNoIn(orderNos);
        } else if (pids.size() > 0 && orderNos.size() < 1) {
            criteria.andOrderNoEqualTo("mn");
        }

        example.setSize(size);
        example.setStart(start);

        List<PayRecord> data = mapper.selectByExamplePage(example);

        return data;
    }

    public long deleteById(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }


    public List<PayRecord> selectByFinanceNos(List<FinanceBill> financeBills) throws Exception{
        List<String> financeNos = new ArrayList<>();
        for (FinanceBill financeBill : financeBills) {
            financeNos.add(financeBill.getBillNo());
        }
        PayRecordExample example = new PayRecordExample();
        example.or().andOrderNoIn(financeNos);

        return mapper.selectByExample(example);
    }

	public PayRecord queryPayRecordByOrderNo(String orderNo) {
		PayRecordExample example = new PayRecordExample();
		PayRecordExample.Criteria criteria = example.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);

		List<PayRecord> pays = mapper.selectByExample(example);
		if(pays.size()>0){
			return pays.get(pays.size()-1);
		}else {
			return null;
		}
	}

//
//	final String CACHE_LIST = "chche_payrecords";
//
//	private static RedisCache cache = new RedisCache();
//
//    private static final int exp = 7000;
//
//	/**
//	 * 基于缓存的分页
//	 * @param list
//	 * @param start
//	 * @param size
//	 * @return
//	 */
//	public List<PayRecord> queryByPage(int start, int size){
//		List<String> alls = null;
//		if(cache.exist(CACHE_LIST)){
//			alls = cache.lrange(CACHE_LIST, start, size);
//		}else{
//			List<PayRecord> data =  mapper.selectByExample(example);
//			for(PayRecord p:data){
//				cache.lpush(CACHE_LIST, JSONUtil.writeToJson(p));
//			}
//			alls = cache.lrange(CACHE_LIST, start, size);
//		}
//
//		PayRecordExample.Criteria c =example.or();
//
//		List<PayRecord> data =  mapper.selectByExample(example);
//		return null;
//	}

}

