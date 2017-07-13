package cn.com.eship.service.impl;

import cn.com.eship.dao.OIEEpidemicDao;
import cn.com.eship.service.OIEEpidemicSearchService;
import cn.com.eship.utils.PageUtils;
import cn.com.eship.utils.TimeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OIEEpidemicSearchServiceImpl implements OIEEpidemicSearchService{


    private final OIEEpidemicDao epidemicDao;

    @Autowired
    public OIEEpidemicSearchServiceImpl(OIEEpidemicDao epidemicDao) {
        this.epidemicDao = epidemicDao;
    }

    @Override
    public String makeEpidemicNameListJson() throws Exception {
        return new ObjectMapper().writeValueAsString(epidemicDao.findEpidemicNameList());
    }

    @Override
    public String makeRegionListJson() throws Exception {
        return new ObjectMapper().writeValueAsString(epidemicDao.findEpidemicRegionList());
    }

    @Override
    public String makeEpidemicKindListJson() throws Exception {
        return new ObjectMapper().writeValueAsString(epidemicDao.findEpidemicKindList());
    }

    @Override
    public String makeEpidemicEventListJson(String pageNo, String epidemicName, String region,String epidemicClass, String startDate, String endDate,String interval) throws Exception {
        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> parameMap = new HashMap<>();
        parameMap.put("pageNo", PageUtils.getFirstPosition(StringUtils.isNotBlank(pageNo) ? Integer.parseInt(pageNo) : 0));
        if (StringUtils.isNotBlank(epidemicName)) {
            int eid = epidemicDao.findEpidemicIdByCondition(epidemicName);
            parameMap.put("epidemicId", eid);
        }
        if (StringUtils.isNotBlank(region)) {
            int rid = epidemicDao.findRegionIdByCondition(epidemicName);
            parameMap.put("regionId", rid);
        }
        if (StringUtils.isNotBlank(epidemicClass)) {
            List<Integer> list = epidemicDao.findEpidemicIdListByCondition(epidemicClass);
            parameMap.put("epidemicClass", list);
        }
        if (StringUtils.isNotBlank(startDate)) {
            parameMap.put("startDate", TimeUtils.convertToDateString(startDate));
        }
        if (StringUtils.isNotBlank(endDate)) {
            parameMap.put("endDate", TimeUtils.convertToDateString(endDate));
        }
        if (StringUtils.isNotBlank(interval)) {
            parameMap.put("interval", interval);
        }
        List<Map<String, Object>> epidemicAppearList = epidemicDao.findEpidemicEventList(parameMap);
        if (epidemicAppearList != null && epidemicAppearList.size() > 0) {
            jsonMap.put("epidemicEventList", epidemicAppearList);
            jsonMap.put("epidemicEventListCount", epidemicDao.findTotalRecord(parameMap));
        }
        org.codehaus.jackson.map.ObjectMapper objectMapper = new org.codehaus.jackson.map.ObjectMapper();
        objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return objectMapper.writeValueAsString(jsonMap);
    }

    @Override
    public String makeEpidemicSourceJson(String rowKey) throws Exception {

        return null;
    }

    @Override
    public String makeEpidemicRecentOutbreakRegionJson(int interval) throws Exception {
        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> parameMap = new HashMap<>();
        List<Map<String, Object>> jsonList = new ArrayList<>();
        parameMap.put("interval", interval);
        List<Map<String, Object>> epidemicAppearList = epidemicDao.findEpidemicEventList(parameMap);
        Map<String, Map<String, Object>> hashMap = new HashMap<>();
        if (epidemicAppearList != null && epidemicAppearList.size() > 0) {
            for (Map<String, Object> report : epidemicAppearList) {
                if (report.get("regionNameEng") != null && !"".equals(report.get("regionNameEng"))) {
                    Map<String, Object> map = new HashMap<>();
                    String region = (String) report.get("regionNameEng");
                    int outbreaks = (int) report.get("outbreaks");
                    String eName = (report.get("epidemicNameCn") != null && !"".equals(report.get("epidemicNameCn"))) ? (String) report.get("epidemicNameCn") : (String) report.get("disease");
                    String event = report.get("date") + " " + report.get("regionNameCn") + " 发生 "
                            + report.get("diseaseClass") + " " + eName + " "+ report.get("outbreaks") + "次";
                    if (hashMap.get(region) != null) {
                        map = hashMap.get(region);
                        map.put("name", region);
                        map.put("value", (int) map.get("value") + outbreaks);
                        map.put("data", map.get("data") + "</br>" +event);
                    } else {
                        map.put("name", region);
                        map.put("value", outbreaks);
                        map.put("data", event);
                    }
                    hashMap.put(region,map);
                }
            }
            jsonList.addAll(hashMap.values());
        }
        List<Map<String, Object>> list = findOutbreakRegionWithReason();
        if (jsonList.size() > 0) {
            jsonMap.put("alertList", jsonList);
        }
        if (list!=null&&list.size()>0){
            jsonMap.put("regionList", list);
        }
        return new ObjectMapper().writeValueAsString(jsonMap);
    }

    private List<Map<String, Object>> findOutbreakRegionWithReason() throws Exception {
        Map<String, Object> parameMap = new HashMap<>();
        List<Map<String, Object>> jsonList = new ArrayList<>();
        parameMap.put("interval", 150);
        List<Map<String, Object>> epidemicAppearList = epidemicDao.findEpidemicEventList(parameMap);
        Map<String, Map<String, Object>> jsonMap = new HashMap<>();
        if (epidemicAppearList != null && epidemicAppearList.size() > 0) {
            for (Map<String, Object> report : epidemicAppearList) {
                if (report.get("regionNameEng") != null && !"".equals(report.get("regionNameEng"))) {
                    Map<String, Object> map = new HashMap<>();
                    String region = (String) report.get("regionNameEng");
                    String reason = StringUtils.isNotEmpty((String) report.get("reason"))?(String) report.get("reason"):"";
                    String eName = (report.get("epidemicNameCn") != null && !"".equals(report.get("epidemicNameCn"))) ? (String) report.get("epidemicNameCn") : (String) report.get("disease");
                    String event = report.get("date") + " " + report.get("regionNameCn") + " 发生 "
                            + report.get("diseaseClass") + " " + eName + " "+ report.get("outbreaks") + "次";
                    int level = transformEpidemicEventResion(reason);
                    if (jsonMap.get(region) != null) {
                        map = jsonMap.get(region);
                        map.put("name", region);
                        if (level>(int)map.get("value"))map.put("value",level);
                        map.put("data", map.get("data") + "</br>" +event);
                    } else {
                        map.put("name", region);
                        map.put("value", level);
                        map.put("data", event);
                    }
                    jsonMap.put(region,map);
                }
            }
            jsonList.addAll(jsonMap.values());
        }
        return jsonList;
    }

    private int transformEpidemicEventResion(String reason){
        reason = reason.replaceAll("\\pP|\\pS|\\pZ","").toLowerCase();
        String tenLevel = "First occurrence,First occurrence in the country".replaceAll("\\pP|\\pS|\\pZ","").toLowerCase();
        String sevenLevel = "Change in epidemiology,Emerging disease,New host,New pathogen,New strain，New strain in the country".replaceAll("\\pP|\\pS|\\pZ","").toLowerCase();
        String fourLevel = "Unexpected change or increase,Unusual host".replaceAll("\\pP|\\pS|\\pZ","").toLowerCase();
        String oneLevel = "Recurrence".replaceAll("\\pP|\\pS|\\pZ","").toLowerCase();
        if (oneLevel.contains(reason)) return 1;
        if (fourLevel.contains(reason)) return 4;
        if (sevenLevel.contains(reason)) return 7;
        if (tenLevel.contains(reason)) return 10;
        return 0;
    }
}
