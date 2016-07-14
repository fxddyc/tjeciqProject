package cn.com.eship;

import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.model.Region;
import cn.com.eship.service.IndexService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IndexService indexService = applicationContext.getBean(IndexService.class);
        EpidemicAppear epidemicAppear = new EpidemicAppear();
        epidemicAppear.setRegion(new Region());
        //epidemicAppear.getRegion().setRegionCn("中国");
        System.out.println(indexService.makeEpidemicTopTenJson(epidemicAppear));
    }
}
