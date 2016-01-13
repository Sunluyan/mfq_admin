package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.bean.Presental;
import com.mfq.admin.web.bean.example.PresentalExample;
import com.mfq.admin.web.dao.PresentalMapper;

/**
 * Created by liuzhiguo1 on 16/1/6.
 */
@Service
public class PresentalService {
    private static final Logger logger = LoggerFactory
            .getLogger(OrderService.class);

    @Resource
    PresentalMapper presentalMapper;
    @Resource
    PresentalExample example;

    public void selectByCode(){
        example.or().andCodeBetween("1000","2000");
    }
    public void selectByCod(){
        selectByCode();
        List<Presental> list = presentalMapper.selectByExample(example);
        System.out.println("共查出来"+list.size()+"条数据");
        for (Presental presental : list) {
            System.out.println(presental.toString());
        }
    }
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        PresentalService mapper = ac.getBean(PresentalService.class);
        mapper.selectByCod();
    }
}
