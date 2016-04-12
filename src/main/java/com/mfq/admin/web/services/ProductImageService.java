package com.mfq.admin.web.services;

import com.mfq.admin.web.bean.ProductImage;
import com.mfq.admin.web.bean.ProductImageExample;
import com.mfq.admin.web.constants.ProductImageType;
import com.mfq.admin.web.dao.ProductImageMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiguo1 on 16/3/14.
 */
@Service
public class ProductImageService {
    @Resource
    ProductImageMapper mapper;

    @Transactional
    public void saveOrInsert(Long pid, String image, ProductImageType type) throws Exception {
        if(StringUtils.isBlank(image))return;

        ProductImage productImage = new ProductImage();
        productImage.setType(type.getId());
        productImage.setPid(pid.intValue());
        productImage.setImg(image);
        ProductImageExample example = new ProductImageExample();
        example.or().andPidEqualTo(pid.intValue()).andTypeEqualTo(type.getId());

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
        example.or().andPidEqualTo(pid.intValue()).andTypeEqualTo(ProductImageType.DETAIL.getId());
        example.setOrderByClause("`index` asc");
        List<ProductImage> list = mapper.selectByExample(example);
        if(list.size()>images.length) throw new Exception("产品详情图片 Exception");


        for (int i = 0; i < images.length; i++) {
            if(StringUtils.isBlank(images[i])) continue;


            if(list.size()-1 >= i ){
                //更新
                ProductImage p = list.get(i);
                p.setImg(images[i]);
                int count = mapper.updateByPrimaryKeySelective(p);
                if(count!=1) throw new Exception("添加产品详情出错");
            }else{
                //新增
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

    //获得产品的所有图片
    public void getImages(Model model, Long id) {
        ProductImageExample example = new ProductImageExample();
        example.or().andPidEqualTo(id.intValue());
        List<ProductImage> list = mapper.selectByExample(example);
        List<String> details = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ProductImage productImage = list.get(i);
            if(productImage.getType() == ProductImageType.AFTER.getId()){
                model.addAttribute("after",productImage.getImg());
            }
            else if(productImage.getType() == ProductImageType.BEFORE.getId()){
                model.addAttribute("before",productImage.getImg());
            }
            else if(productImage.getType() == ProductImageType.BEAUTIFUL.getId()){
                model.addAttribute("beautiful",productImage.getImg());
            }
            else if(productImage.getType() == ProductImageType.SURGERY.getId()){
                System.out.println(productImage.getType() + "   "+ ProductImageType.SURGERY.getId());
                model.addAttribute("surgery",productImage.getImg());
            }
            else if(productImage.getType() == ProductImageType.SQUARE.getId()){
                System.out.println(productImage.getType() + "   "+ ProductImageType.SQUARE.getId());
                model.addAttribute("square",productImage.getImg());
            }
            else if(productImage.getType() == ProductImageType.DETAIL.getId()){
                System.out.println(productImage.getType() + "   "+ ProductImageType.DETAIL.getId());
                details.add(productImage.getImg());
            }

        }
        model.addAttribute("details",details);
    }
}
