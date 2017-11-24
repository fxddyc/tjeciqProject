package cn.com.eship.service.impl;


import cn.com.eship.service.DataWarehouseSercice;
import cn.com.eship.utils.ConfigUtils;
import cn.com.eship.utils.PageUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by simon on 16/9/13.
 */
@Service
public class DataWarehouseSerciceImpl implements DataWarehouseSercice {
    @Autowired
    private Connection connection;

    @Override
    public String makeDataWareHouseListJson(String word) throws Exception {
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        // 请求发布在本地 Tomcat上服务
        PostMethod method = new PostMethod(ConfigUtils.readValue("esconfig.properties", "eshost") + "/words/wordline/_search");
        try {
            HttpClient client = new HttpClient();
            method.setRequestHeader("Content-type", "application/json; charset=UTF-8");
            method.setRequestHeader("Accept", "application/json; charset=UTF-8");
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            Map<String, Object> existsMap = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(word)) {
                existsMap.put("field", new String(("wordsMap." + word.trim()).getBytes(), "utf-8"));
                queryMap.put("exists", existsMap);
                jsonMap.put("query", queryMap);
            }

            method.setRequestBody(new ObjectMapper().writeValueAsString(jsonMap));
            client.executeMethod(method);
            String receive = method.getResponseBodyAsString();
            Map<String, Object> map = new ObjectMapper().readValue(receive, Map.class);
            List<Object> array = new ArrayList<Object>();
            array = (List<Object>) ((Map<String, Object>) map.get("hits")).get("hits");
            for (Object mapTemp : array) {
                Map<String, Object> map1 = (Map<String, Object>) mapTemp;
                Map<String, Object> map2 = (Map<String, Object>) map1.get("_source");
                Map<String, Object> resultJsonMap = new HashMap<String, Object>();
                resultJsonMap.put("rowKey", (StringUtils.isNotBlank((String) map2.get("rowKey")) ? map2.get("rowKey").toString() : ""));
                resultJsonMap.put("title", (StringUtils.isNotBlank((String) map2.get("title")) ? map2.get("title").toString() : ""));
                jsonList.add(resultJsonMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return new ObjectMapper().writeValueAsString(jsonList);
    }

    @Override
    public String makeDataWareHouseWordListJson(String rowKey) throws Exception {
        // 请求发布在本地 Tomcat上服务
        Map<String, Object> resultMapJson = new HashMap<String, Object>();
        PostMethod method = new PostMethod(ConfigUtils.readValue("esconfig.properties", "eshost") + "/words/wordline/_search");
        try {
            HttpClient client = new HttpClient();

            method.setRequestHeader("Content-type", "application/json; charset=UTF-8");
            method.setRequestHeader("Accept", "application/json; charset=UTF-8");
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            Map<String, Object> matchMap = new HashMap<String, Object>();
            matchMap.put("rowKey", rowKey);
            queryMap.put("match_phrase", matchMap);
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
                resultMapJson = (Map<String, Object>) map2.get("wordsMap");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }

        return resultMapJson != null ? new ObjectMapper().writeValueAsString(sortMap(resultMapJson)) : "{}";
    }

    @Override
    public String makeDataWareHouseDetailJson(String rowKey) throws Exception {
        Map<String, String> jsonMap = new HashMap<String, String>();
        Table table = connection.getTable(TableName.valueOf("epidemicInfo"));
        //Scan scan = new Scan(rowKey.getBytes("utf-8"));
        Get get = new Get(rowKey.getBytes());
        //scan.addColumn("c1".getBytes(),"epidemicName".getBytes()).addColumn("c1".getBytes(),"imgUrl".getBytes());
        //ResultScanner resultScanner = table.getScanner(scan);
        //Result result = resultScanner.next();
        Result result = table.get(get);
        if (result != null) {
            if (result.getRow() != null && result.getRow().length > 0) {
                jsonMap.put("contentUrl", new String(result.getRow(), "utf-8"));
            } else {
                jsonMap.put("contentUrl", "");
            }

            if (result.getValue("c1".getBytes(), "time".getBytes()) != null && result.getValue("c1".getBytes(), "time".getBytes()).length > 0) {
                jsonMap.put("time", new String(result.getValue("c1".getBytes(), "time".getBytes()), "utf-8"));
            } else {
                jsonMap.put("time", "");
            }

            if (result.getValue("c1".getBytes(), "articleTitle".getBytes()) != null && result.getValue("c1".getBytes(), "articleTitle".getBytes()).length > 0) {
                jsonMap.put("titel", new String(result.getValue("c1".getBytes(), "articleTitle".getBytes()), "utf-8"));
            } else {
                jsonMap.put("titel", "");
            }

            if (result.getValue("c1".getBytes(), "epidemicName".getBytes()) != null && result.getValue("c1".getBytes(), "epidemicName".getBytes()).length > 0) {
                jsonMap.put("epidemicName", new String(result.getValue("c1".getBytes(), "epidemicName".getBytes()), "utf-8"));
            } else {
                jsonMap.put("epidemicName", "");
            }
            if (result.getValue("c1".getBytes(), "content".getBytes()) != null && result.getValue("c1".getBytes(), "content".getBytes()).length > 0) {
                String content = new String(result.getValue("c1".getBytes(), "content".getBytes()), "utf-8");
                jsonMap.put("content", PageUtils.handlerHtml(content));
            } else {
                jsonMap.put("content", "");
            }
        }
        return new ObjectMapper().writeValueAsString(jsonMap);
    }

    public static Map<String, Object> sortMap(Map<String, Object> map){
        Map<String, Object> sortedMap = new LinkedHashMap<String, Object>();
        if (map != null && !map.isEmpty()) {
            List<Map.Entry<String, Object>> entryList = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
            Collections.sort(entryList, new TimesComparator());
            Iterator<Map.Entry<String, Object>> iter = entryList.iterator();
            Map.Entry<String, Object> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }



    static class TimesComparator implements Comparator<Map.Entry<String, Object>> {

        public int compare(Map.Entry<String, Object> o1,
                           Map.Entry<String, Object> o2) {
            return Integer.parseInt(o2.getValue().toString())-Integer.parseInt(o1.getValue().toString());
        }

    }
}

