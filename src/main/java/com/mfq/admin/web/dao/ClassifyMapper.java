package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.ProductClassify;

@MQMDao
public interface ClassifyMapper {

    public long insertOne(ProductClassify productClassify);

    public List<ProductClassify> findAll();
    
    public ProductClassify findById(@Param("id") int id);
    
    public ProductClassify findByIdAndRoodId(@Param("id") int id, @Param("rootId") int rootId);

    public List<ProductClassify> findByRootId(@Param("rootId") int rootId);

	public List<Integer> findClassIdsByRootId(@Param("rootId") int rootId);

	public List<ProductClassify> findByLevel(@Param("rootId") int rootId);

	public long delClassify(@Param("id") int id);

	public long updateClassify(ProductClassify productClassify);

	public void delClassifyByRoot(@Param("rid")int id);

}