package cn.com.eship;


import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        //test3();
        test5();
    }

    /**
     * insert one date in hbase
     *
     * @throws Exception
     */
    public static void test1() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Table table = connection.getTable(TableName.valueOf("epidemicInfo"));
        HColumnDescriptor[] hColumnDescriptors = table.getTableDescriptor().getColumnFamilies();
        for (HColumnDescriptor hColumnDescriptor : hColumnDescriptors) {
            System.out.println(hColumnDescriptor.getNameAsString());
        }
        Put put = new Put("http://www.aqsiq.gov.cn/xxgk_13386/tsxx/yqts/201608/t20160822_472656.htm".getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "content".getBytes("utf-8"), ("<tbody><tr>\n" +
                "        <td class='niandai'><span>疫情预警</span></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "        <td class='border'><div class='sj_h'><h1>西尼罗热疫情</h1>\n" +
                "        </div>\n" +
                "        <div class='xj2'>2016-08-22</div>\n" +
                "        <div class='zh' style='width:870px; margin:0 auto;line-height:140%;'>\n" +
                "        <div class='TRS_Editor'><div class='TRS_PreAppend' style='overflow-x: hidden; word-break: break-all'>\n" +
                "        <p class='MsoNormal' style='line-height: 24pt; text-indent: 24.1pt'><b><span style='font-family: 宋体; font-size: 12pt'>罗马尼亚</span></b><span style='font-family: 宋体; font-size: 12pt'>：<span lang='EN-US'>8</span>月<span lang='EN-US'>7</span>日，罗马尼亚卫生部通报当地近<span lang='EN-US'>4</span>年来的第<span lang='EN-US'>1</span>例西尼罗热相关死亡病例，为一名<span lang='EN-US'>79</span>岁的女性。</span></p>\n" +
                "        <p class='MsoNormal' style='line-height: 24pt; text-indent: 24pt'><span style='font-family: 宋体; font-size: 12pt'>截至<span lang='EN-US'>2016</span>年<span lang='EN-US'>8</span>月<span lang='EN-US'>4</span>日， 在欧盟成员国和<span lang='EN-US'>22</span>个邻近国家已报告<span lang='EN-US'>5</span>例西尼罗热病例。</span></p>\n" +
                "        <p class='MsoNormal' style='line-height: 24pt; text-indent: 24.1pt'><b><span style='font-family: 宋体; font-size: 12pt'>美国</span></b><span style='font-family: 宋体; font-size: 12pt'>：美国科罗拉多州西尼罗热病例在<span lang='EN-US'>2016</span>年急剧增加，人类、动物和蚊子体内均有检出。截至目前，已报告<span lang='EN-US'>13</span>例西尼罗热病例，其中包括<span lang='EN-US'>1</span>例死亡病例；阿拉巴马州莫比尔县报告今年首例西尼罗热病例。</span></p>\n" +
                "        <p class='MsoNormal' style='line-height: 24pt; text-indent: 24pt'><span style='font-family: 宋体; font-size: 12pt'>（来源：<span lang='EN-US'>Outbreak news today </span>、<span lang='EN-US'>ECDC<span>&nbsp; </span></span>深圳局、广西局、福建局报送）</span></p>\n" +
                "        </div></div>\n" +
                "        </div></td>\n" +
                "        </tr>\n" +
                "        <tr><td>\n" +
                "        <table width='100%' border='0' cellspacing='0' cellpadding='0'>\n" +
                "        <tbody><tr>\n" +
                "        <td width='10%' align='center' style='font-size:14px;font-weight:bold;color:#006699'>相关附件：</td>\n" +
                "        <td align='left' style='font-size:14px;font-weight:bold;color:#006699'></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "        <td><hr color='#cccccc'></td>\n" +
                "        <td><hr color='#cccccc'></td>\n" +
                "        </tr>\n" +
                "        </tbody></table>\n" +
                "        </td></tr>\n" +
                "        </tbody>").getBytes("utf-8"));
        //date
        put.addColumn("c1".getBytes("utf-8"), "time".getBytes("utf-8"), "2016-08-22".getBytes("utf-8"));
        //标题
        put.addColumn("c1".getBytes("utf-8"), "titel".getBytes("utf-8"), "西尼罗热疫情".getBytes("utf-8"));
        //疫情名称
        put.addColumn("c1".getBytes("utf-8"), "epidemicName".getBytes("utf-8"), "西尼罗热疫情".getBytes("utf-8"));
        table.put(put);
        table.close();

    }

    /**
     * get some cell value from hbase
     *
     * @throws Exception
     */
    public static void test2() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Table table = connection.getTable(TableName.valueOf("epidemicInfo"));
        Get get = new Get("http://www.aqsiq.gov.cn/xxgk_13386/tsxx/yqts/201608/t20160822_472656.htm".getBytes("utf-8"));
        Result result = table.get(get);
        List<Cell> cellList = result.getColumnCells("c1".getBytes("utf-8"), "content".getBytes("utf-8"));
        for (Cell cell : cellList) {
            System.out.println(cell);
        }
        table.close();
    }


    /**
     * 百度百科建表
     *
     * @throws Exception
     */
    public static void test3() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Admin admin = connection.getAdmin();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("epidemicBaike"));
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("c1");
        hTableDescriptor.addFamily(hColumnDescriptor);
        admin.createTable(hTableDescriptor);
        admin.close();
    }

    public static void test4() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Table table = connection.getTable(TableName.valueOf("epidemicBaike"));
        Put put = new Put("1".getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "imgUrl".getBytes("utf-8"), "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1772238410,3442357385&fm=58".getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "content".getBytes("utf-8"), ("<div class='lemma-summary' label-module='lemmaSummary'>\n" +
                "<div class='para' label-module='para'>霍乱是因摄入的食物或水受到霍乱弧菌污染而引起的一种急性腹泻性传染病。每年，估计有300万～500万霍乱病例，另有10万～12万人死亡。病发高峰期在夏季，能在数小时内造成腹泻脱水甚至死亡。</div><div class='para' label-module='para'>霍乱是由霍乱弧菌所引起的。O1和O139这两种霍乱弧菌的血清型能够引起疾病暴发。大多数的疾病暴发由O1型霍乱弧菌引起，而1992年首次在孟加拉国确定的O139型仅限于东南亚一带。非O1非O139霍乱弧菌可引起轻度腹泻，但不会造成疾病流行。最近，在亚洲和非洲的一些地区发现了新的变异菌株。据观察认为，这些菌株可引起更为严重的霍乱疾病，死亡率更高。</div><div class='para' label-module='para'>霍乱弧菌存在于水中，最常见的感染原因是食用被患者粪便污染过的水。霍乱弧菌能产生霍乱毒素，造成分泌性腹泻，即使不再进食也会不断腹泻，洗米水状的粪便是霍乱的特征。</div>\n" +
                "</div>").getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "epidemicName".getBytes("utf-8"), ("霍乱").getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "contentUrl".getBytes("utf-8"), ("http://baike.baidu.com/link?url=nAX-BzmPQiBBlQOgiyjDXbh1OJq40bO9gzUUjnb1Gsp79TGdIv9fILxYg52hNQTVGTO4XWugoQalgZHtz_8iE_").getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "summary".getBytes("utf-8"), ("霍乱是因摄入的食物或水受到霍乱弧菌污染而引起的一种急性腹泻性传染病。每年，估计有300万～500万霍乱病例，另有10万～12万人死亡。病发高峰期在夏季，能在数小时内造成腹泻脱水甚至死亡。\n" +
                "霍乱是由霍乱弧菌所引起的。O1和O139这两种霍乱弧菌的血清型能够引起疾病暴发。大多数的疾病暴发由O1型霍乱弧菌引起，而1992年首次在孟加拉国确定的O139型仅限于东南亚一带。非O1非O139霍乱弧菌可引起轻度腹泻，但不会造成疾病流行。最近，在亚洲和非洲的一些地区发现了新的变异菌株。据观察认为，这些菌株可引起更为严重的霍乱疾病，死亡率更高。\n" +
                "霍乱弧菌存在于水中，最常见的感染原因是食用被患者粪便污染过的水。霍乱弧菌能产生霍乱毒素，造成分泌性腹泻，即使不再进食也会不断腹泻，洗米水状的粪便是霍乱的特征").getBytes("utf-8"));
        table.put(put);
        table.close();
    }


    /**
     * 百度百科建表
     *
     * @throws Exception
     */
    public static void test5() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Table table = connection.getTable(TableName.valueOf("epidemicBaike"));
        Scan scan = new Scan();
        ResultScanner resultScanner = table.getScanner(scan);
        System.out.println(new String(resultScanner.next().getRow(),"utf-8"));

    }
}


