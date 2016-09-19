package cn.com.eship.controller;

import cn.com.eship.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by simon on 16/9/19.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @RequestMapping("/regionList")
    public void regionList(HttpServletResponse response) throws Exception {
        response.getOutputStream().write(commonService.makeRegionListJson().getBytes("utf-8"));
    }

    @RequestMapping("/epidemicNameList")
    public void epidemicNameList(HttpServletResponse response) throws Exception {
        response.getOutputStream().write(commonService.makeEpidemicNameListJson().getBytes("utf-8"));
    }
}
