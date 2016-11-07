package cn.com.eship.task;

import cn.com.eship.model.Queue;
import cn.com.eship.model.Spiders;
import cn.com.eship.service.SpiderService;
import cn.com.eship.utils.ConfigUtils;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by simon on 2016/10/30.
 */
@Component
public class SpiderTask {
    @Autowired
    private SpiderService spiderService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void job1() throws Exception {
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
