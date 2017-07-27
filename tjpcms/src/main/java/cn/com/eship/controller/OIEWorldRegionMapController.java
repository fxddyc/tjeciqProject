package cn.com.eship.controller;

import cn.com.eship.service.OIEEpidemicSearchService;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/oieWorldRegion")
public class OIEWorldRegionMapController {

    @Autowired
    private OIEEpidemicSearchService oieEpidemicSearchService;

    @RequestMapping("/toOIEWorldRegionPage")
    public String toDailyReportPage() {
        return "OIEWorldRegionMap";
    }

    @RequestMapping("epidemicRecentOutbreakRegion")
    public void findRecentOutbreakRegion(String alertDataInterval,String mapDataInterval,String startDate,String endDate,HttpServletResponse response) throws Exception {
        List<Map<String, Object>> alertList = oieEpidemicSearchService.findAlertListData(alertDataInterval);
        List<Map<String, Object>> mapDataList = oieEpidemicSearchService.findMapListData(mapDataInterval,startDate,endDate);
        Map<String, Object> jsonMap = new HashMap<>();
        if (alertList!=null&&alertList.size() > 0) {
            jsonMap.put("alertList", alertList);
        }
        if (mapDataList!=null&&mapDataList.size()>0){
            jsonMap.put("mapDataList", mapDataList);
        }
        response.getOutputStream().write(new ObjectMapper().writeValueAsString(jsonMap).getBytes("utf-8"));
    }

}
