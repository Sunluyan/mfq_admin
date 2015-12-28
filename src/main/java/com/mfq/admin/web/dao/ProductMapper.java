package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.Product;

@MQMDao
public interface ProductMapper {

    public long insertProduct(Product p);
    
    public long findCount();
    
    public Product findById(@Param("id") long id);

    public List<Product> findByIds(@Param("list") List<Long> list);
    
    public List<Product> findByPage(@Param("start") long start, @Param("pagesize") long pagesize);

    public void updateProduct(Product model);
    
    public long deleteProduct(@Param("id") long id);
    
    public long deleteProducts(@Param("list") List<Long> list);
    
}