package cn.com.eship;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
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
	       config.set("zookeeper.znode.parent", "/hbase-unsecure");
        config.set("hbase.zookeeper.property.clientPort", "2181");  
        config.set("hbase.cluster.distributed", "true");
        HTableDescriptor hTableDescriptor = new HTableDescriptor("epidemicInfo");
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("c1");
        hTableDescriptor.addFamily(hColumnDescriptor);
        HBaseAdmin hAdmin = new HBaseAdmin(config);
        hAdmin.createTable(hTableDescriptor);
        
        
        
	}
}
