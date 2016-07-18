package cn.com.eship;

import cn.com.eship.service.EpidemicService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        EpidemicService epidemicService = applicationContext.getBean(EpidemicService.class);
        //System.out.println(epidemicService.makeEpidemicAppearListJson());
    }
}
