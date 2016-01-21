package com.mfq.admin.web.services;

import com.mfq.admin.web.bean.OrderFreedom;
import com.mfq.admin.web.bean.OrderFreedomExample;
import com.mfq.admin.web.constants.OrderStatus;
import com.mfq.admin.web.dao.OrderFreedomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hui on 16/1/11.
 */
@Service
public class OrderFreedomService {

    private static final Logger logger = LoggerFactory.getLogger(OrderFreedomService.class);

    @Resource
    OrderFreedomMapper orderFreedomMapper;

    public List<OrderFreedom> selectByUid(long uid) {
        OrderFreedomExample example = new OrderFreedomExample();
        example.or().andUidEqualTo(uid);
        return orderFreedomMapper.selectByExample(example);
    }

    public OrderFreedom selectByOrderNo(String orderNo) {
        OrderFreedomExample example = new OrderFreedomExample();
        example.or().andOrderNoEqualTo(orderNo);
        List<OrderFreedom> list = orderFreedomMapper.selectByExample(example);

        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 更新随意单状态
     * @param orderNo
     * @param fromStatus
     * @param toStatus
     * @return
     */
    public long updateOrderStatus(String orderNo, OrderStatus fromStatus , OrderStatus toStatus){
        OrderFreedomExample example = new OrderFreedomExample();
        example.or().andOrderNoEqualTo(orderNo).andStatusEqualTo(fromStatus.getValue());
        OrderFreedom orderFreedom = new OrderFreedom();
        orderFreedom.setStatus(toStatus.getValue());
        return orderFreedomMapper.updateByExampleSelective(orderFreedom,example);
    }

    public long updateOrderOnlinepay(String orderNo, BigDecimal newOnlinePay){
        OrderFreedomExample example = new OrderFreedomExample();
        example.or().andOrderNoEqualTo(orderNo);
        OrderFreedom orderFreedom = new OrderFreedom();
        orderFreedom.setOnlinePay(newOnlinePay);
        return orderFreedomMapper.updateByExampleSelective(orderFreedom,example);
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        OrderFreedomMapper mapper = ac.getBean(OrderFreedomMapper.class);
        List<OrderFreedom> list = mapper.selectByExample(new OrderFreedomExample());


    }

}
