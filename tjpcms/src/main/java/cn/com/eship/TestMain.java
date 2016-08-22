package cn.com.eship;

import cn.com.eship.dao.EpidemicAppearDao;
import cn.com.eship.dao.impl.EpidemicAppearDaoImpl;
import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.service.EpidemicService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        getDirectoryFromHdfs();
    }

    private static void getDirectoryFromHdfs() throws FileNotFoundException, IOException {
        String dst = "hdfs://sandbox.hortonworks.com:8020";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FileStatus fileList[] = fs.listStatus(new Path(dst));
        int size = fileList.length;
        for (int i = 0; i < size; i++) {
            System.out.println("name:" + fileList[i].getPath().getName() + "/t/tsize:" + fileList[i].getLen());
        }
        fs.close();
    }
}
