package com.mfq.admin.web.services;

import com.mfq.admin.web.bean.ProductImage;
import com.mfq.admin.web.bean.ProductImageExample;
import com.mfq.admin.web.constants.ProductImageType;
import com.mfq.admin.web.dao.ProductImageMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuzhiguo1 on 16/3/14.
 */
@Service
public class ProductImageService {
    @Resource
    ProductImageMapper mapper;

    public void saveOrInsert(Long pid, String image, ProductImageType type) throws Exception {
        if(StringUtils.isBlank(image))return;
        ProductImage productImage = new ProductImage();
        productImage.setType(type.getId());
        productImage.setPid(pid.intValue());
        productImage.setImg(image);
        ProductImageExample example = new ProductImageExample();
        example.or().andPidEqualTo(pid.intValue());

        if(mapper.selectByExample(example).size() == 0){
            //添加
            mapper.insert(productImage);
        }
        else{
            //修改
            mapper.updateByExampleSelective(productImage,example);
        }
    }

    //1,找到所有原有的.
    //2,循环现在的,如果不是空,就更新.
    //3,如果超出了原有的,就插入新的.
    //4,如果原有的多于现在的,则报错.
    //5,如果每次更新结果不是1,报错.
    public void saveDetails(Long pid,String[] images) throws Exception{
        ProductImageExample example = new ProductImageExample();
        example.or().andPidEqualTo(pid.intValue());
        example.setOrderByClause("`index` desc");
        List<ProductImage> list = mapper.selectByExample(example);
        for (int i = 0; i < images.length; i++) {
            ProductImage p = new ProductImage();
            p.setPid(pid.intValue());
            p.setImg(images[i]);
            p.setIndex(i);
            p.setType(ProductImageType.DETAIL.getId());
            int count = mapper.insertSelective(p);
            if(count!=1)throw new Exception("添加产品详情出错");
        }
    }
}
