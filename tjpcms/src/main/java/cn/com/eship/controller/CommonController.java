package cn.com.eship.controller;

import cn.com.eship.model.Queue;
import cn.com.eship.model.Spiders;
import cn.com.eship.service.CommonService;
import cn.com.eship.service.SpiderService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/9/19.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SpiderService spiderService;
    @Autowired
    private Queue queue;

    @RequestMapping("/regionList")
    public void regionList(HttpServletResponse response) throws Exception {
        response.getOutputStream().write(commonService.makeRegionListJson().getBytes("utf-8"));
    }

    @RequestMapping("/epidemicNameList")
    public void epidemicNameList(HttpServletResponse response) throws Exception {
        response.getOutputStream().write(commonService.makeEpidemicNameListJson().getBytes("utf-8"));
    }

    @RequestMapping("/kindNameList")
    public void kindNameList(HttpServletResponse response) throws Exception {
        response.getOutputStream().write(commonService.makekindWordsListJson().getBytes("utf-8"));
    }

    @RequestMapping("/pollTask")
    public void pollTask(HttpServletResponse response) throws Exception {
        Object obj = queue.getTaskQueue().poll();
        if (obj != null) {
            response.getOutputStream().write(new ObjectMapper().writeValueAsString((Map<String, String>) obj).getBytes("utf-8"));

        } else {
            response.getOutputStream().write("None".getBytes("utf-8"));

        }

    }

    @RequestMapping("/pollUrl")
    public void pollUrl(HttpServletResponse response) throws Exception {

        Object obj = queue.getUrlQueue().poll();
        if (obj != null) {
            response.getOutputStream().write(new ObjectMapper().writeValueAsString((Map<String, String>) obj).getBytes("utf-8"));

        } else {
            response.getOutputStream().write("None".getBytes("utf-8"));

        }


    }

    @RequestMapping("/pullUrl")
    public void pullUrl(String urlJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // url titleXpath contentXpath
        Map<String, String> map = objectMapper.readValue(urlJson, Map.class);
        queue.getUrlQueue().add(map);
    }

    @RequestMapping("/test1")
    public void test1() throws Exception {
        List<Spiders> spidersList = spiderService.findSpidersList("");
        for (Spiders spiders : spidersList){
            Map<String,String> map = new HashMap<String, String>();
            map.put("startUrl",spiders.getStartUrl());
            map.put("titleXpath",spiders.getTitleXpath());
            map.put("pageUrlXpath",spiders.getPageUrlXpath());
            map.put("contentXpath",spiders.getContentXpath());
            map.put("fetchUrlXpath",spiders.getFetchUrlXpath());
            queue.getTaskQueue().add(map);

        }
        System.out.println(queue.getUrlQueue());
        System.out.println(queue.getTaskQueue());
    }

    @RequestMapping("/test2")
    public void test2() throws Exception {
        System.out.println(queue.getUrlQueue());
        System.out.println(queue.getTaskQueue());
    }
}
