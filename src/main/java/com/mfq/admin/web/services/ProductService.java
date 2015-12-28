package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.ProductDetailMapper;
import com.mfq.admin.web.dao.ProductMapper;
import com.mfq.admin.web.models.Product;
import com.mfq.admin.web.models.ProductDetail;

@Service
public class ProductService {

    @Resource
    ProductMapper pmapper;
    
    @Resource
    ProductDetailMapper pdmapper;
    
    public long insertProduct(Product model){
        return pmapper.insertProduct(model);
    }
    
    public long findCount(){
        return pmapper.findCount();
    }
    
    public Product findById(long id){
        return pmapper.findById(id);
    }
    
    public List<Product> findByIds(List<Long> ids){
        return pmapper.findByIds(ids);
    }

    public List<Product> findByPage(long start, long pagesize){
        return pmapper.findByPage(start, pagesize);
    }

    public void updateProduct(Product model){
        pmapper.updateProduct(model);
    }
    
    public long deleteProduct(long id){
        return pmapper.deleteProduct(id);
    }
    
    public long deleteProducts(List<Long> list){
        return pmapper.deleteProducts(list);
    }
    

    public ProductDetail findDetailByPid(long pid){
        return pdmapper.findByPid(pid);
    }
    
    public long insertDetail(ProductDetail model){
        return pdmapper.insertDetail(model);
    }
    
    public void updateOne(ProductDetail model){
    	if(model.getPid() < 1){
    		return;
    	}
    	ProductDetail d = pdmapper.findByPid(model.getPid());
    	if(d == null ){
    		insertDetail(model);			 
    	}else{
    		pdmapper.updateOne(model);
    	}
    }

}
