package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.HomeClassify;

@MQMDao
public interface HomeClassifyMapper {

    public long insertHomeClassify(HomeClassify classify);
    
    public HomeClassify findById(@Param("id") long id);
    
    public List<HomeClassify> queryAll();
    
    public long updateHomeClassify(HomeClassify model);
    
    public long deleteHomeClassify(@Param("id") long id);
    
}