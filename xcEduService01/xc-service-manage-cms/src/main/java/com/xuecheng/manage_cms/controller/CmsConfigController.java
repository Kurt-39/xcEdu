package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cms/config")
public class CmsConfigController {
    @Autowired
    private CmsConfigService cmsConfigService;
    @ResponseBody
    @RequestMapping("/getmodel/{id}")
    public CmsConfig getModle(@PathVariable("id") String id){
        return cmsConfigService.getConfigById(id);
    }
}
