package cn.com.eship.controller;

import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.model.Region;
import cn.com.eship.service.IndexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by simon on 16/7/14.
 */
@Controller
@RequestMapping("index")
public class IndexController {
    private final Logger logger = Logger.getLogger(IndexController.class);
    @Autowired
    private IndexService indexService;

    @RequestMapping("indexPage")
    public String indexPage() {
        return "index";
    }

    /**
     * 全球疫情Top10
     *
     * @param response
     */
    @RequestMapping("epidemicTopTen")
    public void epidemicTopTen(HttpServletResponse response) {
        EpidemicAppear epidemicAppear = new EpidemicAppear();
        try {
            response.getOutputStream().write(indexService.makeEpidemicTopTenJson(epidemicAppear).getBytes("utf-8"));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    /**
     * 中国疫情TOP10
     * @param regionName
     * @param response
     */
    @RequestMapping("epidemicLocalTopTen")
    public void epidemicLocalTopTen(String regionName,HttpServletResponse response){
        EpidemicAppear epidemicAppear = new EpidemicAppear();
        epidemicAppear.setRegion(new Region());
        epidemicAppear.getRegion().setRegionCn(regionName);
        try {
            response.getOutputStream().write(indexService.makeEpidemicLocalTopTenJson(epidemicAppear).getBytes("utf-8"));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
