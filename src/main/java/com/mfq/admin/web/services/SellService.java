package com.mfq.admin.web.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.bean.example.HospitalExample;
import com.mfq.admin.web.bean.example.ProductClassifyExample;
import com.mfq.admin.web.bean.example.ProductExample;
import com.mfq.admin.web.bean.example.ProductImgExample;
import com.mfq.admin.web.dao.HospitalMapper;
import com.mfq.admin.web.dao.ProductClassifyMapper;
import com.mfq.admin.web.dao.ProductImgMapper;
import com.mfq.admin.web.dao.ProductMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.mfq.admin.web.constants.PFlag;
import com.mfq.admin.web.constants.ProductType;
import com.mfq.admin.web.utils.DateUtil;

@Service
public class SellService {

    @Resource
    ProductService productService;
    @Resource
    ProductMapper productMapper;
    @Resource
    HospitalService hospitalService;
    @Resource
    HospitalMapper hospitalMapper;
    @Resource
    ProductClassifyMapper classifyMapper;
    @Resource
    HomeClassifyService homeClassifyService; 
    @Resource
    ProductImgMapper productImgMapper;

    long PageSize = 50;

    public void buildEditModel(Long id, Model model) {
        Product item = null;
        ProductDetail detail = null;
        if (id == null || id == 0) {
            item = new Product();
            detail = new ProductDetail();
            item.setTotalNum(0l);
            item.setViewNum(0l);
            item.setSaleNum(0l);
            item.setFlag(PFlag.DEFAULT.getValue());
            item.setImg("");
            item.setOnline(false);
            item.setDateStart(new Date()); // 默认开始日期
            item.setDateEnd(DateUtil.addYear(new Date(), 1)); // 默认结束日期－有效期一年
        } else {
            item = productService.findById(id);
            detail = productService.findDetailByPid(id);
        }
        model.addAttribute("item", item); // 产品
        model.addAttribute("detail", detail); // 产品详情
        
        model.addAttribute("types", ProductType.values());

        ProductClassifyExample productClassifyExample = new ProductClassifyExample();
        List<ProductClassify> classify = classifyMapper.selectByExample(productClassifyExample);
        model.addAttribute("classify", classify); // 分类

        List<Hospital> hospitals = hospitalService.findAll(); // 医院
        model.addAttribute("hospitals", hospitals);

        ProductImgExample productImgExample = new ProductImgExample();
        List<ProductImg> imgs = productImgMapper.selectByExample(productImgExample);
        
        model.addAttribute("item_img", imgs);
        
        long classId = 0; 
        long hospitalId = 0; 
        if(classify.size() > 0){
        	classId = classify.get(0).getId();
        }
        if(hospitals.size() > 0){
        	hospitals.get(0).getId();
        }
        if (item != null && item.getId() > 0) {
            classId = item.getTid();
            hospitalId = item.getHospitalId();
        }
        
        List<HomeClassify> hclasses = homeClassifyService.queryAll();
        model.addAttribute("classId", classId);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("cityId", item.getCityId());
        model.addAttribute("type2" ,item.getType2());
        model.addAttribute("homeclass", hclasses);
    }

    /**
     * 查找产品
     * @param page
     * @param orderno
     * @param proname
     * @param hosname
     * @param orderby   例如: "id desc" , "price desc"
     * @param model
     */
    public void findByPage(long page,String orderno,String proname,String hosname, String orderby, Model model) {
        long start = (page - 1) * PageSize;
        // 商品列表

        //List<Product> items = productService.findByPage(start, PageSize);
        ProductExample productExample = new ProductExample();
        if(StringUtils.isNotBlank(proname)){
            productExample.or().andNameLike("%"+proname+"%");
        }
        if(StringUtils.isNotBlank(hosname)){
            //如果医院不是null的话,通过医院名称把医院id查出来,然后当做条件查询产品
            HospitalExample hospitalExample = new HospitalExample();
            hospitalExample.or().andNameLike("%"+hosname+"%");
            List<Hospital> hospitalsByName = hospitalMapper.selectByExample(hospitalExample);
            if(hospitalsByName == null || hospitalsByName.size() == 0){
                return ;
            }
            List<Long> hosIds  = new ArrayList<>();
            for (Hospital hospital : hospitalsByName) {
                hosIds.add(hospital.getId());
            }
            productExample.or().andHospitalIdIn(hosIds);
        }
        List<Product> items = productMapper.findByPageAndExample(start,PageSize,productExample,orderby);

        model.addAttribute("items", items);
        model.addAttribute("hosname", hosname);
        model.addAttribute("proname", proname);
        model.addAttribute("orderby", orderby);

        ProductClassifyExample productClassifyExample = new ProductClassifyExample();
        List<ProductClassify> classify = classifyMapper.selectByExample(productClassifyExample);
        model.addAttribute("classify", classify);

        List<Hospital> hospitals = hospitalService.findAll(); // 医院
        model.addAttribute("hospitals", hospitals);

        long count = productMapper.findByPageAndExampleCount(productExample);
        model.addAttribute("itemcount", count);

        model.addAttribute("page", page);
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        ProductMapper productMapper = ac.getBean(ProductMapper.class);
        long start = 0;
        long PageSize = 50;
        ProductExample productExample = new ProductExample();
        productExample.or().andNameLike("%"+"针"+"%");
        String orderby = "id desc";
        List<Product> items = productMapper.findByPageAndExample(start,PageSize,productExample,orderby);
        for (Product item : items) {
            System.out.println(item);
        }
    }

    @Transactional
    public Product saveItem(int fq, String type2, Long id, String name, int classify, int type, int cityId,
            int hospitalId, BigDecimal price, BigDecimal marketPrice, BigDecimal onlinePay,
            BigDecimal hospitalPay, Date dateStart, Date dateEnd, int flag,
            String img, boolean isOnline, long totalNum, long remainNum, long saleNum, long viewNum,
            String consumeStep, String reserve, String specialNote,
            String body, String cureMeans, String cureDur, int cureHospital, String recoverDur, String merit, String cureMethod, String crowd, String tabooCrowd, String warning,
                            String cureNum, String anesMethod, String doctorLevel, String cureCycle) {
        // 维护商品
        Product p = null;
        ProductDetail d = null;
        ProductType productType = ProductType.fromId(type);
        if(productType == null){
        	productType = ProductType.NORMAL;
        }
        if (id != null && id > 0) {
            p = productService.findById(id);
            d = productService.findDetailByPid(id);
            p = setProductByParam(p, fq, type2, name, classify, productType, cityId, hospitalId, dateStart,
                    dateEnd, flag, img, isOnline, totalNum, remainNum, saleNum, viewNum, price, marketPrice,
                    onlinePay, hospitalPay);
            productService.updateProduct(p);
            d = setProductDetailByParam(d, id, consumeStep, reserve, specialNote,
                    body, cureMeans, cureDur, cureHospital, recoverDur, merit, cureMethod, crowd, tabooCrowd, warning, cureNum, anesMethod, doctorLevel, cureCycle);
            productService.updateOne(d);
        } else {
            p = new Product();
            d = new ProductDetail();
            p = setProductByParam(p, fq, type2, name, classify, productType, cityId, hospitalId, dateStart,
                    dateEnd, flag, img, isOnline, totalNum, remainNum, saleNum, viewNum, price, marketPrice,
                    onlinePay, hospitalPay);
            productService.insertProduct(p);
            d = setProductDetailByParam(d, p.getId(), consumeStep, reserve,
                    specialNote, body,cureMeans, cureDur, cureHospital, recoverDur, merit, cureMethod, crowd, tabooCrowd, warning, cureNum, anesMethod, doctorLevel, cureCycle);
            productService.insertDetail(d);
        }
        return p;
    }

    private Product setProductByParam(Product p, int fq, String type2, String name, int classify, ProductType type,
            int cityId, int hospitalId, Date dateStart, Date dateEnd, int flag,
            String img, boolean isOnline, long totalNum, long remainNum, long saleNum, long viewNum,
            BigDecimal price, BigDecimal marketPrice, BigDecimal onlinePay, BigDecimal hospitalPay) {
        p.setName(name);
        p.setType2(type2);
        p.setTid(classify);
        p.setCityId(cityId);
        p.setType(type);
        if(fq == 1){
        	p.setIsFq(true);
        }else{
        	p.setIsFq(false);
        }
        p.setHospitalId((long)hospitalId);
        p.setPrice(price);
        p.setMarketPrice(marketPrice);
        p.setDateStart(dateStart);
        p.setDateEnd(dateEnd);
        p.setTotalNum(totalNum);
        p.setRemainNum(remainNum);
        p.setFlag(flag);
        p.setOnlinePay(onlinePay);
        p.setHospitalPay(hospitalPay);
        if (StringUtils.isNotBlank(img)) {
            p.setImg(img);
        }else{
            p.setImg("");
        }
        p.setOnline(isOnline);
        p.setSaleNum(saleNum);
        p.setViewNum(viewNum);
        p.setpPrice(new BigDecimal(0));
        p.setpNum(0);
        p.setUpdatedAt(new Date());
        
        return p;
    }

    private ProductDetail setProductDetailByParam(ProductDetail d, long pid,
            String consumeStep, String reserve, String specialNote,
            String body, String cureMeans, String cureDur, int cureHospital, String recoverDur, String merit, String cureMethod, String crowd, String tabooCrowd, String warning,
                                         String cureNum, String anesMethod, String doctorLevel, String cureCycle) {
    	if(d == null)
    		d = new ProductDetail();
        d.setPid(pid);
        d.setConsumeStep(consumeStep);
        d.setReserve(reserve);
        d.setSpecialNote(specialNote);
        d.setBody(body);
        d.setCureMeans(cureMeans);
        d.setCureDur(cureDur);
        if(cureHospital == 1){
        	d.setCureHospital("需要");
        }else{
        	d.setCureHospital("不需要");
        }
        
        d.setRecoverDur(recoverDur);
        d.setMerit(merit);
        d.setCureMethod(cureMethod);
        d.setCrowd(crowd);
        d.setTabooCrowd(tabooCrowd);
        d.setWarning(warning);
        d.setCureNum(cureNum);
        d.setAnesMethod(anesMethod);
        d.setDoctorLevel(doctorLevel);
        d.setCureCycle(cureCycle);
        
        return d;
    }

	public long saveProductImg(long id, String[] imgs) {
		if(id < 1){
			return 0;
		}
		if(imgs.length > 0){
			if(!"".equals(imgs[0])){
				productImgMapper.deleteByPrimaryKey(id);
			}
		}
		
		for(String img: imgs){
			ProductImg pimg=new ProductImg();
			pimg.setPid(id);
			pimg.setImg(img);
			if("".equals(img))
				continue;			
			long i = productImgMapper.insert(pimg);
			if(i>0){
				System.out.println("insert img "+i);
			}
		}
		return 1;
	}

	public long del(Long id) {
		if(id < 1){
			return 0;
		}
		long result = productService.deleteProduct(id);
		return result;
		
	}
}