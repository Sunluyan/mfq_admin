package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.Product;
import com.mfq.admin.web.bean.example.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MQMDao
@Component
public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    long insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    public List<Product> findByPage(@Param("start") long start, @Param("pagesize") long pagesize);

    List<Product> findByPageAndExample(@Param("start") long start,
                                               @Param("pagesize") long pagesize,
                                               @Param("example")ProductExample example,
                                       @Param("orderby")String orderby);

    long findByPageAndExampleCount(@Param("example")ProductExample example);

    long lastId();


}