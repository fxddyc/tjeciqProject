package cn.com.eship.controller;

import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.service.EpidemicService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

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

    /**
     * 疫情列表
     *
     * @param pageNo
     * @param response
     * @throws Exception
     */
    @RequestMapping("epidemicList")
    public void epidemicList(String pageNo, HttpServletResponse response) throws Exception {
        response.getOutputStream().write(epidemicService.makeEpidemicAppearListJson(pageNo).getBytes("utf-8"));
    }

    @ResponseBody
    @RequestMapping("epidemicNameList")
    public void epidemicNameList(String keyword, HttpServletResponse response) throws Exception {
        if (keyword != null && !"".equals(keyword)) {
            List<Object> list = epidemicService.getEpidemicNameList(keyword);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(list);
            response.getOutputStream().write(jsonArray.toString().getBytes("utf-8"));
        }
    }

    @ResponseBody
    @RequestMapping("epidemicRegionList")
    public void epidemicRegionList(String keyword, HttpServletResponse response) throws Exception {
        if (keyword != null && !"".equals(keyword)) {
            List<Object> list = epidemicService.getEpidemicRegionList(keyword);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(list);
            response.getOutputStream().write(jsonArray.toString().getBytes("utf-8"));
        }
    }

    @RequestMapping("epidemicDetail")
    public String epidemicDetail(String epidemicAppearId, Model model) throws Exception {
        model.addAttribute("epidemicAppear",epidemicService.getEpidemicAppearById(epidemicAppearId));
        return "epidemicDetail";
    }
}
