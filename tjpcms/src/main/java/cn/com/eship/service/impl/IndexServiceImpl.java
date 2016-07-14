package cn.com.eship.service.impl;

import cn.com.eship.dao.EpidemicAppearDao;
import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.service.IndexService;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/7/14.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private EpidemicAppearDao epidemicAppearDao;

    /**
     * 全球疫情TOP10
     *
     * @return
     */
    @Override
    public String makeEpidemicTopTenJson(EpidemicAppear epidemicAppear) throws Exception {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("epidemicNames", new ArrayList<Object>());
        jsonMap.put("epidemicTop", new ArrayList<Object>());
        List<Object> epidemicToptenList = epidemicAppearDao.findEpidemicTopten(setEpidemicAppearDaoParamer(epidemicAppear));
        if (epidemicToptenList != null && epidemicToptenList.size() > 0) {
            List<String> epidemicNamesList = new ArrayList<>();
            List<Map<String, Object>> epidemicTopList = new ArrayList<Map<String, Object>>();
            for (Object epidemic : epidemicToptenList) {
                Map<String, Object> map = new HashMap<String, Object>();
                epidemicNamesList.add((String) (((Object[]) epidemic)[0]));
                map.put("value", ((Object[]) epidemic)[1]);
                map.put("name", ((Object[]) epidemic)[0]);
                epidemicTopList.add(map);
            }
            jsonMap.put("epidemicNames", epidemicNamesList);
            jsonMap.put("epidemicTop", epidemicTopList);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jsonMap);
    }

    @Override
    public String makeEpidemicLocalTopTenJson(EpidemicAppear epidemicAppear) throws Exception {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("epidemicLocalTopTenNames", new ArrayList<String>());
        jsonMap.put("epidemicLocalTopTenValues", new ArrayList<Long>());
        List<Object> epidemicLocalTopTenList = epidemicAppearDao.findEpidemicTopten(setEpidemicAppearDaoParamer(epidemicAppear));
        if (epidemicLocalTopTenList != null && epidemicLocalTopTenList.size() > 0) {
            List<Long> epidemicLocalTopTenValues = new ArrayList<Long>();
            List<String> epidemicLocalTopTenNames = new ArrayList<String>();
            for (Object epidemicLocalTopTen : epidemicLocalTopTenList) {
                epidemicLocalTopTenNames.add((String) ((Object[]) epidemicLocalTopTen)[0]);
                epidemicLocalTopTenValues.add((Long) ((Object[]) epidemicLocalTopTen)[1]);
            }
            jsonMap.put("epidemicLocalTopTenNames",epidemicLocalTopTenNames);
            jsonMap.put("epidemicLocalTopTenValues",epidemicLocalTopTenValues);
        }
        return new ObjectMapper().writeValueAsString(jsonMap);
    }


    private Map<String, String> setEpidemicAppearDaoParamer(EpidemicAppear epidemicAppear) {
        Map<String, String> map = new HashMap<String, String>();
        if (epidemicAppear != null) {
            //设置地域名称
            if (epidemicAppear.region != null && StringUtils.isNotBlank(epidemicAppear.region.getRegionCn())) {
                map.put("epidemicAppear.region.regionCn", epidemicAppear.region.getRegionCn());
            }
        }
        return map;
    }
}
