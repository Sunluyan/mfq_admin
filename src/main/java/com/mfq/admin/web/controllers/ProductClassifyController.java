package com.mfq.admin.web.controllers;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.HomeClassify;
import com.mfq.admin.web.bean.ProductClassify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.mfq.admin.web.services.HomeClassifyService;
import com.mfq.admin.web.services.ProductClassifyService;
import com.mfq.admin.web.services.QiniuManipulater;

@Controller
@RequestMapping("/classes")
public class ProductClassifyController {

    private static final Logger logger = LoggerFactory
            .getLogger(ProductClassifyController.class);

    @Resource
    ProductClassifyService productClassifyService;
    @Resource
    HomeClassifyService homeClassifyService;

    
    @RequestMapping(value = { "/list/2/" }, method = RequestMethod.GET)
	public String show(Model model) {
		String ret = "classes/index1";
		try{
			
			List<ProductClassify> list = productClassifyService.findByLevel(1);
    		
    		List<ProductClassify> data = Lists.newArrayList();
    		for(ProductClassify pc:list){
    			ProductClassify p = productClassifyService.findById(pc.getRootId());
    			if(p == null)
    				continue;
    			pc.setDesp(p.getName());
    			data.add(pc);
    		}
	    	model.addAttribute("data", data);
    		ret = "classes/index2";
    		
		}catch(Exception e){
			logger.info("ProductClasssify show is error {}", e);
		}
		
		return ret;
    }
    
    
    @RequestMapping(value = { "/list/oper","/list/oper/" }, method = RequestMethod.GET)
    public String showOper(Model model){
    	String ret = "";
    	try{
	    	List<HomeClassify> data = homeClassifyService.queryAll();
			model.addAttribute("data", data);
			ret = "classes/index3";
    	}catch(Exception e){
    		logger.info("ProductClasssify  is error {}", e);
    	}
		
		return ret;
    }
    
    @RequestMapping(value = { "/list",
            "/list/" }, method = RequestMethod.GET)
    public String showOne(Model model) {
    	String ret = "classes/index1";
    	try{
	    	
		    	List<ProductClassify> data = productClassifyService.findByLevel(0);
		    	model.addAttribute("data", data);
		    	ret = "classes/index1";

	    }catch(Exception e){
	    	logger.info("product showOne one is error {}",e);
	    }
        return ret;
    }
    
    @RequestMapping(value = {"/del","/del/"}, method = RequestMethod.GET)
    public String del(Model model,
    		@RequestParam(value = "t", required = true) int t,
    		@RequestParam(value = "id", required = true) int id){
    	
        try{
        	if(t == 3){
        		long result = homeClassifyService.delHomeClassify(id);
        		if(result > 0){
        			return "redirect:/classes/list/oper/";
        		}else{
        			model.addAttribute("msg", "删除出错!!");
        			return "classes/list/oper/";
        		}
        	}
        	if(id > 0){
        		long result = productClassifyService.delClassify(id);
        		if(result > 0){
        			if(t ==2 ){
        				return "redirect:/classes/list/2/";
        			}
        			return "redirect:/classes/list/";
        		}else{
        			model.addAttribute("msg", "删除出错!!");
        			return "classes/index1";
        		}
        	}
        }catch (Exception e){
        	model.addAttribute("msg", "删除出错!!");
            logger.info("classes is error {}", e);
        }
        return "classes/index1";
    }

    @RequestMapping(value = { "/edit_f","/edit_f/" }, method = RequestMethod.POST)
    public String doEditO(Model model,
    		@RequestParam(value = "id", defaultValue="0") int rid,
    		@RequestParam(value = "t", defaultValue="0") int t,
    		@RequestParam(value = "name", defaultValue="") String name,
    		@RequestParam(value = "file_img", defaultValue = "") MultipartFile file){
    	
    	String ret = "";
    	try{
    		if(t == 1){
    			String imgs = "";
    			if (!file.isEmpty()) {
                    File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
                    file.transferTo(tmpFile);
                    imgs = QiniuManipulater.qiniuUploadProdImg(tmpFile);
    			}
    			
    			productClassifyService.saveProductClassifyRoot(rid, name, imgs);
    			
    			ret = "redirect:/classes/list/";
    		}
    		
    	}catch(Exception e){
    		logger.info("product do edit one is error {}",e);
    	}
    	return ret;
    	
    }
    
    @RequestMapping(value = { "/edit","/edit/" }, method = RequestMethod.POST)
    public String doEdit(Model model,
    		@RequestParam(value = "id", defaultValue="0") int rid,
    		@RequestParam(value = "t", defaultValue="0") int t,
    		@RequestParam(value = "name", defaultValue="") String name,
    		@RequestParam(value = "root_id", defaultValue = "0")int root_id,
    		@RequestParam(value = "index" , defaultValue = "0") int index){
    	
    	String ret = "";
    	try{
    		if(t == 2){
    			productClassifyService.saveProductClassify(rid, name, root_id);
    			ret = "redirect:/classes/list/2/";
    		}else if(t == 3){
    			homeClassifyService.saveHomeClassify(rid, name, index);
    			
    			ret = "redirect:/classes/list/oper/";
    		}
    		
    	}catch(Exception e){
    		logger.info("product get edit one is error {}",e);
    	}
    	return ret;
    	
    }
    @RequestMapping(value = { "/edit","/edit/" }, method = RequestMethod.GET)
    public String edit(Model model,
    		@RequestParam(value = "id", defaultValue="0") int rid,
    		@RequestParam(value = "t", defaultValue="0") int t){
    	
    	String ret ="";
    	try{
        	if(rid >= 0){
        		if(t == 1){
        			if(rid > 0){
		        		ProductClassify classe = productClassifyService.findById(rid);
		        		
		        		model.addAttribute("id", classe.getId());
		        		model.addAttribute("name", classe.getName());
		        		model.addAttribute("img", classe.getHgImage());
        			}
	        		ret = "classes/edit1";
        		}else if(t == 2){
        			if(rid > 0 ){
	        			ProductClassify classe = productClassifyService.findById(rid);
	        			
	        			model.addAttribute("id", classe.getId());
	        			model.addAttribute("name", classe.getName());
	        			model.addAttribute("root_id", classe.getRootId());
	        			
        			}
        			List<ProductClassify> r = productClassifyService.findByRootId(0);
        			model.addAttribute("roots", r);
        			ret = "classes/edit2";
        		}else if(t == 3){
        			if(rid > 0){
        				HomeClassify classify = homeClassifyService.findById(rid);
        				model.addAttribute("name", classify.getName());
        				model.addAttribute("url", classify.getUrl());
        				model.addAttribute("index", classify.getIndex());
        				model.addAttribute("id", classify.getId());
        			}
        			
        			ret = "classes/edit3";
        		}
        	}

        }catch (Exception e){
        	logger.info("product do edit2 one is error {}",e);
        	model.addAttribute("msg", "错误！！");
            logger.info("classes is error {}", e);
        }
    	return ret;
    }

}