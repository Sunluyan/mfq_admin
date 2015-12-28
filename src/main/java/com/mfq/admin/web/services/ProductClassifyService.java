package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.ClassifyMapper;
import com.mfq.admin.web.models.ProductClassify;

@Service
public class ProductClassifyService {

    @Resource
    ClassifyMapper mapper;

    public ProductClassify findById(int id) {
        return mapper.findById(id);
    }
    
    public List<ProductClassify> findClassifys(){
    	return mapper.findAll();
    }
    
    public List<ProductClassify> findByRootId(int rootId){
    	return mapper.findByRootId(rootId);
    }
    
    public List<ProductClassify> findByLevel(int rootId){
    	return mapper.findByLevel(rootId);
    }

	public long delClassify(int id) {
		
		long t = mapper.delClassify(id);
		if(t > 0){
			mapper.delClassifyByRoot(id);
			return 1;
		}else{
			return 0;
		}
	}

	public long saveProductClassifyRoot(int id, String name, String imgs) {
		ProductClassify p = null ;
		
		if(id <= 0){
			p = new ProductClassify();
			p.setName(name);
			p.setHgImage(imgs);
			p.setFlag("1");
			p.setRootId(0);
			p.setIcon("");
			p.setDesp(name);
			return mapper.insertOne(p);
		}else{
			p = mapper.findByIdAndRoodId(id, 0);
			
			p.setName(name);
			if(!StringUtils.isBlank(imgs)){
				p.setHgImage(imgs);
			}
			p.setFlag("1");
			return mapper.updateClassify(p);
		}
	}

	public long saveProductClassify(int id, String name, int root_id) {
		ProductClassify p = null ;
		if(id <= 0){
			p = new ProductClassify();
			p.setName(name);
			p.setHgImage("");
			p.setFlag("1");
			p.setRootId(root_id);
			p.setIcon("");
			p.setDesp(name);
			return mapper.insertOne(p);
		}else{
			p = mapper.findById(id);
			p.setName(name);
			p.setHgImage("");
			p.setFlag("1");
			p.setRootId(root_id);
			return mapper.updateClassify(p);
		}
	}



}
