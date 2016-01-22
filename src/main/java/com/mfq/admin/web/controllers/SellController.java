package com.mfq.admin.web.controllers;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.admin.web.bean.Product;
import com.mfq.admin.web.services.ProductClassifyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mfq.admin.web.services.QiniuManipulater;
import com.mfq.admin.web.services.SellService;
import com.mfq.admin.web.utils.DateUtil;
import com.mfq.admin.web.utils.JSONUtil;

/**
 * 权限控制功能
 */
@Controller
public class SellController extends BaseController {

    private static final Logger logger = LoggerFactory
            .getLogger(SellController.class);

    @Resource
    SellService sellService;
    @Resource
    ProductClassifyService classifyService;

    /**
     * 商品管理
     * orderno , proname , hosname , page , orderby(time , price , type)
     * @param model
     * @return
     */
    @RequestMapping(value = "/sell/items/", method = {RequestMethod.GET,RequestMethod.POST})
    public String index(
            @RequestParam(defaultValue = "1", required = false) long page,
            @RequestParam(defaultValue = "" , required = false) String orderno,
            @RequestParam(defaultValue = "" , required = false) String proname,
            @RequestParam(defaultValue = "" , required = false) String hosname,
            @RequestParam(defaultValue = "id desc" , required = false) String orderby,
            @RequestParam(defaultValue = "motherfucker",required = false) String online,
            Model model) {
    	try{

            sellService.findByPage(page,orderno,proname,hosname,orderby,model,online);
    	}catch(Exception e){
    		logger.info("sell items error {}",e);
    	}
        return "/sell/items";
    }

    @RequestMapping(value = "/sell/item/add/", method = RequestMethod.GET)
    public String add(Model model) {

        Long id = 0L;
        sellService.buildEditModel(id, model);
        model.addAttribute("t", 0);  //状态添加
        return "/sell/item_edit";
    }
    
    @RequestMapping(value = "/sell/item/delete/", method = RequestMethod.GET)
    public String del(
    		@RequestParam(defaultValue = "0", required = false) Long id,
    		Model model){
    	long r = sellService.del(id);
    	if(r>0){
    		logger.info("r delete success id={}", id);
    	}else{
    		logger.info("r delete error id={}", id);
    	}
    	return "redirect:/sell/items/";
    }

    @RequestMapping(value = "/sell/item/edit/", method = RequestMethod.GET)
    public String edit(
            @RequestParam(defaultValue = "0", required = false) Long id,
            Model model) {
    	try{
	        sellService.buildEditModel(id, model);
	        model.addAttribute("t", 1);  //状态添加
	        return "/sell/item_edit";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "/sell/item_edit";
    }
    
    @RequestMapping(value = "/sell/upload/img/", method = { RequestMethod.POST })
    @ResponseBody
    public String uploadExcel(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "file_data") MultipartFile file,
    		Model model){
    	String ret = "";
    	try {
    		String fileName= file.getOriginalFilename();
    		if (!file.isEmpty()) {
                File tmpFile = new File("/tmp/" + fileName);
                file.transferTo(tmpFile);
            } else { // 判断是否需要更新img
            	return JSONUtil.toJson(1002, "文件空！！", null);
            }
    		ret = JSONUtil.successResultJson(fileName);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return ret;
    }

    /**
     * 获取分类   ajax
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = {"/sell/classify/","/sell/classify"}, method = {RequestMethod.GET, RequestMethod.POST},produces = "application/json;charset=utf-8")
    public @ResponseBody String getClassify(Model model,HttpServletRequest request){
        String ret = "";
        try {
            ret = classifyService.findClassify();

        }catch (Exception e){
            logger.error("classify data is error {}", e);
        }
        logger.info("sell classify ret is {}", ret);
        return ret;
    }

    @RequestMapping(value = "/sell/item/", method = RequestMethod.POST)
    public String saveItem(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "files") MultipartFile[] file,
            @RequestParam(value = "classify", required = true) int rootId,
            @RequestParam(value = "classify2", required = true) int classifyId,
            @RequestParam(value = "type", required = true) int type,
            @RequestParam(value = "type2", required = true) String type2,
            @RequestParam(value = "city_id", required = true) int cityId,
            @RequestParam(value = "hospital", required = true) int hospitalId,
            @RequestParam(value = "price", required = true) BigDecimal price,
            @RequestParam(value = "market_price", required = true) BigDecimal marketPrice,
            @RequestParam(value = "online_pay", defaultValue = "0") BigDecimal onlinePay,
            @RequestParam(value = "dateStart", required = true) String dateStart,
            @RequestParam(value = "dateEnd", required = true) String dateEnd,
            @RequestParam(value = "flag", defaultValue = "0") int flag,
            @RequestParam(value = "is_online", defaultValue = "false") boolean isOnline,
            @RequestParam(value = "total_num", defaultValue = "0") long totalNum,
            @RequestParam(value = "sale_num", defaultValue = "0") long saleNum,
            @RequestParam(value = "view_num", defaultValue = "0") long viewNum,
            @RequestParam(value = "consume_step", defaultValue = "") String consumeStep,
            @RequestParam(value = "reserve", defaultValue = "") String reserve,
            @RequestParam(value = "special_note", defaultValue = "") String specialNote,
            @RequestParam(value = "cure_means", defaultValue = "") String cureMeans,
            @RequestParam(value = "cure_dur", defaultValue = "") String cureDur,
            @RequestParam(value = "cure_hospital", defaultValue = "") int cureHospital,
            @RequestParam(value = "recover_dur", defaultValue = "") String recoverDur,
            @RequestParam(value = "merit", defaultValue = "") String merit,
            @RequestParam(value = "cure_method", defaultValue = "") String cureMethod,
            @RequestParam(value = "crowd", defaultValue = "") String crowd,
            @RequestParam(value = "taboo_crowd", defaultValue = "") String tabooCrowd,
            @RequestParam(value = "warnings", defaultValue = "") String warning,
            @RequestParam(value = "cure_num", defaultValue = "") String cureNum,
            @RequestParam(value = "anes_method", defaultValue = "") String anesMethod,
            @RequestParam(value = "doctor_level", defaultValue = "") String doctorLevel,
            @RequestParam(value = "cure_cycle", defaultValue = "") String cureCycle,
            @RequestParam(value = "fq", required = true) int fq,
            Model model) {

        Date start = DateUtil.convertYYYYMMDD(dateStart);
        Date end = DateUtil.convertYYYYMMDD(dateEnd);

        String body = "";
        try {
            String [] imgs  = {"", "", "", ""};
            for(int i=0;i<file.length;i++){
            	if (!file[i].isEmpty()) {
                    File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
                    file[i].transferTo(tmpFile);
                    imgs[i] = QiniuManipulater.qiniuUploadProdImg(tmpFile);
                    
                }else {
                	imgs[i] = "";
                }
            }

            BigDecimal hospitalPay = price.subtract(onlinePay);
            Product p = sellService.saveItem(fq, type2,id, name, rootId,classifyId, type, cityId,
                    hospitalId, price, marketPrice, onlinePay, hospitalPay, start, end, flag,
                    StringUtils.isNotBlank(imgs[0])?imgs[0]:null, isOnline, totalNum, totalNum, saleNum, viewNum, consumeStep, reserve,
                    specialNote, body, cureMeans, cureDur, cureHospital, recoverDur, merit, cureMethod, crowd, tabooCrowd, warning, cureNum, anesMethod, doctorLevel, cureCycle);
            
            if(!"".equals(imgs[0])){
            	sellService.saveProductImg(p.getId(), imgs);
            }
            
            logger.info("save item ok! pid={}, p={}", p == null ? 0 : p.getId(),
                    p);
            
            return "redirect:/sell/items/";
        } catch (Exception e) {
            logger.error("save item error", e);
        }

        model.addAttribute("msg", "上传产品图片异常！");
        sellService.buildEditModel(id, model);

        return "/sell/item_edit";
    }
}