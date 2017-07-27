package cn.com.eship.service.impl;

import cn.com.eship.dao.OIEEpidemicDao;
import cn.com.eship.service.OIEEpidemicSearchService;
import cn.com.eship.utils.CommenUtils;
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
            int rid = epidemicDao.findRegionIdByCondition(region);
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
    public List<Map<String, Object>> findAlertListData(String alertDataInterval) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        List<Map<String, Object>> jsonList = new ArrayList<>();
        if (StringUtils.isNotBlank(alertDataInterval)) {
            paramMap.put("interval", alertDataInterval);
        }
        List<Map<String, Object>> epidemicAppearList = epidemicDao.findEpidemicEventList(paramMap);
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
        return jsonList;
    }

    public List<Map<String, Object>> findMapListData(String mapDataInterval,String startDate,String endDate) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        List<Map<String, Object>> jsonList = new ArrayList<>();
        if (StringUtils.isNotBlank(mapDataInterval)) {
            paramMap.put("interval", mapDataInterval);
        }
        if (StringUtils.isNotBlank(startDate)) {
            paramMap.put("startDate", TimeUtils.convertToDateString(startDate));
        }
        if (StringUtils.isNotBlank(endDate)) {
            paramMap.put("endDate", TimeUtils.convertToDateString(endDate));
        }
        List<Map<String, Object>> epidemicAppearList = epidemicDao.findEpidemicEventList(paramMap);
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
                    int level = transformEpidemicEventReason(reason);
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

    private int transformEpidemicEventReason(String reason){
        if(CommenUtils.compareString("First occurrence",reason)){
            return 10;
        }
        if(CommenUtils.compareString("First occurrence in the country",reason)){
            return 9;
        }
        if(CommenUtils.compareString("Emerging disease",reason)){
            return 8;
        }
        if(CommenUtils.compareString("Change in epidemiology",reason)){
            return 7;
        }
        if(CommenUtils.compareString("New host",reason)){
            return 6;
        }
        if(CommenUtils.compareString("New pathogen",reason)){
            return 5;
        }
        if(CommenUtils.compareString("New strain",reason)){
            return 4;
        }
        if(CommenUtils.compareString("New strain in the country",reason)){
            return 3;
        }
        if(CommenUtils.compareString("Recurrence",reason)){
            return 2;
        }
        if(CommenUtils.compareString("Unexpected change or increase",reason)){
            return 1;
        }
        if(CommenUtils.compareString("Unusual host",reason)){
            return 0;
        }
        return 0;
    }
}
