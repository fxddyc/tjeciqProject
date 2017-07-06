package cn.com.eship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/oieWorldRegion")
public class OIEWorldRegionMapController {

    @RequestMapping("/toOIEWorldRegionPage")
    public String toDailyReportPage() {
        return "OIEWorldRegionMap";
    }

}
