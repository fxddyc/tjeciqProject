package cn.com.eship.controller;

import cn.com.eship.model.Queue;
import cn.com.eship.model.Spiders;
import cn.com.eship.service.CommonService;
import cn.com.eship.service.SpiderService;
import cn.com.eship.utils.ConfigUtils;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.UUID;

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


    @RequestMapping("/test1")
    public void test1() throws Exception {
        List<Spiders> spidersList = spiderService.findSpidersList("");
        for (Spiders spiders : spidersList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("startUrl", spiders.getStartUrl());
            map.put("titleXpath", spiders.getTitleXpath());
            map.put("pageUrlXpath", spiders.getPageUrlXpath());
            map.put("contentXpath", spiders.getContentXpath());
            map.put("fetchUrlXpath", spiders.getFetchUrlXpath());
            String key = UUID.randomUUID().toString().replace("-", "");
            ProducerConfig config = new ProducerConfig(ConfigUtils.readProperties("produre.properties"));
            Producer<String, String> producer = new Producer<String, String>(config);
            String msg = "";
            try {
                msg = new org.apache.htrace.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("spidertask", key, msg);
            producer.send(data);
        }
    }
}
