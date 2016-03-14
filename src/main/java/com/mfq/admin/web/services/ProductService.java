package com.mfq.admin.web.services;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.bean.example.ProductDetailExample;
import com.mfq.admin.web.bean.example.ProductExample;
import com.mfq.admin.web.constants.ProductType;
import com.mfq.admin.web.dao.ProFqRecordMapper;
import com.mfq.admin.web.dao.ProductDetailMapper;
import com.mfq.admin.web.dao.ProductMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
            return new ArrayList<>();
        }
        example.or().andPidEqualTo(pid.intValue());
        return proFqRecordMapper.selectByExample(example);
    }

    /**
     * 添加分期价
     * @param pid
     * @param periodPay
     * @throws Exception
     */
    @Transactional
    public void addProFqRecord(Integer pid, Float periodPay) throws Exception{
        if(periodPay == null || periodPay.equals(0) || periodPay == 0){
            return;
        }
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

    /**
     * 添加产品
     Long id, String name, Integer tid, Long hospitalId, Integer cityId,
     Integer flag, String img,  ProductType type, String type2, Boolean isFq,
     BigDecimal price, BigDecimal marketPrice,  Date dateStart, Date dateEnd,
     Long totalNum , Boolean online, Date createdAt, Date updatedAt
     */
    public Integer addPro(String name, Integer tid, Long hosId, Integer cityId, Integer flag,
                          String img, Boolean isFq, BigDecimal price, BigDecimal marketPrice,
                          Date start, Date end,Long totalNum,int type,String type2,
                          Boolean isOnline) throws Exception{
        Product product = new Product(null,name,tid,hosId,cityId,flag,img, ProductType.fromId(type),type2,isFq,price,marketPrice,
                start,end,totalNum,isOnline,new Date(),new Date());
        Integer count = mapper.insertSelective(product);
        if(count != 1){
            throw new Exception("添加产品失败");
        }
        return count;
    }

    /**
     * 修改产品
     */
    public Integer editPro(Long id ,String name, Integer tid, Long hosId, Integer cityId, Integer flag,
                           String img, Boolean isFq, BigDecimal price, BigDecimal marketPrice,
                           Date start, Date end,Long totalNum,int type,String type2,
                           Boolean isOnline) throws Exception{
        Product product = new Product(id,name,tid,hosId,cityId,flag,img, ProductType.fromId(type),type2,isFq,price,marketPrice,
                start,end,totalNum,isOnline,null,new Date());
        Integer count = mapper.updateByPrimaryKeySelective(product);
        if(count != 1){
            throw new Exception("修改产品失败");
        }
        return count;
    }


    /**
     * 添加产品详情
     Integer id, Integer pid, String description, String preferential, String attention,
     Integer flag, String ask

     */
    public Product addProDetail(Integer pid ,String desc,String preferential,String attention,
                                String[] questions,String[] answers) throws Exception{

        String ask = "";
        if(questions.length != answers.length){
            throw new Exception("问答的长度不同");
        }
        List<Object> list = new ArrayList<>();
        for (int i = 0;i<questions.length;i++) {
            Map<String,Object> map= new HashMap<>();
            map.put("question",questions[i]);
            map.put("answer",answers[i]);
            list.add(map);
        }
        JSONArray jsonArray = new JSONArray(list);
        ask = jsonArray.toString();
        System.out.println(ask);

        ProductDetailNew productDetailNew = new ProductDetailNew(null,pid,desc,preferential,attention,
                null,ask);
        return null;
    }


    /**
     * 修改产品详情
     */
    public Product editProDetail(Product product){

        return null;
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        ProductService productService = ac.getBean(ProductService.class);
        String[] questions = {"问题1","问题2","问题3"};
        String[] answer = {"回答1","回答2","回答3"};
        productService.addProDetail(null,null,null,null,questions,answer);
    }

}
