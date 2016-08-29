package cn.com.eship.bean;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * Created by simon on 16/8/23.
 */
public class HbaseConfigrationFactory {

    private String hbaseZookeeperQuorum;
    private String zookeeperZnodeParent;
    private String hbaseZookeeperPropertyClientPort;
    private String hbaseClusterDistributed;


    public String getHbaseZookeeperQuorum() {
        return hbaseZookeeperQuorum;
    }

    public void setHbaseZookeeperQuorum(String hbaseZookeeperQuorum) {
        this.hbaseZookeeperQuorum = hbaseZookeeperQuorum;
    }

    public String getZookeeperZnodeParent() {
        return zookeeperZnodeParent;
    }

    public void setZookeeperZnodeParent(String zookeeperZnodeParent) {
        this.zookeeperZnodeParent = zookeeperZnodeParent;
    }

    public String getHbaseZookeeperPropertyClientPort() {
        return hbaseZookeeperPropertyClientPort;
    }

    public void setHbaseZookeeperPropertyClientPort(String hbaseZookeeperPropertyClientPort) {
        this.hbaseZookeeperPropertyClientPort = hbaseZookeeperPropertyClientPort;
    }

    public String getHbaseClusterDistributed() {
        return hbaseClusterDistributed;
    }

    public void setHbaseClusterDistributed(String hbaseClusterDistributed) {
        this.hbaseClusterDistributed = hbaseClusterDistributed;
    }


    public Configuration getConfigration() {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", hbaseZookeeperQuorum);
        config.set("zookeeper.znode.parent", zookeeperZnodeParent);
        config.set("hbase.zookeeper.property.clientPort", hbaseZookeeperPropertyClientPort);
        config.set("hbase.cluster.distributed", hbaseClusterDistributed);
        return config;
    }


}
