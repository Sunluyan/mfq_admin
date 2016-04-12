package com.mfq.admin.web.controllers;

import java.awt.geom.Area;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.AreaCity;
import com.mfq.admin.web.bean.Hospital;
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

import com.mfq.admin.web.services.CityService;
import com.mfq.admin.web.services.HospitalService;
import com.mfq.admin.web.services.QiniuManipulater;

@Controller
public class HospitalController {

    private static final Logger logger = LoggerFactory
            .getLogger(HospitalController.class);

    @Resource
    HospitalService service;
    @Resource
    CityService cityService;

    /**
     * 医院管理
     *
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "/hospital/list/", method = {RequestMethod.GET, RequestMethod.POST})
    public String hospitalList(
            @RequestParam(defaultValue = "1", required = false) Long page,
            @RequestParam(value = "hosname", defaultValue = "") String hosname,
            @RequestParam(value = "cityid", defaultValue = "0") Integer cityid,
            Model model) {
        if (cityid == 0) {
            cityid = null;
        }
        List<Hospital> list;
        list = service.findByNameAndCity(hosname, cityid);
        //通过数据库查询，得到所有已有citys，并去重
        List<AreaCity> citys = cityService.findAllExistAreaCity();

        model.addAttribute("cityId",cityid);
        model.addAttribute("citys", citys);
        model.addAttribute("hospitals", list);
        model.addAttribute("hosname", hosname);
        model.addAttribute("page", page);

        return "hospital/hospital_list";
    }


    /**
     * 医院信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/hospital/info/", method = {RequestMethod.GET, RequestMethod.POST})
    public String hospitalInfo(
            @RequestParam(value = "id", defaultValue = "0") long hospitalId,
            Model model) {

        Hospital hospital = service.findById(hospitalId);
        model.addAttribute("hospital", hospital);
        return "hospital/hospital_edit";
    }

    /**
     * 医院信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/hospital/edit/", method = {RequestMethod.GET})
    public String hospitalEdit(
            @RequestParam(value = "id", defaultValue = "0") long hospitalId,
            Model model) {

        Hospital hospital = service.findById(hospitalId);
        model.addAttribute("hospital", hospital);
        if(StringUtils.isNotEmpty(hospital.getDetails())){
            String[] details = hospital.getDetails().split(",");
            model.addAttribute("details",details);
        }

        return "hospital/hospital_edit";
    }

    @RequestMapping(value = "/hospital/edit/", method = {RequestMethod.POST})
    public String hospitaltoEdit(
            @RequestParam(value = "id", defaultValue = "0") long hospitalId,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "img_file") MultipartFile imgFile,
            @RequestParam(value = "address", defaultValue = "") String address,
            @RequestParam(value = "desc", defaultValue = "") String desc,
            @RequestParam(value = "cityname", defaultValue = "") String cityname,
            @RequestParam(value = "details", defaultValue = "") MultipartFile[] details,
            Model model) {
        try {

            String img = null;
            if (!imgFile.isEmpty()) {
                File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
                imgFile.transferTo(tmpFile);
                img = QiniuManipulater.qiniuUploadProdImg(tmpFile);
                logger.info("img -is {}", img);
            } else { // 判断是否需要更新img
                img = "";
            }

            String[] detailImgs = new String[details.length];
            //上传图片
            for (int i = 0; i < details.length; i++) {
                File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
                details[i].transferTo(tmpFile);
                if(details[i].getSize() == 0){
                    detailImgs[i] = "";
                }else{
                    detailImgs[i] = QiniuManipulater.qiniuUploadProdImg(tmpFile);
                }
            }




            Hospital hospital = service.saveHospital(hospitalId, name, img, address, desc,detailImgs);
            model.addAttribute("hospital", hospital);
            return "redirect:/hospital/list/";

        } catch (Exception e) {
            logger.error("save item error", e);
        }
        model.addAttribute("msg", "上传产品图片异常！");

        return "redirect:/hospital/list/";
    }

}
