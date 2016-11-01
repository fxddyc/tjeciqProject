package cn.com.eship;

import cn.com.eship.model.Queue;
import cn.com.eship.model.Spiders;
import cn.com.eship.service.SpiderService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 2016/10/27.
 */
public class KafkaMain {
    public static void main(String[] args) throws Exception {
        KafkaMain kafkaMain = new KafkaMain();
        kafkaMain.test1();
    }


    public void test1() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Queue queue = applicationContext.getBean(Queue.class);
        SpiderService spiderService = applicationContext.getBean(SpiderService.class);
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

}
