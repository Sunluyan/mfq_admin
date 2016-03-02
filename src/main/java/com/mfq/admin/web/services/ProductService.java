package com.mfq.admin.web.services;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import com.mfq.admin.web.bean.ProFqRecord;
import com.mfq.admin.web.bean.ProFqRecordExample;
import com.mfq.admin.web.bean.Product;
import com.mfq.admin.web.bean.ProductDetail;
import com.mfq.admin.web.bean.example.ProductDetailExample;
import com.mfq.admin.web.bean.example.ProductExample;
import com.mfq.admin.web.dao.ProFqRecordMapper;
import com.mfq.admin.web.dao.ProductDetailMapper;
import com.mfq.admin.web.dao.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    @Resource
    ProductMapper mapper;
    
    @Resource
    ProductDetailMapper productDetailMapper;
    @Resource
    ProFqRecordMapper proFqRecordMapper;
    
    public long insertProduct(Product model){
        return mapper.insert(model);
    }
    
    public long findCount() {
        return mapper.countByExample(new ProductExample());
    }
    
    public Product findById(long id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<Product> findByIds(List<Long> ids){
        ProductExample example = new ProductExample();
        example.or().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<Product> findByPage(long start, long pagesize){
        return mapper.findByPage(start, pagesize);
    }

    public void updateProduct(Product model){
        mapper.updateByPrimaryKey(model);
    }
    
    public long deleteProduct(long id){
        Product p = mapper.selectByPrimaryKey(id);
        p.setFlag(-1);  // flag -1 表示该产品已删除
        return mapper.updateByPrimaryKey(p);
    }
    

    public ProductDetail findDetailByPid(long pid){
        ProductDetailExample example = new ProductDetailExample();
        example.or().andPidEqualTo(pid);
        ProductDetail pDetail = productDetailMapper.selectByExample(example).get(0);

        ProductDetail detail = productDetailMapper.selectByExampleWithBLOBs(example).get(0);

        pDetail.setBody(detail.getBody());
        pDetail.setCureMethod(detail.getCureMethod());
        pDetail.setTabooCrowd(detail.getTabooCrowd());
        pDetail.setWarning(detail.getWarning());
        pDetail.setCrowd(detail.getCrowd());
        pDetail.setMerit(detail.getMerit());
        return pDetail;
    }
    
    public long insertDetail(ProductDetail model){
        return productDetailMapper.insert(model);
    }
    
    public void updateOne(ProductDetail model){
    	if(model.getPid() < 1){
    		return;
    	}

    	ProductDetail d  = findDetailByPid(model.getPid());
    	if(d == null ){
    		insertDetail(model);			 
    	}else{
            ProductDetailExample example = new ProductDetailExample();
            example.or().andPidEqualTo(model.getPid());
            productDetailMapper.updateByExampleSelective(model,example);
    	}
    }

    public List<Product> findByHidAndName(Long hid, String pname) {

        List<Product> data = Lists.newArrayList();
        
        String name = "%"+pname+"%";
        
        ProductExample example = new ProductExample();
        
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike(name);
        
        if(hid != null && hid!=0){
        
        	criteria.andHospitalIdEqualTo(hid);
        	
        }
        
        data = mapper.selectByExample(example);
        return data;
    }

    public List<ProFqRecord> findProFqRecordByPid(Long pid){
        ProFqRecordExample example = new ProFqRecordExample();
        if(pid == null || pid == 0){
            return new ArrayList<ProFqRecord>();
        }
        example.or().andPidEqualTo(pid.intValue());
        return proFqRecordMapper.selectByExample(example);
    }

    @Transactional
    public void addProFqRecord(Integer pid, Float periodPay) throws Exception{
        ProFqRecordExample example = new ProFqRecordExample();
        example.or().andPidEqualTo(pid);
        List<ProFqRecord> list = proFqRecordMapper.selectByExample(example);
        if(list.size() == 0){//如果没有的话就添加
            ProFqRecord record = new ProFqRecord();
            record.setPid(pid);
            record.setPeriod(0);
            record.setPeriodPay(periodPay);
            int i = proFqRecordMapper.insertSelective(record);
            if(i != 1){
                throw new Exception("插入出错!");
            }
        }else{
            ProFqRecord record = list.get(0);
            record.setPeriodPay(periodPay);
            int i = proFqRecordMapper.updateByPrimaryKey(record);
            if(i != 1){
                throw new Exception("更新出错");
            }
        }


    }
}
