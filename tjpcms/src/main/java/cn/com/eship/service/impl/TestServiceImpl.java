package cn.com.eship.service.impl;

import cn.com.eship.dao.OIEEpidemicDao;
import cn.com.eship.service.TestService;
import cn.com.eship.utils.CommenUtils;
import cn.com.eship.utils.TimeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestServiceImpl implements TestService {
    private final OIEEpidemicDao epidemicDao;

    @Autowired
    public TestServiceImpl(OIEEpidemicDao epidemicDao) {
        this.epidemicDao = epidemicDao;
    }

    @Override
    public Map<String, List> findMapListData(String mapDataInterval, String startDate, String endDate) throws Exception {

        List<Map<String, Object>> epidemicAppearList = getEpidemicAppearList(mapDataInterval,startDate,endDate);
        Map<String, List> jsonMap = new HashMap<>();
        if (epidemicAppearList != null && epidemicAppearList.size() > 0) {
            Map<String, Map<String, List<String>>> regionEventMap = new HashMap<>();
            Map<String,Set<String>> rorMap = new HashMap<>();
            for (Map<String, Object> report : epidemicAppearList) {
                if (report.get("regionNameEng") != null && !"".equals(report.get("regionNameEng"))) {
                    String region = (String) report.get("regionNameEng");
                    String reason = StringUtils.isNotEmpty((String) report.get("reason")) ? (String) report.get("reason") : "";
                    String eName = (report.get("epidemicNameCn") != null && !"".equals(report.get("epidemicNameCn"))) ? (String) report.get("epidemicNameCn") : (String) report.get("disease");
                    String event = " * " + report.get("date") + " " + report.get("regionNameCn") + " 发生 "
                            + report.get("diseaseClass") + " " + eName + " " + report.get("outbreaks") + "次";
                    int level = transformReason(reason);
                    String levelStr = Integer.toString(level);
                    if (regionEventMap.get(region) != null) {
                        Map<String, List<String>> reasonMap = regionEventMap.get(region);
                        if (reasonMap.get(levelStr) != null) {
                            List<String> eventList = reasonMap.get(levelStr);
                            eventList.add(event);
                        } else {
                            List<String> eventList = new ArrayList<>();
                            eventList.add(event);
                            reasonMap.put(levelStr, eventList);
                        }
                    } else {
                        Map<String, List<String>> reasonMap = new HashMap<>();
                        List<String> eventList = new ArrayList<>();
                        eventList.add(event);
                        reasonMap.put(levelStr, eventList);
                        regionEventMap.put(region, reasonMap);
                    }

                    if(rorMap.get(levelStr)!=null){
                        Set<String> set= rorMap.get(levelStr);
                        set.add(region);
                    }else {
                        Set<String> set = new HashSet<>();
                        set.add(region);
                        rorMap.put(levelStr,set);
                    }
                }
            }

            for(Map.Entry<String,Set<String>> entry : rorMap.entrySet()){
                List<Object> jsonList = new ArrayList<>();
                String reason = entry.getKey();
                Set<String> regions= entry.getValue();
                for (String region:regions){
                    List<String> data = new ArrayList<>();
                    Map<String,Object> map = new HashMap<>();
                    Map<String, List<String>> rm = regionEventMap.get(region);
                    if (rm!=null){
                        data = rm.get(reason);
                        map.put("name",region);
                        map.put("value",reason);
                        map.put("data",data);
                        jsonList.add(map);
                    }else {
                        continue;
                    }
                }
                jsonMap.put(reason,jsonList);
            }
            return jsonMap;
        }else {
            return jsonMap;
        }
    }

    private int transformReason(String reason) {
        if (CommenUtils.compareString("First occurrence", reason)) {
            return 512;
        }
        if (CommenUtils.compareString("First occurrence in the country", reason)) {
            return 256;
        }
        if (CommenUtils.compareString("Emerging disease", reason)) {
            return 128;
        }
        if (CommenUtils.compareString("Change in epidemiology", reason)) {
            return 64;
        }
        if (CommenUtils.compareString("New host", reason)) {
            return 32;
        }
        if (CommenUtils.compareString("New pathogen", reason)) {
            return 16;
        }
        if (CommenUtils.compareString("New strain", reason)) {
            return 8;
        }
        if (CommenUtils.compareString("New strain in the country", reason)) {
            return 4;
        }
        if (CommenUtils.compareString("Recurrence", reason)) {
            return 2;
        }
        if (CommenUtils.compareString("Unexpected change or increase", reason)) {
            return 1;
        }
        if (CommenUtils.compareString("Unusual host", reason)) {
            return 0;
        }
        return 0;
    }

    private List<Map<String, Object>> getEpidemicAppearList(String mapDataInterval, String startDate, String endDate)throws Exception{
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtils.isNotBlank(mapDataInterval)) {
            paramMap.put("interval", mapDataInterval);
        }
        if (StringUtils.isNotBlank(startDate)) {
            paramMap.put("startDate", TimeUtils.convertToDateString(startDate));
        }
        if (StringUtils.isNotBlank(endDate)) {
            paramMap.put("endDate", TimeUtils.convertToDateString(endDate));
        }
        return epidemicDao.findEpidemicEventList(paramMap);
    }

}
