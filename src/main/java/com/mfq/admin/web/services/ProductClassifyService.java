package com.mfq.admin.web.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.admin.web.bean.Product;
import com.mfq.admin.web.bean.ProductClassify;
import com.mfq.admin.web.bean.example.ProductClassifyExample;
import com.mfq.admin.web.dao.ProductClassifyMapper;
import com.mfq.admin.web.utils.JSONUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


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
		example.setOrderByClause("root_id desc");
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


	public String findClassify() {
		String ret ;

		List<ProductClassify> list = findByLevel(0);


		List<Map<String,Object>> data= Lists.newArrayList();
		for(ProductClassify classify:list){
			Map<String,Object> map = Maps.newHashMap();
			List<ProductClassify> ls = findByRootId(classify.getId());
			map.put("id",classify.getId());
			map.put("classify", classify);
			map.put("items", ls);

			data.add(map);
		}
		ret = JSONUtil.successResultJson(data);

		return ret;
	}


	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		ProductClassifyService service = ac.getBean(ProductClassifyService.class);
		String ret = service.findClassify();
		System.out.println(ret);
	}
}


class comparator1 implements Comparator
{
	public int compare(Object o1,Object o2)
	{
		ProductClassify classify1 = (ProductClassify)o1;
		ProductClassify classify2 = (ProductClassify)o2;
		if(classify1.getId() == classify2.getId()){
			return 0;
		}
		return -1;
	}
}
