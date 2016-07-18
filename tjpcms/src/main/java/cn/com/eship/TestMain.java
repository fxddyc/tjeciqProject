package cn.com.eship;

import cn.com.eship.dao.EpidemicAppearDao;
import cn.com.eship.dao.impl.EpidemicAppearDaoImpl;
import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.service.EpidemicService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        EpidemicAppearDao epidemicAppearDao = applicationContext.getBean(EpidemicAppearDaoImpl.class);
        System.out.println(epidemicAppearDao.findEpidemicAppearCount());
    }
}
