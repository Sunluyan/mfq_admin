package com.mfq.admin.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuzhiguo1 on 16/1/28.
 */
@Controller
public class AnalyseController {

    private static final Logger logger = LoggerFactory.getLogger(AnalyseController.class);

    @RequestMapping(value = "/analyse/user/",method = {RequestMethod.GET})
    public String user(Model model){
        System.out.println("motherfucker");
        return "/analyse/user";
    }

}
