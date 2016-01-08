package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.ProductClassify;
import com.mfq.admin.web.bean.example.ProductClassifyExample;
import com.mfq.admin.web.dao.ProductClassifyMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class ProductClassifyService {

    @Resource
	ProductClassifyMapper mapper;

    public ProductClassify findById(int id) {
        return mapper.selectByPrimaryKey(id);
    }
    
    public List<ProductClassify> findClassifys()
	{
		ProductClassifyExample example = new ProductClassifyExample();
    	return mapper.selectByExample(example);
    }
    
    public List<ProductClassify> findByRootId(int rootId)
	{
		ProductClassifyExample example = new ProductClassifyExample();
		example.or().andRootIdEqualTo(rootId);
    	return mapper.selectByExample(example);
    }
    
    public List<ProductClassify> findByLevel(int rootId)
	{
		ProductClassifyExample example = new ProductClassifyExample();
		if(rootId == 0){
			example.or().andRootIdEqualTo(rootId);
		}else{
			example.or().andRootIdNotEqualTo(rootId);
		}

		return mapper.selectByExample(example);
    }

	public long delClassify(int id) {
		
		long t = mapper.deleteByPrimaryKey(id);
		if(t > 0){
			ProductClassifyExample example = new ProductClassifyExample();
			example.or().andRootIdEqualTo(id);
			mapper.deleteByExample(example);
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
			return mapper.insert(p);
		}else{
			ProductClassifyExample example = new ProductClassifyExample();
			example.or().andIdEqualTo(id).andRootIdEqualTo(0);
			p = mapper.selectByExample(example).get(0);
			
			p.setName(name);
			if(!StringUtils.isBlank(imgs)){
				p.setHgImage(imgs);
			}
			p.setFlag("1");
			return mapper.updateByPrimaryKeySelective(p);
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
			return mapper.insert(p);
		}else{
			p = mapper.selectByPrimaryKey(id);
			p.setName(name);
			p.setHgImage("");
			p.setFlag("1");
			p.setRootId(root_id);
			return mapper.updateByPrimaryKeySelective(p);
		}
	}



}
