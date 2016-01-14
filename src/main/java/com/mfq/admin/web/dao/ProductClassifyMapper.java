package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.ProductClassify;
import com.mfq.admin.web.bean.example.ProductClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MQMDao
@Component
public interface ProductClassifyMapper {
    int countByExample(ProductClassifyExample example);

    int deleteByExample(ProductClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductClassify record);

    int insertSelective(ProductClassify record);

    List<ProductClassify> selectByExample(ProductClassifyExample example);

    ProductClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductClassify record, @Param("example") ProductClassifyExample example);

    int updateByExample(@Param("record") ProductClassify record, @Param("example") ProductClassifyExample example);

    int updateByPrimaryKeySelective(ProductClassify record);

    int updateByPrimaryKey(ProductClassify record);
}