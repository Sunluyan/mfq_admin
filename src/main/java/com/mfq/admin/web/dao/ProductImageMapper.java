package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.ProductImage;
import com.mfq.admin.web.bean.ProductImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@MQMDao
public interface ProductImageMapper {
    int countByExample(ProductImageExample example);

    int deleteByExample(ProductImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByExample(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);
}