package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.ProductImg;

@MQMDao
public interface ProductImgMapper {

    public List<ProductImg> findByPid(@Param("pid") long pid);
    
    public long insertImg(ProductImg model);
    
    public long updateOne(ProductImg model);

	public long delImg(@Param("pid") long id);
}