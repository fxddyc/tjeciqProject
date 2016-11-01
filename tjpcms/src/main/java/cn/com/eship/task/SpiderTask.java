package cn.com.eship.task;

import cn.com.eship.model.Queue;
import cn.com.eship.model.Spiders;
import cn.com.eship.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 2016/10/30.
 */
@Component
public class SpiderTask {
    @Autowired
    private Queue queue;
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
            queue.getTaskQueue().add(map);
        }
    }
}
