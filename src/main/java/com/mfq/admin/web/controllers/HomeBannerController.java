package com.mfq.admin.web.controllers;

import java.io.File;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mfq.admin.web.bean.HomeBanner;
import com.mfq.admin.web.services.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mfq.admin.web.constants.ImageType;
import com.mfq.admin.web.constants.QiniuBucketEnum;

@Controller
public class HomeBannerController {

    private static final Logger logger = LoggerFactory
            .getLogger(HomeBannerController.class);

    @Resource
    HomeBannerService service;
	@Resource
	UserService userService;
	@Resource
	ProductService productService;
	@Resource
	HospitalService hospitalService;
	@Resource
	OrderService orderService;

	@RequestMapping(value="/home/",method = RequestMethod.GET)
	public String home(HttpServletRequest request,Model model) throws Exception {
        model.addAttribute("a",userService.getYesterdayNewDevice());
        model.addAttribute("b",userService.getYesterdayNewUserCount());
        model.addAttribute("c",userService.getYesterdayNewOrder());
        model.addAttribute("d",userService.getYesterdayNewOrderOfPay());
        model.addAttribute("e",userService.getCountHospital());
        model.addAttribute("f",userService.getCountProduct());
        return "/app/home";
	}
    
    @RequestMapping(value = "/home/banner/", method = {RequestMethod.GET })
    public String list(HttpServletRequest request,
    		@RequestParam(value = "mobile", defaultValue="", required = true) String mobile,
    		@RequestParam(value = "page", defaultValue="0", required = true)long page,
    		Model model) {

        try{
            List<HomeBanner> banners = service.queryHomeBanners();
            model.addAttribute("items", banners);
        }catch (Exception e){
            logger.info("home banner is error {}", e);
        }
        return "/app/home_banner";
    }




    @RequestMapping(value = "/home/banner/edit/", method = {RequestMethod.POST })
    public String add(HttpServletRequest request,
                       @RequestParam(value = "file_img", required = true) MultipartFile file_img,
                       @RequestParam(value = "bid", defaultValue = "0", required = true) long id,
                       @RequestParam(value = "name", defaultValue = "", required = true) String name,
                       @RequestParam(value = "pid", defaultValue = "0", required = true) long pid,
                       @RequestParam(value = "type", defaultValue = "0", required = true) int type,
                       @RequestParam(value = "is_url", required = true) int isUrl,
                       @RequestParam(value = "burl", defaultValue="#", required = true)String url,
                       Model model) {

        try{
        	String img = "";
        	if(!file_img.isEmpty()){
        		String file_name = file_img.getOriginalFilename();
        		
        		File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
        		file_img.transferTo(tmpFile);
        		img = QiniuManipulater.qiniuUploadImg(tmpFile , QiniuBucketEnum.IMG2, ImageType.BANNER, file_name);
        	}
        	
        	if(isUrl == 0){
        		url = "#";
        	}
        	long result = service.insertBanner(id, img, name, type, pid,  url);
        	
    		if(result > 0){
    			return "redirect:/home/banner/";
    		}else{
    			model.addAttribute("msg", "添加失败!!");
    			List<HomeBanner> banners = service.queryHomeBanners();
                model.addAttribute("items", banners);
                return "/app/home_banner";
    		}
        }catch (Exception e){
        	model.addAttribute("msg", "添加失败!!");
            logger.info("home banner is error {}", e);
        }
        return "redirect:/home/banner/";
        
    }

    @RequestMapping(value = "/home/banner/del/", method = { RequestMethod.GET })
    public String del(HttpServletRequest request,
    				@RequestParam(value = "id", defaultValue = "0", required = true) long id,
                      Model model) {

        try{
        	if(id > 0){
        		long result = service.deleteBanner(id);
        		if(result > 0){
        			return "redirect:/home/banner/";
        		}else{
        			model.addAttribute("msg", "删除出错!!");
        			List<HomeBanner> banners = service.queryHomeBanners();
                    model.addAttribute("items", banners);
        			return "/app/home_banner";
        		}
        	}
        }catch (Exception e){
        	model.addAttribute("msg", "删除出错!!");
            logger.info("home banner is error {}", e);
        }
        return "/app/home_banner";
    }

    @RequestMapping(value = "/home/banner/edit/", method = { RequestMethod.GET})
    public String edit(HttpServletRequest request,
    		@RequestParam(value = "id", defaultValue = "0") long id,
                      Model model) {
        try{
        	if(id > 0){
        		System.out.println(id);
        		HomeBanner banner = service.queryHomeBanner(id);
        		
        		String img = banner.getImg();
        		String url = banner.getUrl();
        		if(StringUtils.isBlank(url) || "#".equals(url)){
        			url = "";
        			model.addAttribute("is_url", false);
        		}else{
        			model.addAttribute("is_url", true);
        		}
        		model.addAttribute("id", banner.getId());
        		model.addAttribute("img", img);
        		model.addAttribute("url", url);
        		model.addAttribute("type", banner.getpType());
        		model.addAttribute("name", banner.getName());
        		model.addAttribute("pid", banner.getpId());
        	}

        }catch (Exception e){
        	model.addAttribute("msg", "错误！！");
            logger.info("home banner is error {}", e);
        }
        return "/app/edit_banner";
    }





}
