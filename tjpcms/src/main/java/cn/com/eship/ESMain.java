package cn.com.eship;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/9/16.
 */
public class ESMain {
    public static void main(String[] args) throws Exception {
        test2();

    }

    public static void test1() throws Exception {
        // 请求发布在本地 Tomcat上服务
        PostMethod method = new PostMethod("http://localhost:9200/words/wordline/_search");
        try {
            HttpClient client = new HttpClient();

            method.setRequestHeader("Content-type", "application/json; charset=UTF-8");
            method.setRequestHeader("Accept", "application/json; charset=UTF-8");
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            Map<String, Object> existsMap = new HashMap<String, Object>();
            existsMap.put("field", new String("wordsMap.首次".getBytes(), "utf-8"));
            queryMap.put("exists", existsMap);
            //jsonMap.put("query", queryMap);
            method.setRequestBody(new ObjectMapper().writeValueAsString(jsonMap));
            client.executeMethod(method);
            String receive = method.getResponseBodyAsString();
            Map<String, Object> map = new ObjectMapper().readValue(receive, Map.class);
            List<Object> array = new ArrayList<Object>();
            array = (List<Object>) ((Map<String, Object>) map.get("hits")).get("hits");
            for (Object mapTemp : array) {
                Map<String, Object> map1 = (Map<String, Object>) mapTemp;
                Map<String, Object> map2 = (Map<String, Object>) map1.get("_source");
                System.out.println(map2.get("rowKey").toString() + (StringUtils.isNotBlank((String) map2.get("title")) ? map2.get("title").toString() : ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
    }


    /**
     * 分词列表
     *
     * @throws Exception
     */
    public static void test2() throws Exception {
        // 请求发布在本地 Tomcat上服务
        PostMethod method = new PostMethod("http://localhost:9200/words/wordline/_search");
        try {
            HttpClient client = new HttpClient();

            method.setRequestHeader("Content-type", "application/json; charset=UTF-8");
            method.setRequestHeader("Accept", "application/json; charset=UTF-8");
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            Map<String, Object> matchMap = new HashMap<String, Object>();
            matchMap.put("rowKey", "1");
            queryMap.put("match", matchMap);
            jsonMap.put("query", queryMap);
            method.setRequestBody(new ObjectMapper().writeValueAsString(jsonMap));
            client.executeMethod(method);
            String receive = method.getResponseBodyAsString();
            Map<String, Object> map = new ObjectMapper().readValue(receive, Map.class);
            List<Object> array = new ArrayList<Object>();
            array = (List<Object>) ((Map<String, Object>) map.get("hits")).get("hits");
            for (Object mapTemp : array) {
                Map<String, Object> map1 = (Map<String, Object>) mapTemp;
                Map<String, Object> map2 = (Map<String, Object>) map1.get("_source");
                System.out.println(map2.get("wordsMap"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
    }
}
