package com.mfq.admin.web.services;

import com.mfq.admin.web.bean.SysOperationRecord;
import com.mfq.admin.web.bean.SysOperationRecordExample;
import com.mfq.admin.web.bean.SysUser;
import com.mfq.admin.web.constants.SysOperationType;
import com.mfq.admin.web.dao.SysOperationRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhiguo1 on 16/2/23.
 */
@Service
public class SysOperationService {

    private static final Logger logger = LoggerFactory
            .getLogger(SysOperationService.class);

    @Resource
    SysOperationRecordMapper mapper;

    public SysOperationRecord saveToData(String sysName,int sysId , Long uid ,String username, String data , Long time ,SysOperationType operationType) throws Exception {
        SysOperationRecord record = new SysOperationRecord();

        record.setSysUsername(sysName);
        record.setSysId(sysId);
        record.setUserId(uid);
        record.setUsername(username);
        record.setContent(data);
        record.setTime(new Date(time));
        record.setType(operationType);
        int count = mapper.insertSelective(record);
        if(count != 1){
            throw new Exception("数据插入错误");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        record.setTimeSDF(sdf.format(record.getTime()));
        return record;
    }

    public List<SysOperationRecord> selectOperationByType(SysOperationType sysOperationType){
        SysOperationRecordExample example = new SysOperationRecordExample();
        example.or().andTypeEqualTo(sysOperationType.getType());
        List<SysOperationRecord> list = mapper.selectByExample(example);
        return list;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        SysOperationService service = ac.getBean(SysOperationService.class);
    }
}

