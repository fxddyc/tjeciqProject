package cn.com.eship.controller;

import cn.com.eship.service.EpidemicBaikeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by simon on 16/7/15.
 */
@Controller
@RequestMapping("epidemicBaike")
public class EpidemicBaikeController {
    private Logger logger = Logger.getLogger(EpidemicBaikeController.class);
    @Autowired
    private EpidemicBaikeService epidemicBaikeService;

    @RequestMapping("epidemicBaikePage")
    public String epidemicBaikePage(Model model) throws Exception {
        model.addAttribute("baikeList", epidemicBaikeService.findAllepidemicBaike());
        return "epidemicBaike";
    }

    @RequestMapping("fetchBaikeInfo")
    public void fetchBaikeInfo(String id, HttpServletResponse response) throws Exception {
        response.getOutputStream().write(epidemicBaikeService.makeBaikeInfoJson(id).getBytes());
    }
}
