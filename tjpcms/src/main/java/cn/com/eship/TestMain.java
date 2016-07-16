package cn.com.eship;

import cn.com.eship.service.EpidemicBaikeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        EpidemicBaikeService epidemicBaikeService = applicationContext.getBean(EpidemicBaikeService.class);
        epidemicBaikeService.makeBaikeInfoJson("11");

    }
}
