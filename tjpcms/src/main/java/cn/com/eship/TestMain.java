package cn.com.eship;

import cn.com.eship.bean.HbaseConfigrationFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        HBaseAdmin hBaseAdmin = applicationContext.getBean(HBaseAdmin.class);
        for (TableName tableName : hBaseAdmin.listTableNames()) {
            System.out.println(tableName.getNameAsString());
        }


    }
}
