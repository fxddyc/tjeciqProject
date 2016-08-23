package cn.com.eship;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;


/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
	public static void main(String[] args) throws Exception {
		Configuration config = HBaseConfiguration.create();
	       config.set("hbase.zookeeper.quorum",  
                    "sandbox.hortonworks.com");  
        config.set("hbase.zookeeper.property.clientPort", "2181");  
        config.set("hbase.cluster.distributed", "true");
        HBaseAdmin hAdmin = new HBaseAdmin(config);
        TableName[] tableNames = hAdmin.listTableNames();
        System.out.println(tableNames);
        
	}
}
