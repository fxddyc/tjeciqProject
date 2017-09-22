package cn.com.eship.service.impl;

import cn.com.eship.dao.OieEpidemiologicalEventEntityDao;
import cn.com.eship.model.OieEpidemiologicalEventEntity;
import cn.com.eship.service.OIEEpidemicPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 2017/9/19.
 */
@Service
public class OIEEpidemicPathServiceImpl implements OIEEpidemicPathService {
    @Autowired
    private OieEpidemiologicalEventEntityDao oieEpidemiologicalEventEntityDao;

    @Override
    public Map<String, Object> findOieEpidemiologicalEventEntityList(OieEpidemiologicalEventEntity epidemiologicalEventEntity) throws Exception {
        List<Object[]> list = oieEpidemiologicalEventEntityDao.findOieEpidemiologicalEventCountPath(epidemiologicalEventEntity);
        List<List<Object>> pathResult = new ArrayList<>();
        List<List<Object>> pathAndValueResult = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        //上一次循环国家名字
        Object pCountryName = null;
        for (Object[] objectArray : list) {
            if (pCountryName != null) {
                Map<Object, Object> pathStartLocationMap = new HashMap<>();
                pathStartLocationMap.put("name", pCountryName);
                Map<Object, Object> pathEndLocationMap = new HashMap<>();
                pathEndLocationMap.put("name", objectArray[0]);
                Map<Object, Object> pathEndLocationValueMap = new HashMap<>();
                pathEndLocationValueMap.put("name", objectArray[0]);
                pathEndLocationValueMap.put("value", objectArray[1]);

                List<Object> pathResultListItem = new ArrayList<>();
                List<Object> pathResultValueListItem = new ArrayList<>();
                pathResultListItem.add(pathStartLocationMap);
                pathResultListItem.add(pathEndLocationMap);
                pathResultValueListItem.add(pathStartLocationMap);
                pathResultValueListItem.add(pathEndLocationValueMap);

                pathResult.add(pathResultListItem);
                pathAndValueResult.add(pathResultValueListItem);
                pCountryName = objectArray[0];
            }else {
                pCountryName = objectArray[0];
            }
        }
        resultMap.put("pathList", pathResult);
        resultMap.put("pathAndValueResult", pathAndValueResult);
        return resultMap;
    }
}
