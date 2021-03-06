package com.mfq.admin.web.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.bean.example.ProductClassifyExample;
import com.mfq.admin.web.bean.example.ProductExample;
import com.mfq.admin.web.bean.example.ProductImgExample;
import com.mfq.admin.web.constants.ProductImageType;
import com.mfq.admin.web.dao.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    ProductClassifyMapper productClassifyMapper;
    @Resource
    HomeClassifyService homeClassifyService;
    @Resource
    ProductImgMapper productImgMapper;
    @Resource
    ActivityMapper activityMapper;
    @Resource
    ProductImageService productImageService;
    @Resource
    ProductDetailNewMapper productDetailNewMapper;


    private static final Logger logger = LoggerFactory
            .getLogger(SellService.class);

    long PageSize = 50;

    public void buildEditModel(Long id, Model model) {
        Product item = null;
        ProductDetailNew detail = null;

        if (id == null || id == 0) {
            item = new Product();
            detail = new ProductDetailNew();
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
            ProductDetailNewExample example = new ProductDetailNewExample();
            example.or().andPidEqualTo(id.intValue());
            List<ProductDetailNew> details = productDetailNewMapper.selectByExampleWithBLOBs(example);
            if(details.size()>0) {
                detail = details.get(0);
            }

        }


        model.addAttribute("item", item); // 产品
        model.addAttribute("detail", detail); // 产品详情
        model.addAttribute("types", ProductType.values());



        List<Hospital> hospitals = hospitalService.findAll(); // 医院
        model.addAttribute("hospitals", hospitals);

        ProductImgExample productImgExample = new ProductImgExample();
        productImgExample.createCriteria().andPidEqualTo(id);
        productImgExample.setOrderByClause(" `index` ");
        List<ProductImg> imgs = productImgMapper.selectByExample(productImgExample);

        model.addAttribute("item_img", imgs);//产品主要图片
        productImageService.getImages(model,id);

        ProductClassifyExample productClassifyExample = new ProductClassifyExample();
        List<ProductClassify> classify = productClassifyMapper.selectByExample(productClassifyExample);
        model.addAttribute("classify", classify); // 分类

        long classId = 0, rootId = 0;
        long hospitalId = 0;
        if (classify.size() > 0) {
            classId = classify.get(0).getId();
        }
        if (hospitals.size() > 0) {
            hospitals.get(0).getId();
        }
        logger.info("item {}", item);
        if (item != null && item.getId() != null && item.getId() > 0) {
            classId = item.getTid();
            hospitalId = item.getHospitalId();
        }

        for(ProductClassify c:classify){
            if(c.getId() == classId){
                if(c.getRootId() == 0){
                    rootId = c.getId();

                }else {
                    rootId = c.getRootId();
                }

            }
        }

        List<HomeClassify> hclasses = homeClassifyService.queryAll();
        model.addAttribute("rootId",rootId);
        model.addAttribute("classId", classId);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("cityId", item.getCityId());
        model.addAttribute("type2", item.getType2());
        model.addAttribute("homeclass", hclasses);

        //加入分期情况
        List<ProFqRecord> proFqRecords = productService.findProFqRecordByPid(item.getId());
        model.addAttribute("fqs",proFqRecords);
    }





    public void buildEditModel_old(Long id, Model model) {
        Product item = null;
        ProductDetailNew detail = null;

        if (id == null || id == 0) {
            item = new Product();
            detail = new ProductDetailNew();
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
//            ProductDetailNewExample example = new ProductDetailNewExample();
//            example.or().andPidEqualTo(id.intValue());
//            List<ProductDetailNew> details = productDetailNewMapper.selectByExampleWithBLOBs(example);
//            if(details.size()>0) {
//                detail = details.get(0);
//            }
            ProductDetail p_detail = productService.findDetailByPid(id);
            model.addAttribute("detail",p_detail);

        }


        model.addAttribute("item", item); // 产品
//        model.addAttribute("detail", detail); // 产品详情
        model.addAttribute("types", ProductType.values());



        List<Hospital> hospitals = hospitalService.findAll(); // 医院
        model.addAttribute("hospitals", hospitals);

        ProductImgExample productImgExample = new ProductImgExample();
        productImgExample.createCriteria().andPidEqualTo(id);
        productImgExample.setOrderByClause(" `index` ");
        List<ProductImg> imgs = productImgMapper.selectByExample(productImgExample);

        model.addAttribute("item_img", imgs);//产品主要图片
        productImageService.getImages(model,id);

        ProductClassifyExample productClassifyExample = new ProductClassifyExample();
        List<ProductClassify> classify = productClassifyMapper.selectByExample(productClassifyExample);
        model.addAttribute("classify", classify); // 分类

        long classId = 0, rootId = 0;
        long hospitalId = 0;
        if (classify.size() > 0) {
            classId = classify.get(0).getId();
        }
        if (hospitals.size() > 0) {
            hospitals.get(0).getId();
        }
        logger.info("item {}", item);
        if (item != null && item.getId() != null && item.getId() > 0) {
            classId = item.getTid();
            hospitalId = item.getHospitalId();
        }

        for(ProductClassify c:classify){
            if(c.getId() == classId){
                if(c.getRootId() == 0){
                    rootId = c.getId();

                }else {
                    rootId = c.getRootId();
                }

            }
        }

        List<HomeClassify> hclasses = homeClassifyService.queryAll();
        model.addAttribute("rootId",rootId);
        model.addAttribute("classId", classId);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("cityId", item.getCityId());
        model.addAttribute("type2", item.getType2());
        model.addAttribute("homeclass", hclasses);

        //加入分期情况
        List<ProFqRecord> proFqRecords = productService.findProFqRecordByPid(item.getId());
        model.addAttribute("fqs",proFqRecords);
    }

    /**
     * 查找产品
     *
     * @param page
     * @param orderno
     * @param proname
     * @param hosname
     * @param orderby 例如: "id desc" , "price desc"
     * @param model
     */
    public void findByPage(long page, String orderno, String proname, String hosname, String orderby, Model model, String online) {
        long start = (page - 1) * PageSize;
        // 商品列表

        //List<Product> items = productService.findByPage(start, PageSize);
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria or = productExample.createCriteria();
        or.andFlagNotEqualTo(-1);
        if (StringUtils.isNotBlank(proname)) {
            or.andNameLike("%" + proname + "%");
        }
        if (StringUtils.isNotBlank(online)) {
            if (online.equals("true")) {
                or.andOnlineEqualTo(1);
            }else if(online.equals("false")){
                or.andOnlineEqualTo(0);
            }
        }

        if (StringUtils.isNotBlank(hosname)) {
            //如果医院不是null的话,通过医院名称把医院id查出来,然后当做条件查询产品
            HospitalExample hospitalExample = new HospitalExample();
            hospitalExample.or().andNameLike("%" + hosname + "%");
            List<Hospital> hospitalsByName = hospitalMapper.selectByExample(hospitalExample);
            if (hospitalsByName == null || hospitalsByName.size() == 0) {
                return;
            }
            List<Long> hosIds = new ArrayList<>();
            for (Hospital hospital : hospitalsByName) {
                hosIds.add(hospital.getId());
            }
            or.andHospitalIdIn(hosIds).andFlagNotEqualTo(-1);
        }
        List<Product> items = productMapper.findByPageAndExample(start, PageSize, productExample, orderby);

        model.addAttribute("items", items);
        model.addAttribute("hosname", hosname);
        model.addAttribute("proname", proname);
        model.addAttribute("orderby", orderby);
        model.addAttribute("online",online);

        ProductClassifyExample productClassifyExample = new ProductClassifyExample();
        List<ProductClassify> classify = productClassifyMapper.selectByExample(productClassifyExample);
        model.addAttribute("classify", classify);

        List<Hospital> hospitals = hospitalService.findAll(); // 医院
        model.addAttribute("hospitals", hospitals);

        long count = productMapper.findByPageAndExampleCount(productExample);
        model.addAttribute("itemcount", count);

        model.addAttribute("page", page);
    }

    public static void main(String[] args) {
        Date date = DateUtil.convertLong("2016-03-04 14:04:40");
    }

    @Transactional
    public Product saveItem(int fq, String type2, Long id, String name,int rootId, int classify, int type, int cityId,
            int hospitalId, BigDecimal price, BigDecimal marketPrice, BigDecimal onlinePay,
            BigDecimal hospitalPay, Date dateStart, Date dateEnd, int flag,
            String img, boolean isOnline, long totalNum, long remainNum, long saleNum, long viewNum,
            String consumeStep, String reserve, String specialNote,
            String body, String cureMeans, String cureDur, int cureHospital, String recoverDur, String merit, String cureMethod, String crowd, String tabooCrowd, String warning,
                            String cureNum, String anesMethod, String doctorLevel, String cureCycle , float fq_price) throws Exception {

        // 维护商品
        Product p = null;
        ProductDetail d = null;
        ProductType productType = ProductType.fromId(type);
        if(productType == null){
        	productType = ProductType.NORMAL;
        }
        if (id != null && id > 0) {
            p = productService.findById(id);
            if(classify == 0){
                classify = rootId;
            }

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
            p.setOrderNo(1);
            d = new ProductDetail();
            p = setProductByParam(p, fq, type2, name, classify, productType, cityId, hospitalId, dateStart,
                    dateEnd, flag, img, isOnline, totalNum, remainNum, saleNum, viewNum, price, marketPrice,
                    onlinePay, hospitalPay);
            long re = productService.insertProduct(p);


            d = setProductDetailByParam(d, p.getId(), consumeStep, reserve,
                    specialNote, body,cureMeans, cureDur, cureHospital, recoverDur, merit, cureMethod, crowd, tabooCrowd, warning, cureNum, anesMethod, doctorLevel, cureCycle);
            productService.insertDetail(d);

        }
        productService.addProFqRecord(p.getId().intValue(),fq_price);

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
        }else {
            p.setImg(null);
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

    @Transactional
	public long saveProductImg(long id, String[] imgs) throws Exception{
		if(id < 1){
			return 0;
		}

        ProductImgExample example = new ProductImgExample();
        ProductImgExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(id);
        example.setOrderByClause(" `index`,`id` ");
        List<ProductImg> pimgs = productImgMapper.selectByExample(example);

        if(pimgs.size() > 0){

            for(int i=0;i<imgs.length;i++){

                ProductImg img = new ProductImg();

                if(pimgs.size()>i){
                   img = pimgs.get(i);
                }

                img.setPid(id);
                img.setImg(imgs[i]);
                img.setDesc("");
                img.setIndex(i);
                img.setFlag(0);

                if(pimgs.size()>i){
                    ProductImgExample exp = new ProductImgExample();
                    ProductImgExample.Criteria c = exp.createCriteria().andIdEqualTo(img.getId());
                    if(!"".equals(img.getImg())) {
                        productImgMapper.updateByExampleSelective(img, exp);
                    }

                }else {


                    long result = productImgMapper.insert(img);
                    if (result > 0) {
                        System.out.println("insert img " + i);
                    }
                }
            }


        }else {

            for (int i=0;i<imgs.length;i++) {
                ProductImg pimg = new ProductImg();
                pimg.setPid(id);
                pimg.setImg(imgs[i]);
                pimg.setDesc("");
                pimg.setIndex(i);
                pimg.setFlag(0);
                logger.info("pid:"+id+"   img"+imgs[i]);
                long result = productImgMapper.insert(pimg);
                if (result > 0) {
                    System.out.println("insert img " + i);
                }
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


    public void saveOnline(String name,Date begin,Date end , String smallImg,
                           String bigImg,String pids,Integer isEnd) throws Exception{

        Activity activity = new Activity(null,name,smallImg,bigImg,pids,begin,end,1,isEnd);
        activityMapper.insertSelective(activity);
    }
    public void updateOnline(Integer id ,String name,Date begin,Date end , String smallImg,
                             String bigImg,String pids,Integer isEnd){

        Activity activity = new Activity(id,name,smallImg,bigImg,pids,begin,end,1,isEnd);
        activityMapper.updateByPrimaryKeySelective(activity);
    }


    public void saveOffline(String name,Date begin,Date end , String smallImg,
                            String link,String place ,String time,Integer isEnd){

        Activity activity = new Activity(null,name,smallImg,link,2,begin,end,time,place,isEnd);
        activityMapper.insertSelective(activity);
    }
    public void updateOffline(Integer id ,String name,Date begin,Date end , String smallImg,
                              String link,String place ,String time,Integer isEnd){

        Activity activity = new Activity(id,name,smallImg,link,2,begin,end,time,place,isEnd);
        activityMapper.updateByPrimaryKeySelective(activity);
    }


    public List<Activity> selectActivity(Integer page,String name,Integer isOnline){
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            criteria.andActivityNameLike("%"+name+"%");
        }
        if(isOnline != 0){
            criteria.andTypeEqualTo(isOnline);
        }

        List<Activity> list = activityMapper.selectByExample(example);
        return list;
    }


    public Activity selectActivityById(Integer id)  {
        return activityMapper.selectByPrimaryKey(id);
    }


    @Transactional
    public void saveProImages(Long pid,String[] proImages,String before,String after,String beautiful,
                              String surgery,String[] detail,String square) throws Exception{
        saveProductImg(pid,proImages);

        productImageService.saveOrInsert(pid,before, ProductImageType.BEFORE);
        productImageService.saveOrInsert(pid,after, ProductImageType.AFTER);
        productImageService.saveOrInsert(pid,beautiful, ProductImageType.BEAUTIFUL);
        productImageService.saveOrInsert(pid,surgery, ProductImageType.SURGERY);
        productImageService.saveOrInsert(pid,square, ProductImageType.SQUARE);
        productImageService.saveDetails(pid,detail);

    }

}