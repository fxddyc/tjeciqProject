package cn.com.eship.service.impl;

import cn.com.eship.dao.EpidemicBaikeDao;
import cn.com.eship.model.Baidubaike;
import cn.com.eship.service.EpidemicBaikeService;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/7/15.
 */
@Service
public class EpidemicBaikeServiceImpl implements EpidemicBaikeService {
    @Autowired
    private EpidemicBaikeDao epidemicBaikeDao;

    @Override
    public List<Baidubaike> findAllepidemicBaike() throws Exception {
        return epidemicBaikeDao.findAllEpidemicBaike();
    }

    @Override
    public String makeBaikeInfoJson(String id) throws Exception {
        Map<String, String> jsonMap = new HashMap<String, String>();
        Baidubaike baidubaike = epidemicBaikeDao.findBaidubaikeById(id);
        String url = baidubaike.getPagUrl();
        HttpClient httpClient = new HttpClient();
        List<Header> headers = new ArrayList<Header>();
        headers.add(new Header("Host", "baike.baidu.com"));
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
        headers.add(new Header("Cookie", "BAIDUID=06180969C593070C215423430968D552:FG=1; BIDUPSID=06180969C593070C215423430968D552; PSTM=1460686855; H_PS_PSSID=1465_19037_18241_20537_17944_20417_17001_15356_12057; pgv_pvi=8988939264; pgv_si=s2613999616"));
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
        GetMethod getMethod = new GetMethod("http://baike.baidu.com/link?" + url.split("\\?")[1].split("&")[0]);
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            // 读取内容
            byte[] responseBody = getMethod.getResponseBody();
            // 处理内容
            String html = new String(responseBody);

            Document document = Jsoup.parse(html);
            jsonMap.put("content", document.getElementsByClass("lemma-summary").first().getElementsByClass("para").first().text());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        return new ObjectMapper().writeValueAsString(jsonMap);
    }
}
