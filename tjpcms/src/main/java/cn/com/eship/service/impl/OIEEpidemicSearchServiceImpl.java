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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OIEEpidemicSearchServiceImpl implements OIEEpidemicSearchService{

    @Autowired
    private OIEEpidemicDao epidemicDao;

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
    public String makeEpidemicEventListJson(String pageNo, String epidemicName, String region,String epidemicClass, String startDate, String endDate) throws Exception {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> parameMap = new HashMap<String, Object>();
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
        List<Map<String, Object>> epidemicAppearList = epidemicDao.findEpidemicEventList(parameMap);
        if (epidemicAppearList != null && epidemicAppearList.size() > 0) {
            jsonMap.put("epidemicEventList", epidemicAppearList);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+epidemicDao.findTotalRecord(parameMap));
            jsonMap.put("epidemicEventListCount", epidemicDao.findTotalRecord(parameMap));
        }
        org.codehaus.jackson.map.ObjectMapper objectMapper = new org.codehaus.jackson.map.ObjectMapper();
        objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return objectMapper.writeValueAsString(jsonMap);
    }
}
