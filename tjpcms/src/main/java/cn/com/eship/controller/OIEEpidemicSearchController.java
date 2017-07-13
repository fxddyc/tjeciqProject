package cn.com.eship.controller;

import cn.com.eship.service.OIEEpidemicSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/oieEpidemicSearch")
public class OIEEpidemicSearchController {
    @Autowired
    private OIEEpidemicSearchService oieEpidemicSearchService;

    @RequestMapping("/toOIEEpidemicSearchPage")
    public String toOIEEpidemicSearchPage() {
        return "OIEEpidemicSearch";
    }

    @RequestMapping("/toOIEDetailPage")
    public String toOIEDetailPage() {
        return "oieDetailPage";
    }

    @RequestMapping("/regionList")
    public void regionList(HttpServletResponse response) throws Exception {
        response.getOutputStream().write(oieEpidemicSearchService.makeRegionListJson().getBytes("utf-8"));

    }

    @RequestMapping("/epidemicNameList")
    public void epidemicNameList(HttpServletResponse response) throws Exception {
        response.getOutputStream().write(oieEpidemicSearchService.makeEpidemicNameListJson().getBytes("utf-8"));
    }

    @RequestMapping("/epidemicKindList")
    public void epidemicKindList(HttpServletResponse response) throws Exception {
        response.getOutputStream().write(oieEpidemicSearchService.makeEpidemicKindListJson().getBytes("utf-8"));
    }

    @RequestMapping("/epidemicEventList")
    public void epidemicEventList(String pageNo, String epidemicName, String region,String epidemicClass, String startDate, String endDate,String interval, HttpServletResponse response) throws Exception {
        response.getOutputStream().write(oieEpidemicSearchService.makeEpidemicEventListJson(pageNo,epidemicName,region,epidemicClass,startDate,endDate,interval).getBytes("utf-8"));
    }

    @RequestMapping("epidemicSource")
    public void epidemicSource(String rowKey, HttpServletResponse response) throws Exception {
        response.getOutputStream().write(oieEpidemicSearchService.makeEpidemicSourceJson(rowKey).getBytes("utf-8"));
    }

    @RequestMapping("epidemicRecentOutbreakRegion")
    public void findRecentOutbreakRegion(int interval,HttpServletResponse response) throws Exception {
        response.getOutputStream().write(oieEpidemicSearchService.makeEpidemicRecentOutbreakRegionJson(interval).getBytes("utf-8"));
    }


}
