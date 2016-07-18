package cn.com.eship.controller;

import cn.com.eship.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by simon on 16/7/16.
 */
@Controller
@RequestMapping("epidemic")
public class EpidemicController {
    @Autowired
    private EpidemicService epidemicService;

    @RequestMapping("epidemicListPage")
    public String epidemicListPage() {
        return "epidemicList";

    }

    @RequestMapping("epidemicList")
    public void epidemicList(String pageNo,HttpServletResponse response) throws Exception {
        response.getOutputStream().write(epidemicService.makeEpidemicAppearListJson(pageNo).getBytes("utf-8"));
    }
}
